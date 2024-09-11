package dao;

import entity.FacturaProducto;
import entity.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaProductoDao implements CrudDAO<FacturaProducto> {


    private Connection conn;

    public FacturaProductoDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(FacturaProducto fp) {
        String query = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, fp.getIdFactura()); // idFactura
            ps.setInt(2, fp.getIdProducto()); // idProducto
            ps.setInt(3, fp.getCantidad()); // cantidad
            ps.executeUpdate();
            System.out.println("Factura_Producto insertado exitosamente.");
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


    public FacturaProducto getById(int pk1, int pk2) {
        String query = "SELECT fp.cantidad" +
                "FROM factura_producto fp " +
                "WHERE fp.idFactura = ? AND fp.idProducto = ?";
        FacturaProducto factura_productoById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk1); // Establecer el parámetro en la consulta SQL
            ps.setInt(2, pk2); // Establecer el parámetro en la consulta SQL
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                int valor= rs.getInt("valor");

                // Crear una nueva instancia de Factura_Producto con los datos recuperados de la consulta
                factura_productoById = new FacturaProducto(pk1, pk2, valor);
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

        return factura_productoById;
    }

    @Override
    public void delete(int id) {

    }

    public void delete(int pk1, int pk2) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'delete'");
        String query = "DELETE FROM factura_producto " +
                "WHERE idFactura = ? AND idProducto = ?";
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk1);
            ps.setInt(2, pk2);

            if ( ps.executeUpdate() > 0) {
                System.out.println("Registro eliminado con éxito.");
            } else {
                System.out.println("No se encontró ningún registro con el ID proporcionado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void update(FacturaProducto fp) {

        String query = "UPDATE factura_producto SET cantidad = ? " +
                " WHERE idFactura = ? AND idProducto = ? ";

        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement(query);
            ps.setInt(1, fp.getCantidad());
            ps.setInt(2, fp.getIdFactura());
            ps.setInt(3, fp.getIdProducto());
            if (ps.executeUpdate() > 0 ){
                System.out.println("Factura Producto actualizado exitosamente.");
            }else{
                System.out.println("Factura Producto no encontrado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FacturaProducto getById(int id) {
        return null;
    }


    public List<FacturaProducto> selectList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectList'");
    }


}
