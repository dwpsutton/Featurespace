package messaging;

/**
 * Created by User on 11/12/2016.
 */
public class Tweet extends Message{

    public Tweet(String userId, String text) {
        super(userId, text);
    }


    public Tweet() {
        super();
    }
}
