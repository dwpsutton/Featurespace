package console;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import entity.Film;
import entity.Genre;
import session.FilmDAO;
import session.FilmDAO.Type;
import session.FilmException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {

	public static void main(String[] args) {

		Film film = new Film("There's something about Mary",
				2, LocalDate.of(1997, 1, 27), Genre.COMEDY);
		Path path = Paths.get("object.bin");
		//Serialize object
		try (ObjectOutputStream oos = new ObjectOutputStream(
				Files.newOutputStream(path))) {
			oos.writeObject(film);
		} catch (IOException e) {
			System.out.println(e);
		}

		//Deserialize object
		if (!Files.exists(path))
			return;
		try (ObjectInputStream ois = new ObjectInputStream(
				Files.newInputStream(path))) {
			Film f = (Film) ois.readObject();
			System.out.println(f);
		} catch (Exception e) {
			System.out.println(e);
		}



		// TODO Auto-generated method stub
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu1");
//		EntityManager em =  factory.createEntityManager();
//		String jpql = "select f from Film f order by f.title";
//		TypedQuery<Film> query = em.createQuery(jpql, Film.class);
//		Collection<Film> films = query.getResultList();
//		for (Film f : films) {
//			System.out.println(f.getTitle());
//		}
//		em.close();

 
	}

}
