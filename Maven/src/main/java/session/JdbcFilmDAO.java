package session;

import entity.Film;
import entity.Genre;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcFilmDAO implements FilmDAO {

    private String url = "jdbc:mysql://localhost:3306/filmstore";
    private String username = "root";
    private String password = "carpond";

    @Override
    public Long insert(Film film) {
        String sql = "insert into Film (title, stock, released, genre, version) values (?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, film.getTitle());
            statement.setInt(2, film.getStock());
            LocalDate released = film.getReleased();
            //setDate takes a java.sql.Date argument
            statement.setDate(3, Date.valueOf(released));
            statement.setString(4, film.getGenre().toString());
            statement.setInt(5, 0);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Long generatedId = rs.getLong(1);
                film.setId(generatedId);
                return generatedId;
            }
            throw new FilmException("Primary key was not generated");
        } catch (Exception ex) {
            throw new FilmException(ex.getMessage());
        }
    }

    @Override
    public boolean update(Film film) {
        String sql = "update film set title = ?, released = ?, genre = ?, stock = ?, version = version + 1 where id = ? and version = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            statement.setString(1, film.getTitle());
            statement.setDate(2, Date.valueOf(film.getReleased()));
            statement.setString(3, film.getGenre().toString());
            statement.setInt(4, film.getStock());
            statement.setLong(5, film.getId());
            statement.setInt(6, film.getVersion());
            return statement.executeUpdate() == 1;
        } catch (Exception ex) {
            throw new FilmException(ex.getMessage());
        }
    }

    @Override
    public boolean delete(Long filmId) {
        String sql = "delete from film where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);//default
            connection.setAutoCommit(false);
            statement.setLong(1, filmId);
            boolean updated = statement.executeUpdate() == 1;
            connection.commit();
            return updated;
        } catch (Exception ex) {
            throw new FilmException(ex.getMessage());
        }
    }

    private Film toFilm(ResultSet rs) throws SQLException {
        Film film = new Film();
        film.setId(rs.getLong("id"));
        film.setStock(rs.getInt("stock"));
        //convert a java.sql.Date to a LocalDate
        film.setReleased(rs.getDate("released").toLocalDate());
        film.setTitle(rs.getString("title"));
        film.setGenre(Genre.valueOf(rs.getString("genre")));
        return film;
    }

    @Override
    public Collection<Film> selectAll() {
        Collection<Film> films = new ArrayList<>();
        String sql = "select * from film";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Film film = toFilm(rs);
                films.add(film);
            }
        } catch (Exception ex) {
            throw new FilmException(ex.getMessage());
        }
        return films;
    }

    @Override
    public Collection<Film> selectByTitle(String search) {
        Collection<Film> films = new ArrayList<>();
        String sql = "select * from film where lcase (title) like ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + search.toLowerCase() + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Film film = toFilm(rs);
                films.add(film);
            }
        } catch (Exception ex) {
            throw new FilmException(ex.getMessage());
        }
        return films;
    }

    @Override
    public Film selectById(Long id) {
        String sql = "select * from film where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return toFilm(rs);
            }
        } catch (Exception ex) {
            throw new FilmException(ex.getMessage());
        }
        return null;
    }
}
