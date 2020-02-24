package algorithm.sort;

import algorithm.util.random.RandomArrayUtil;
import org.junit.Test;

import java.util.Arrays;

import static algorithm.sort.BaseSort.isSorted;
import static algorithm.sort.BucketSort.sort;

public class BucketSortTest {

    @Test
    public void bucketSortTest() {
        int maxBound = 1000;
        double[] a = RandomArrayUtil.getRandomDoubleArray(0, maxBound, 20);
        sort(a, maxBound + 1);
        System.out.println(Arrays.toString(a));
        assert isSorted(a);
    }
}