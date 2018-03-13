package cs3500;

import org.junit.Test;

import cs3500.animator.model.Position;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Position enum. Tests to make sure that retrieving the string representation of a
 * position produces the outcome as expected.
 */

public class PositionTest {

  @Test
  public void testGetMess() {
    assertEquals("Lower-left corner", Position.LL.getMess());
    assertEquals("Lower-right corner", Position.LR.getMess());
    assertEquals("Upper-left corner", Position.UL.getMess());
    assertEquals("Upper-right corner", Position.UR.getMess());
    assertEquals("Center", Position.C.getMess());
  }


}
