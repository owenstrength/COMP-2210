/**
 * Implements shift-right behavior in an array.
 *
 */

public class ShiftRight {

    /**
     * Shifts the elements at a[index] through a[a.length - 2] one
     * position to the right.
     */
    public static void shiftRight(Object[] array, int index) {

        int j = 0;
        Object[] temp = array.clone();
        for (int i = 0; i < array.length - 2; i++) {
            if (i == index) {
                j = 1;
            } else {

                if (j == 1 && array[index] != null) {
                    array[index] = null;
                }
                array[i] = temp[i - j];
            }
        }
        System.out.println(temp[1]);
        System.out.println(array[1]);

    }

}
