package factory;

import dao.ClienteDao;
import dao.FacturaDao;
import dao.ProductoDao;
import dao.FacturaProductoDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDaoFactory extends AbstractFactory {

    private static MySqlDaoFactory instance = null;
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String uri = "jdbc:mysql://localhost:3306/entregable1";
    public static Connection conn;

    private MySqlDaoFactory() {
    }

    public static synchronized MySqlDaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }

    public static Connection createConnection() {
        if (conn != null) {
            return conn;
        }
        String driver = DRIVER;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                  NoSuchMethodException | SecurityException | ClassNotFoundException |
                 InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "root");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   @Override
    public ClienteDao getClienteDao() {
        return new ClienteDao(createConnection());
    }

    @Override
    public FacturaDao getFacturaDao() {
        return new FacturaDao(createConnection());
    }

    @Override
    public ProductoDao getProductoDao() {

        return new ProductoDao(createConnection());
    }

    @Override
    public FacturaProductoDao getFactura_ProductoDao() {

        return new FacturaProductoDao(createConnection());
    }
}
