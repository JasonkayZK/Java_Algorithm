package algorithm.sort;

import algorithm.util.random.RandomArrayUtil;
import org.junit.Test;

import static algorithm.sort.BaseSort.isSorted;
import static algorithm.sort.BaseSort.show;
import static algorithm.sort.HeapSort.sort;

public class HeapSortTest {

    @Test
    public void sortTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 1000, 50);
        sort(a);
        show(a);
        assert isSorted(a);
    }
}