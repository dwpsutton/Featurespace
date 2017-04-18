package messaging;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by User on 15/12/2016.
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

        //supplier - function that creates a new result container
        Supplier<ArrayList<Message>> supplier = () -> new ArrayList<Message>();

        //accumulator - function for incorporating an additional element into a result
        BiConsumer<ArrayList<Message>, Message> accumulator = (x, y) -> {
            x.add(y);
        };

        //combiner - function for combining two values, compatible with the accumulator
        BiConsumer<ArrayList<Message>, ArrayList<Message>> combiner =
                (x, y) -> {
                    x.addAll(y);
                };


        List<String> filteredMessages = messages.stream().filter(m -> m.getText().length() < 50).map(m -> m.getText()).collect(Collectors.toList());

        filteredMessages.forEach(System.out::println);
    }
}



