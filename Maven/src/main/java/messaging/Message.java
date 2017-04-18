package messaging;

/**
 * Created by User on 15/12/2016.
 */
public class Message {

    protected String text;
    protected String userId;

    public Message(String userId, String text) {
        this.text = text;
        this.userId=userId;
    }

    public Message() {

    }

    @Override
    public boolean equals(Object obj) {
        return ((Message)obj).text.equals(text) && obj.getClass().equals(getClass());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
