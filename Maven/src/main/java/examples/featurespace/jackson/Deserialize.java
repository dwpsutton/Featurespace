package examples.featurespace.jackson;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class Deserialize {
    public static void main(String args[]) {
        try {
            URL url = new URL("http://sdineen.uk/api/films/");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            //Film [] films = mapper.readValue(jsonString, Film[].class);
            Film [] films = mapper.readValue(url, Film[].class);
            for (Film film:films) {
                System.out.println(film.getTitle());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

