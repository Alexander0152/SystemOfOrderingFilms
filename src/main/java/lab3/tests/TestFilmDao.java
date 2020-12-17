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

public class TestFilmDao {
    private String connectionFileName = "src/main/resources/connectionInfoTest.txt";

    private FilmService filmService = new FilmService();
    private static Film testFilm = new Film();

    @BeforeClass
    public static void setUpClass() throws Exception {
        testFilm.setName("Avatar");
        testFilm.setPrice(10);

        List<String> reviewsList = new ArrayList<>();
        String review = "Very good film!";
        reviewsList.add(review);

        testFilm.setReviews(reviewsList);
    }

    @Test
    public void testGetAllFilms() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        List<Film> filmList = filmService.getAllFilms(connectionFileName);
        int amountOfFilms = filmList.size();

        Assert.assertEquals(3, amountOfFilms);
    }

    @Test
    public void testGetFilmByName() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String name = "Avatar";
        Film film = filmService.getFilmByName(connectionFileName, name);
        String result = film.getName();

        Assert.assertEquals("Avatar", result);
    }

    @Test
    public void changeFilmInfo() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String name = "Avatar";
        Film film = filmService.getFilmByName(connectionFileName, name);
        String result = film.getName();

        Assert.assertEquals("Avatar", result);
    }

    @Test
    public void addFilmReview() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String userName = "John";
        String filmName = "Harry Potter";
        String review = "I like this film";

        filmService.addFilmReview(connectionFileName, userName, filmName, review);
        Film film = filmService.getFilmByName(connectionFileName,"Harry Potter");

        Boolean result = false;
        for(String filmReview: film.getReviews()){
            if(filmReview.compareTo(review) == 0){
                result = true;
            }
        }
        Assert.assertEquals(true, result);
    }
}

