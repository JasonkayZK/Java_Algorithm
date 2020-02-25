package algorithm.basic;

/**
 * 斐波那契数列生成算法
 * <p>
 * fib(n):
 * - n <= 0: 0
 * - n = 1: 1
 * - n > 1: fib(n-2) + fib(n-1)
 * <p>
 * Example: 0 1 1 2 3 5 8 13 21 34 55...
 *
 * @author zk
 */
public class Fibonacci {

    /**
     * 前五十个斐波那契
     */
    public static final long[] DICT = new long[]{
            0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987,
            1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393,
            196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887,
            9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141,
            267914296, 433494437, 701408733, 1134903170, 1836311903, 2971215073L,
            4807526976L, 7778742049L, 12586269025L
    };

    /**
     * 递归计算生成fib(n)
     * <p>
     * 时间复杂度: O(N^2)
     * <p>
     * 空间复杂度：O(N)
     *
     * @param n 计算fib(n)
     * @return fib(n)
     */
    public static long fibRecursively(int n) {
        return n < 2 ? Math.max(0, n) : fibRecursively(n - 1) + fibRecursively(n - 2);
    }

    /**
     * 循环计算fib(n)
     * <p>
     * 时间复杂度：O（N）
     * <p>
     * 空间复杂度：O（1）
     *
     * @param n 计算fib(n)
     * @return fib(n)
     */
    public static long fibByArray(int n) {
        if (n <= 2) return Math.max(0, n);

        long[] res = {0, 1};

        long fibOne = 1, fibTwo = 0, fibN = 0;
        for (int i = 1; i < n; i++) {
            fibN = fibOne + fibTwo;
            fibTwo = fibOne;
            fibOne = fibN;
        }
        return fibN;
    }

    /**
     * 通过公式计算fib(n)
     * <p>
     * 时间复杂度：O（logN）
     * <p>
     * 空间复杂度：O（1）
     *
     * @param n 计算fib(n)
     * @return fib(n)
     */
    public static long fibByFormula(int n) {
        return (long) ((Math.pow((1 + Math.sqrt(5)) / 2, n) - Math.pow((1 - Math.sqrt(5)) / 2, n)) / Math.sqrt(5));
    }

    /**
     * 矩阵乘法实现(单线程最优解)
     *
     * @param n 计算fib(n)
     * @return fib(n)
     */
    public static long fibByMatrix(int n) {
        if (n <= 2) return Math.max(0, n);
        FibMatrix fibMatrix = FibMatrix.power(n - 1);
        return fibMatrix.m00;
    }

    /**
     * 测试生成的数据是否正确
     *
     * @param n   第n个斐波那契数
     * @param ans 计算的答案
     * @return 生成的数据是否正确
     */
    public static boolean test(int n, long ans) {
        if (n > 50) throw new RuntimeException("n应当小于等于50");
        return ans == DICT[n];
    }

    /**
     * fibByMatrix计算使用到的2x2的矩阵
     */
    private static class FibMatrix {
        private long m00;

        private long m01;

        private long m10;

        private long m11;

        public FibMatrix(long m00, long m01, long m10, long m11) {
            this.m00 = m00;
            this.m01 = m01;
            this.m10 = m10;
            this.m11 = m11;
        }

        /**
         * 定义2×2矩阵的乘法运算
         *
         * @param matrix1 matrix1
         * @param matrix2 matrix2
         * @return 矩阵乘法结果
         */
        public static FibMatrix multiply(FibMatrix matrix1, FibMatrix matrix2) {
            FibMatrix matrix12 = new FibMatrix(1, 1, 1, 0);

            matrix12.m00 = matrix1.m00 * matrix2.m00 + matrix1.m01 * matrix2.m10;
            matrix12.m01 = matrix1.m00 * matrix2.m01 + matrix1.m01 * matrix2.m11;
            matrix12.m10 = matrix1.m10 * matrix2.m00 + matrix1.m11 * matrix2.m10;
            matrix12.m11 = matrix1.m10 * matrix2.m01 + matrix1.m11 * matrix2.m11;
            return matrix12;
        }

        /**
         * 定义2×2矩阵的幂运算(使用快速幂的方法)
         *
         * @return 矩阵的n次幂
         */
        public static FibMatrix power(int n) {
            FibMatrix matrix = new FibMatrix(1, 1, 1, 0);
            if (n == 1) {
            } else if (n % 2 == 0) {
                matrix = power(n / 2);
                matrix = multiply(matrix, matrix);
            } else if (n % 2 == 1) {
                matrix = power((n - 1) / 2);
                matrix = multiply(matrix, matrix);
                matrix = multiply(matrix, new FibMatrix(1, 1, 1, 0));
            }
            return matrix;
        }
    }
}
