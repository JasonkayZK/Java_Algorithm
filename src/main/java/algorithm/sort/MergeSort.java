package algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 * <p>
 * 归并排序的核心是: 归并操作
 * <p>
 * 归并操作将两个有序的数组归并为更大的一个有序数组
 * <p>
 * 要将一个数组排序, 可以先(递归的)将它分为两半分别排序, 然后将结果归并起来
 * <p>
 * 平均时间: O(NlgN)
 * <p>
 * 最坏时间: O(6NlgN) 此时数组为完全树
 * <p>
 * 最好时间: O(N) 数组元素全部相同
 * <p>
 * 空间: O(N) 开辟了一个和排序数组相同大小的数组用于归并
 *
 * @author zk
 */
@SuppressWarnings("unchecked")
public class MergeSort extends BaseSort {

    /**
     * threshold to insertion sort
     */
    private static final int THRESHOLD = 7;

    /**
     * 使用成员变量防止使用递归过多的创建数组!
     */
    private static Comparable[] aux;

    private MergeSort() {
    }

    public static <K extends Comparable<K>> void sortTopDown(K[] a) {
        aux = new Comparable[a.length];
        sortTopDown(a, 0, a.length - 1);
    }

    public static <K extends Comparable<K>> void advancedSort(K[] a) {
        // 此次未使用成员变量的辅助数组
        var helper = Arrays.copyOf(a, a.length);
        advancedSort(helper, a, 0, a.length - 1);
    }

    /**
     * 自底向上的归并排序:
     * <p>
     * 自底向上进行lgN次的两两归并
     *
     * 当数组长度为2的幂时: 自顶向下和自底向上仅仅是访问次序不同(比较次数和数组访问次数相同)
     *
     * 但其他时候不同;
     *
     * 非常适合链表的原地归并!(此时空间复杂度为O(1))
     *
     * @param a 待排序数组
     */
    public static <K extends Comparable<K>> void sortBottomUp(K[] a) {
        aux = new Comparable[a.length];
        for (int sz = 1, len = a.length; sz < len; sz *= 2)
            for (int lo = 0; lo < len - sz; lo += sz + sz)
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, len - 1));
    }

    /**
     * 自顶向下的归并排序(lo...hi)区间
     *
     * @param a  待排序数组
     * @param lo 排序左边界
     * @param hi 排序右边界(包括)
     */
    private static <K extends Comparable<K>> void sortTopDown(K[] a, int lo, int hi) {
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        // 将左半边排序
        sortTopDown(a, lo, mid);
        // 将右半边排序
        sortTopDown(a, mid + 1, hi);

        // 归并结果
        merge(a, lo, mid, hi);
    }

    /**
     * 优化的自顶向下的归并排序(lo...hi)区间
     * <p>
     * 1.对小规模子数组采用插入排序:
     * - 递归对于小规模的数组将产生过多的小数组甚至是空数组调用栈
     * <p>
     * 2.测试数组是否已经有序:
     * - 若a[mid]<=a[mid+1], 认为数组已经有序, 可以跳过merge()方法
     * <p>
     * 3.不将元素复制到辅助数组aux
     * - 调用两种排序方法: 一种将数据从输入数组排序到辅助数组, 另一个方法反之;
     *
     * @param src 源数组
     * @param dst 目标数组(当前递归中的辅助数组)
     * @param lo  排序左边界
     * @param hi  排序右边界(包括)
     */
    private static <K extends Comparable<K>> void advancedSort(K[] src, K[] dst, int lo, int hi) {
        // 1.当排序大小小于THRESHOLD, 使用插排
        if (hi <= lo + THRESHOLD) {
            insertionSort(dst, lo, hi);
            return;
        }

        // 归并
        int mid = lo + (hi - lo) / 2;
        advancedSort(dst, src, lo, mid);
        advancedSort(dst, src, mid + 1, hi);

//         if (!less(src[mid+1], src[mid])) {
//            for (int i = lo; i <= hi; i++) dst[i] = src[i];
//            return;
//         }

        // 2.测试数组是否已经有序, 跳过归并
        // 使用System.arraycopy()比上述循环略快
        if (!less(src[mid + 1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
    }

    /**
     * 原地归并的方法
     * <p>
     * 将子数组a[lo...mid]和a[mid+1...hi]归并成一个有序的数组
     * <p>
     * 并将结果放在a[lo...hi]中
     *
     * @param a   待归并数组
     * @param lo  左边界
     * @param mid 中间
     * @param hi  右边界
     */
    private static <K extends Comparable<K>> void merge(K[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        // 将a[lo...hi]复制到aux[lo...hi]
        if (hi + 1 - lo >= 0) {
            System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        }

        // 在这里由于Java不允许创建静态泛型, 所以采用了强制类型转换: 父类 -> 子类
        for (int k = lo; k <= hi; k++) {
            // 左半边用尽
            if (i > mid) a[k] = (K) aux[j++];
                // 右半边用尽
            else if (j > hi) a[k] = (K) aux[i++];
                // 右半边小于左半边
            else if (less(aux[j], aux[i])) a[k] = (K) aux[j++];
                // 右半边大于等于左半边
            else a[k] = (K) aux[i++];
        }
    }

    /**
     * 不使用辅助数组进行复制归并(但是仍需要空间)
     *
     * @param src 源数组
     * @param dst 目标数组
     * @param lo  归并左边界
     * @param hi  归并右边界
     */
    private static <K extends Comparable<K>> void merge(K[] src, K[] dst, int lo, int mid, int hi) {
//        assert isSorted(src, lo, mid);
//        assert isSorted(src, mid + 1, hi);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) dst[k] = src[j++];
            else if (j > hi) dst[k] = src[i++];
                // 保证稳定性
            else if (less(src[j], src[i])) dst[k] = src[j++];
            else dst[k] = src[i++];
        }

//         PostCondition: dst[lo .. hi] is sorted subarray
//        assert isSorted(dst, lo, hi);
    }

    /**
     * 使用插排排序a[lo...hi]子区间
     *
     * @param a  排序数组
     * @param lo 排序左边界
     * @param hi 排序右边界
     */
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

}
