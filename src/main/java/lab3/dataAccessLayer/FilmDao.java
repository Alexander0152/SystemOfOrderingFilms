package lab3.dataAccessLayer;

import lab3.businessLayer.Film;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FilmDao {
    private String connectionFileName;

    public FilmDao(String connectionFileName) {
        this.connectionFileName = connectionFileName;
    }

    public List<Film> getAllFilms() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

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

        List<Film> filmList = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection(host, login, password);

        Statement st = con.createStatement();
        String sql = ("SELECT * FROM films");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Film film = new Film();

            film.setName(rs.getString("name"));
            film.setPrice(rs.getDouble("price"));

            filmList.add(film);
        }

        for (Film film : filmList) {
            String filmName = film.getName();

            String reviews = String.format("SELECT * FROM reviews WHERE film_name = '%s';", filmName);
            ResultSet rsReviews = st.executeQuery(reviews);

            List<String> reviewList = new ArrayList<>();
            while (rsReviews.next()) {
                String review = rsReviews.getString("review");
                reviewList.add(review);
            }

            film.setReviews(reviewList);
        }
        con.close();

        return filmList;
    }

    public Film getFilmByName(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

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
        String sql = String.format("SELECT * FROM films WHERE name = '%s';", name);
        ResultSet rs = st.executeQuery(sql);

        Film film = new Film();
        while (rs.next()) {

            film.setName(name);
            film.setPrice(rs.getDouble("price"));
        }

        String reviews = String.format("SELECT * FROM reviews WHERE film_name = '%s';", name);
        ResultSet rsReviews = st.executeQuery(reviews);

        List<String> reviewList = new ArrayList<>();
        while (rsReviews.next()) {
            String review = rsReviews.getString("review");
            reviewList.add(review);
        }

        film.setReviews(reviewList);

        con.close();

        return film;
    }

    public void changeFilmInfo(Film film) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

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

        String delete = String.format("UPDATE films SET price = '%d' WHERE name = '%d';", film.getPrice(), film.getName());

        PreparedStatement preparedStatement = con.prepareStatement(delete);
        preparedStatement.executeUpdate();

        con.close();
    }

    public void addFilm(Film film) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
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
        String getLastFilmId = ("SELECT * FROM films ORDER BY id DESC LIMIT 1;");
        ResultSet rs = st.executeQuery(getLastFilmId);

        int lastFilmId = 1;
        if (rs.next()) {
            lastFilmId = rs.getInt("id");
            lastFilmId++;
        }

        String sql = "INSERT INTO films (id, name, price) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = con.prepareStatement(sql);

        preparedStatement.setInt(1, lastFilmId);
        preparedStatement.setString(2, film.getName());
        preparedStatement.setDouble(3, film.getPrice());
        preparedStatement.executeUpdate();

        con.close();
    }

    public void removeFilm(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
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

        String delete = String.format("DELETE * f, r FROM films f JOIN reviews r ON f.name = r.name " +
                "WHERE f.name = '%d';", name);

        PreparedStatement preparedStatement = con.prepareStatement(delete);
        preparedStatement.executeUpdate();

        con.close();
    }

    public void addFilmReview(String userName, String filmName, String review) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
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
        String getLastReviewId = ("SELECT * FROM reviews ORDER BY id DESC LIMIT 1;");
        ResultSet rs = st.executeQuery(getLastReviewId);

        int lastReviewId = 1;
        if (rs.next()) {
            lastReviewId = rs.getInt("id");
            lastReviewId++;
        }

        String sql = "INSERT INTO reviews (id, film_name, customer_name, review) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = con.prepareStatement(sql);

        preparedStatement.setInt(1, lastReviewId);
        preparedStatement.setString(2, filmName);
        preparedStatement.setString(3, userName);
        preparedStatement.setString(4, review);
        preparedStatement.executeUpdate();

        con.close();
    }
}
