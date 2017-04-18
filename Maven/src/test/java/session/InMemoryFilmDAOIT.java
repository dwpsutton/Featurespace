package session;

import entity.Film;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

//@Ignore
public class InMemoryFilmDAOIT {

	@Test
	public void test() {
		FilmDAO sut = new InMemoryFilmDAO();
		int intialSize = sut.selectAll().size();
		sut.insert(new Film());
		assertEquals(intialSize + 1,sut.selectAll().size());
	}
}
