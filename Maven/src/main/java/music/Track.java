package music;


import java.io.Serializable;
import java.time.LocalDate;

//http://www.telegraph.co.uk/music/what-to-listen-to/the-100-greatest-songs-of-all-time/
public class Track implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String artist;
    private String name;
    private LocalDate Date;
    private Genre genre;
    private String description;

    public Track(String name, String artist, int year, Genre genre, String description) {
        setName(name);
        setArtist(artist);
        setDate(LocalDate.of(year,1,1));
        setGenre(genre);
        setDescription(description);
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", artist='" + artist + '\'' +
                ", name='" + name + '\'' +
                ", Date=" + Date +
                ", genre=" + genre +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Track)obj).id==id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
