package music;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * Created by User on 21/01/2017.
 */
public class SerializerImpl implements Serializer {
    private Path path = Paths.get("object.bin");

    @Override
    public Collection<Library> load() {
        if (!Files.exists(path))
            return null;
        try (ObjectInputStream ois = new ObjectInputStream(
                Files.newInputStream(path))) {
            return (Collection<Library>) ois.readObject();
        } catch (Exception e) {
            throw new MusicLibraryException(e.getMessage());
        }
    }

    @Override
    public void save(Collection<Library> libraries) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(path))) {
            oos.writeObject(libraries);
        } catch (IOException e) {
            throw new MusicLibraryException(e.getMessage());
        }

    }

    @Override
    public boolean delete() {
        try {
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new MusicLibraryException(e.getMessage());
        }
    }
}
