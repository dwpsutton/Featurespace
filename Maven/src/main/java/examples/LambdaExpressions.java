package examples;

import messaging.Message;
import messaging.SMS;
import messaging.Tweet;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdaExpressions {

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

		List<Message> filteredMessages = null;

		for (Message message : filteredMessages) {
			System.out.println(message.getText());
		}

	}

}
