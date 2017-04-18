package examples.featurespace.easymock;

import entity.Film;

public class InMemoryFilmDAO  {
    private Serializer serializer;

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    public Film[] selectAll() {
          return  serializer.deserialize();
    }

}
