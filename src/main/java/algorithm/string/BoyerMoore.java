package algorithm.string;

import algorithm.util.iostream.StdOut;

import java.util.Arrays;

/**
 * Boyer-Moore最右匹配算法
 *
 *  Compilation:  javac BoyerMoore.java
 *  Execution:    java BoyerMoore pattern text
 *  Dependencies: StdOut.java
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using the
 *  bad-character rule part of the Boyer-Moore algorithm.
 *  (does not implement the strong good suffix rule)
 *
 *  Test Case:
 *
 *  % java BoyerMoore abracadabra abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:               abracadabra
 *
 *  % java BoyerMoore rab abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:         rab
 *
 *  % java BoyerMoore bcara abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                                   bcara
 *
 *  % java BoyerMoore rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java BoyerMoore abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 * @author zk
 */
public class BoyerMoore {

    /**
     * 字符集数量
     */
    private final int R;

    /**
     * 记录字符在pat中的最右位置
     */
    private int[] right;

    /**
     * 匹配模式
     */
    private String pat;

    /**
     * 初始化模式字符串
     *
     * @param pat 模式字符串
     * @param R 字符集数量
     */
    public BoyerMoore(String pat,int R) {
        this.R = R;
        this.pat = pat;
        right = new int[R];

        // 先初始化为-1, 然后计算最靠右出现的次数
        // 不包括在模式字符串中的字符的值为-1
        // 包括在模式字符串中的字符的值为它在模式字符串中出现的最右位置
        Arrays.fill(right, -1);
        for (int i = 0; i < pat.length(); i++) {
            right[pat.charAt(i)] = i;
        }
    }

    public BoyerMoore(String pat) {
        this(pat, 256);
    }

    public int search(String text) {
        int m = pat.length(), n = text.length();
        int skip;

        // i为文本指针
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            // 从模式字符串pat的末尾向前匹配
            for (int j = m - 1; j >= 0; j--) {
                // 出现不匹配
                if (pat.charAt(j) != text.charAt(i + j)) {
                    // 根据最右跳跃表计算文本指针i应当向前移动的数量
                    skip = Math.max(1, j - right[text.charAt(i + j)]);
                    break;
                }
            }
            // 找到匹配字符串
            if (skip == 0) {
                return i;
            }
        }
        // 未找到字符串
        return n;
    }

    public static void main(String[] args) {
        String pat = args[0], txt = args[1];

        BoyerMoore boyermoore = new BoyerMoore(pat);
        int offset = boyermoore.search(txt);

        // print results
        StdOut.println("text:    " + txt);

        StdOut.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
