package bank;

/**
 * Created by User on 25/01/2017.
 */
public class BusinessAccount extends Account {
    public BusinessAccount(String name, String address, long number, double balance) {
        super(name, address, number, balance, 0);
    }

    /**
     * If balance between 0 and 100 £5
     * 100 to 1000 £7
     * above 1000 £10
     * @return
     */

    @Override
    public double calculateMonthlyFee() {
        return super.calculateMonthlyFee();
    }
}
