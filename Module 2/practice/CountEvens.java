/**
 * Count the number of even values in an array.
 *
 */
public class CountEvens {

    // C O M P L E T E T H I S M E T H O D

    /**
     * Returns the number of even values in the paramter.
     */
    public static int countEvens(int[] values) {
        int cnt = 0;
        for (int num : values) {
            if (num % 2 == 0) {
                cnt++;
            }
        }
        return cnt;
    }

}
