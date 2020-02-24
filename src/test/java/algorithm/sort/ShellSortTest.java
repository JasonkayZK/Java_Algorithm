package algorithm.sort;

import algorithm.util.iostream.StdOut;
import algorithm.util.random.RandomArrayUtil;
import algorithm.util.watch.Stopwatch;
import org.junit.Test;

import java.util.Arrays;

import static algorithm.sort.BaseSort.isSorted;
import static algorithm.sort.ShellSort.sort;

public class ShellSortTest {

    /**
     * 将不同step值的希尔排序进行比较, 并与插入排序进行比较
     *
     * 结果如下:
     *
     * Step: 3
     * Array length: 100000, Shell sort max step: 88573
     * [random]: (0.06 seconds)
     * Array length: 100000, Shell sort max step: 88573
     * [random + duplicate]: (0.07 seconds)
     *
     * Step: 7
     * Array length: 100000, Shell sort max step: 19608
     * [random]: (0.04 seconds)
     * Array length: 100000, Shell sort max step: 19608
     * [random + duplicate]: (0.07 seconds)
     *
     * Step: 19
     * Array length: 100000, Shell sort max step: 7240
     * [random]: (0.08 seconds)
     * Array length: 100000, Shell sort max step: 7240
     * [random + duplicate]: (0.13 seconds)
     *
     * Step: 97
     * Array length: 100000, Shell sort max step: 9507
     * [random]: (0.31 seconds)
     * Array length: 100000, Shell sort max step: 9507
     * [random + duplicate]: (0.60 seconds)
     *
     * Step: 10000000
     * Array length: 100000, Shell sort max step: 1
     * [random]: (16.98 seconds)
     * Array length: 100000, Shell sort max step: 1
     * [random + duplicate]: (28.00 seconds)
     *
     * Insertion Sort:
     * [random]: (22.34 seconds)
     * [random + duplicate]: (33.75 seconds)
     *
     * 实验证明, 对于递增系数巨大的希尔排序, 性能会恶化到与插入排序性能相似!
     *
     */
    @Test
    public void compareStep() {
        // 正常随机数组
        Integer[] a11 = RandomArrayUtil.getRandomBoxedIntArray(0, 1000000, 100000);
        Integer[] a12 = Arrays.copyOf(a11, a11.length);
        Integer[] a13 = Arrays.copyOf(a11, a11.length);
        Integer[] a14 = Arrays.copyOf(a11, a11.length);
        Integer[] a15 = Arrays.copyOf(a11, a11.length);
        Integer[] a16 = Arrays.copyOf(a11, a11.length);

        // 大量重复数组
        Integer[] a21 = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 100000);
        Integer[] a22 = Arrays.copyOf(a21, a21.length);
        Integer[] a23 = Arrays.copyOf(a21, a21.length);
        Integer[] a24 = Arrays.copyOf(a21, a21.length);
        Integer[] a25 = Arrays.copyOf(a21, a21.length);
        Integer[] a26 = Arrays.copyOf(a21, a21.length);

        System.out.println("Array created!");

        ShellSort.step = 3;
        System.out.println("Step: " + ShellSort.step);
        Stopwatch stopwatch = new Stopwatch();
        sort(a11);
        StdOut.printf("%s (%.2f seconds)\n", "[random]:", stopwatch.elapsedTime());
        sort(a21);
        StdOut.printf("%s (%.2f seconds)\n", "[random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a11);
        assert isSorted(a21);
        System.out.println();

        ShellSort.step = 7;
        System.out.println("Step: " + ShellSort.step);
        stopwatch = new Stopwatch();
        sort(a12);
        StdOut.printf("%s (%.2f seconds)\n", "[random]:", stopwatch.elapsedTime());
        sort(a22);
        StdOut.printf("%s (%.2f seconds)\n", "[random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a12);
        assert isSorted(a22);
        System.out.println();

        ShellSort.step = 19;
        System.out.println("Step: " + ShellSort.step);
        stopwatch = new Stopwatch();
        sort(a13);
        StdOut.printf("%s (%.2f seconds)\n", "[random]:", stopwatch.elapsedTime());
        sort(a23);
        StdOut.printf("%s (%.2f seconds)\n", "[random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a13);
        assert isSorted(a23);
        System.out.println();

        ShellSort.step = 97;
        System.out.println("Step: " + ShellSort.step);
        stopwatch = new Stopwatch();
        sort(a14);
        StdOut.printf("%s (%.2f seconds)\n", "[random]:", stopwatch.elapsedTime());
        sort(a24);
        StdOut.printf("%s (%.2f seconds)\n", "[random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a14);
        assert isSorted(a24);
        System.out.println();

        ShellSort.step = 10000000;
        System.out.println("Step: " + ShellSort.step);
        stopwatch = new Stopwatch();
        sort(a15);
        StdOut.printf("%s (%.2f seconds)\n", "[random]:", stopwatch.elapsedTime());
        sort(a25);
        StdOut.printf("%s (%.2f seconds)\n", "[random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a15);
        assert isSorted(a25);
        System.out.println();

        System.out.println("Insertion Sort:");
        stopwatch = new Stopwatch();
        InsertionSort.sort(a16);
        StdOut.printf("%s (%.2f seconds)\n", "[random]:", stopwatch.elapsedTime());
        InsertionSort.sort(a26);
        StdOut.printf("%s (%.2f seconds)\n", "[random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a16);
        assert isSorted(a26);
    }
}