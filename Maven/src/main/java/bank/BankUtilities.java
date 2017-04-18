package bank;

import java.util.Random;

/**
 * Created by User on 25/01/2017.
 */
public class BankUtilities {
    public static long generateAccountNumber(){
        Random random = new Random();
        return random.nextInt(10000);
    }
}
