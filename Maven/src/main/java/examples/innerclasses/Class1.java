package examples.innerclasses;

/**
 * Created by User on 17/04/2017.
 */
public class Class1 {
    public static void main(String[] args) {
        //An instance of InnerClass can exist only within an instance of outer class
        Bowl bowl = new Bowl();
        Bowl.Goldfish goldfish = bowl.new Goldfish();

        //Static inner classes can exist independently of outer class
        Customer.Address address = new Customer.Address();
    }
}
