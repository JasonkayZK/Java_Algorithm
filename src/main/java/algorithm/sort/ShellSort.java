package algorithm.sort;

/**
 * 希尔排序法又叫“缩小增量排序法”, 是对直接插入排序法的优化:
 *
 * 通过设置一个增量，对原始序列进行分组，对每组用直接插入排序法排序再整合，再缩小增量，周而复始直至增量为1，完成排序
 *
 * 其实到希尔算法进行到最后，n的值变为1（即增量或者称跳跃数变为1）的时候，它就是直接插入排序
 *
 * 只不过这时候，整个序列基本上是有序的，需要交换的数据已经非常少了，提高效率
 *
 * @author zk
 */
public class ShellSort extends BaseSort {

    /**
     * 构建希尔排序递增序列的步长系数
     */
    public static int step = 3;

    public static <K extends Comparable<K>> void sort(K[] a) {
        int h = 1, len = a.length;

        // 构建插排的步长h
        // 1, 4, 13, 40, 121, 364, 1093, ...
        while (h < len / step) h = step * h + 1;
        System.out.println(String.format("Array length: %s, Shell sort max step: %s", len, h));

        while (h >= 1) {
            // 根据h序列自: ... -> 121 -> 40 -> 13 -> 4 -> 1 进行插排
            for (int i = h; i < len; ++i) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= step;
        }
    }

}
