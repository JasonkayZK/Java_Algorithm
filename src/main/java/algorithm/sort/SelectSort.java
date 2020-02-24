package algorithm.sort;

/**
 * 选择排序
 *
 * 每一轮遍历选择最小的的元素放在当前排序序列开头, 并且当前数组个数减一
 *
 * 平均时间: O(N^2) N^2/4比较 + N^2/4交换
 *
 * 最好时间: O(N^2) N-1比较 + 0交换
 *
 * 最坏时间: O(N^2) N^2/2比较 + N^2/2交换
 *
 * 空间: O(1)
 *
 * @author zk
 */
public class SelectSort extends BaseSort {

    private SelectSort() {
    }

    /**
     * 选择排序最简单实现
     */
    public static <K extends Comparable<K>> void sort(K[] a) {
        for (int i = 0, len = a.length; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
//            show(a);
        }
    }

    /**
     * 每次排序可以选择两个: 最大和最小分别和左右位置进行交换
     */
    public static <K extends Comparable<K>> void sortBilaterally(K[] a) {
        int left = 0, right = a.length - 1;

        while (left < right) {
            // 记录无序区最大元素和最小元素下标
            int max = left, min = left;

            for (int j = left + 1; j <= right; ++j) {
                // 找最大元素下标
                if (less(a[j], a[min])) {
                    min = j;
                }
                // 找最小元素下标
                if (less(a[max], a[j])) {
                    max = j;
                }
            }

            // 最小值如果是第一个则没有必要交换
            if (min != left) {
                exch(a, min, left);
            }

            // 这里很重要
            // 如果最大元素下标是left,前面已经和最小元素交换了，此时最大元素下标应该是min
            if (max == left) {
                max = min;
            }

            // 最大值如果是最后一个则没必要交换
            if (max != right) {
                exch(a, max, right);
            }

            left++;
            right--;
        }

    }

}
