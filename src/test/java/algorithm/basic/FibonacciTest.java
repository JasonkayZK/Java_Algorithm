package algorithm.basic;

import algorithm.util.iostream.StdOut;
import org.junit.Test;
import top.jasonkayzk.jutil.RandomUtils;

import static algorithm.basic.Fibonacci.fibByArray;
import static algorithm.basic.Fibonacci.fibByFormula;
import static algorithm.basic.Fibonacci.fibByMatrix;
import static algorithm.basic.Fibonacci.fibRecursively;
import static algorithm.basic.Fibonacci.test;

public class FibonacciTest {

    @Test
    public void fibRecursivelyTest() {
        for (int i = 0; i < 5; i++) {
            int n = RandomUtils.getRandomInteger(0, 50);
            long res = fibRecursively(n);
            System.out.println(String.format("fib(%d) = %d", n, res));
            assert test(n, res);
        }
    }

    @Test
    public void fibByArrayTest() {
        for (int i = 0; i < 5; i++) {
            int n = RandomUtils.getRandomInteger(0, 50);
            long res = fibByArray(n);
            System.out.println(String.format("fib(%d) = %d", n, res));
            assert test(n, res);
        }
    }

    @Test
    public void fibByFormulaTest() {
        for (int i = 0; i < 5; i++) {
            int n = RandomUtils.getRandomInteger(0, 50);
            long res = fibByFormula(n);
            System.out.println(String.format("fib(%d) = %d", n, res));
            assert test(n, res);
        }
    }

    @Test
    public void fibByMatrixTest() {
        for (int i = 0; i < 5; i++) {
            int n = RandomUtils.getRandomInteger(0, 50);
            long res = fibByMatrix(n);
            System.out.println(String.format("fib(%d) = %d", n, res));
            assert test(n, res);
        }
    }

    /**
     * 比较所有算法计算fib(50)
     *
     * 结果如下:
     *
     * fibRecursively: (60008 milliseconds)
     * fibByArray: (0 milliseconds)
     * fibByFormula: (0 milliseconds)
     * fibByMatrix: (0 milliseconds)
     *
     */
    @Test
    public void compare() {
        long current = System.currentTimeMillis();
        long res = fibRecursively(50);
        StdOut.printf("%s (%d milliseconds)\n", "fibRecursively:", System.currentTimeMillis() - current);
        assert test(50, res);

        current = System.currentTimeMillis();
        res = fibByArray(50);
        StdOut.printf("%s (%d milliseconds)\n", "fibByArray:", System.currentTimeMillis() - current);
        assert test(50, res);

        current = System.currentTimeMillis();
        res = fibByFormula(50);
        StdOut.printf("%s (%d milliseconds)\n", "fibByFormula:", System.currentTimeMillis() - current);
        assert test(50, res);

        current = System.currentTimeMillis();
        res = fibByMatrix(50);
        StdOut.printf("%s (%d milliseconds)\n", "fibByMatrix:", System.currentTimeMillis() - current);
        assert test(50, res);
    }

}