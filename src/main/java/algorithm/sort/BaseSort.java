package algorithm.sort;

import algorithm.util.iostream.StdOut;

import java.util.Arrays;

/**
 * 排序类的例子
 *
 * 主要实现了比较less()方法, 交换exch()方法
 *
 * 以及输出数组show(), 排序验证isSorted()方法
 *
 * @author zk
 */
public abstract class BaseSort {

    /**
     * 排序类应当实现的具体排序方法
     *
     */
    private static void sort() {}

    /**
     * 比较v和w, 返回v是否比w小
     *
     * @param v 比较值v
     * @param w 比较值w
     * @return v < w返回true, v >= w返回false
     */
    public static <K extends Comparable<K>> boolean less(K v, K w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 在数组a中交换索引i, j对应元素
     *
     * @param a 数组a
     * @param i 索引i
     * @param j 索引j
     */
    public static <K extends Comparable<K>> void exch(K[] a, int i, int j) {
        K t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static <K extends Comparable<K>> void show(K[] a) {
        Arrays.stream(a).forEach(x -> System.out.print(x + " "));
        StdOut.println();
    }

    /**
     * 判断数组a是否有序
     *
     * @param a 数组
     * @return a有序?
     */
    public static <K extends Comparable<K>> boolean isSorted(K[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    public static boolean isSorted(int[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    public static boolean isSorted(double[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    /**
     * 判断数组a[lo...hi]区间是否有序
     */
    public static <K extends Comparable<K>> boolean isSorted(K[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    public static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    public static boolean isSorted(double[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

}
