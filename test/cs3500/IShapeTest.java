package cs3500;

import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Position;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ShapeType;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the IShape interface. These test various functions of the IShape interface, such as
 * making sure a proper shape can be created, various data about the shapes can be properly
 * retrieved, and that the particular information associated with a created shape is as expected.
 */

public class IShapeTest {
  Color colorForC = new Color(0, 0, 1);
  Color colorForR = new Color(1, 0, 0);
  Point2D dPForR = new Point2D.Double(200.0, 200.0);
  Point2D dPForC = new Point2D.Double(500.0, 100.0);
  IShape r1 = new Rectangle("R", Position.LL, dPForR, colorForR,
          1, 100, 50.0, 100.0,1);
  IShape o1 = new Oval("C", Position.C, dPForC, colorForC, 6, 100, 60, 30,1);
  List<IShape> lS = new ArrayList<IShape>(Arrays.asList(r1));

  // Testing the getName method by calling the getName on each shape and check does output string
  // correspond with the shape name
  @Test
  public void testGetName() {
    assertEquals("R", r1.getName()); // rectangle
    assertEquals("C", o1.getName()); // oval
  }

  // Testing the toString method by calling toString on each chape and check the output String
  @Test
  public void testToString() {
    assertEquals("Name: R\n" + "Type: rectangle\nLower-left corner: (200.0,200.0)" +
            ", Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\nAppears at t=1.0s\n"
            + "Disappears at t=100.0s", r1.toString()); // rectangle
    assertEquals("Name: C\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, Y " +
            "radius: 30.0, Color: (0.0,0.0,1.0)\nAppears at t=6.0s\n" +
            "Disappears at t=100.0s", o1.toString());  // oval
  }

  // Testing the getWX method by checking the output equals to the correct double representation
  // of the width or xRadius
  @Test
  public void testGetWX() {
    assertEquals(50.0, r1.getWX(), 0); // rectangle
    assertEquals(60.0, o1.getWX(), 0); // oval
  }

  // Testing the getHY method by checking the output equals to the correct double representation
  // of the height or yRadius
  @Test
  public void testGetHY() {
    assertEquals(100.0, r1.getHY(), 0); // rectangle
    assertEquals(30.0, o1.getHY(), 0); // oval
  }

  // Testing the getLoc method by checking the ouput Point2D corresponds with the shape's location
  @Test
  public void testGetLoc() {
    assertEquals(dPForR, r1.getLoc());// rectangle
    assertEquals(dPForC, o1.getLoc());// oval
  }

  // Testing the getColor method by checking the output color corresponds with the shape's color
  @Test
  public void testGetColor() {
    assertEquals(colorForR, r1.getC());// rectangle
    assertEquals(colorForC, o1.getC());// oval
  }

  // Testing the sameShape method by cross checking with different type of shapes
  // and the shape itself
  @Test
  public void testSameShape() {
    assertEquals(false, r1.sameShape(o1));
    assertEquals(true, r1.sameShape(r1));
    assertEquals(false, o1.sameShape(r1));
    assertEquals(true, o1.sameShape(o1));

  }

  // Testing timeStatus method by checking the output correspond to the correct string
  // representation of the shape's appear and disappear time
  @Test
  public void testTimeStatus() {
    // rectangle
    assertEquals("Appears at t=1.0s\n" + "Disappears at t=100.0s", r1.timeStatus());
    // oval
    assertEquals("Appears at t=6.0s\n" + "Disappears at t=100.0s", o1.timeStatus());
  }

  // Testing colorStatus method by checking the output matches the correct string representation
  // of the shape's color
  @Test
  public void testColorStatus() {
    assertEquals("(1.0,0.0,0.0)", r1.colorStatus());// rectangle
    assertEquals("(0.0,0.0,1.0)", o1.colorStatus());// oval
  }

  // Testing changeC method by checking the color field after calling the method
  @Test
  public void testChangeC() {
    r1.changeC(new Color(6, 6, 6));
    o1.changeC(new Color(87, 5, 19));
    assertEquals("(6.0,6.0,6.0)", r1.colorStatus());
    assertEquals("(87.0,5.0,19.0)", o1.colorStatus());

  }

  // Testing scale method method by checking the width or x-radius and height or y-radius fields
  // after calling the method
  @Test
  public void testScale() {
    r1.scale(30.0, 5.0);
    o1.scale(-5.0, 10.0);
    assertEquals(30.0, r1.getWX(), 0);
    assertEquals(5.0, r1.getHY(), 0);
    assertEquals(-5.0, o1.getWX(), 0);
    assertEquals(10.0, o1.getHY(), 0);
  }

