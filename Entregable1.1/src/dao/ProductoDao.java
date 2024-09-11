package dao;

import dto.ProductoRecaudacionDTO;
import entity.Cliente;
import entity.FacturaProducto;
import entity.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao implements CrudDAO<Producto>{


    private Connection conn;

    public ProductoDao(Connection conn) {
        this.conn = conn;
    }
    public void insert(Producto p) {
        String query = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, p.getIdProducto()); // idCliente
            ps.setString(2, p.getNombre()); // nombre
            ps.setFloat(3, p.getValor()); // email
            ps.executeUpdate();
            System.out.println("Producto insertado exitosamente.");
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

    public Producto getById(int pk) {
        String query = "SELECT p.nombre, p.valor," +
                "FROM Producto p " +
                "WHERE p.idProducto = ?";
        Producto productoById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk); // Establecer el parámetro en la consulta SQL
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                String nombre = rs.getString("nombre");
                float valor = rs.getFloat("valor");

                // Crear una nueva instancia de Cliente con los datos recuperados de la consulta
                productoById = new Producto(pk, nombre, valor);
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

        return productoById;
    }

    @Override
    public Producto getById(int id1, int id2) {
        return null;
    }

    public ProductoRecaudacionDTO findProductoRecaudacionDTO() { //Retorno una lista con todas las FacturasProducto
        ProductoRecaudacionDTO prDTO = null;


        String query = "SELECT p.idProducto AS idProducto, p.nombre AS nombre, p.valor AS valor, " +
                "pc.totalCantidad AS totalCantidad, " +
                "(p.valor * pc.totalCantidad) AS Recaudacion " +  // Agregar espacio después de 'AS Recaudacion'
                "FROM producto p " +
                "JOIN (SELECT idProducto, SUM(cantidad) AS totalCantidad " +
                "FROM factura_producto " +
                "GROUP BY idProducto) AS pc " +
                "ON p.idProducto = pc.idProducto " +
                "ORDER BY Recaudacion DESC " +
                "LIMIT 1";


        PreparedStatement ps=null;
        ResultSet rs;

        try{
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                int valor = rs.getInt("valor");
                int totalCantidad = rs.getInt("totalCantidad");
                int maxRecaudacion = rs.getInt("Recaudacion");


                // Crear una nueva instancia de FacturaProducto con los datos recuperados
                prDTO = new ProductoRecaudacionDTO(idProducto,
                        nombre, valor, totalCantidad, maxRecaudacion);

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
        return prDTO;
    }

    public void delete(int id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'delete'");
        String query = "DELETE FROM producto WHERE idProducto = ?";
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

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


    public void update(Producto p) {

        String query = "UPDATE producto SET nombre = ?, valor = ? WHERE idProducto = ?";

        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement(query);
            ps.setString(1, p.getNombre());
            ps.setFloat(2, p.getValor());
            ps.setInt(3, p.getIdProducto());
            if (ps.executeUpdate() > 0 ){
                System.out.println("Producto actualizado exitosamente.");
            }else{
                System.out.println("Producto no encontrado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public List<Producto> selectList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectList'");
    }


}
