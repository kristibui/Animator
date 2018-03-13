package cs3500;

import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.animator.model.ChangeColor;
import cs3500.animator.model.ChangeLocation;
import cs3500.animator.model.ChangeScale;
import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Position;
import cs3500.animator.model.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the IMove interface. These test various functions of the different types of moves that
 * can be performed to a shape, such as testing to make sure that a move can be properly created,
 * retrieving information from the move, and that the information in a particular move is as
 * expected.
 */

public class IMoveTest {
  Color colorForC = new Color(0, 0, 1);
  Color colorC1 = new Color(0, 100, 0);
  Color colorForR = new Color(1, 0, 0);
  Point2D dPForR = new Point2D.Double(200.0, 200.0);
  Point2D dPForC = new Point2D.Double(500.0, 100.0);
  Point2D dPRM1 = new Point2D.Double(300.0, 300.0);
  Point2D dPOM1 = new Point2D.Double(500.0, 400.0);
  IShape r1 = new Rectangle("R", Position.LL, dPForR, colorForR
          , 1, 100, 50.0, 100.0,1);
  IShape o1 = new Oval("C", Position.C, dPForC, colorForC, 6, 100, 60, 30,1);
  IMove rM1 = new ChangeLocation(r1, 10, 50, dPRM1);
  IMove oM1 = new ChangeLocation(o1, 20, 70, dPOM1);
  IMove rM2 = new ChangeLocation(r1, 70, 100, dPForR);
  IMove oC1 = new ChangeColor(o1, 20, 40, colorC1);
  IMove rS1 = new ChangeScale(r1, 51, 70, 25.0, 100.0);
  IMove rS2Overlap = new ChangeScale(r1, 30, 80, 50.0, 43.0);
  List<IMove> listOfM = new ArrayList<IMove>(Arrays.asList(rM1, oM1, rM2, rS1));

  // Testing change method by checking calling change on all three types of moves, then check
  // the output has the updated fields based on the given current time
  @Test
  public void testChange() {
    assertEquals(180.0, rM1.change(2).getLoc().getX(), 0);
    assertEquals(114.47368421052632, rS1.change(2).getWX(), 0);
    assertEquals("<animate attributeType=\"xml\" begin=\"5100.0ms\" dur=\"1900.0ms\" " +
                    "attributeName=\"width\" from=\"114.47368421052632\" to=\"25.0\" " +
                    "fill=\"freeze\"/>\n" +
                    "<animate attributeType=\"xml\" begin=\"5100.0ms\" dur=\"1900.0ms\" " +
                    "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\"/>",
            rS1.toSVG(false));
  }

  // Testing toString method by checking the output string corresponds with the correct string
  // representation of the move
  @Test
  public void testToString() {
    assertEquals("Shape R moves from (200.0,200.0) to (300.0,300.0) " +
            "from t=10.0s to t=50.0s", rM1.toString()); // location
    assertEquals("Shape R scales from Width: 50.0 Height: 100.0 to Width: 25.0 " +
            "Height: 100.0 from t=51.0s to t=70.0s", rS1.toString()); // scale
    assertEquals("Shape C changes color from (0.0,0.0,1.0) to (0.0,100.0,0.0) " +
            "from t=20.0s to t=40.0s", oC1.toString()); // color
  }

  // Testing getST method by checking the output matches the correct start time
  @Test
  public void testGetST() {
    assertEquals(10, rM1.getST(), 0); // location
    assertEquals(51, rS1.getST(), 0); // scale
    assertEquals(20, oC1.getST(), 0); // color
  }

  // Testing getET method by checking the output matches the correct end time
  @Test
  public void testGetET() {
    assertEquals(100, rM2.getET(), 0); // location
    assertEquals(80, rS2Overlap.getET(), 0); // scale
    assertEquals(40, oC1.getET(), 0);  // color
  }

  // Testing getTarget method by checking the output matches the correct shape
  // that the move is changing
  @Test
  public void testGetTarget() {
    assertEquals(r1, rM1.getTarget()); // location
    assertEquals(r1, rS1.getTarget()); // scale
    assertEquals(o1, oC1.getTarget()); // color
  }

