package algorithm.basic;

import org.junit.Test;

/**
 * @author zk
 */
public class OverflowDemo {

    @Test
    public void overflow() {
        int OVERFLOW = -2147483648;
        // -2147483648
        System.out.println(Math.abs(OVERFLOW));
    }

    @Test
    public void overflow2() {
        double OVERFLOW = -2147483648.0;
        // 2.147483648E9
        System.out.println(Math.abs(OVERFLOW));
    }

    @Test
    public void divide() {
        // -4
        System.out.println(-14 / 3);
        // -4
        System.out.println(14 / -3);
        // -2
        System.out.println(-14 % 3);
        // 2
        System.out.println(14 % -3);
    }


}
