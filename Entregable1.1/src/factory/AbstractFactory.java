package factory;


import dao.ClienteDao;
import dao.FacturaDao;
import dao.FacturaProductoDao;
import dao.ProductoDao;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public abstract ClienteDao getClienteDao();
    public abstract FacturaDao getFacturaDao();
    public abstract ProductoDao getProductoDao();
    public abstract FacturaProductoDao getFactura_ProductoDao();


    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return MySqlDaoFactory.getInstance();
            }
            case DERBY_JDBC: return null;
            default: return null;
        }
    }

}