  // Testing validMove method by checking that this move does not overlap with any other moves
  // in the given list
  @Test
  public void testValidMove() {
    assertEquals(true, oC1.validMove(listOfM));
    assertEquals(false, rS2Overlap.validMove(listOfM));
    assertEquals(false, rM1.validMove(listOfM));
  }

  // Testing overlapT method by checking if two moves have overlaping times
  @Test
  public void testOverlapT() {
    assertEquals(true, rS1.overlapT(rS2Overlap));
    assertEquals(true, oC1.overlapT(oC1));
    assertEquals(false, rM1.overlapT(rM2));
  }

  // Testing getType method by checking the output matches the correct move type of this move
  @Test
  public void testGetType() {
    assertEquals("Change Location", oM1.getType()); // location
    assertEquals("Change Scale", rS1.getType()); // scale
    assertEquals("Change Color", oC1.getType()); // color
  }

  // Testing toSVG method by checking the output matches the correct svg string representation
  // of the move
  @Test
  public void testToSVG() {
    assertEquals("<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
            + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\"/>\n<animate "
            + "attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" attributeName=\"y\" "
            + "from=\"200.0\" to=\"300.0\" fill=\"freeze\"/>", rM1.toSVG(false));
    assertEquals("<animate attributeName=\"fill\" begin=\"2000.0ms\" dur=\"2000.0ms\" "
            + "values=\"rgb(0,0,1);rgb(0,100,0)\" fill=\"freeze\"/>", oC1.toSVG(false));
    assertEquals("<animate attributeType=\"xml\" begin=\"5100.0ms\" dur=\"1900.0ms\" "
            + "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\"/>\n<animate "
            + "attributeType=\"xml\" begin=\"5100.0ms\" dur=\"1900.0ms\" attributeName=\"height\" "
            + "from=\"100.0\" to=\"100.0\" fill=\"freeze\"/>", rS1.toSVG(false));

    assertEquals("<animate attributeType=\"xml\" begin=\"base.begin+1000.0ms\" " +
                    "dur=\"4000.0ms\" attributeName=\"x\" from=\"200.0\" to=\"300.0\" " +
                    "fill=\"freeze\"/>\n" +
                    "<animate attributeType=\"xml\" begin=\"base.begin+1000.0ms\"" +
                    " dur=\"4000.0ms\" " +
                    "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\"/>",
            rM1.toSVG(true));
    assertEquals("<animate attributeName=\"fill\" begin=\"base.begin+2000.0ms\" " +
                    "dur=\"2000.0ms\" values=\"rgb(0,0,1);rgb(0,100,0)\" fill=\"freeze\"/>",
            oC1.toSVG(true));
    assertEquals("<animate attributeType=\"xml\" begin=\"base.begin+5100.0ms\" " +
            "dur=\"1900.0ms\" attributeName=\"width\" from=\"50.0\" to=\"25.0\" " +
            "fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" begin=\"base.begin+5100.0ms\" "
            + "dur=\"1900.0ms\" attributeName=\"height\" from=\"100.0\" to=\"100.0\" " +
            "fill=\"freeze\"/>", rS1.toSVG(true));
  }

  // Testing updateTarget by checking the move have a updated target after calling updateTarget
  @Test
  public void testUpdateTarget() {
    rS1.updateTarget(o1);
    rM1.updateTarget(r1.updateColor(5, 6, 7));
    oC1.updateTarget(o1.updateSize(9.0, 10.0));
    assertEquals(o1, rS1.getTarget());
    assertEquals(new Color(5, 6, 7), rM1.getTarget().getC());
    assertEquals(9.0, oC1.getTarget().getWX(), 0);
    assertEquals(10.0, oC1.getTarget().getHY(), 0);
  }

  // Testing getNew by checking that the toString afte
  @Test
  public void testGetNew() {

    String oldMove1 = rM1.toString();
    String newMove1 = rM1.getNew().toString();
    assertEquals(oldMove1, newMove1);

    String oldMove2 = oC1.toString();
    String newMove2 = oC1.getNew().toString();
    assertEquals(oldMove2, newMove2);

    String oldMove3 = rS1.toString();
    String newMove3 = rS1.getNew().toString();
    assertEquals(oldMove3, newMove3);

  }

}
