package console;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jaxrsClient.Film;
import jaxrsClient.Genre;
import messaging.Message;
import messaging.SMS;
import messaging.Tweet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by User on 15/04/2017.
 */
public class Class1 {
    public static void main(String[] args) {
        Tweet t1 = new Tweet("user1", "Uneasy lies the head that wears a crown");
        Tweet t2 = new Tweet("user1", "The fool doth think he is wise, but the wise man knows himself to be a fool");
        SMS t3 = new SMS("user2", "What hath God wrought");
        SMS t4 = new SMS("user2", "They make a desert and call it peace");
        SMS t5 = new SMS("user2", "But at my back I always hear time's winged chariot hurrying near");

        HashSet<Message> messages = new HashSet<>();
        messages.add(t1);
        messages.add(t2);
        messages.add(t3);
        messages.add(t4);
        messages.add(t5);

        Predicate<Message> messagePredicate = m -> m.getText().length() < 50;
        List<Message> filteredMessages = messages.
                stream().
                filter(messagePredicate).
                collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
        ;

        for (Message message : filteredMessages) {
            System.out.println(message.getText());
        }
    }
}
