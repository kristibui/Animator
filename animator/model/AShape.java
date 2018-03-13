package cs3500.animator.model;


import java.awt.Color;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * This abstract class abstracts the method and fields that are common in the different shape
 * classes that implements IShape. This class represents all the commonalities associated with the
 * shapes that a client can create and add to a simple animation.
 */

public abstract class AShape implements IShape {

  protected String name;
  protected Position pos;
  protected Point2D loc;
  protected Color color;
  protected Double appearT;
  protected Double disappearT;
  protected Double duration;
  protected int layer;

  /**
   * The constructor for all the shapes that will extend from AShape. The constructor also
   * initializes all the fields with the given inputs.
   *
   * @param name  string representing the name of the shape
   * @param p     represents the position of the shape
   * @param l     represents the location of the shape
   * @param dC    represents the color of the shape
   * @param aT    represents the appearance of the shape
   * @param dT    represents the time of disappearance of the shape
   * @param layer represents the layer of the shape
   */

  public AShape(String name, Position p, Point2D l,
                Color dC, Double aT, Double dT, int layer) {
    this.name = name;
    this.pos = p;
    this.loc = l;
    this.color = dC;
    this.appearT = aT;
    this.disappearT = dT;
    this.duration = dT - aT;
    this.layer = layer;
  }

  @Override
  public void changeC(Color newC) {

    this.color = newC;
  }

  @Override
  public void moveLoc(double newX, double newY) {
    this.loc = new Point2D.Double(newX, newY);
  }

  @Override
  public boolean sameShape(IShape s) {
    return this.name.equals(s.getName());
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Point2D getLoc() {
    return this.loc;
  }

  @Override
  public Color getC() {
    return this.color;
  }

  @Override
  public Double getST() {
    return this.appearT;
  }

  @Override
  public Double getET() {
    return this.disappearT;
  }


  @Override
  public String timeStatus() {
    return "Appears at t=" + appearT + "s" + "\nDisappears at t=" + disappearT + "s";
  }


  @Override
  public String colorStatus() {
    String r = Double.toString(color.getRed() * 1.0);
    String g = Double.toString(color.getGreen() * 1.0);
    String b = Double.toString(color.getBlue() * 1.0);
    return "(" + r + "," + g + "," + b + ")";
  }

  @Override
  public boolean canAdd(List<IShape> l) {
    boolean valid = true;
    for (IShape aL : l) {
      valid = valid && !aL.sameShape(this);
    }
    return valid;
  }

  @Override
  public Position getPos() {
    return this.pos;
  }

  @Override
  public int getLayer() {
    return this.layer;
  }


}
