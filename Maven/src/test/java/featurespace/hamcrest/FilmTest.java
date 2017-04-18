package featurespace.hamcrest;

import entity.Film;
import entity.Genre;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class FilmTest {

	LocalDate sb;

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
		assertThat(id,nullValue());
		assertEquals("The Pink Panther", title);
		assertThat(title,is(equalTo("The Pink Panther")));

		assertEquals(5, stock);
		assertEquals(LocalDate.of(1964, 1, 20), released);
		assertEquals(Genre.COMEDY, genre);
	}

	/**
	 * throw an exception, either from the constructor or the setStock method if
	 * a negative value is passed in
	 */
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowExceptionIfStockNegative() {
		new Film("The Pink Panther", -1, LocalDate.of(1964, 1, 20), Genre.COMEDY);
	}

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
	@Test
	public void filmsWithSameTitleShouldBeEqual() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);

		// act (execute methods under test) and assert (verify test results)
		assertTrue(film1.equals(film2));
	}

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
	@Test
	public void filmsWithSameTitleShouldHaveEqualHashcodes() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);

		// act (execute methods under test) and assert (verify test results)
		assertTrue(film1.hashCode() == film2.hashCode());
	}

}
