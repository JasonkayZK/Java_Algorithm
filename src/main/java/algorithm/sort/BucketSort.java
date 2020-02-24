package algorithm.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 桶排序（Bucket Sort）
 * <p>
 * 桶排序是计数排序的升级版
 * <p>
 * 它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定
 * <p>
 * 工作原理:
 * <p>
 * - 假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序
 * （有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排）
 * <p>
 * 算法过程:
 * <p>
 * - 1.设置一个定量的数组当作空桶；
 * - 2.遍历输入数据，并且把数据一个一个放到对应的桶里去；
 * - 3.对每个不是空的桶进行排序；
 * - 4.从不是空的桶里把排好序的数据拼接起来
 * <p>
 * 评价:
 * <p>
 * - 桶排序最好情况下使用线性时间O(n)
 * <p>
 * - 桶排序的时间复杂度取决于对各个桶之间数据进行排序的时间复杂度, 因为其它部分的时间复杂度都为O(n)
 * <p>
 * - 很显然，桶划分的越小，各个桶之间的数据越少，排序所用的时间也会越少。但相应的空间消耗就会增大
 * <p>
 * 最坏时间: O(N^2) 所有的数据都放入了一个桶内，桶内自排序算法为插入排序
 * <p>
 * 最好时间: O(N) 桶的数量越多，理论上分到每个桶中的元素就越少，桶内数据的排序就越简单，其时间复杂度就越接近于线性
 * <p>
 * 极端情况下，区间小到只有1，即桶内只存放一种元素
 * <p>
 * 此时桶内的元素不再需要排序，因为它们都是相同的元素，这时桶排序差不多就和计数排序一样了
 *
 * @author zk
 */
public class BucketSort extends BaseSort {

    /**
     * 设置桶的默认数量为5
     */
    private static final int DEFAULT_BUCKET_SIZE = 5;

    /**
     * 这里仅仅作为演示, 排序要求:
     * <p>
     * 输入的桶大小bucketSize, 应当大于待排序浮点数的整数部分, 且浮点数均大于零才行!
     * <p>
     * (因为在getBucketIndex方法中仅仅取了浮点数的整数部分作为桶的index)
     *
     * @param arr        待排序数组
     * @param bucketSize 桶大小(在本例中为浮点数整数部分最大值+1)
     */
    public static void sort(double[] arr, int bucketSize) {
        // 新建一个桶的集合
        ArrayList<LinkedList<Double>> buckets = new ArrayList<>();

        bucketSize = Math.max(bucketSize, DEFAULT_BUCKET_SIZE);

        for (int i = 0; i < bucketSize; i++) {
            // 新建一个桶，并将其添加到桶的集合中去
            // 由于桶内元素会频繁的插入，所以选择 LinkedList作为桶的数据结构
            buckets.add(new LinkedList<>());
        }

        // 将输入数据全部放入桶中并完成排序
        for (double data : arr) {
            int index = getBucketIndex(data);
            insertSort(buckets.get(index), data);
        }

        // 将桶中元素全部取出来并放入arr中输出
        int index = 0;
        for (LinkedList<Double> bucket : buckets) {
            for (Double data : bucket) {
                arr[index++] = data;
            }
        }
    }

    /**
     * 计算得到输入元素应该放到哪个桶内
     */
    private static int getBucketIndex(double data) {
        // 这里例子写的比较简单，仅使用浮点数的整数部分作为其桶的索引值
        // 实际开发中需要根据场景具体设计
        return (int) data;
    }

    /**
     * 我们选择插入排序作为桶内元素排序的方法
     * <p>
     * 每当有一个新元素到来时，我们都调用该方法将其插入到恰当的位置
     */
    private static void insertSort(List<Double> bucket, double data) {
        ListIterator<Double> it = bucket.listIterator();
        boolean insertFlag = true;
        while (it.hasNext()) {
            if (data <= it.next()) {
                // 把迭代器的位置偏移回上一个位置
                it.previous();
                // 把数据插入到迭代器的当前位置
                it.add(data);
                insertFlag = false;
                break;
            }
        }
        if (insertFlag) {
            // 否则把数据插入到链表末端
            bucket.add(data);
        }
    }

}
