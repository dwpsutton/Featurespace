package music;

import java.util.Collection;

/**
 * Created by User on 21/01/2017.
 */
public interface Serializer {
    Collection<Library> load();

    void save(Collection<Library> libraries);

    boolean delete();
}
