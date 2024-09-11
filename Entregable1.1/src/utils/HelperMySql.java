package utils;

import entity.Cliente;
import entity.Factura;
import entity.FacturaProducto;
import entity.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class HelperMySql {
    private Connection conn = null;

    public HelperMySql() {//Constructor
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/entregable1";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "root");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void dropTables() throws SQLException {
        String dropFactura_Producto = "DROP TABLE IF EXISTS Factura_Producto";
        this.conn.prepareStatement(dropFactura_Producto).execute();
        this.conn.commit();

        String dropFactura = "DROP TABLE IF EXISTS Factura";
        this.conn.prepareStatement(dropFactura).execute();
        this.conn.commit();

        String dropProducto = "DROP TABLE IF EXISTS Producto";
        this.conn.prepareStatement(dropProducto).execute();
        this.conn.commit();

        String dropCliente = "DROP TABLE IF EXISTS Cliente";
        this.conn.prepareStatement(dropCliente).execute();
        this.conn.commit();

    }

    public void createTables() throws SQLException {
        String tableCliente = "CREATE TABLE IF NOT EXISTS cliente(" +
                "idCliente INT," +
                "nombre VARCHAR(500)," +
                "email VARCHAR(150)," +
                "PRIMARY KEY(idCliente))";
        conn.prepareStatement(tableCliente).execute();
        conn.commit();

        String tableFactura = "CREATE TABLE IF NOT EXISTS factura(" +
                "idFactura INT," +
                "idCliente INT," +
                "PRIMARY KEY(idFactura)," +
                "FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente) ON DELETE CASCADE)";
        conn.prepareStatement(tableFactura).execute();
        conn.commit();

        String tableProducto = "CREATE TABLE IF NOT EXISTS producto(" +
                "idProducto INT," +
                "nombre VARCHAR(45)," +
                "valor FLOAT," +
                "PRIMARY KEY(idProducto))";
        conn.prepareStatement(tableProducto).execute();
        conn.commit();

        String tableFacturaProducto = "CREATE TABLE IF NOT EXISTS factura_producto (" +
                "idFactura INT, " +
                "idProducto INT, " +
                "cantidad INT, " +
                "PRIMARY KEY (idFactura, idProducto), " +
                "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura) ON DELETE CASCADE, " +
                "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto) ON DELETE CASCADE)";
        conn.prepareStatement(tableFacturaProducto).execute();
        conn.commit();

}

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\csv\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};  // Puedes configurar tu encabezado personalizado aquí si es necesario
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB() throws Exception {
        try {

            for (CSVRecord row : getData("clientes.csv")) {
                if (row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                    String idString = row.get(0);
                    String nombreString = row.get(1);
                    String emailString = row.get(2);

                    if (!idString.isEmpty() && !nombreString.isEmpty() && !emailString.isEmpty()) {
                        try {
                            int id = Integer.parseInt(idString);

                            Cliente cliente = new Cliente(id, nombreString, emailString);
                            insertCliente(cliente, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de cliente: " + e.getMessage());
                        }
                    }
                }
            }

            System.out.println("Clientes insertados");

        for (CSVRecord row : getData("facturas.csv")) {
            if (row.size() >= 2) { // Verificar que hay al menos 4 campos en el CSVRecord
                String idFacturaString = row.get(0);
                String idClienteString = row.get(1);

                if (!idFacturaString.isEmpty() && !idClienteString.isEmpty()) {
                    try {
                        int idFactura = Integer.parseInt(idFacturaString);
                        int idCliente = Integer.parseInt(idClienteString);
                        Factura factura = new Factura(idFactura, idCliente);
                        insertFactura(factura, conn);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de facturas: " + e.getMessage());
                    }
                }
            }
        }

        System.out.println("Facturas insertadas");

        for (CSVRecord row : getData("productos.csv")) {
            if (row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                String idProductoString = row.get(0);
                String nombreString = row.get(1);
                String valorString = row.get(2);

                if (!idProductoString.isEmpty() && !nombreString.isEmpty()  && !valorString.isEmpty()) {
                    try {
                        int idProducto = Integer.parseInt(idProductoString);
                        float valor = Float.parseFloat(valorString);

                        Producto producto = new Producto(idProducto, nombreString, valor);
                        insertProducto(producto, conn);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de Producto: " + e.getMessage());
                    }
                }
            }
        }

        System.out.println("Productos insertados");

        for (CSVRecord row : getData("facturas-productos.csv")) {
            if (row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                String idFacturaString = row.get(0);
                String idProductoString = row.get(1);
                String cantidadString = row.get(2);

                if (!idFacturaString.isEmpty() && !idProductoString.isEmpty()  && !cantidadString.isEmpty()) {
                    try {
                        int idFactura = Integer.parseInt(idFacturaString);
                        int idProducto = Integer.parseInt(idProductoString);
                        int cantidad = Integer.parseInt(cantidadString);

                        FacturaProducto factura_producto = new FacturaProducto(idFactura, idProducto, cantidad);
                        insertFacturaProducto(factura_producto, conn);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de FacturaProducto: " + e.getMessage());
                    }
                }
            }
        }

        System.out.println("FacturaProducto insertados");




    } catch (SQLException e) {
        e.printStackTrace();
    }

    }





    private int insertCliente (Cliente cliente, Connection conn) throws Exception{
        String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1,cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3,cliente.getEmail());

            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertFactura(Factura factura, Connection conn) throws Exception {

        String insert = "INSERT INTO Factura (idFactura, IdCliente) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1,factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());

           if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertProducto(Producto producto, Connection conn) throws Exception {

        String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1,producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3,producto.getValor());

            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertFacturaProducto(FacturaProducto facturaProducto, Connection conn) throws Exception {

        String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1,facturaProducto.getIdFactura());
            ps.setInt(2, facturaProducto.getIdProducto());
            ps.setInt(3,facturaProducto.getCantidad());

            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }



    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null){
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
