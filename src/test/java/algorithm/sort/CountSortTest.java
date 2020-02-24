package algorithm.sort;

import algorithm.util.random.RandomArrayUtil;
import org.junit.Test;

import java.util.Arrays;

import static algorithm.sort.BaseSort.isSorted;
import static algorithm.sort.CountSort.sort;

public class CountSortTest {

    @Test
    public void sortTest() {
        int[] a = RandomArrayUtil.getRandomIntArray(0, 1000, 20);
        sort(a);
        System.out.println(Arrays.toString(a));
        assert isSorted(a);
    }

    @Test
    public void sortTest2() {
        int[] a = RandomArrayUtil.getRandomIntArray(0, 10, 100);
        sort(a);
        System.out.println(Arrays.toString(a));
        assert isSorted(a);
    }
}