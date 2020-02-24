package algorithm.sort;

/**
 * 堆排序
 * <p>
 * 主要借助维护最大堆稳定的sink()方法:
 * <p>
 * 现将一个数组构造成为一个最大堆
 * <p>
 * 然后以类似于每次在堆中将头元素(当前堆中最大值)放入末尾并删除的操作完成排序
 * <p>
 * 时间: < O(2NlgN + 2N) 2N来自于堆的构造, 2NlgN来自于每次下沉操作最大可能需要2lgN次比较
 * <p>
 * 空间: O(1)
 * <p>
 * 堆排序的空间复杂度是O(1), 这在嵌入式等内存要求严格的场景下很有用!!!
 *
 * @author zk
 */
public class HeapSort extends BaseSort {

    public static <K extends Comparable<K>> void sort(K[] a) {
        int len = a.length;

        buildMaxHeap(a);
        for (int i = len - 1; i > 0; i--) {
            exch(a, 0, i);
            len--;
            heapify(a, 0, len);
        }
    }

    /**
     * 建立最大堆
     *
     * @param a 原始数组
     */
    private static <K extends Comparable<K>> void buildMaxHeap(K[] a) {
        for (int len = a.length, i = len >> 1; i >= 0; i--) {
            heapify(a, i, a.length);
        }
    }

    /**
     * 堆调整, 针对第i个元素重建堆
     *
     * @param a 堆数组
     * @param i 针对第i个元素重建堆
     */
    private static <K extends Comparable<K>> void heapify(K[] a, int i, int bound) {
        int largest = i;
        int left = 2 * i + 1, right = 2 * i + 2;

        if (left < bound && less(a[largest], a[left])) {
            largest = left;
        }

        if (right < bound && less(a[largest], a[right])) {
            largest = right;
        }

        if (largest != i) {
            exch(a, i, largest);
            heapify(a, largest, bound);
        }
    }

}
