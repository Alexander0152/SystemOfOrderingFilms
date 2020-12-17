package lab3.serviceLater;

import lab3.businessLayer.Film;
import lab3.dataAccessLayer.FilmDao;

import java.sql.SQLException;
import java.util.List;

public class FilmService {

    public List<Film> getAllFilms(String filePath) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        FilmDao dao = new FilmDao(filePath);
        return dao.getAllFilms();
    }

    public Film getFilmByName(String filePath, String name) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        FilmDao dao = new FilmDao(filePath);
        return dao.getFilmByName(name);
    }

    public void changeFilmInfo(String filePath, Film film) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        FilmDao dao = new FilmDao(filePath);
        dao.changeFilmInfo(film);
    }

    public void addFilm(String filePath, Film newFilm) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        FilmDao dao = new FilmDao(filePath);
        dao.addFilm(newFilm);
    }

    public void removeFilm(String filePath, String filmName) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        FilmDao dao = new FilmDao(filePath);
        dao.removeFilm(filmName);
    }

    public void addFilmReview(String filePath, String userName, String filmName, String review) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        FilmDao dao = new FilmDao(filePath);
        dao.addFilmReview(userName, filmName, review);
    }
}
