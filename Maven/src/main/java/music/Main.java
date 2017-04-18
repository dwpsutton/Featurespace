package music;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//http://www.telegraph.co.uk/music/what-to-listen-to/the-100-greatest-songs-of-all-time/
        AccountManager accountManager = new AccountManager();
        boolean deleted = accountManager.deleteData();
        System.out.println(deleted);

        Library library = accountManager.createLibrary("user1");

        Track track1 = new Track("Life On Mars?","David Bowie", 1971, Genre.ROCK, "Gloriously strange sci-fi anthem. A stirring, yearning melody combines with vivid, poetic imagery to accomplish a trick very particular to the art of the song: to be at once completely impenetrable and yet resonant with personal meaning. You want to raise your voice and sing along, yet Bowie’s abstract cut-up lyrics force you to invest the song with something of yourself just to make sense of the experience, and then carries you away to a place resonant with intense, individual emotion. The magic and mystery of music and lyrics. It is something to behold.");
        Track track2 = new Track("Let It Be","The Beatles", 1970, Genre.POP, "Anthem of consolation, inspired by Paul McCartney’s dream of a visit from his own mother, Mary." );
        Track track3 = new Track("Tangled Up in Blue", "Bob Dylan", 1975, Genre.POP, "Dylan’s dazzling narrative of star-crossed love and divorce was shaped by abstract art into a tableaux you can come at from any angle and discover something new.");
        Track track4 = new Track("Unchained Melody","The Righteous Brothers", 1965, Genre.POP, "A thousand karaoke versions cannot destroy this epic, vocally demanding ballad." );
        Track track5 = new Track("Lola", "The Kinks", 1970, Genre.POP, "Witty, compassionate, inspirational song of confused, transgender love, boasting dazzling rhymes, exultant melody and explosive emotion.");
        Track track6 = new Track("Redemption Song","Bob Marley", 1979,Genre.POP,"Simple, stirring, strangely wistful anthem of freedom, both personal and political.");
        Track track7 = new Track("Saint James Infirmary", "Louis Armstrong", 1928, Genre.BLUES, "A singer ponders lost love and prepares for his own funeral on a sad, defiant contemplation of mortality that has been passed down through the mists of time and recorded by artists as diverse as Billie Holiday and the White Stripes.");
        Track track8 = new Track("Somewhere Over the Rainbow", "Judy Garland", 1939, Genre.POP, "The rising melody and wistful lyric perfectly encapsulate yearning for a different, better life." );
        Track track9 = new Track("I’ve Got You Under My Skin","Frank Sinatra", 1956, Genre.POP, "Has there ever been a more streamlined, sensual evocation of addictive desire?" );
        Track track10 = new Track("Everybody Hurts", "REM", 1993, Genre.POP, "A secular hymn of compassion constructed around a simple picked rock-and-roll guitar motif." );

        //add tracks to library
        library.addTrack(track1);
        library.addTrack(track2);
        library.addTrack(track3);
        library.addTrack(track4);
        library.addTrack(track5);
        library.addTrack(track6);
        library.addTrack(track7);
        library.addTrack(track8);
        library.addTrack(track9);
        library.addTrack(track10);

        //add tracks to playlist
        Playlist playlist = library.createPlaylist("playlist1");
        Collection<Track> tracks = library.selectTracksByGenre(Genre.BLUES);
        tracks.forEach(track -> playlist.addTrack(track));

        //save library
        accountManager.saveData();

        //retrieve library
        accountManager = new AccountManager();
        accountManager.loadData();
        library = accountManager.selectLibrary("user1");
        Collection<Track> trackCollection = library.selectAllTracks();

        //get an ordered list of tracks in library
        List<Track> trackList = new ArrayList<>(trackCollection);
        Comparator<Track> comparator = (t1, t2) -> t1.getId() > t2.getId() ? 1 : -1;
        trackList.sort(comparator);
        for (Track track:trackList) {
            System.out.println(track);
        }
        System.out.println("**************************************************");

        //display tracks in playlist1
        Collection<Track> tracksInPlaylist = library.selectPlaylistByName("playlist1").selectAllTracks();
        for (Track track:tracksInPlaylist) {
            System.out.println(track);
        }


        boolean removed = accountManager.removeLibrary("user1");
        assert (removed);

    }
}
