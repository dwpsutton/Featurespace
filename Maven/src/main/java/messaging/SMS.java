package messaging;

/**
 * Created by User on 15/12/2016.
 */
public class SMS extends Message {
    public SMS(String userId, String text) {
        super(userId, text);
    }

    @Override
    public void setText(String text) {

        super.setText(text);
    }
}
