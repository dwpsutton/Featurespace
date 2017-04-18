package session;

import entity.Film;
import entity.Genre;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/*
use filmstore;
set foreign_key_checks = 0;
truncate table film;
truncate table ordertable_film;
truncate table ordertable;
 */

//@Ignore
public class JdbcFilmDAOIT {

    @Before
    public void init(){
        String url = "jdbc:mysql://localhost:3306/filmstore";
        String username = "root";
        String password = "carpond";

        String[] commands = {
                "set foreign_key_checks = 0;",
                "create table if not exists Film (id bigint not null auto_increment, genre varchar(255), released date, stock integer not null, title varchar(255), version integer not null, primary key (id));",
                "truncate table film;"
                ,"truncate table ordertable_film;"
                ,"truncate table ordertable;"
        };
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            for (String command : commands) {
                statement.addBatch(command);
            }
            statement.executeBatch();
        } catch (Exception ex) {
            throw new FilmException(ex.getMessage());
        }
    }


    @Test
    public void test() {
        Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
        Film film2 = new Film("The Godfather", 2, LocalDate.of(1972, 4, 17), Genre.CRIME);
        Film film3 = new Film("Avatar", 2, LocalDate.of(2009, 7, 2), Genre.SCIENCE_FICTION);
        FilmDAO dao = new JdbcFilmDAO();
        long id1 = dao.insert(film1);
        long id2 = dao.insert(film2);
        long id3 = dao.insert(film3);

        assertEquals(film1, dao.selectById(id1));
        assertEquals(film2, dao.selectById(id2));
        assertEquals(3, dao.selectAll().size());
        assertEquals(2, dao.selectByTitle("at").size());

        film1.setTitle("The Return of the Pink Panther");
        dao.update(film1);

        assertEquals("The Return of the Pink Panther", dao.selectById(id1).getTitle());

        dao.delete(id1);
        dao.delete(id2);
        dao.delete(id3);
        assertEquals(0,dao.selectAll().size());
    }
}