  // Testing moveLoc method by checking the x and y field after calling the method
  @Test
  public void testMoveLoc() {
    r1.moveLoc(10.0, 10.0);
    o1.moveLoc(-50.0, 100.0);
    assertEquals(10.0, r1.getLoc().getX(), 0); // rectangle x
    assertEquals(10.0, r1.getLoc().getY(), 0); // rectangle y
    assertEquals(-50.0, o1.getLoc().getX(), 0); // oval
    assertEquals(100.0, o1.getLoc().getY(), 0); // oval
    assertEquals("<ellipse id=\"C\" cx=\"-50.0\" cy=\"100.0\" rx=\"60.0\" " +
            "ry=\"30.0\" fill=\"rgb(0,0,1)\" visibility=\"hidden\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" " +
            "begin=\"600.0ms\" dur=\"9400.0ms\" fill=\"remove\" />", o1.toSVG(false));
  }

  // Testing getST method by checking if the method output outputs the correct start time
  @Test
  public void testGetST() {
    assertEquals(1, r1.getST(), 0);
    assertEquals(6, o1.getST(), 0);
  }

  // Testing getET method by checking if the method outputs the correct end time
  @Test
  public void testGetET() {
    assertEquals(100, r1.getET(), 0);
    assertEquals(100, o1.getET(), 0);
  }

  // Testing getType method by checking if the method outputs the correct shapeType
  @Test
  public void testGetType() {
    assertEquals(ShapeType.OVAL, o1.getType());
    assertEquals(ShapeType.RECTANGLE, r1.getType());
  }

  // Testing toSVG method by checking the the output is in the correct svg string format
  @Test
  public void testToSVG() {
    assertEquals("<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" " +
            "fill=\"rgb(0,0,1)\" visibility=\"hidden\" >\n<set attributeName=\"visibility\" " +
            "attributeType=\"xml\" to=\"visible\" begin=\"600.0ms\" dur=\"9400.0ms\" fill=\"" +
            "remove\" />", o1.toSVG(false));
    assertEquals("<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" " +
            "fill=\"rgb(1,0,0)\" visibility=\"hidden\" >\n<set attributeName=\"visibility\" " +
            "attributeType=\"xml\" to=\"visible\" begin=\"100.0ms\" dur=\"9900.0ms\" " +
            "fill=\"remove\" />", r1.toSVG(false));
    // need to test when it is true

  }

  // Testing canAdd method by checking with an arrayList with r1 in it
  @Test
  public void testCanAdd() {
    assertEquals(false, r1.canAdd(lS));
    assertEquals(true, o1.canAdd(lS));
    lS.add(o1);
    assertEquals(false, o1.canAdd(lS));

  }

  // Testing updateSize method by checking the output shape has the updated width and height
  // or x and y radius
  @Test
  public void testUpdateSize() {
    IShape updatedR = r1.updateSize(41, 8);
    IShape updatedO = o1.updateSize(90, 10);
    assertEquals(41, updatedR.getWX(), 0);
    assertEquals(8, updatedR.getHY(), 0);
    assertEquals(90, updatedO.getWX(), 0);
    assertEquals(10, updatedO.getHY(), 0);
  }

  // Testing updateColor method by checking the output shape has the updated color
  @Test
  public void testUpdateColor() {
    IShape updatedR = r1.updateColor(5, 5, 5);
    IShape updatedO = o1.updateColor(8, 90, 10);
    assertEquals("(5.0,5.0,5.0)", updatedR.colorStatus());
    assertEquals("(8.0,90.0,10.0)", updatedO.colorStatus());

  }

  // Testing updateLoc method by checking the output shape has the updates location of the shape
  @Test
  public void testUpdateLoc() {
    IShape updatedR = r1.updateLoc(5.0, 150.0);
    IShape updatedO = o1.updateLoc(85.0, 1.0);
    assertEquals(new Point2D.Double(5.0, 150.0), updatedR.getLoc());
    assertEquals(new Point2D.Double(85.0, 1.0), updatedO.getLoc());
  }

  // Tests getNew by comparing the string of the new shape
  @Test
  public void testGetNew() {

    String oldShape1 = this.r1.toString();
    String newShape1 = this.r1.getNew().toString();
    assertEquals(oldShape1, newShape1);

    String oldShape2 = this.o1.toString();
    String newShape2 = this.o1.getNew().toString();
    assertEquals(oldShape2, newShape2);

  }

  // Tests reset by checking the outputted string of reset is as expected
  @Test
  public void testReset() {
    assertEquals("<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"x\" to=\"200.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"y\" " +
            "to=\"200.0\" fill=\"freeze\"/>\n" +
            "<animate attributeName=\"fill\" begin=\"base.end\" dur=\"1ms\" " +
            "values=\"rgb(1,0,0)\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"width\" to=\"50.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"height\" to=\"100.0\" fill=\"freeze\"/>\n", r1.reset());
    assertEquals("<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"cx\" to=\"500.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"cy\" to=\"100.0\" fill=\"freeze\"/>\n" +
            "<animate attributeName=\"fill\" begin=\"base.end\" dur=\"1ms\" " +
            "values=\"rgb(0,0,1)\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"rx\" to=\"60.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"ry\" to=\"30.0\" fill=\"freeze\"/>\n", o1.reset());

  }

  @Test
  public void testC() {
    //assertEquals(Color.BLUE, Color.getColor("BLUE"));
  }

}
