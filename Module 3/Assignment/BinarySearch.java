import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        Arrays.sort(a);
        int low = 0;
        int high = a.length - 1;
        int res = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (comparator.compare(a[mid], key) > 0) {
                high = mid - 1;
            } else if (comparator.compare(a[mid], key) < 0) {
                low = mid + 1;
            } else {
                res = mid;
                high = mid - 1;
            }
        }
        return res;
    }

    /**
     * Returns the index of the last key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        Arrays.sort(a);
        int low = 0;
        int high = a.length - 1;
        int res = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (comparator.compare(a[mid], key) > 0) {
                high = mid - 1;
            } else if (comparator.compare(a[mid], key) < 0) {
                low = mid + 1;
            } else {
                res = mid;
                low = mid + 1;
            }
        }
        return res;
    }

}
