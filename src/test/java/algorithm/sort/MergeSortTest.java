package algorithm.sort;

import algorithm.util.iostream.StdOut;
import algorithm.util.random.RandomArrayUtil;
import algorithm.util.watch.Stopwatch;
import org.junit.Test;

import java.util.Arrays;

import static algorithm.sort.BaseSort.isSorted;
import static algorithm.sort.BaseSort.show;
import static algorithm.sort.MergeSort.advancedSort;
import static algorithm.sort.MergeSort.sortTopDown;
import static algorithm.sort.MergeSort.sortBottomUp;

public class MergeSortTest {

    @Test
    public void sortTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        sortTopDown(a);
        show(a);
        assert isSorted(a);
    }

    @Test
    public void advancedSortTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        advancedSort(a);
        show(a);
        assert isSorted(a);
    }

    @Test
    public void sortBottomUpTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        sortBottomUp(a);
        show(a);
        assert isSorted(a);
    }

    /**
     * 对归并排序的各种方法进行性能测试
     *
     * 分别对一千万个随机数组和随机重复数组排序, 结果如下:
     *
     * sortTopDown [random]: (4.22 seconds)
     * sortTopDown [random + duplicate]: (7.40 seconds)
     *
     * sortBottomUp [random]: (5.06 seconds)
     * sortBottomUp [random + duplicate]: (9.26 seconds)
     *
     * advancedSort [random]: (3.80 seconds)
     * advancedSort [random + duplicate]: (6.56 seconds)
     *
     * 可知:
     *
     * 1.含有重复元素的排序反而稍快!
     *
     * 2.自顶向下的排序稍快于自底向上的排序(但是自底向上归并的优势在于: 针对链表排序的空间复杂度为O(n))
     *
     * 3.优化后的自顶向下的归并排序显然性能更加优越
     *
     * 归并排序性能冠军: advancedSort
     *
     */
    @Test
    public void compareSort() {
        // 正常随机数组
        Integer[] a11 = RandomArrayUtil.getRandomBoxedIntArray(0, 100000000, 10000000);
        Integer[] a12 = Arrays.copyOf(a11, a11.length);
        Integer[] a13 = Arrays.copyOf(a11, a11.length);

        // 大量重复数组
        Integer[] a21 = RandomArrayUtil.getRandomBoxedIntArray(0, 1000, 10000000);
        Integer[] a22 = Arrays.copyOf(a21, a21.length);
        Integer[] a23 = Arrays.copyOf(a21, a21.length);

        System.out.println("Array created!");

        Stopwatch stopwatch = new Stopwatch();
        sortTopDown(a11);
        StdOut.printf("%s (%.2f seconds)\n", "sortTopDown [random]:", stopwatch.elapsedTime());
        sortTopDown(a21);
        StdOut.printf("%s (%.2f seconds)\n", "sortTopDown [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a11);
        assert isSorted(a21);
        System.out.println();

        stopwatch = new Stopwatch();
        sortBottomUp(a12);
        StdOut.printf("%s (%.2f seconds)\n", "sortBottomUp [random]:", stopwatch.elapsedTime());
        sortBottomUp(a22);
        StdOut.printf("%s (%.2f seconds)\n", "sortBottomUp [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a12);
        assert isSorted(a22);
        System.out.println();

        stopwatch = new Stopwatch();
        advancedSort(a13);
        StdOut.printf("%s (%.2f seconds)\n", "advancedSort [random]:", stopwatch.elapsedTime());
        advancedSort(a23);
        StdOut.printf("%s (%.2f seconds)\n", "advancedSort [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a13);
        assert isSorted(a23);
        System.out.println();
    }

}