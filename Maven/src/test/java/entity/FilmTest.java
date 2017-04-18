package entity;

import entity.Film;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class FilmTest {

	/**
	 * Generate the Film class in the src/main/java directory, using Eclipse.
	 * Add a constructor, fields, get and set methods, so that the assertions
	 * pass
	 */
	@Ignore
	@Test
	public void constructorShouldInitialiseFields() {
		// arrange and act
		Film film = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);

		Long id = film.getId();
		String title = film.getTitle();
		int stock = film.getStock();
		LocalDate released = film.getReleased();
		Genre genre = film.getGenre();

		// assert
		assertNull(id);
		assertEquals("The Pink Panther", title);
		assertEquals(5, stock);
		assertEquals(LocalDate.of(1964, 1, 20), released);
		assertEquals(Genre.COMEDY, genre);
	}

	/**
	 * throw an exception, either from the constructor or the setStock method if
	 * a negative value is passed in
	 */
	@Ignore
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowExceptionIfStockNegative() {
		new Film("The Pink Panther", -1, LocalDate.of(1964, 1, 20), Genre.COMEDY);
	}

	//@Ignore
	@Test
	public void filmShouldWorkWithList() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		List<Film> films = new ArrayList<>();
		//act
		films.add(film1);
		films.remove(film2);
		//assert
		assertEquals(0,films.size());
	}

	/**
	 * Override the equals method in the Film class
	 */
	@Ignore
	@Test
	public void filmsWithSameTitleShouldBeEqual() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);

		// act (execute methods under test) and assert (verify test results)
		assertTrue(film1.equals(film2));
	}

	//@Ignore
	@Test
	public void filmShouldWorkWithSet() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Set<Film> films = new HashSet<>();
		//act
		films.add(film1);
		films.remove(film2);
		//assert
		assertEquals(0,films.size());
	}

	/**
	 * Override the equals and hashCode methods in the Film class
	 */
	@Ignore
	@Test
	public void filmsWithSameTitleShouldHaveEqualHashcodes() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);

		// act (execute methods under test) and assert (verify test results)
		assertTrue(film1.hashCode() == film2.hashCode());
	}



}
