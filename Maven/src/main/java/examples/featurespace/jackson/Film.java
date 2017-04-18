package examples.featurespace.jackson;



import java.io.Serializable;
import java.util.Date;

public class Film implements Serializable {

    private Long id;
    private Genre genre;
    private java.util.Date released;
    private int stock;
    private String title;

    public Film() {
    }

    public Film(String title, int stock, Date released, Genre genre) {
        setTitle(title);
        setStock(stock);
        this.released=released;
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

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
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
