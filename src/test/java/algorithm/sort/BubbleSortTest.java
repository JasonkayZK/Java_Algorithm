package algorithm.sort;

import algorithm.util.iostream.StdOut;
import algorithm.util.random.RandomArrayUtil;
import algorithm.util.watch.Stopwatch;
import org.junit.Test;

import java.util.Arrays;

import static algorithm.sort.BaseSort.isSorted;
import static algorithm.sort.BaseSort.show;
import static algorithm.sort.BubbleSort.sort;
import static algorithm.sort.BubbleSort.sortBilaterally;
import static algorithm.sort.BubbleSort.sortWithCheckBound;
import static algorithm.sort.BubbleSort.sortWithFlag;

public class BubbleSortTest {

    @Test
    public void sortTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 1000, 20);
        sort(a);
        show(a);
        assert isSorted(a);
    }

    @Test
    public void sortWithFlagTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 1000, 20);
        sortWithFlag(a);
        show(a);
        assert isSorted(a);
    }


    @Test
    public void sortWithCheckBoundTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 1000, 20);
        sortWithCheckBound(a);
        show(a);
        assert isSorted(a);
    }


    @Test
    public void sortWithTwoSwap() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 1000, 20);
        sortBilaterally(a);
        show(a);
        assert isSorted(a);
    }

    /**
     * 对冒泡排序的各种方法进行性能测试
     *
     * 分别对五万个随机数组和随机重复数组排序, 结果如下:
     *
     * sort method[random]: (9.04 seconds)
     * sort method[random + duplicate]: (15.53 seconds)
     *
     * sortWithFlag method[random]: (8.02 seconds)
     * sortWithFlag method[random + duplicate]: (14.17 seconds)
     *
     * sortWithCheckBound method[random]: (8.42 seconds)
     * sortWithCheckBound method[random + duplicate]: (15.16 seconds)
     *
     * sortBilaterally method[random]: (6.47 seconds)
     * sortBilaterally method[random + duplicate]: (11.73 seconds)
     *
     * 从结果可见, 通过将最简单的冒泡排序进行逐步优化, 性能有了部分提高
     *
     * 冒泡排序性能冠军: sortBilaterally
     * (因为使用了双向排序)
     *
     */
    @Test
    public void sortCompare() {
        // 正常随机数组
        Integer[] a11 = RandomArrayUtil.getRandomBoxedIntArray(0, 1000000, 50000);
        Integer[] a12 = Arrays.copyOf(a11, a11.length);
        Integer[] a13 = Arrays.copyOf(a11, a11.length);
        Integer[] a14 = Arrays.copyOf(a11, a11.length);

        // 大量重复数组
        Integer[] a21 = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50000);
        Integer[] a22 = Arrays.copyOf(a21, a21.length);
        Integer[] a23 = Arrays.copyOf(a21, a21.length);
        Integer[] a24 = Arrays.copyOf(a21, a21.length);
        System.out.println("Array created!");

        Stopwatch stopwatch = new Stopwatch();
        sort(a11);
        StdOut.printf("%s (%.2f seconds)\n", "sort method[random]:", stopwatch.elapsedTime());
        sort(a21);
        StdOut.printf("%s (%.2f seconds)\n", "sort method[random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a11);
        assert isSorted(a21);
        System.out.println();

        stopwatch = new Stopwatch();
        sortWithFlag(a12);
        StdOut.printf("%s (%.2f seconds)\n", "sortWithFlag method[random]:", stopwatch.elapsedTime());
        sortWithFlag(a22);
        StdOut.printf("%s (%.2f seconds)\n", "sortWithFlag method[random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a12);
        assert isSorted(a22);
        System.out.println();

        stopwatch = new Stopwatch();
        sortWithCheckBound(a13);
        StdOut.printf("%s (%.2f seconds)\n", "sortWithCheckBound method[random]:", stopwatch.elapsedTime());
        sortWithCheckBound(a23);
        StdOut.printf("%s (%.2f seconds)\n", "sortWithCheckBound method[random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a13);
        assert isSorted(a23);
        System.out.println();

        stopwatch = new Stopwatch();
        sortBilaterally(a14);
        StdOut.printf("%s (%.2f seconds)\n", "sortBilaterally method[random]:", stopwatch.elapsedTime());
        sortBilaterally(a24);
        StdOut.printf("%s (%.2f seconds)\n", "sortBilaterally method[random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a14);
        assert isSorted(a24);
    }

}