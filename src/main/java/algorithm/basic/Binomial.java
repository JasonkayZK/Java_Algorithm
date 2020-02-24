package algorithm.basic;

import java.util.Arrays;

public class Binomial {

    @Deprecated
    private static double binomial(int n, int k, double p) {
        if (n == 0 && k == 0) return 1.0;
        if (n < 0 || k < 0) return 0.0;
        return (1.0 - p) * binomial(n - 1, k, p) + p * binomial(n - 1, k - 1, p);
    }

    private static double binomialWithStore(int n, int k, double p, double[][] arr) {
        if (n < 0 || k < 0) return 0.0;
        if (arr[n][k] > 0) return arr[n][k];
        arr[n][k] = (1.0 - p) * binomialWithStore(n - 1, k, p, arr) + p * binomialWithStore(n - 1, k - 1, p, arr);
        return arr[n][k];
    }

    public static void main(String[] args) {
        double start = System.currentTimeMillis();
        System.out.println(binomial(30, 20, 0.25));
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        double[][] binomialArr = new double[100 + 1][100 + 1];
        Arrays.stream(binomialArr).forEach(x -> Arrays.fill(x, -1));
        binomialArr[0][0] = 1;
        System.out.println(binomialWithStore(30, 20, 0.25, binomialArr));
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");

    }

}
