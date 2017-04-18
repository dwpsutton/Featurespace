package bank;

/**
 * Created by User on 25/01/2017.
 */
public class PersonalAccount extends Account {
    public PersonalAccount(String name, String address, long number, double balance, double overdraftLimit) {
        super(name, address, number, balance, overdraftLimit);
    }

    @Override
    public double calculateMonthlyFee() {
        return super.calculateMonthlyFee();
    }
}
