import java.util.Arrays;

/**
 * Defines a library of selection methods
 * on arrays of ints.
 *
 * @author Owen Strength (ods0005@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 1/23/2023
 *
 */
public final class Selector {

    /**
     * Can't instantiate this class.
     *
     * D O N O T C H A N G E T H I S C O N S T R U C T O R
     *
     */
    private Selector() {
    }

    /**
     * Selects the minimum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int min(int[] a) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException();
        }
        int currMin = a[0];
        for (int num : a) {
            if (num < currMin) {
                currMin = num;
            }
        }
        return currMin;
    }

    /**
     * Selects the maximum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int max(int[] a) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException();
        }
        int currMax = a[0];
        for (int num : a) {
            if (num > currMax) {
                currMax = num;
            }
        }
        return currMax;
    }

    /**
     * Removes all duplicate values
     */
    public static int removeDuplicates(int a[]) {
        int n = a.length;
        int j = 0;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] != a[i + 1]) {
                a[j++] = a[i];
            }
        }

        a[j++] = a[n - 1];

        return j;
    }

    /**
     * Selects the kth minimum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth minimum value. Note that there is no kth
     * minimum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmin(int[] a, int k) {
        if (a == null || a.length == 0 || k < 1) {
            throw new IllegalArgumentException();
        }
        int[] c = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        Arrays.sort(c);
        int newLength = removeDuplicates(c);
        int[] b = new int[newLength];
        for (int i = 0; i < newLength; i++) {
            b[i] = c[i];
        }
        if (k > newLength) {
            throw new IllegalArgumentException();
        }
        return b[k - 1];
    }

    /**
     * Selects the kth maximum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth maximum value. Note that there is no kth
     * maximum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmax(int[] a, int k) {
        if (a == null || a.length == 0 || k < 1) {
            throw new IllegalArgumentException();
        }
        int[] c = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        Arrays.sort(c);
        int newLength = removeDuplicates(c);
        int[] b = new int[newLength];
        for (int i = 0; i < newLength; i++) {
            b[i] = c[i];
        }
        if (k > newLength) {
            throw new IllegalArgumentException();
        }
        return b[b.length - k];
    }

    /**
     * Returns an array containing all the values in a in the
     * range [low..high]; that is, all the values that are greater
     * than or equal to low and less than or equal to high,
     * including duplicate values. The length of the returned array
     * is the same as the number of values in the range [low..high].
     * If there are no qualifying values, this method returns a
     * zero-length array. Note that low and high do not have
     * to be actual values in a. This method throws an
     * IllegalArgumentException if a is null or has zero length.
     * The array a is not changed by this method.
     */
    public static int[] range(int[] a, int low, int high) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException();
        }
        int i = 0;
        int[] res = new int[a.length];
        for (int num : a) {
            if (num >= low && num <= high) {
                res[i] = num;
                i++;
            }
        }
        int[] finalRes = new int[i];
        i = 0;
        for (int num : res) {
            if (num != 0) {
                finalRes[i] = num;
                i++;
            }
        }
        return finalRes;

    }

    /**
     * Returns the smallest value in a that is greater than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int ceiling(int[] a, int key) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException();
        }
        int currMin = 100000;
        for (int num : a) {
            if (num < currMin && num >= key) {
                currMin = num;
            }
        }
        if (currMin == 100000) {
            throw new IllegalArgumentException();
        }
        return currMin;
    }

    /**
     * Returns the largest value in a that is less than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int floor(int[] a, int key) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException();
        }
        int currMax = -1000;
        for (int num : a) {
            if (num > currMax && num <= key) {
                currMax = num;
            }
        }
        if (currMax == -1000) {
            throw new IllegalArgumentException();
        }
        return currMax;
    }

}
