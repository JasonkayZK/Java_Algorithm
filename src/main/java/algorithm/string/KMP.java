package algorithm.string;

import algorithm.util.iostream.StdOut;

/**
 * KMP进行字符串模式匹配
 *
 * @author zk
 */
public class KMP {

    /**
     * 模式匹配字符串
     */
    private String pat;

    /**
     * 有限状态自动机
     */
    private int[][] dfa;

    public KMP(String pat) {
        // 由模式字符串构造DFA
        this.pat = pat;

        // M为模式长度
        int M = pat.length();
        // R为字母表长度
        int R = 256;

        dfa = new int[R][M];

        // 初始化0, 0
        dfa[pat.charAt(0)][0] = 1;

        // X为回退记录值
        for (int X = 0, j = 1; j < M; ++j) {
            // 复制匹配失败时回退值
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            // 设置匹配成功的值
            dfa[pat.charAt(j)][j] = j + 1;

            // 更新X(回退记录值), 参考前面处理的回退
            X = dfa[pat.charAt(j)][X];
        }
    }

    /**
     * 通过pat模式, 匹配txt
     *
     * @param txt 待匹配文本
     *
     * @return 首次出现的index, 若匹配失败返回txt.length
     */
    public int search(String txt) {
        int i, j, N = txt.length(), M = pat.length();

        for (i = 0, j = 0; i < N && j < M; ++i) {
            j = dfa[txt.charAt(i)][j];
        }

        // 找到匹配(到达模式字符串的末尾)
        if (j == M) {
            return i - M;
        }
        // 未找到匹配(到达文本字符串的结尾)
        return N;
    }

    public static void main(String[] args) {
        String pat = args[0], txt = args[1];

        KMP kmp = new KMP(pat);
        StdOut.println("text:    " + txt);

        int offset = kmp.search(txt);

        StdOut.print("pattern: ");
        for (int i = 0; i < offset; ++i) {
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
