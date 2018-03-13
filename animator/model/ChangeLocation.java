package cs3500.animator.model;

import java.awt.geom.Point2D;

/**
 * This class represents a type of move that can be performed in a simple animation, where the
 * position of a shape is changed over a duration of time.
 */

public class ChangeLocation extends AMove {

  private Point2D to;
  private String type = "Change Location";

  /**
   * This constructor creates a new instance of a ChangeLocation, initializing the fields based on
   * the given input.
   *
   * @param target represents the IShape that is changing position
   * @param sT     represents the starting time of the changing of position
   * @param eT     represents the ending time of the changing of position
   * @param to     the final position that the shape is moving to
   */

  public ChangeLocation(IShape target, double sT, double eT, Point2D to) {
    super(target, sT, eT);
    this.to = to;
  }

  @Override
  public IShape change(double curTime) {

    double xPosition;
    double yPosition;

    xPosition = target.getLoc().getX() * ((endT - curTime) / duration)
            + to.getX() * ((curTime - startT) / duration);
    yPosition = target.getLoc().getY() * ((endT - curTime) / duration)
            + to.getY() * ((curTime - startT) / duration);
    target.moveLoc(xPosition, yPosition);
    return target;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public String toSVG(boolean enableLoop) {
    Point2D pre = target.getLoc();
    String svg = "";
    String loop = "";
    if (enableLoop) {
      loop = "base.begin+";
    }
    switch (target.getType()) {
      case RECTANGLE:
        svg = "<animate attributeType=\"xml\" begin=\"" + loop + startT * 100 + "ms\" dur=\""
                + duration * 100
                + "ms\" attributeName=\"x\" from=\"" + pre.getX() + "\" to=\"" + to.getX()
                + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" begin=\"" + loop
                + startT * 100
                + "ms\" dur=\"" + duration * 100 + "ms\" attributeName=\"y\" from=\"" + pre.getY()
                + "\" to=\"" + to.getY() + "\" fill=\"freeze\"/>";
        break;
      case OVAL:
        svg = "<animate attributeType=\"xml\" begin=\"" + loop + startT * 100 + "ms\" dur=\""
                + duration * 100
                + "ms\" attributeName=\"cx\" from=\"" + pre.getX() + "\" to=\"" + to.getX()
                + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" begin=\"" + loop
                + startT * 100
                + "ms\" dur=\"" + duration * 100 + "ms\" attributeName=\"cy\" from=\"" + pre.getY()
                + "\" to=\"" + to.getY() + "\" fill=\"freeze\"/>";
        break;
      default:
        break;
    }
    return svg;
  }

  @Override
  public String toString() {
    return "Shape " + target.getName() + " moves from (" + target.getLoc().getX() + ","
            + target.getLoc().getY() + ") to (" + to.getX() + "," + to.getY() + ") from t="
            + startT + "s" + " to t=" + endT + "s";
  }

  @Override
  public IMove getNew() {
    return new ChangeLocation(target.getNew(), startT, endT, to);
  }

  @Override
  public Point2D getToLoc() {
    return to;
  }

}
