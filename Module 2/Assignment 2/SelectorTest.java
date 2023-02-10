import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SelectorTest {

    static Comparator<Integer> ascendingInteger = new Comparator<Integer>() {
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }
    };

    @Before
    public void setUp() {
    }

    @Test
    public void rangeTest() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(2);
        coll.add(8);
        coll.add(7);
        coll.add(3);
        coll.add(4);
        Comparator<Integer> comp = ascendingInteger;
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(2);
        res.add(3);
        res.add(4);
        assertEquals(res, Selector.range(coll, 1, 5, comp));

        ArrayList<Integer> coll2 = new ArrayList<Integer>();
        coll2.add(7);
        ArrayList<Integer> res2 = new ArrayList<Integer>();

        assertEquals(res2, Selector.range(coll2, 11, 11, comp));

    }

    @Test
    public void floorTest() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(-3);
        coll.add(3);
        coll.add(9);
        coll.add(7);
        coll.add(0);
        Comparator<Integer> comp = ascendingInteger;
        int res = Selector.floor(coll, 11, comp);
        assertEquals(9, res);

        ArrayList<Integer> coll2 = new ArrayList<Integer>();
        coll2.add(9);
        coll2.add(7);
        int res2 = Selector.floor(coll2, 7, comp);
        assertEquals(7, res2);

    }

    @Test
    public void ceilingTest() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(2);
        coll.add(2);
        coll.add(8);
        coll.add(7);
        coll.add(3);
        coll.add(4);
        Comparator<Integer> comp = ascendingInteger;
        int res = Selector.ceiling(coll, 1, comp);
        assertEquals(2, res);

    }

    @Test
    public void minTest() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(-3);
        coll.add(3);
        coll.add(9);
        coll.add(7);
        coll.add(0);
        Comparator<Integer> comp = ascendingInteger;
        int res = Selector.min(coll, comp);
        assertEquals(-3, res);

    }

    @Test
    public void kMinTest() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(1);
        coll.add(1);
        coll.add(5);
        coll.add(7);
        coll.add(9);
        Comparator<Integer> comp = ascendingInteger;
        int res = Selector.kmin(coll, 1, comp);
        assertEquals(1, res);

        int res2 = Selector.kmin(coll, 4, comp);
        assertEquals(9, res2);

        int res3 = Selector.kmin(coll, 2, comp);
        assertEquals(5, res3);

    }

    @Test
    public void kMaxTest() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(1);
        coll.add(1);
        coll.add(5);
        coll.add(7);
        coll.add(9);
        Comparator<Integer> comp = ascendingInteger;
        int res = Selector.kmax(coll, 1, comp);
        assertEquals(9, res);

        int res2 = Selector.kmax(coll, 4, comp);
        assertEquals(1, res2);

        int res3 = Selector.kmax(coll, 2, comp);
        assertEquals(7, res3);

    }
}
