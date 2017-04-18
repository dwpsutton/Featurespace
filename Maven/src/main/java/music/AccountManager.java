package music;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * Created by User on 21/01/2017.
 */
public class AccountManager {
    private Serializer serializer = new SerializerImpl();
    private Collection<Library> libraries = new HashSet<>();

    public Library createLibrary(String username) {
        boolean duplicateLibrary = libraries.stream().anyMatch(library -> library.getName().equals(username));
        if (duplicateLibrary)
            throw new MusicLibraryException("There is an existing library with this username");
        Library library = new Library(username);
        boolean added = libraries.add(library);
        return library;
    }

    public Library selectLibrary(String username) {
        Optional<Library> optional = libraries.stream().filter(library -> library.getName().equals(username)).findFirst();
        if (!optional.isPresent())
            throw new MusicLibraryException("There is no library with this username");
        return optional.get();
    }

    public boolean removeLibrary(String username) {
        return libraries.removeIf(library -> library.getName().equals(username));
    }

    public void loadData(){
        Collection<Library> libraries = serializer.load();
        if (libraries!=null)
            this.libraries = libraries;
    }

    public void saveData(){
        serializer.save(libraries);
    }

    public boolean deleteData(){
        return serializer.delete();
    }
}
