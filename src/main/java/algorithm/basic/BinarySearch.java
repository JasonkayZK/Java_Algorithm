package algorithm.basic;

import algorithm.util.iostream.In;
import algorithm.util.iostream.StdIn;
import algorithm.util.iostream.StdOut;

import java.util.Arrays;

// java BinarySearch tinyW.txt < tinyT.txt
public class BinarySearch {

    public static void main(String[] args) {
        // read the integers from a file
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        // read integer key from standard input; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.rank(key, whitelist) == -1)
                StdOut.println(key);
        }
    }

    public static int rank(int key, int[] a) {
        return indexOf(key, a);
    }

    public static int indexOf(int key, int[] a) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

}
