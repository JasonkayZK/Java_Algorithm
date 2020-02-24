package algorithm.sort;

/**
 * 基数排序（Radix Sort）
 * <p>
 * 基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前
 * <p>
 * 算法步骤:
 * <p>
 * - 1.取得数组中的最大数，并取得位数；
 * - 2.arr为原始数组，从最低位开始取每个位组成radix数组；
 * - 3.对radix进行计数排序（利用计数排序适用于小范围数的特点）
 * <p>
 * 算法分析:
 * <p>
 * 基数排序分别排序，分别收集，所以是稳定的
 * <p>
 * 时间: 待排数据可以分为d个关键字，则基数排序的时间复杂度将是O(d*2n)
 * <p>
 * - 基数排序的性能比桶排序要略差，每一次关键字的桶分配都需要O(n)的时间复杂度
 * <p>
 * - 而且分配之后得到新的关键字序列又需要O(n)的时间复杂度
 * <p>
 * - 当然d要远远小于n，因此基本上还是线性级别的
 * <p>
 * 空间: 基数排序的空间复杂度为O(n+k)，其中k为桶的数量
 * (一般来说n>>k，因此额外空间需要大概n个左右)
 *
 * @author zk
 */
public class RadixSort extends BaseSort {

    private static final int[] RADIX_DICT = {1, 10, 100, 1000, 10000, 1000000, 1000000, 10000000, 100000000, 100000000};

    /**
     * 基数排序
     *
     * @param arr 要排序的数组
     * @param max 数组中最大的数有几位
     */
    public static void sort(int[] arr, int max) {
        // count数组用来计数
        int[] count = new int[arr.length];
        // bucket用来当桶
        int[] bucket = new int[arr.length];

        // k表示第几位，1代表个位，2代表十位，3代表百位, ...
        for (int k = 1; k <= max; k++) {
            // 把count置空，防止上次循环的数据影响
            for (int i = 0; i < arr.length; i++) {
                count[i] = 0;
            }

            // 分别统计第k位是0,1,2,3,4,5,6,7,8,9的数量
            // 此循环用来统计每个桶中的数据的数量
            for (int value : arr) {
                count[getFigure(value, k)]++;
            }

            // 利用count[i]来确定放置数据的位置
            for (int i = 1; i < arr.length; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 执行完此循环之后的count[i]就是第i个桶右边界的位置

            // 利用循环把数据装入各个桶中，注意是从后往前装
            // 这里是重点，一定要仔细理解
            for (int i = arr.length - 1; i >= 0; i--) {
                int j = getFigure(arr[i], k);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }

            // 将桶中的数据取出来，赋值给arr
            for (int i = 0, j = 0; i < arr.length; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    /**
     * 此函数返回整型数i的第k位是什么
     *
     * @param i 整数i
     * @param k 求i的第k位
     * @return 整型数i的第k位的值
     */
    private static int getFigure(int i, int k) {
        return (i / RADIX_DICT[k - 1]) % 10;
    }

}
