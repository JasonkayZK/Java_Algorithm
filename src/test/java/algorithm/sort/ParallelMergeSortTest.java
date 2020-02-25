package algorithm.sort;

import algorithm.util.random.RandomArrayUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static algorithm.sort.ParallelMergeSort.parallelMergeSort;

public class ParallelMergeSortTest {

    /**
     * 比较单线程归并排序, ForkJoin并发排序和Stream流排序
     *
     * 排序结果:
     *
     * 1.排序十万个数
     *
     * Sequent time is: 4 milliseconds
     * Parallel time is: 16 processors is 34 milliseconds
     * ParallelStream time is: 29 milliseconds
     *
     * 2.排序一百万个数
     *
     * Sequent time is: 461 milliseconds
     * Parallel time is: 16 processors is 435 milliseconds
     * ParallelStream time is: 220 milliseconds
     *
     * 3.排序一亿个数
     *
     * Sequent time is: 58868 milliseconds
     * Parallel time is: 16 processors is 28884 milliseconds
     * ParallelStream time is: 1606 milliseconds
     *
     */
    @Test
    public void parallelMergeSortTest() {
        // 排序大小
        final int sortSize = 100000000;

        Integer[] arr1 = RandomArrayUtil.getRandomBoxedIntArray(sortSize);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        List<Integer> arr3 = Arrays.stream(arr1).collect(Collectors.toList());
        System.out.println("Arrays created!");

        long startTime = System.currentTimeMillis();
        MergeSort.sortTopDown(arr2);
        long endTime = System.currentTimeMillis();
        System.out.println("Sequent time is: " + (endTime - startTime) + " milliseconds");
        assert BaseSort.isSorted(arr2);

        startTime = System.currentTimeMillis();
        parallelMergeSort(arr1);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel time is: " + Runtime.getRuntime().availableProcessors() + " processors is " + (endTime - startTime) + " milliseconds");
        assert BaseSort.isSorted(arr1);

        startTime = System.currentTimeMillis();
        int[] res = arr3.stream().parallel().mapToInt(Integer::intValue).sorted().toArray();
        endTime = System.currentTimeMillis();
        System.out.println("ParallelStream time is: " + (endTime - startTime) + " milliseconds");
        assert BaseSort.isSorted(res);
    }
}