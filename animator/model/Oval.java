package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Represents an oval shape, which is a type of shape that can be created in a Simple Animation. An
 * Oval has two unique dimensions, an x-radius and y-radius.
 */

public class Oval extends AShape {

  private double xRadius;
  private double yRadius;

  /**
   * This constructor creates a new Oval and initializes its fields based on the given inputs.
   *
   * @param name  represents the name of the oval
   * @param p     represents the position of the oval
   * @param l     represents the location of the oval
   * @param c     represents the color of the oval
   * @param aT    represents the time of appearance for the oval
   * @param dT    represents the time of disappearance for the oval
   * @param xR    represents the x-radius of the oval
   * @param yR    represents the y-radius of the oval
   * @param layer represents the layer of the shape
   */

  public Oval(String name, Position p, Point2D l, Color c,
              double aT, double dT, double xR, double yR, int layer) {

    super(name, p, l, c, aT, dT, layer);
    this.xRadius = xR;
    this.yRadius = yR;
  }

  @Override
  public double getWX() {
    return this.xRadius;
  }

  @Override
  public double getHY() {
    return this.yRadius;
  }

  @Override
  public void scale(double newWX, double newHY) {
    this.xRadius = newWX;
    this.yRadius = newHY;
  }

  @Override
  public ShapeType getType() {
    return ShapeType.OVAL;
  }


  @Override
  public String toSVG(boolean enableLoop) {
    String colorS = "(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    String loop = "";
    if (enableLoop) {
      loop = "repeatCount=\"indefinite\"";
    }
    return "<ellipse id=\"" + name + "\" cx=\"" + loc.getX() + "\" cy=\"" + loc.getY() + "\" rx=\""
            + xRadius + "\" ry=\"" + yRadius + "\" fill=\"rgb" + colorS + "\" visibility=\""
            + "hidden\" >\n<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" "
            + "begin=\"" + appearT * 100 + "ms\" dur=\"" + duration * 100 + "ms\" fill=\"remove\" "
            + loop + "/>";

  }

  @Override
  public IShape updateSize(double wX, double hY) {
    return new Oval(name, pos, loc, color, appearT, disappearT, wX, hY, layer);
  }

  @Override
  public IShape updateColor(int redColor, int greenColor, int blueColor) {
    return new Oval(name, pos, loc, new Color(redColor, greenColor, blueColor), appearT,
            disappearT, xRadius, yRadius, layer);
  }

  @Override
  public IShape updateLoc(double xPosition, double yPosition) {
    return new Oval(name, pos, new Point2D.Double(xPosition, yPosition), color, appearT,
            disappearT, xRadius, yRadius, layer);
  }

  @Override
  public IShape getNew() {
    return new Oval(name, pos, loc, color, appearT, disappearT, xRadius, yRadius, layer);
  }

  @Override
  public String reset() {
    String colorS = "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    return "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cx\" " +
            "to=\"" + loc.getX() + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" " +
            "begin=\"base.end\" dur=\"1ms\" attributeName=\"cy\" to=\"" + loc.getY()
            + "\" fill=\"freeze\"/>\n<animate attributeName=\"fill\" begin=\"base.end\" dur=\"1ms\""
            + " values=\"" + colorS + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" " +
            "begin=\"base.end\" dur=\"1ms\" attributeName=\"rx\" " +
            "to=\"" + xRadius + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" " +
            "begin=\"base.end\" dur=\"1ms\" attributeName=\"ry\" to=\"" + yRadius
            + "\" fill=\"freeze\"/>\n";
  }


  @Override
  public String toString() {
    return "Name: " + name + "\n" + "Type: oval\n" + pos.getMess() + ": (" + loc.getX()
            + "," + loc.getY() + ")" + ", " + "X radius: " + xRadius + ", Y radius: " + yRadius
            + ", Color: " + colorStatus() + "\n" + timeStatus();
  }


}
