package jaxrsClient;



import java.io.Serializable;
import java.time.LocalDate;

/**
 * Film that can be deserialized from JSON
 */
public class Film implements Serializable {

    private Long id;
    private Genre genre;
    private java.util.Date released;//GSON CAN'T DESERIALIZE LocalDate
    private int stock;
    private String title;

    public Film() {
    }

    public Film(String title, int stock, LocalDate released, Genre genre) {
        setTitle(title);
        setStock(stock);
        setReleased(released);
        setGenre(genre);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Film ? ((Film)obj).title.equals(title) : false;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }


    public Genre getGenre() {
        return genre;
    }

    public Long getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public String getTitle() {
        return title;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setId(Long id) {
        this.id = id;

    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleased() {
        return new java.sql.Date(released.getTime()).toLocalDate();
    }

    public void setReleased(LocalDate released) {
        this.released = java.sql.Date.valueOf(released);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", genre=" + genre +
                ", released=" + String.format("%tY",released) +
                ", stock=" + stock +
                ", title='" + title + '\'' +
                '}';
    }
}
