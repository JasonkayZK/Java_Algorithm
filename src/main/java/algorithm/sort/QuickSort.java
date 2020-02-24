package algorithm.sort;

import algorithm.util.random.StdRandom;

/**
 * 快速排序
 * <p>
 * - 选定一个轴(为了便于处理, 一般选择子数组的首个元素)
 * <p>
 * - 然后基于切分算法: 将数组a切分为a[lo...j-1] < a[j] < a[j+1...hi]
 * <p>
 * - 递归地在左右子数组进行上述步骤
 * <p>
 * 平均时间: O(2NlnN ≈ 1.39NlgN) 平均需要2NlnN次比较
 * <p>
 * 最好时间: O(NlgN) 每次选中中位数进行对半切分
 * <p>
 * 最坏时间: O(N^2/2) 当数组已经是一个有序数组时, 每次切分一个元素, 需要N^2/2次比较
 * <p>
 * 空间: O(1)
 * <p>
 * 为了避免最差情况, 我们需要合理选择轴元素, 方法有:
 * <p>
 * 1.排序前将数组重排: 如使用: StdRandom.shuffle(a);
 * <p>
 * 2.选择合适的key(轴元素), 如使用left, mid, right的中位数
 * (为了保证排序的统一性, 可以将中位数和最左侧left元素交换, 从而保证排序算法不变)
 *
 * @author zk
 */
public class QuickSort extends BaseSort {

    /**
     * 子数组切换为插排的阈值
     */
    private static final int INSERTION_SORT_THRESHOLD = 8;

    private QuickSort() {
    }

