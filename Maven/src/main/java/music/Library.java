package music;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by User on 20/01/2017.
 */
public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Collection<Track> tracks = new HashSet<>();
    private Collection<Playlist> playlists = new HashSet<>();

    public Library() {
    }

    public Library(String username) {
        setName(username);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
       return  ((Library)obj).name.equals(name);
    }

    public Playlist createPlaylist(String name) {
        //check if playlist with this name exists
        boolean existingPlaylist = playlists.stream().anyMatch(playlist -> playlist.getName().equals(name));
        if (existingPlaylist)
            throw new IllegalArgumentException("A playlist with this name already exists");
        Playlist playlist = new Playlist(name);
        boolean added = playlists.add(playlist);
        if (added)
            return playlist;
        throw new MusicLibraryException("playlist was not created");
    }

    
    public boolean deletePlaylist(String name) {
        return playlists.removeIf(playlist -> playlist.getName().equals(name));
    }

    
    public Playlist selectPlaylistByName(String name) {
        Optional<Playlist> optional = playlists.stream().filter(playlist -> playlist.getName().equals(name)).findFirst();
        if (optional.isPresent())
            return optional.get();
        return null;
    }

    
    public void addTrack(Track track) {
        track.setId(nextId());
        tracks.add(track);
    }

    private int nextId() {
        int id = tracks.isEmpty() ? 1 : tracks.stream().mapToInt(Track::getId).max().getAsInt()+1;
        return id;
    }

    
    public boolean removeTrack(int trackId) {
        return tracks.removeIf(track -> track.getId() == trackId);
    }

    
    public void clearTracks() {
        tracks.clear();
    }

    
    public Collection<Track> selectAllTracks() {
        return tracks;
    }

    
    public Collection<Track> selectTracksByGenre(Genre genre) {
        return tracks.stream().filter(track -> track.getGenre() == genre).collect(Collectors.toList());
    }

    
    public Collection<Track> selectTracksByName(String name) {
        return tracks.stream().filter(track -> track.getName().equals(name)).collect(Collectors.toList());
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

}
