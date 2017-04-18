package examples.featurespace.apache;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by User on 16/04/2017.
 */
public class Math {
    public static void main(String[] args) {
        long a = NumberUtils.toLong("5");//0 if conversion fails
        System.out.println(a);
    }
}
