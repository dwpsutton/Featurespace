package examples.featurespace.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

public class Serialize {
    public static void main(String args[]) {
        try {
            //Serialize to JSON
            Date date = new GregorianCalendar(1997, 4, 27).getTime();
            Film film = new Film("There's something about Mary", 2, date, Genre.COMEDY);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(film);
            System.out.printf("Serialized object: %s%n",jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

