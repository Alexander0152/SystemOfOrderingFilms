package lab3.dataAccessLayer;

import lab3.businessLayer.Customer;
import lab3.businessLayer.Film;
import lab3.businessLayer.PersonStatus;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomerDao {
    private String connectionFileName;

    public CustomerDao(String connectionFileName) {
        this.connectionFileName = connectionFileName;
    }

    public List<Customer> getAllCustomers() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        String host = "";
        String login = "";
        String password = "";
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(connectionFileName);
            property.load(fis);

            host = property.getProperty("db.host");
            login = property.getProperty("db.login");
            password = property.getProperty("db.password");

        } catch (IOException e) {
            System.err.println("Error: File doesn't exist!");
        }

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection(host, login, password);

        Statement st = con.createStatement();
        String sql = String.format("SELECT * FROM users WHERE status = '%s';", PersonStatus.CUSTOMER);
        ResultSet rs = st.executeQuery(sql);

        List<Customer> customerList = new ArrayList<>();
        while (rs.next()) {
            Customer customer = new Customer();

            customer.setName(rs.getString("name"));
            customer.setStatus(PersonStatus.CUSTOMER);
            customer.setDiscount(rs.getBoolean("discount"));

            customerList.add(customer);
        }
        con.close();

        return customerList;
    }

    public Customer getCustomerByName(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        String host = "";
        String login = "";
        String password = "";
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(connectionFileName);
            property.load(fis);

            host = property.getProperty("db.host");
            login = property.getProperty("db.login");
            password = property.getProperty("db.password");

        } catch (IOException e) {
            System.err.println("Error: File doesn't exist!");
        }

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection(host, login, password);

        Statement st = con.createStatement();
        String sql = String.format("SELECT * FROM users WHERE name = '%s';", name);
        ResultSet rs = st.executeQuery(sql);

        Customer customer = new Customer();
        while (rs.next()) {

            customer.setName(rs.getString("name"));
            customer.setStatus(PersonStatus.CUSTOMER);
            customer.setDiscount(rs.getBoolean("discount"));
        }
        con.close();

        return customer;
    }

    public void changeUserStatus(String userName, PersonStatus newStatus) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        String host = "";
        String login = "";
        String password = "";
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(connectionFileName);
            property.load(fis);

            host = property.getProperty("db.host");
            login = property.getProperty("db.login");
            password = property.getProperty("db.password");

        } catch (IOException e) {
            System.err.println("Error: File doesn't exist!");
        }

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection(host, login, password);

        String sql = String.format("UPDATE users SET status = '%s' WHERE name = '%s';", newStatus, userName);

        PreparedStatement preparedStatement = con.prepareStatement(sql);

        preparedStatement.executeUpdate();
        con.close();
    }

    public void changeUserDiscount(String userName, boolean discount) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        String host = "";
        String login = "";
        String password = "";
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(connectionFileName);
            property.load(fis);

            host = property.getProperty("db.host");
            login = property.getProperty("db.login");
            password = property.getProperty("db.password");

        } catch (IOException e) {
            System.err.println("Error: File doesn't exist!");
        }

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection(host, login, password);

        String sql = String.format("UPDATE users SET discount = %b WHERE name = '%s';", discount, userName);

        PreparedStatement preparedStatement = con.prepareStatement(sql);

        preparedStatement.executeUpdate();
        con.close();
    }
}