    public static <K extends Comparable<K>> void sort(K[] a) {
        // 重排数组a, 消除对输入的依赖
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static <K extends Comparable<K>> void sort2(K[] a) {
        StdRandom.shuffle(a);
        sort2(a, 0, a.length - 1);
    }

    public static <K extends Comparable<K>> void advancedSort(K[] a) {
        advancedSort(a, 0, a.length - 1);
    }

    public static <K extends Comparable<K>> void advancedSort2(K[] a) {
        advancedSort2(a, 0, a.length - 1);
    }

    public static <K extends Comparable<K>> void threeWaySort(K[] a) {
        threeWaySort(a, 0, a.length - 1);
    }

    /**
     * 最基本的快速排序实现
     *
     * @param a  待排序子数组
     * @param lo 数组左边界
     * @param hi 数组右边界
     */
    private static <K extends Comparable<K>> void sort(K[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);

        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    /**
     * 切分(交换法): 将数组a切分为a[lo...j-1] < a[j] < a[j+1...hi]
     * <p>
     * 快速排序的切分算法(快排核心)
     *
     * @param a  待排序数组
     * @param lo 子数组左边界
     * @param hi 子数组右边界
     * @return 切分后的标准元素key的index
     */
    private static <K extends Comparable<K>> int partition(K[] a, int lo, int hi) {
        // 将数组切分为a[lo...j-1], a[j], a[j+1...hi]
        int i = lo, j = hi + 1;

        // 选定开头为标准元素
        K key = a[lo];

        while (true) {
            // 左右移动, 跳过已经有序的位置
            while (less(a[++i], key)) if (i == hi) break;
            while (less(key, a[--j])) if (j == lo) break;

            // 切分完毕, 跳过交换
            if (i >= j) break;

            // 找到乱序的, 交换
            exch(a, i, j);
        }

        // 将key放入对应位置
        exch(a, lo, j);
        return j;
    }

    /**
     * 基本快排的另一种实现方法
     * <p>
     * (将partition方法inline, 并使用挖坑法代替交换!)
     *
     * @param a  待排序数组
     * @param lo 子数组左边界
     * @param hi 子数组右边界
     */
    private static <K extends Comparable<K>> void sort2(K[] a, int lo, int hi) {
        if (a.length <= 1 || lo >= hi) return;

        int left = lo, right = hi;

        // 选定数组第一个数字作为key
        var key = a[left];

        while (left < right) {
            // 从右向左遍历,找到小于key的,放入下标left中
            // !less(a[right], key) <=> !(a[right] < key) <=> a[right] >= key
            // 相等也要移动
            while (left < right && !less(a[right], key)) right--;
            a[left] = a[right];

            // 从左向右遍历,找到大于key的,放入下标right中
            // !less(key, a[left]) <=> !(key < a[left]) <=> a[left] <= key
            // 相等也要移动
            while (left < right && !less(key, a[left])) left++;
            a[right] = a[left];
        }

        // 此时left == right, 这就是所谓的轴
        // 把key放入轴中，轴左边的都<key,轴右边的都>key
        a[left] = key;

        // 此时轴在数组中间，说明把数组分成两部分，此时要对这两部分分别进行快排
        sort2(a, lo, left - 1);
        sort2(a, left + 1, hi);
    }

    /**
     * 针对基本快排进行优化的排序算法
     * <p>
     * 优化包括:
     * - 1.小数组切换到插入排序: 通常THRESHOLD选择5~15均可
     * <p>
     * - 2.三取样切分, 选择子数组一小部分的中位数来切分
     *
     * @param a  待排序数组
     * @param lo 排序左边界
     * @param hi 排序右边界
     */
    private static <K extends Comparable<K>> void advancedSort(K[] a, int lo, int hi) {
        if (hi <= lo) return;

        // 子数组长度小于阈值, 使用插排
        int n = hi - lo + 1;
        if (n <= INSERTION_SORT_THRESHOLD) {
            insertionSort(a, lo, hi);
            return;
        }

        int j = advancedPartition(a, lo, hi);
        advancedSort(a, lo, j - 1);
        advancedSort(a, j + 1, hi);
    }

    /**
     * 优化的切分算法, 选择[lo, (lo+hi)/2 , hi]三者中的中位数作为轴
     *
     * @param a  待切分数组
     * @param lo 切分左边界
     * @param hi 切分右边界
     * @return 轴元素
     */
    private static <K extends Comparable<K>> int advancedPartition(K[] a, int lo, int hi) {
        // 选择[lo, (lo+hi)/2 , hi]三者中的中位数作为轴
        int n = hi - lo + 1, m = median3(a, lo, lo + n / 2, hi);
        exch(a, m, lo);

        int i = lo, j = hi + 1;
        var key = a[lo];

        // 若a[lo]是唯一最大元素
        // 将a[lo]与a[hi]交换, 此时即: a[lo...hi-1] < a[hi]
        while (less(a[++i], key)) {
            if (i == hi) {
                exch(a, lo, hi);
                return hi;
            }
        }

        // 若a[lo]是唯一最小元素
        // 此时不需要动, 直接返回lo的index, 因为a[lo] < a[lo+1...hi]
        while (less(key, a[--j])) {
            if (j == lo + 1) return lo;
        }

        // 其他情况
        while (i < j) {
            exch(a, i, j);
            while (less(a[++i], key)) ;
            while (less(key, a[--j])) ;
        }

        // 将轴key放到指定位置
        exch(a, lo, j);

        // 此时, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    /**
     * 寻找数组a中三个元素的中位数, 返回index
     *
     * @return 中位数元素index
     */
    private static <K extends Comparable<K>> int median3(K[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
                (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
                (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
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

    /**
     * 针对基本快排进行优化的排序算法的另一种实现(挖坑法)
     * <p>
     * 将advancedPartition进行inline操作
     *
     * @param a  待排序数组
     * @param lo 排序左边界
     * @param hi 排序右边界
     */
    private static <K extends Comparable<K>> void advancedSort2(K[] a, int lo, int hi) {
        if (hi <= lo) return;

        // 子数组长度小于阈值, 使用插排
        int n = hi - lo + 1;
        if (n <= INSERTION_SORT_THRESHOLD) {
            insertionSort(a, lo, hi);
            return;
        }

        // 选择[lo, (lo+hi)/2 , hi]三者中的中位数作为轴
        int m = median3(a, lo, lo + (hi - lo + 1) / 2, hi);
        exch(a, m, lo);

        int left = lo, right = hi;
        var key = a[left];

        while (left < right) {
            // 从右向左遍历,找到小于key的,放入下标left中
            // !less(a[right], key) <=> !(a[right] < key) <=> a[right] >= key
            // 相等也要移动
            while (left < right && !less(a[right], key)) right--;
            a[left] = a[right];

            // 从左向右遍历,找到大于key的,放入下标right中
            // !less(key, a[left]) <=> !(key < a[left]) <=> a[left] <= key
            // 相等也要移动
            while (left < right && !less(key, a[left])) left++;
            a[right] = a[left];
        }

        // 此时left == right, 这就是所谓的轴
        // 把key放入轴中，轴左边的都<key,轴右边的都>key
        a[left] = key;

        // 此时轴在数组中间，说明把数组分成两部分，此时要对这两部分分别进行快排
        advancedSort2(a, lo, left - 1);
        advancedSort2(a, left + 1, hi);
    }

    /**
     * 三向切分的快排(信息量最优的快速排序): 适用于大量重复元素的排序
     *
     * 从左至右遍历数组一次:
     *
     * - 指针lt使得a[lo...lt-1]中的元素都小于key
     *
     * - 指针gt使得a[gt+1...hi]中的元素都大于key
     *
     * - 指针i使得a[lt...i-1]中的元素都等于key
     *
     * - 而a[i...gt]中的元素为未确定
     *
     * 处理时一开始i和lo相等, 进行三向比较:
     *
     * - a[i] < key: 将a[lt]和a[i]交换, 将lt和i加一;
     * - a[i] > key: 将a[gt]和a[i]交换, 将gt减一;
     * - a[i] = key: 将i加一;
     *
     * 上述操作均会保证数组元素不变并且缩小gt-i的值(这样循环才会结束)
     *
     * @param a  待排序数组
     * @param lo 排序左边界
     * @param hi 排序右边界
     */
    private static <K extends Comparable<K>> void threeWaySort(K[] a, int lo, int hi) {
        if (hi <= lo) return;

        // 子数组长度小于阈值, 使用插排
        int n = hi - lo + 1;
        if (n <= INSERTION_SORT_THRESHOLD) {
            insertionSort(a, lo, hi);
            return;
        }

        // 选择[lo, (lo+hi)/2 , hi]三者中的中位数作为轴
        int m = median3(a, lo, lo + (hi - lo + 1) / 2, hi);
        exch(a, m, lo);

        int lt = lo, gt = hi;
        var key = a[lt];

        int i = lo + 1;

        while (i <= gt) {
            int cmp = a[i].compareTo(key);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else ++i;
        }

        threeWaySort(a, lo, lt -1);
        threeWaySort(a, gt + 1, hi);
    }

}
