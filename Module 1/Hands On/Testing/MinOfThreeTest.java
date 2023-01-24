import static org.junit.Assert.assertArrayEquals;

import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MinOfThreeTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   @Test public void min1Test() {
      int myMin = MinOfThree.min1(2,3,2);
      Assert.assertEquals(myMin, 2);
   }

   @Test public void min2Test() {
      int myMin = MinOfThree.min2(2,3,2);
      Assert.assertEquals(myMin, 2);
   }
}
