import dao.ClienteDao;
import dao.FacturaProductoDao;
import dao.ProductoDao;
import dto.ClienteDTO;
import dto.ProductoRecaudacionDTO;
import factory.AbstractFactory;
import factory.MySqlDaoFactory;
import utils.HelperMySql;

import java.util.List;


public class Main {

    public static void main(String[] args) {

    try {
       /* HelperMySql dbMySql = new HelperMySql();
        dbMySql.dropTables();
        dbMySql.createTables();
        dbMySql.populateDB();
        dbMySql.closeConnection();
*/
        AbstractFactory factoryMySql = AbstractFactory.getDAOFactory(1);

        ProductoDao pDAO = factoryMySql.getProductoDao();
        ProductoRecaudacionDTO prDTO = null;
        prDTO = pDAO.findProductoRecaudacionDTO();
        System.out.println("//////////////////////////////////////////");
        System.out.println("3) Producto Mayor Recaudación: "+ prDTO.toString());
        System.out.println("//////////////////////////////////////////");

        System.out.println("4) Lista de CLientes por Facturacion");
        ClienteDao cDAO = factoryMySql.getClienteDao();
        for (ClienteDTO c : cDAO.getClientesByFacturacion()) {
            System.out.println(c.toString());
        }

    }catch (Exception e) {
            System.out.println("No se pudo crear la Base de Datos");
    }

    }
}
