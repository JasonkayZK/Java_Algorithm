package algorithm.basic;

import java.util.concurrent.RecursiveTask;

/**
 * 通过ForkJoin并行计算斐波那契start~end的和
 *
 * @author zk
 */
public class MultiThreadFibonacci extends RecursiveTask<Long> {

    private int start;

    private int end;

    /**
     * 当计算10以下的斐波那契, 直接计算(避免创建过多调用栈, 进行优化)
     */
    private static final long THRESHOLD = 10L;

    public MultiThreadFibonacci(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int len = end - start;

        if (len <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i <= end; ++i) {
                sum += fibByFormula(i);
            }
            return sum;
        } else {
            int mid = start + (end - start) / 2;
            MultiThreadFibonacci left = new MultiThreadFibonacci(start, mid);
            left.fork();

            MultiThreadFibonacci right = new MultiThreadFibonacci(mid + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }

    /**
     * 通过公式计算fib(n)
     *
     * @param n 计算fib(n)
     * @return fib(n)
     */
    private static long fibByFormula(int n) {
        return (long) ((Math.pow((1 + Math.sqrt(5)) / 2, n) - Math.pow((1 - Math.sqrt(5)) / 2, n)) / Math.sqrt(5));
    }

}
