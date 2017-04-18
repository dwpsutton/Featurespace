package bank;

import java.util.Random;

/**
 * Created by User on 25/01/2017.
 */
public class Account {
    String name;
    String address;
    long number;
    double balance;
    double overdraftLimit;

    public Account(String name, String address, long number, double balance, double overdraftLimit) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.balance = balance;
        this.overdraftLimit = overdraftLimit;
    }

    public static long generateAccountNumber(){
        Random random = new Random();
        return random.nextInt(10000);
    }

    public void increaseBalance(double amount){

    }
    public boolean decreaseBalance(double amount){
        return false;
    }
    public double calculateMonthlyFee(){
        return 0;
    }
}


