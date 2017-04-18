package examples.featurespace.apache;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by User on 16/04/2017.
 */
public class Csv {
    public static void main(String[] args) throws IOException {
        Reader in = new FileReader("D:/Google Drive/Courses/Java/2017/Maven/src/main/java/examples/featurespace/apache/book1.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader("Last Name", "First Name").parse(in);
        for (CSVRecord record : records) {
            String lastName = record.get("Last Name");
            String firstName = record.get("First Name");
            System.out.printf("%s %s%n",firstName,lastName);
        }
    }
}
