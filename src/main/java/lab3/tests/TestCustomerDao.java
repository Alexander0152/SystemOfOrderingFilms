package lab3.tests;

import lab3.businessLayer.Customer;
import lab3.businessLayer.Film;
import lab3.businessLayer.PersonStatus;
import lab3.serviceLater.CustomerService;
import lab3.serviceLater.FilmService;
import org.junit.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestCustomerDao {
    private String connectionFileName = "src/main/resources/connectionInfoTest.txt";

    private CustomerService customerService = new CustomerService();
    private static Film testFilm = new Film();

    @BeforeClass
    public static void setUpClass() throws Exception {
    }


    @Test
    public void changeUserStatus() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String userName = "John";
        PersonStatus newStatus = PersonStatus.CUSTOMER;

        customerService.changeUserStatus(connectionFileName, userName, newStatus);
        Customer customer = customerService.getCustomerByName(connectionFileName, userName);

        PersonStatus result = customer.getStatus();

        Assert.assertEquals(newStatus, result);
    }

    @Test
    public void changeUserDiscount() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String userName = "John";
        boolean discount = true;

        customerService.changeUserDiscount(connectionFileName, userName, discount);
        Customer customer = customerService.getCustomerByName(connectionFileName, userName);

        boolean result = customer.getDiscount();

        Assert.assertEquals(discount, result);
    }
}

