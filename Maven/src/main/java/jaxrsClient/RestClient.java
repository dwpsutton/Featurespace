package jaxrsClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import session.FilmDAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * GSON can't parse LocalDate, so change Film class to use java.util.Date
 */
public class RestClient {

	//private static String urlString = "http://localhost:8080/IntelliJ-1.0/rest/films/";
	private static String urlString = "http://sdineen.uk/api/films/";
	public static void main(String[] args) {
		RestClient client = new RestClient();

		Film film = new Film("There's something about Mary", 2, LocalDate.of(1997, 1, 27), Genre.COMEDY);
//		long responseCode = client.insert(film);
//		System.out.println(responseCode);

			Collection<Film>films = client.selectByTitle("mary");
			for (Film f : films) {
                System.out.println(f);
            }
	}

	public long insert(Film film) {
		try {
			//Gson gson = new Gson();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(film);
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			OutputStream os = connection.getOutputStream();
			os.write(jsonString.getBytes());
			os.close();
			connection.disconnect();
			return connection.getResponseCode();
		} catch (IOException e) {
			return 500;
		}
	}

	//add transient keyword to released field in Film class
	//alternatively, add a new Film class with a Date instead of a LocalDate field
	public Collection<Film> selectByTitle(String search) {
		try {
			Thread.sleep(2000);
			URL url = new URL(urlString+search.replace(" ","%20"));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			String jsonString = reader.readLine();
			System.out.println(jsonString);
			connection.disconnect();
			reader.close();
			Gson gson = new GsonBuilder()
					.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
					.create();
			Film[] films = gson.fromJson(jsonString, Film[].class);
			return Arrays.asList(films);
		} catch (IOException | InterruptedException e) {
			return null;
		}
	}

}
