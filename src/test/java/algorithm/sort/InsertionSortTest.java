package algorithm.sort;

import algorithm.util.iostream.StdOut;
import algorithm.util.random.RandomArrayUtil;
import algorithm.util.watch.Stopwatch;
import org.junit.Test;

import java.util.Arrays;

import static algorithm.sort.BaseSort.isSorted;
import static algorithm.sort.BaseSort.show;
import static algorithm.sort.InsertionSort.binaryInsertSort;
import static algorithm.sort.InsertionSort.shellSort;
import static algorithm.sort.InsertionSort.sort;
import static algorithm.sort.InsertionSort.sortWithOverride;
import static algorithm.sort.InsertionSort.twoPathInsertSort;

public class InsertionSortTest {

    @Test
    public void sortTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        sort(a);
        show(a);
        assert isSorted(a);
    }

    @Test
    public void sortWithOverrideTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        sortWithOverride(a);
        show(a);
        assert isSorted(a);
    }

    @Test
    public void binaryInsertSortTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        binaryInsertSort(a);
        show(a);
        assert isSorted(a);
    }

    @Test
    public void twoPathInsertSortTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        twoPathInsertSort(a);
        show(a);
        assert isSorted(a);
    }

    @Test
    public void shellSortTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        shellSort(a);
        show(a);
        assert isSorted(a);
    }

    /**
     * 对插入排序的各种方法进行性能测试
     *
     * 分别对十万个随机数组和随机重复数组排序, 结果如下:
     *
     * sort [random]: (20.17 seconds)
     * sort [random + duplicate]: (32.51 seconds)
     *
     * sortWithOverride [random]: (13.37 seconds)
     * sortWithOverride [random + duplicate]: (20.18 seconds)
     *
     * binaryInsertSort [random]: (10.65 seconds)
     * binaryInsertSort [random + duplicate]: (16.19 seconds)
     *
     * twoPathInsertSort [random]: (17.17 seconds)
     * twoPathInsertSort [random + duplicate]: (33.55 seconds)
     *
     * Array length: 100000, Shell sort max step: 88573
     * shellSort [random]: (0.05 seconds)
     * Array length: 100000, Shell sort max step: 88573
     * shellSort [random + duplicate]: (0.06 seconds)
     *
     * 从结果可知, 对于选择了合适的递增序列的希尔排序, 和普通的插入排序的差别提升了N个数量级!
     *
     * 插入排序冠军: shellSort(使用了3为系数的递增序列: 1, 4, 13, 40, 121, 364, 1093, ...)
     *
     */
    @Test
    public void compareSort() {
        // 正常随机数组
        Integer[] a11 = RandomArrayUtil.getRandomBoxedIntArray(0, 1000000, 100000);
        Integer[] a12 = Arrays.copyOf(a11, a11.length);
        Integer[] a13 = Arrays.copyOf(a11, a11.length);
        Integer[] a14 = Arrays.copyOf(a11, a11.length);
        Integer[] a15 = Arrays.copyOf(a11, a11.length);

        // 大量重复数组
        Integer[] a21 = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 100000);
        Integer[] a22 = Arrays.copyOf(a21, a21.length);
        Integer[] a23 = Arrays.copyOf(a21, a21.length);
        Integer[] a24 = Arrays.copyOf(a21, a21.length);
        Integer[] a25 = Arrays.copyOf(a21, a21.length);

        System.out.println("Array created!");

        Stopwatch stopwatch = new Stopwatch();
        sort(a11);
        StdOut.printf("%s (%.2f seconds)\n", "sort [random]:", stopwatch.elapsedTime());
        sort(a21);
        StdOut.printf("%s (%.2f seconds)\n", "sort [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a11);
        assert isSorted(a21);
        System.out.println();

        stopwatch = new Stopwatch();
        sortWithOverride(a12);
        StdOut.printf("%s (%.2f seconds)\n", "sortWithOverride [random]:", stopwatch.elapsedTime());
        sortWithOverride(a22);
        StdOut.printf("%s (%.2f seconds)\n", "sortWithOverride [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a12);
        assert isSorted(a22);
        System.out.println();

        stopwatch = new Stopwatch();
        binaryInsertSort(a13);
        StdOut.printf("%s (%.2f seconds)\n", "binaryInsertSort [random]:", stopwatch.elapsedTime());
        binaryInsertSort(a23);
        StdOut.printf("%s (%.2f seconds)\n", "binaryInsertSort [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a13);
        assert isSorted(a23);
        System.out.println();

        stopwatch = new Stopwatch();
        twoPathInsertSort(a14);
        StdOut.printf("%s (%.2f seconds)\n", "twoPathInsertSort [random]:", stopwatch.elapsedTime());
        twoPathInsertSort(a24);
        StdOut.printf("%s (%.2f seconds)\n", "twoPathInsertSort [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a14);
        assert isSorted(a24);
        System.out.println();

        stopwatch = new Stopwatch();
        shellSort(a15);
        StdOut.printf("%s (%.2f seconds)\n", "shellSort [random]:", stopwatch.elapsedTime());
        shellSort(a25);
        StdOut.printf("%s (%.2f seconds)\n", "shellSort [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a15);
        assert isSorted(a25);
        System.out.println();
    }
}