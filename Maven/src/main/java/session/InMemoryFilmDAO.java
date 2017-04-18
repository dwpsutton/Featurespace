package session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalLong;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


import entity.Film;

public class InMemoryFilmDAO implements FilmDAO {

    //	private Long currentId = 1L;
    private AtomicLong currentId = new AtomicLong(1L);
    private Serializer serializer;
    private Map<Long, Film> films;

    public InMemoryFilmDAO() {
    }

    public InMemoryFilmDAO(Map<Long, Film> films) {
        this.films = films;
    }

    //for unit test
    public InMemoryFilmDAO(Map<Long, Film> films, Serializer serializer) {
        this.serializer = serializer;
        this.films = films;
    }

    //for unit test (interactions test)
//	public InMemoryFilmDAO(Serializer serializer) {
//		this.serializer=serializer;
//	}

    @Override
    public boolean delete(Long filmId) {
        boolean deleted = films.remove(filmId) == null ? false : true;
        //serializer.serialize(films);
        return deleted;
    }

    @Override
    public Long insert(Film film) {
        //Long id = currentId.incrementAndGet();
        //Long id = currentId++;
        Long id = null;
        synchronized (this) {
            OptionalLong optional = films.values().stream().mapToLong(f -> f.getId()).max();
            id = optional.isPresent() ? optional.getAsLong() + 1 : 1;
            film.setId(id);
            films.putIfAbsent(id, film);
        }
        if (serializer != null)
            serializer.serialize(films);
        return id;
    }

    @Override
    public Collection<Film> selectAll() {
        if (serializer != null)
            films = serializer.deserialize();
        return films.values();
    }

    @Override
    public Film selectById(Long id) {
        return films.get(id);
    }

    @Override
    public Collection<Film> selectByTitle(String search) {

        Collection<Film> filmCollection = films.values();
        return filmCollection.stream().filter(f -> f.getTitle().contains(search)).collect(Collectors.toList());
    }

    @Override
    public boolean update(Film film) {
        boolean updated = films.replace(film.getId(), film) == null ? false : true;
        return updated;
    }
}
