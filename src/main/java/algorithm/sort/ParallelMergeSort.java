package algorithm.sort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 归并排序的多线程版本
 *
 * @author zk
 */
public class ParallelMergeSort extends BaseSort {

    @SuppressWarnings("unchecked")
    public static <K extends Comparable<K>> void parallelMergeSort(K[] arr) {
        K[] aux = (K[]) new Comparable[arr.length];

        ForkJoinPool pool = new ForkJoinPool();
        // 创建任务
        RecursiveAction mainTask = new MergeTask<>(arr, aux, 0, arr.length - 1);
        // 执行任务
        pool.invoke(mainTask);
    }

    public static class MergeTask<K extends Comparable<K>> extends RecursiveAction {
        /**
         * 小数组排序优化:
         * <p>
         * 小于500的子数组不再使用递归调用
         * <p>
         * 而是直接使用插入排序
         */
        private final int THRESHOLD = 15;

        private K[] arr;

        private K[] tmp;

        private int start;

        private int end;

        public MergeTask(K[] arr, K[] tmp, int start, int end) {
            this.arr = arr;
            this.tmp = tmp;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= THRESHOLD) {
                insertionSort(arr, start, end);
            } else {
                // 获取左一半和右一半
                int mid = start + (end - start) / 2;
                MergeTask leftTask = new MergeTask<>(arr, tmp, start, mid);
                MergeTask rightTask = new MergeTask<>(arr, tmp, mid + 1, end);

                // Recursively sort the two halves
                leftTask.fork();
                rightTask.fork();

                leftTask.join();
                rightTask.join();

                // Merge firstHalf with second
                merge(arr, tmp, start, mid + 1, end);
            }
        }
    }

    /**
     * 不使用辅助数组进行复制归并(但是仍需要空间)
     *
     * @param arr   源数组
     * @param tmp   目标数组
     * @param left  归并区间左边界
     * @param mid   归并区间切分位置
     * @param right 归并右边界
     */
    private static <K extends Comparable<K>> void merge(K[] arr, K[] tmp, int left, int mid, int right) {
        int leftEnd = mid - 1, tmpPos = left;
        int numElements = right - left + 1;

        while (left <= leftEnd && mid <= right) {
            if (less(arr[left], arr[mid]))
                tmp[tmpPos++] = arr[left++];
            else
                tmp[tmpPos++] = arr[mid++];
        }

        while (left <= leftEnd)
            tmp[tmpPos++] = arr[left++];

        while (mid <= right)
            tmp[tmpPos++] = arr[mid++];

        for (int i = 0; i < numElements; i++, right--)
            arr[right] = tmp[right];
    }

    /**
     * 使用插排排序a[lo...hi]子区间
     *
     * @param a  排序数组
     * @param lo 排序左边界
     * @param hi 排序右边界
     */
    private static <K extends Comparable<K>> void insertionSort(K[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

}
