package session;

import entity.Film;
import entity.Genre;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

//@Ignore
public class InMemoryFilmDAOTest {

	// arrange
	private Map<Long, Film> films = new HashMap<>();
	private FilmDAO sut = new InMemoryFilmDAO(films);

	@Test
	public void insertShouldReturnGeneratedId() {

		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Godfather", 2, LocalDate.of(1972, 4, 17), Genre.CRIME);

		// act
		Long id1 = sut.insert(film1);
		Long id2 = sut.insert(film2);

		// assert
		assertTrue(id1 instanceof Long);
		assertTrue(id2 > id1);
	}

	@Test
	public void updateShouldModifyFilmInCollection() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		films.put(1L, film1);

		Film film2 = new Film();
		film2.setId(1L);
		film2.setTitle("The Black Panther");

		// act
		boolean updated = sut.update(film2);

		// assert
		assertTrue(updated);
		assertEquals("The Black Panther", films.get(1L).getTitle());
	}

	@Test
	public void deleteShouldRemoveFilm() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		films.put(1L, film1);

		// act
		boolean deleted = sut.delete(1L);

		// assert
		assertTrue(deleted);
		assertTrue(films.isEmpty());
	}

	@Test
	public void selectByIdShouldReturnMatchingFilm() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Godfather", 2, LocalDate.of(1972, 4, 17), Genre.CRIME);
		films.put(1L, film1);
		films.put(2L, film2);

		// act
		Film retrievedFilm = sut.selectById(1L);

		// assert
		assertTrue(retrievedFilm.equals(film1));
	}

	@Test
	public void selectAllShouldReturnCollection() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Godfather", 2, LocalDate.of(1972, 4, 17), Genre.CRIME);
		films.put(1L, film1);
		films.put(2L, film2);

		// act
		Collection<Film> films = sut.selectAll();

		// assert
		assertTrue(films.size() == 2);
	}

	@Test
	public void selectByTitleShouldGetMatchingFilms() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Godfather", 2, LocalDate.of(1972, 4, 17), Genre.CRIME);
		Film film3 = new Film("Avatar", 2, LocalDate.of(2009, 7, 2), Genre.SCIENCE_FICTION);
		films.put(1L, film1);
		films.put(2L, film2);
		films.put(3L, film3);

		// act
		Collection<Film> films = sut.selectByTitle("at");

		// assert
		assertTrue(films.size() == 2);
	}

}
