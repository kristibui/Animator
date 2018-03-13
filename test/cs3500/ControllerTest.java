package cs3500;

import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.IController;
import cs3500.animator.controller.VisualController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ChangeColor;
import cs3500.animator.model.ChangeLocation;
import cs3500.animator.model.ChangeScale;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Position;
import cs3500.animator.model.Rectangle;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the controller. Tests functions of the controller, such as making sure that the
 * Controllers properly facilitates interaction between the model and the view. Note that the tests
 * for the public method action in the controller are in the tests for the views.
 */

public class ControllerTest {

  IAnimationModel model1 = new AnimationModel();
  IView view1 = new TextualView();
  IView view2 = new SVGView();
  IController controller1 = new AnimationController(model1, view1);
  IController controller2 = new AnimationController(model1, view2);
  IController controller3 = new VisualController(model1, view1, "visual", 1, Color.WHITE);
  IController controller4 = new VisualController(model1, view1, "hybrid", 10,Color.WHITE);

  Color colorForC = new Color(0, 0, 1);
  Color colorC1 = new Color(0, 100, 0);
  Color colorForR = new Color(1, 0, 0);
  Point2D dPForR = new Point2D.Double(200.0, 200.0);
  Point2D dPForC = new Point2D.Double(500.0, 100.0);
  Point2D dPRM1 = new Point2D.Double(300.0, 300.0);
  IShape r1 = new Rectangle("R", Position.LL, dPForR, colorForR,
          1, 100, 50.0, 100.0,1);
  IShape o1 = new Oval("C", Position.C, dPForC, colorForC, 6, 100, 60, 30,1);
  IMove rM1 = new ChangeLocation(r1, 10, 50, dPRM1);
  IMove oC1 = new ChangeColor(o1, 20, 40, colorC1);
  IMove rS1 = new ChangeScale(r1, 51, 70, 25.0, 100.0);

  // Tests the method getString by checking to make sure that calling getString in the controller
  // returns the string as expected
  @Test
  public void testGetString() {
    assertEquals("", this.controller1.getString());
    assertEquals("<svg width=\"800\" height=\"800\" version=\"1.1\"\n" +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "</svg>", this.controller2.getString());

    model1.addShape(r1);
    model1.addShape(o1);
    model1.addMove(rM1);
    model1.addMove(oC1);
    model1.addMove(rS1);

    assertEquals("Shapes:\n" +
            "Name: R\n" +
            "Type: rectangle\n" +
            "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
            "Appears at t=1.0s\n" +
            "Disappears at t=100.0s\n" +
            "\n" +
            "Name: C\n" +
            "Type: oval\n" +
            "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n" +
            "Appears at t=6.0s\n" +
            "Disappears at t=100.0s\n" +
            "\n" +
            "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s\n" +
            "Shape C changes color from (0.0,0.0,1.0) to (0.0,100.0,0.0) from t=20.0s to t=40.0s\n"
            + "Shape R scales from Width: 50.0 Height: 100.0 to Width: 25.0 Height: " +
            "100.0 from t=51.0s to t=70.0s", this.controller1.getString());

    assertEquals("<svg width=\"800\" height=\"800\" version=\"1.1\"\n" +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" " +
            "fill=\"rgb(1,0,0)\" visibility=\"hidden\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" " +
            "begin=\"100.0ms\" dur=\"9900.0ms\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" " +
            "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" " +
            "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"5100.0ms\" dur=\"1900.0ms\" " +
            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"5100.0ms\" dur=\"1900.0ms\" " +
            "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"x\" to=\"200.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"y\" to=\"200.0\" fill=\"freeze\"/>\n" +
            "<animate attributeName=\"fill\" begin=\"base.end\" dur=\"1ms\" " +
            "values=\"rgb(1,0,0)\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"width\" to=\"50.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"height\" to=\"100.0\" fill=\"freeze\"/>\n" +
            "</rect>\n" +
            "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" " +
            "fill=\"rgb(0,0,1)\" visibility=\"hidden\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" " +
            "begin=\"600.0ms\" dur=\"9400.0ms\" fill=\"remove\" />\n" +
            "<animate attributeName=\"fill\" begin=\"2000.0ms\" dur=\"2000.0ms\" " +
            "values=\"rgb(0,0,1);rgb(0,100,0)\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"cx\" to=\"500.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"cy\" to=\"100.0\" fill=\"freeze\"/>\n" +
            "<animate attributeName=\"fill\" begin=\"base.end\" dur=\"1ms\" " +
            "values=\"rgb(0,0,1)\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"rx\" to=\"60.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"ry\" to=\"30.0\" fill=\"freeze\"/>\n" +
            "</ellipse>\n" +
            "</svg>", this.controller2.getString());

    assertEquals(null, this.controller3.getString());
    assertEquals(null, this.controller4.getString());
  }
}
