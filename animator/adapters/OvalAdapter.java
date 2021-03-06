package cs3500.animator.adapters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import cs3500.animator.model.Oval;
import cs3500.animator.model.Position;
import cs3500.animator.model.Posn;
import cs3500.animator.provider.model.IPosn;
import cs3500.animator.provider.model.IShape;

/**
 * Represents the adapter for an oval shape. This adapter maps out the functions and attributes of
 * our oval with the provided interface for a shape.
 */

public class OvalAdapter extends Oval implements IShape {

  Boolean isVisible;

  /**
   * The constructor for an OvalAdapter.
   *
   * @param name represents the name of the oval
   * @param p    represents the position of the oval
   * @param l    represents the location of the oval
   * @param c    represents the color of the oval
   * @param aT   represents the time of appearance for the oval
   * @param dT   represents the time of disappearance for the oval
   * @param xR   represents the x-radius of the oval
   * @param yR   represents the y-radius of the oval
   */

  public OvalAdapter(String name, Position p, Point2D l, Color c,
                     double aT, double dT, double xR, double yR) {
    super(name, p, l, c, aT, dT, xR, yR,1);
    this.isVisible = false;
  }

  @Override
  public void move(float dx, float dy) {

    this.moveLoc(dx, dy);

  }

  @Override
  public void changeColor(float[] c) {

    this.changeC(new Color(c[0], c[1], c[2]));

  }

  @Override
  public void changeDimension(float dx, float dy) {

    this.scale(dx, dy);

  }

  @Override
  public String stringValue() {

    return this.toString();
  }

  @Override
  public String stringDimensions(float xValue, float yValue) {
    return "";
  }

  @Override
  public boolean appearsAt(int time) {
    return ((getST() <= time) && (getET() >= time));
  }

  @Override
  public IPosn getPosition() {
    return new Posn(Double.valueOf(getLoc().getX()).floatValue(),
            Double.valueOf(getLoc().getY()).floatValue());
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public float[] getColor() {

    float[] newColor = new float[3];
    newColor[0] = getC().getRed();
    newColor[1] = getC().getGreen();
    newColor[2] = getC().getBlue();

    return newColor;
  }

  @Override
  public float getXValue() {
    return Double.valueOf(getLoc().getX()).floatValue();
  }

  @Override
  public float getYValue() {
    return Double.valueOf(getLoc().getX()).floatValue();
  }

  @Override
  public boolean shapeEquals(IShape other) {
    return other.getName().equals(name);
  }

  @Override
  public IShape getCopy() {

    return new OvalAdapter(name, pos, loc, color, appearT, disappearT, getWX(), getHY());
  }

  @Override
  public void draw(Graphics g) {
    Double x = getLoc().getX();
    Double y = getLoc().getY();
    Double w = getWX();
    Double h = getHY();
    g.setColor(getC());
    g.fillOval(x.intValue(), y.intValue(), w.intValue(), h.intValue());
  }

  @Override
  public String shapeTextSVG() {
    return this.toSVG(isVisible);
  }


  @Override
  public void toggleVisibility() {
    this.isVisible = !this.isVisible;
  }

  @Override
  public boolean isVisible() {
    return this.isVisible;
  }
}
