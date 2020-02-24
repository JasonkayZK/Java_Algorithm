package algorithm.sort;

/**
 * 冒泡排序
 * <p>
 * 每一轮遍历将最大的元素放在当前排序序列末尾, 并且当前数组个数从末端减一
 * <p>
 * 相比于选择排序, 冒泡排序使用了更多的交换!
 * <p>
 * 平均时间: O(N^2)
 * <p>
 * 最好时间: O(N^2)
 * <p>
 * 最坏时间: O(N^2)
 * <p>
 * 空间: O(1)
 *
 * @author zk
 */
public class BubbleSort extends BaseSort {

    /**
     * 冒泡排序最基本实现
     */
    public static <K extends Comparable<K>> void sort(K[] a) {
        // 确定排序趟数
        // 最后一次循环仅有一个元素, 所以可以i < len - 1
        for (int i = 0, len = a.length; i < len - 1; i++) {
            // 确定每趟比较次数
            for (int j = 0; j < len - i - 1; j++) {
                if (less(a[j + 1], a[j])) {
                    exch(a, j, j + 1);
                }
            }
//            show(a);
        }
    }

    /**
     * 在交换的地方加一个标记，如果那一趟排序没有交换元素，说明这组数据已经有序，不用再继续下去
     * <p>
     * (这里用到了冒泡每次都要交换的特点, 而选择排序仅仅交换一次!)
     */
    public static <K extends Comparable<K>> void sortWithFlag(K[] a) {
        for (int i = 0, len = a.length; i < len - 1; i++) {
            boolean finished = true;
            for (int j = 0; j < len - 1 - i; j++) {
                if (less(a[j + 1], a[j])) {
                    exch(a, j, j + 1);
                    finished = false;
                }
            }
            if (finished) {
                return;
            }
        }
    }

    /**
     * 对于前面大部分是无序而后边小半部分有序的数据(1，2，5，7，4，3，6，8，9)排序效率也不可观, 对于这种类型数据，我们可以继续优化
     * <p>
     * 可以记下最后一次交换的位置，后边没有交换，必然是有序的
     * <p>
     * 然后下一次排序从第一个比较到上次记录的位置结束即可
     */
    public static <K extends Comparable<K>> void sortWithCheckBound(K[] a) {
        // k作为最后一次交换的位置循环边界
        int len = a.length;
        // pos用来记录最后一次交换的位置
        // k作为循环终止条件(因为需要在循环内部改动pos!)
        int pos = 0, k = len - 1;
        for (int i = 0; i < len - 1; i++) {
            boolean finished = true;
            for (int j = 0; j < k; j++) {
                if (less(a[j + 1], a[j])) {
                    exch(a, j, j + 1);
                    finished = false;
                    // 交换元素，记录最后一次交换的位置
                    pos = j;
                }
            }
            if (finished) break;
            // 下一次比较到记录位置即可
            k = pos;
        }
    }

    /**
     * 一次排序可以确定两个值，正向扫描找到最大值交换到最后，反向扫描找到最小值交换到最前面
     * <p>
     * 同时, 可以记录左右两边均有序时的bound
     * <p>
     * (类似于选择排序)
     */
    public static <K extends Comparable<K>> void sortBilaterally(K[] a) {
        // k作为最后一次交换的位置循环边界
        int len = a.length;

        // leftBound, rightBound记录左右冒泡最后一次交换的位置
        // leftVar, rightVar作为循环终止条件(因为需要在循环内部改动pos)
        int leftBound = 0, leftVar = 0, rightBound = len - 1, rightVar = len - 1;

        // 同时找最大值的最小需要两个下标遍历
        int n = 0;

        for (int i = 0; i < len - 1; i++) {
            boolean finished = true;
            // 正向寻找最大值
            for (int j = leftVar; j < rightVar; j++) {
                if (less(a[j + 1], a[j])) {
                    exch(a, j, j + 1);
                    // 加入标记
                    finished = false;
                    // 交换元素，记录正向冒泡最后一次交换的位置
                    rightBound = j;
                }
            }
            // 如果没有交换过元素，则已经有序,直接结束
            if (finished) return;
            // 下一次正向冒泡比较到记录位置即可
            rightVar = rightBound;

            // 反向寻找最小值
            for (int j = rightVar; j > leftVar; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                    finished = false;
                    leftBound = j;
                }
            }
            leftVar = leftBound;
        }
    }
}
