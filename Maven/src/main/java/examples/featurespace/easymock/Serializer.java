package examples.featurespace.easymock;

import entity.Film;

import java.util.Map;

public interface Serializer {
	//Serialized objects must implement the java.io.Serializable interface

	void serialize(Film[] films);

	Film[] deserialize();
}
