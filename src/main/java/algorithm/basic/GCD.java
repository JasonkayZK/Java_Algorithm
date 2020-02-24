package algorithm.basic;

public class GCD {

    /**
     * 阿基米德算法， 计算两个数的最大公约数
     *
     * @param p 整数p
     * @param q 整数q
     * @return p, q的最大公约数
     */
    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args) {
        System.out.println(gcd(5, 115));
    }
}
