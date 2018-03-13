package cs3500.animator.model;

/**
 * This class represents a type of move that can be performed in a simple animation, where the
 * dimensions of a shape are changed over time.
 */

public class ChangeScale extends AMove {

  private double toWidthX;
  private double toHeightY;
  private String type = "Change Scale";

  /**
   * This constructor creates a new instance of a ChangeScale, initializing the fields based on the
   * given input.
   *
   * @param target the shape that is being changed
   * @param sT     represents the starting time of the changing of dimensions
   * @param eT     represents the ending time of the changing of dimensions
   * @param toWX   represents the width or x-radius the shape is being changed to
   * @param toHY   represents the height or y-radius the shape is being changed to
   */

  public ChangeScale(IShape target, double sT, double eT, double toWX, double toHY) {
    super(target, sT, eT);
    this.toHeightY = toHY;
    this.toWidthX = toWX;
  }

  @Override
  public IShape change(double curTime) {

    double width;
    double height;

    width = target.getWX() * ((endT - curTime) / duration)
            + toWidthX * ((curTime - startT) / duration);
    height = target.getHY() * ((endT - curTime) / duration)
            + toHeightY * ((curTime - startT) / duration);
    target.scale(width, height);
    return target;
  }


  @Override
  public String getType() {
    return type;
  }

  @Override
  public String toSVG(boolean enableLoop) {
    IShape pre = target;
    String svg = "";
    String loop = "";
    if (enableLoop) {
      loop = "base.begin+";
    }
    switch (target.getType()) {
      case RECTANGLE:
        svg = "<animate attributeType=\"xml\" begin=\"" + loop + startT * 100 + "ms\" dur=\""
                + duration * 100
                + "ms\" attributeName=\"width\" from=\"" + pre.getWX() + "\" to=\"" + toWidthX
                + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" begin=\"" + loop
                + startT * 100
                + "ms\" dur=\"" + duration * 100 + "ms\" attributeName=\"height\" from=\""
                + pre.getHY()
                + "\" to=\"" + toHeightY + "\" fill=\"freeze\"/>";
        break;
      case OVAL:
        svg = "<animate attributeType=\"xml\" begin=\"" + loop + startT * 100 + "ms\" dur=\""
                + duration * 100
                + "ms\" attributeName=\"rx\" from=\"" + pre.getWX() + "\" to=\"" + toWidthX
                + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" begin=\"" + loop
                + startT * 100
                + "ms\" dur=\"" + duration * 100 + "ms\" attributeName=\"ry\" from=\"" + pre.getHY()
                + "\" to=\"" + toHeightY + "\" fill=\"freeze\"/>";
        break;
      default:
        break;

    }

    return svg;
  }

  @Override
  public String toString() {
    return "Shape " + target.getName() + " scales from Width: " + target.getWX() + " Height: "
            + target.getHY() + " to Width: " + toWidthX + " Height: " + toHeightY + " from t="
            + startT + "s" + " to t=" + endT + "s";
  }

  @Override
  public IMove getNew() {
    return new ChangeScale(target.getNew()
            , startT, endT, toWidthX, toHeightY);
  }

  @Override
  public Double getTo(String s) {
    switch (s) {
      case "WX":
        return toWidthX;
      case "HY":
        return toHeightY;
      default:
        throw new IllegalArgumentException("not width or height");
    }
  }

}
