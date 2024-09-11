package dao;

import entity.Factura;
import entity.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FacturaDao implements CrudDAO<Factura> {

    private Connection conn;

    public FacturaDao(Connection conn) {
        this.conn = conn;
    }
    public void insert(Factura factura) {
        String query = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, factura.getIdCliente()); // idCliente
            ps.setInt(2, factura.getIdFactura()); //  IdFactura
            ps.executeUpdate();
            System.out.println("Factura insertada exitosamente.");
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



    public Factura getById(int pk) {
        String query = "SELECT f.idCliente" +
                "FROM factura f" +
                "WHERE f.idFactura = ?";
        Factura facturaById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk); // Establecer el parámetro en la consulta SQL
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                int idCliente = rs.getInt("idCliente");

                // Crear una nueva instancia de Factura con los datos recuperados de la consulta
                facturaById = new Factura(pk, idCliente);
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

        return facturaById;
    }

    @Override
    public Factura getById(int id1, int id2) {
        return null;
    }


    public void delete(int id) {

        String query = "DELETE FROM factura WHERE idFactura = ?";
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


    public void update(Factura f) {

        String query = "UPDATE factura SET idCliente = ? WHERE idFactura = ?";

        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement(query);
            ps.setInt(1, f.getIdCliente());
            ps.setInt(2, f.getIdFactura());

            if (ps.executeUpdate() > 0 ){
                System.out.println("Factura actualizada exitosamente.");
            }else{
                System.out.println("Factura no encontrada.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public List<Factura> selectList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectList'");
    }


}
