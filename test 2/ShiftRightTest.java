import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

public class ShiftRightTest {

    @Test
    public void shiftTest() {
        Object[] a = new Object[10];
        Object[] b = new Object[10];
        a[0] = 10;
        a[1] = 14;
        a[2] = 26;
        a[3] = 32;
        b[0] = 10;
        b[2] = 14;
        b[3] = 26;
        b[4] = 32;
        ShiftRight.shiftRight(a, 1);
        Assert.assertArrayEquals(b, a);
    }
}