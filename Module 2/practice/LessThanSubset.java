import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Return the elements in a collection that are strictly less than a specified
 * value.
 *
 */
public class LessThanSubset {

    // C O M P L E T E T H I S M E T H O D

    /**
     * Returns the elements in collection strictly less than value.
     */
    public static <T extends Comparable<T>> Collection<T> lessThanSubset(Collection<T> collection, T value) {

        ArrayList<T> result = new ArrayList<T>(collection.size());
        Iterator<T> iter = collection.iterator();

        while (iter.hasNext()) {
            T curr = iter.next();
            if (curr.compareTo(value) < 0) {
                result.add(curr);
            }
        }
        result.trimToSize();
        return result;
    };
}
