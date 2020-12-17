package lab3.serviceLater;

import lab3.businessLayer.Customer;
import lab3.businessLayer.Film;
import lab3.businessLayer.PersonStatus;
import lab3.dataAccessLayer.CustomerDao;
import lab3.dataAccessLayer.FilmDao;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    public List<Customer> getAllCustomers(String filePath) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        CustomerDao dao = new CustomerDao(filePath);
        return dao.getAllCustomers();
    }

    public Customer getCustomerByName(String filePath, String name) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        CustomerDao dao = new CustomerDao(filePath);
        return dao.getCustomerByName(name);
    }

    public void changeUserStatus(String filePath, String userName, PersonStatus newStatus) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        CustomerDao dao = new CustomerDao(filePath);
        dao.changeUserStatus(userName, newStatus);
    }

    public void changeUserDiscount(String filePath, String userName, boolean discount) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        CustomerDao dao = new CustomerDao(filePath);
        dao.changeUserDiscount(userName, discount);
    }
}
