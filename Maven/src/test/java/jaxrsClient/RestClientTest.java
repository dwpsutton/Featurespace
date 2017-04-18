package jaxrsClient;


import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 30/03/2017.
 */
public class RestClientTest {

    private RestClient restClient = new RestClient();

    @Test
    public void selectByTitleReturnsFilms(){
        Collection<Film> films = restClient.selectByTitle("x");
        assertEquals(7,films.size());
    }

    @Test
    //@Ignore
    public void insertAddsFilm(){
        jaxrsClient.Film film = new jaxrsClient.Film("There's something about Mary",
                2, LocalDate.of(1997, 1, 27), Genre.COMEDY);
        int count = restClient.selectByTitle("There's something about Mary").size();
        restClient.insert(film);

        assertEquals(count+1,restClient.selectByTitle("There's something about Mary").size());
    }
}
