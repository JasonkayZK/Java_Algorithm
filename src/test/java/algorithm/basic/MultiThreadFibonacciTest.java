package algorithm.basic;

import algorithm.util.iostream.StdOut;
import org.junit.Test;

import static algorithm.basic.Fibonacci.fibByFormula;

public class MultiThreadFibonacciTest {

    @Test
    public void compute() {
        int left = 1, right = 50;
        MultiThreadFibonacci f = new MultiThreadFibonacci(left, right);
        long res = f.compute();
        System.out.println(String.format("The sum of %dth~%dth fibonacci is: %d", left, right, res));
    }

    /**
     * 对比单线程和并行的斐波那契
     *
     * 运行结果:
     *
     * fibByFormula: (4547 milliseconds)
     * MultiThreadFibonacci: (960 milliseconds)
     * The sum of 1th~100000000th fibonacci is: 1293530146058730797
     *
     * 可见并行的速度还是很快的!
     */
    @Test
    public void compare() {
        int left = 1, right = 100000000;
        long res1 = 0, res2;

        long current = System.currentTimeMillis();
        for (int i = left; i <= right; i++) {
            res1 += fibByFormula(i);
        }
        StdOut.printf("%s (%d milliseconds)\n", "fibByFormula:", System.currentTimeMillis() - current);

        current = System.currentTimeMillis();
        MultiThreadFibonacci f = new MultiThreadFibonacci(left, right);
        res2 = f.compute();
        StdOut.printf("%s (%d milliseconds)\n", "MultiThreadFibonacci:", System.currentTimeMillis() - current);

        System.out.println(String.format("The sum of %dth~%dth fibonacci is: %d", left, right, res2));
        assert res1 == res2;
    }

}