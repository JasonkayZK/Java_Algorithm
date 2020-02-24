package algorithm.sort;

import algorithm.util.random.RandomArrayUtil;
import org.junit.Test;

import java.util.Arrays;

import static algorithm.sort.BaseSort.isSorted;
import static algorithm.sort.RadixSort.sort;

public class RadixSortTest {

    @Test
    public void sortTest() {
        int[] a = RandomArrayUtil.getRandomIntArray(0, 9999, 20);
        sort(a, 4);
        System.out.println(Arrays.toString(a));;
        assert isSorted(a);
    }
}