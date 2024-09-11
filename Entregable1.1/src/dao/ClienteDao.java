package dao;

import dto.ClienteDTO;
import dto.ProductoRecaudacionDTO;
import entity.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements CrudDAO<Cliente> {

    private Connection conn;

    public ClienteDao(Connection conn) {
        this.conn = conn;
    }
    public void insert(Cliente c) {
        String query = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, c.getIdCliente()); // idCliente
            ps.setString(2, c.getNombre()); // nombre
            ps.setString(3, c.getEmail()); // email
            ps.executeUpdate();
            System.out.println("Cliente insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

    }


    public void delete(int id) {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'delete'");
        String query = "DELETE FROM cliente WHERE idCliente = ?";
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            // Opcional: Maneja el resultado de la operación, por ejemplo, verificando el número de filas afectadas.
            if ( ps.executeUpdate() > 0) {
                System.out.println("Registro eliminado con éxito.");
            } else {
                System.out.println("No se encontró ningún registro con el ID proporcionado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id1, int id2) {

    }


    public Cliente getById(int pk) {
        String query = "SELECT c.nombre, c.email," +
                "FROM cliente c " +
                "WHERE c.idCliente = ?";
        Cliente clienteById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk); // Establecer el parámetro en la consulta SQL
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                // Crear una nueva instancia de Cliente con los datos recuperados de la consulta
                clienteById = new Cliente(pk, nombre, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clienteById;
    }

    @Override
    public Cliente getById(int id1, int id2) {
        return null;
    }


    public List<ClienteDTO> getClientesByFacturacion() { //Retorno una lista con la facturacion de todos los clietes en forma DESC

        List<ClienteDTO> listaClientesDTO = new ArrayList<ClienteDTO>();


        String query = "SELECT c.*, listaClientesFacturas.totalFacturas " +
                " FROM cliente c " +
                " JOIN ( " +
                " SELECT f.idCliente AS idCliente, " +
                " SUM(facturas.importeFactura) AS totalFacturas " +
                " FROM factura f " +
                " JOIN ( " +
                "    SELECT fp.idFactura AS idFactura, " +
                "           SUM(fp.cantidad * p.valor) AS importeFactura " +
                "    FROM factura_producto fp " +
                "    JOIN producto p USING (idProducto) " +
                "    GROUP BY fp.idFactura ) AS facturas " +
                " USING (idFactura) " +
                " GROUP BY f.idCliente) " +
                " AS listaClientesFacturas " +
                " ON c.idCliente = listaClientesFacturas.idCliente " +
                " ORDER BY totalFacturas DESC ";

        PreparedStatement ps=null;
        ResultSet rs;

        try{
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                float totalFacturas = rs.getFloat("totalFacturas");
                // Crear una nueva instancia de FacturaProducto con los datos recuperados
                listaClientesDTO.add(new ClienteDTO(idCliente, nombre , email , totalFacturas));
            }
        } catch (SQLException e) {
            // Puedes lanzar una excepción personalizada o registrar el error
            e.printStackTrace();
        } finally{
            try{
                if(ps!=null){
                    ps.close();
                }
                conn.commit();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        // No es necesario commit para una consulta SELECT
        return listaClientesDTO;
    }



    public void update(Cliente c) {

        String query = "UPDATE cliente SET nombre = ?, email = ? WHERE idCliente = ?";

        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement(query);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getEmail());
            ps.setInt(3, c.getIdCliente());
                if (ps.executeUpdate() > 0 ){
                    System.out.println("Cliente actualizado exitosamente.");
                }else{
                    System.out.println("Cliente no encontrado.");
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Cliente> selectList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectList'");
    }



}
