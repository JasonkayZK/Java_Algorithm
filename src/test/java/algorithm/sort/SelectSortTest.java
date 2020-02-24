package algorithm.sort;

import algorithm.util.iostream.StdOut;
import algorithm.util.random.RandomArrayUtil;
import algorithm.util.watch.Stopwatch;
import org.junit.Test;

import java.util.Arrays;

import static algorithm.sort.BaseSort.isSorted;
import static algorithm.sort.BaseSort.show;
import static algorithm.sort.SelectSort.sort;
import static algorithm.sort.SelectSort.sortBilaterally;

public class SelectSortTest {

    /**
     * 测试冒泡排序算法
     */
    @Test
    public void testSort() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 10, 5);
        sort(a);
        show(a);
        assert isSorted(a);
    }

    /**
     * 测试优化后的冒泡排序
     */
    @Test
    public void sortBilaterallyTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 1000, 20);
        sortBilaterally(a);
        show(a);
        assert isSorted(a);
    }

    /**
     * 对选择排序的各种方法进行性能测试
     *
     * 分别对五万个随机数组和随机重复数组排序, 结果如下:
     *
     * sort [random]: (2.76 seconds)
     * sort [random + duplicate]: (4.45 seconds)
     *
     * sortBilaterally [random]: (2.36 seconds)
     * sortBilaterally [random + duplicate]: (4.16 seconds)
     *
     * 选择排序经过了优化, 性能略微提高
     *
     * 选择排序的性能冠军为: sortBilaterally
     *
     */
    @Test
    public void compareTest() {
        // 正常随机数组
        Integer[] arr1 = RandomArrayUtil.getRandomBoxedIntArray(0, 1000000, 50000);
        Integer[] a1 = Arrays.copyOf(arr1, arr1.length);

        // 大量重复数组
        Integer[] arr2 = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50000);
        Integer[] a2 = Arrays.copyOf(arr2, arr2.length);

        System.out.println("Array created!");

        Stopwatch stopwatch = new Stopwatch();
        sort(a1);
        StdOut.printf("%s (%.2f seconds)\n", "sort [random]:", stopwatch.elapsedTime());
        sort(a2);
        StdOut.printf("%s (%.2f seconds)\n", "sort [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a1);
        assert isSorted(a2);
        System.out.println();

        stopwatch = new Stopwatch();
        sortBilaterally(arr1);
        StdOut.printf("%s (%.2f seconds)\n", "sortBilaterally [random]:", stopwatch.elapsedTime());
        sortBilaterally(arr2);
        StdOut.printf("%s (%.2f seconds)\n", "sortBilaterally [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(arr1);
        assert isSorted(arr2);
    }

}