package examples.featurespace.localisation;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NSample {

    static public void main(String[] args) {
        Locale.setDefault(new Locale("de", "fr"));
        ResourceBundle messages = ResourceBundle.getBundle("examples.featurespace.localisation.MessagesBundle");
        System.out.println(messages.getString("greetings"));
        System.out.println(messages.getString("inquiry"));
        System.out.println(messages.getString("farewell"));
    }
}