package algorithm.sort;

/**
 * 插入排序
 * <p>
 * 类似于将手中的扑克牌排序: 每次将一张牌插入到其他已经有序的牌中适当的位置
 * <p>
 * 适用于小数组、已经近似有序的数组或者链表排序
 * <p>
 * 平均时间: O(N^2) N^2/4比较 + N^2/4交换
 * <p>
 * 最坏时间: O(N^2) N^2/2比较 + N^2/2交换
 * <p>
 * 最好时间: O(N) N-1次比较 + 0次交换
 * <p>
 * 空间: O(1)
 *
 * @author zk
 */
public class InsertionSort extends BaseSort {

    /**
     * 最基本的插入排序
     */
    public static <K extends Comparable<K>> void sort(K[] a) {
        // i可以从1开始, 当i = 0时, 内循环是没有意义的
        for (int i = 1, len = a.length; i < len; ++i) {
            // 将a[j]插入到a[j - 1], a[j - 2], ..., a[0]中
            // 仅当a[j] < a[j - 1]时交换
            // 否则因为前面是有序的, 所以一定有a[j] >= a[j - 1]而不需要再交换
            for (int j = i; j > 0 && less(a[j], a[j - 1]); --j) {
                exch(a, j, j - 1);
            }
//            show(a);
        }
    }

    /**
     * 由于最基本的插入排序中, 只要存在逆序就要交换
     * <p>
     * 可以将交换变为覆盖:
     * <p>
     * 把当前待插入的元素取出，让当前元素与之前的所有元素进行一一比较
     * <p>
     * 前一个元素大于当前元素直接覆盖，而到了最后当找到当前元素的合适位置时只需要一次交换即可
     */
    public static <K extends Comparable<K>> void sortWithOverride(K[] a) {
        for (int i = 0, len = a.length; i < len; i++) {
            // 将当前位置的元素取出
            var cur = a[i];
            int j = i;
            // 如果这个元素比temp大就覆盖，否则就证明该元素之前已经有序就退出循环
            for (; j > 0 && less(cur, a[j - 1]); j--) {
                // 直接用前一个元素进行覆盖
                a[j] = a[j - 1];
            }
            // 将temp中的元素插入合适位置
            a[j] = cur;
        }
    }

    /**
     * 折半插入排序（Binary Insertion Sort）是对插入排序算法的一种改进
     * <p>
     * 排序算法过程，就是不断的依次将元素插入前面已排好序的序列中，在寻找插入点时采用了二分查找
     */
    public static <K extends Comparable<K>> void binaryInsertSort(K[] a) {
        for (int i = 1, len = a.length; i < len; i++) {
            // 将当前位置的元素取出(暂存)
            var temp = a[i];
            // 二分查找插入index
            int index = getInsertIndex(a, i, temp);

            // 覆盖式的插入
            int j = i - 1;
            for (; j >= index + 1; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
        }
    }

    /**
     * 二分法查找插入位置
     *
     * @param a     数组
     * @param end   结束区域
     * @param temp  寻找的键
     * @return 插入index
     */
    private static <K extends Comparable<K>> int getInsertIndex(K[] a, int end, K temp) {
        int low = 0, high = end - 1;
        while (low <= high) {
            var m = low + (high - low) / 2;
            if (less(temp, a[m])) high = m - 1;
            else low = m + 1;
        }
        return high;
    }

    /**
     * 二路插入排序算法:
     * <p>
     * 是在折半插入的基础上进行改进
     * <p>
     * 折半插入在原先直接插入的基础上改进，通过折半查找，以较少的比较次数就找到了要插入的位置
     * <p>
     * 但是在插入的过程中仍然没有减少移动次数，所以2路插入在此基础上改进，减少了移动次数，但是仍然并没有避免移动记录（如果要避免的话还是得改变存储结构）
     * <p>
     * 因此我们设定一个辅助数组b，大小是原来数组相同的大小
     * <p>
     * 将b[0]设为第一个原数组第一个数，通过设置head和tail指向整个有序序列的最小值和最大值
     * <p>
     * 即为序列的尾部和头部，并且将其设置位一个循环数组，这样就可以进行双端插入
     * <p>
     * (之所以能减少移动次数的原因在于可以往2个方向移动记录，故称为2路插入)
     * <p>
     * 具体操作思路：
     * 1.将原数组第一个元素赋值给b[0],作为标志元素
     * 2.按顺序依次插入剩下的原数组的元素
     * - 1.将带插入元素与第一个进行比较，偌大于b[0],则插入b[0]前面的有序序列，否则插入后面的有序序列
     * - 2.对前面的有序序列或后面的有序序列进行折半查找
     * - 3.查找到插入位置后进行记录的移动，分别往head方向前移和往tail方向移动
     * - 4.插入记录
     * 3.将排序好的b数组的数据从head到tail，按次序赋值回原数组
     */
    @SuppressWarnings("unchecked")
    public static <K extends Comparable<K>> void twoPathInsertSort(K[] a) {
        int len = a.length;
        K[] b = (K[]) new Comparable[len];
        b[0] = a[0];

        // 分别记录temp数组中最大值和最小值的位置
        int i, first, tail, k;
        first = tail = 0;

        for (i = 1; i < len; i++) {
            // 待插入元素比最小的元素小
            if (less(a[i], b[first])) {
                first = (first - 1 + len) % len;
                b[first] = a[i];
            }
            // 待插入元素比最大元素大
            else if (less(b[tail], a[i])) {
                tail = (tail + 1 + len) % len;
                b[tail] = a[i];
            }
            // 插入元素比最小大，比最大小
            else {
                k = (tail + 1 + len) % len;
                // 当插入值比当前值小时，需要移动当前值的位置
                while (less(a[i], b[((k - 1) + len) % len])) {
                    b[(k + len) % len] = b[(k - 1 + len) % len];
                    k = (k - 1 + len) % len;
                }
                // 插入该值
                b[(k + len) % len] = a[i];
                // 因为最大值的位置改变，所以需要实时更新tail的位置
                tail = (tail + 1 + len) % len;
            }
        }
        // 将排序记录复制到原来的顺序表里
        for (k = 0; k < len; k++) {
            a[k] = b[(first + k) % len];
        }
    }

    /**
     * 希尔排序法又叫“缩小增量排序法”, 是对直接插入排序法的优化:
     * <p>
     * 通过设置一个增量，对原始序列进行分组，对每组用直接插入排序法排序再整合，再缩小增量，周而复始直至增量为1，完成排序
     * <p>
     * 其实到希尔算法进行到最后，n的值变为1（即增量或者称跳跃数变为1）的时候，它就是直接插入排序
     * <p>
     * 只不过这时候，整个序列基本上是有序的，需要交换的数据已经非常少了，提高效率
     */
    public static <K extends Comparable<K>> void shellSort(K[] a) {
        // See ShellSort
        ShellSort.sort(a);
    }

}
