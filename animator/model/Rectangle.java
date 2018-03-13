package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Represents an oval shape, which is a type of shape that can be created in a Simple Animation. A
 * Rectangle has two unique dimensions, a width and a height.
 */

public class Rectangle extends AShape {

  private double width;
  private double height;

  /**
   * This constructor creates a new Rectangle and initializes its fields based on the given inputs.
   *
   * @param name  represents the name of the oval
   * @param p     represents the position of the oval
   * @param l     represents the location of the oval
   * @param c     represents the color of the oval
   * @param aT    represents the time of appearance for the oval
   * @param dT    represents the time of disappearance for the oval
   * @param w     represents the width of the rectangle
   * @param h     the height of the rectangle
   * @param layer represents the layer of the shape
   */

  public Rectangle(String name, Position p, Point2D l, Color c,
                   double aT, double dT, double w, double h, int layer) {
    super(name, p, l, c, aT, dT, layer);
    this.width = w;
    this.height = h;
  }


  @Override
  public double getWX() {
    return this.width;
  }

  @Override
  public double getHY() {
    return this.height;
  }

  @Override
  public void scale(double newWX, double newHY) {
    this.width = newWX;
    this.height = newHY;
  }

  @Override
  public ShapeType getType() {
    return ShapeType.RECTANGLE;
  }

  @Override
  public String toSVG(boolean enableLoop) {
    String colorS = "(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    String loop = "";
    if (enableLoop) {
      loop = "repeatCount=\"indefinite\"";
    }
    return "<rect id=\"" + name + "\" x=\"" + loc.getX() + "\" y=\"" + loc.getY() + "\" width=\""
            + width + "\" height=\"" + height + "\" fill=\"rgb" + colorS + "\" visibility=\""
            + "hidden\" >\n<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" "
            + "begin=\"" + appearT * 100 + "ms\" dur=\"" + duration * 100 + "ms\" fill=\"remove\" "
            + loop + "/>";


  }

  @Override
  public IShape updateSize(double wX, double hY) {
    return new Rectangle(name, pos, loc, color, appearT, disappearT, wX, hY, layer);
  }

  @Override
  public IShape updateColor(int redColor, int greenColor, int blueColor) {
    return new Rectangle(name, pos, loc, new Color(redColor, greenColor, blueColor), appearT,
            disappearT, width, height, layer);
  }

  @Override
  public IShape updateLoc(double xPosition, double yPosition) {
    return new Rectangle(name, pos, new Point2D.Double(xPosition, yPosition), color, appearT,
            disappearT, width, height, layer);
  }

  @Override
  public IShape getNew() {
    return new Rectangle(name, pos, loc, color, appearT, disappearT, width, height, layer);
  }


  @Override
  public String reset() {
    String colorS = "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    return "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"x\" " +
            "to=\"" + loc.getX() + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" " +
            "begin=\"base.end\" dur=\"1ms\" attributeName=\"y\" to=\"" + loc.getY()
            + "\" fill=\"freeze\"/>\n<animate attributeName=\"fill\" begin=\"base.end\" dur=\"1ms\""
            + " values=\"" + colorS + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" " +
            "begin=\"base.end\" dur=\"1ms\" attributeName=\"width\" " +
            "to=\"" + width + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" " +
            "begin=\"base.end\" dur=\"1ms\" attributeName=\"height\" to=\"" + height
            + "\" fill=\"freeze\"/>\n";
  }


  @Override
  public String toString() {
    return "Name: " + name + "\n" + "Type: rectangle\n" + pos.getMess() + ": (" + loc.getX()
            + "," + loc.getY() + ")" + ", " + "Width: " + width + ", Height: " + height
            + ", Color: " + colorStatus() + "\n" + timeStatus();
  }

}
