package algorithm.util.random;

import algorithm.util.iostream.In;
import top.jasonkayzk.jutil.RandomUtils;

import java.util.Arrays;

/**
 * 获得随机数组数据
 *
 * @author zk
 */
public class RandomArrayUtil {

    /**
     * 指定floor, ceil和数组长度, 生成随机int数组
     *
     * @param floor 数组中最小数
     * @param ceil 数组中最大数
     * @param len 数组长度
     * @return 数组
     */
    public static int[] getRandomIntArray(int floor, int ceil, int len) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = RandomUtils.getRandomInteger(floor, ceil);
        }
        return arr;
    }

    /**
     * 指定数组长度, 生成随机int数组
     *
     * @param len 数组长度
     * @return 随机数组
     */
    public static int[] getRandomIntArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = RandomUtils.getRandomInteger();
        }
        return arr;
    }

    /**
     * 指定floor, ceil和数组长度, 生成随机Integer数组
     *
     * @param floor 数组中最小数
     * @param ceil 数组中最大数
     * @param len 数组长度
     * @return 数组
     */
    public static Integer[] getRandomBoxedIntArray(int floor, int ceil, int len) {
        Integer[] arr = new Integer[len];
        for (int i = 0; i < len; i++) {
            arr[i] = RandomUtils.getRandomInteger(floor, ceil);
        }
        return arr;
    }

    /**
     * 指定数组长度, 生成随机Integer数组
     *
     * @param len 数组长度
     * @return 随机数组
     */
    public static Integer[] getRandomBoxedIntArray(int len) {
        Integer[] arr = new Integer[len];
        for (int i = 0; i < len; i++) {
            arr[i] = RandomUtils.getRandomInteger();
        }
        return arr;
    }

    /**
     * 指定floor, ceil和数组长度, 生成随机double数组
     *
     * @param floor 数组中最小数
     * @param ceil 数组中最大数
     * @param len 数组长度
     * @return 数组
     */
    public static double[] getRandomDoubleArray(int floor, int ceil, int len) {
        double[] arr = new double[len];
        for (int i = 0; i < len; i++) {
            arr[i] = RandomUtils.getRandomDouble(floor, ceil);
        }
        return arr;
    }

    /**
     * 指定数组长度, 生成随机double数组
     *
     * @param len 数组长度
     * @return 随机数组
     */
    public static double[] getRandomDoubleArray(int len) {
        double[] arr = new double[len];
        for (int i = 0; i < len; i++) {
            arr[i] = RandomUtils.getRandomDouble();
        }
        return arr;
    }

    /**
     * 指定floor, ceil和数组长度, 生成随机Double数组
     *
     * @param floor 数组中最小数
     * @param ceil 数组中最大数
     * @param len 数组长度
     * @return 数组
     */
    public static Double[] getRandomBoxedDoubleArray(int floor, int ceil, int len) {
        Double[] arr = new Double[len];
        for (int i = 0; i < len; i++) {
            arr[i] = RandomUtils.getRandomDouble(floor, ceil);
        }
        return arr;
    }

    /**
     * 指定数组长度, 生成随机Double数组
     *
     * @param len 数组长度
     * @return 随机数组
     */
    public static Double[] getRandomBoxedDoubleArray(int len) {
        Double[] arr = new Double[len];
        for (int i = 0; i < len; i++) {
            arr[i] = RandomUtils.getRandomDouble();
        }
        return arr;
    }

    /**
     * 指定生成count个长度为strLen的字符串
     *
     * @param strLen 每个随机字符串固定长度
     * @param count 数组元素个数
     * @return 随机字符串数组
     */
    public static String[] getRandomStringArray(int strLen, int count) {
        String[] res = new String[count];
        for (int i = 0; i < count; i++) {
            res[i] = RandomUtils.getRandomStringOnlyLetter(strLen);
        }
        return res;
    }

    /**
     * 生成指定随机长度的count个字符串数组
     *
     * @param count 元素个数
     * @return 随机字符串数组
     */
    public static String[] getRandomStringArray(int count) {
        String[] res = new String[count];
        for (int i = 0; i < count; i++) {
            // 字符串最大长度25
            res[i] = RandomUtils.getRandomStringOnlyLetter(RandomUtils.getRandomInteger(1, 25));
        }
        return res;
    }

    /**
     * 指定生成count个长度为strLen的仅含有小写字母的字符串
     *
     * @param strLen 每个随机字符串固定长度
     * @param count 数组元素个数
     * @return 随机字符串数组
     */
    public static String[] getRandomLowerCaseStringArray(int strLen, int count) {
        String[] res = new String[count];
        for (int i = 0; i < count; i++) {
            res[i] = RandomUtils.getRandomStringOnlyLowerCase(strLen);
        }
        return res;
    }

    /**
     * 生成指定随机长度的count个含有小写字母的字符串
     *
     * @param count 元素个数
     * @return 随机字符串数组
     */
    public static String[] getRandomLowerCaseStringArray(int count) {
        String[] res = new String[count];
        for (int i = 0; i < count; i++) {
            // 字符串最大长度25
            res[i] = RandomUtils.getRandomStringOnlyLowerCase(RandomUtils.getRandomInteger(1, 25));
        }
        return res;
    }

    public static void main(String[] args) {
        // int
        System.out.println(Arrays.toString(getRandomIntArray(5)));
        System.out.println(Arrays.toString(getRandomIntArray(10, 20, 5)));

        // double
        System.out.println(Arrays.toString(getRandomDoubleArray(5)));
        System.out.println(Arrays.toString(getRandomDoubleArray(10, 20, 5)));

        // string
        System.out.println(Arrays.toString(getRandomStringArray(5)));
        System.out.println(Arrays.toString(getRandomStringArray(5, 5)));
        System.out.println(Arrays.toString(getRandomLowerCaseStringArray(5)));
        System.out.println(Arrays.toString(getRandomLowerCaseStringArray(5, 5)));

    }
}
