package cs3500.animator.model;

import java.awt.Color;

/**
 * This class represents a type of move that can be performed in a simple animation, where the color
 * of a shape is changed in duration of time.
 */

public class ChangeColor extends AMove {

  // Represents the final color of the shape
  protected Color toColor;

  private String type = "Change Color";

  /**
   * This constructor creates a new instance of a ChangeColor, initializing the fields based on the
   * given input.
   *
   * @param target represents the IShape that is changing color
   * @param sT     represents the starting time of the change of color
   * @param eT     represents the ending time of the change of color
   * @param color  represents the color that the shape is being changed to
   */

  public ChangeColor(IShape target, double sT, double eT, Color color) {
    super(target, sT, eT);
    this.toColor = color;
  }

  @Override
  public IShape change(double curTime) {

    int redColor = 0;
    int greenColor = 0;
    int blueColor = 0;

    redColor = Math.round(Double.valueOf(target.getC().getRed() *
            ((endT - curTime) / duration) +
            toColor.getRed() * ((curTime - startT) / duration)).floatValue());
    greenColor = Math.round(Double.valueOf(target.getC().getGreen() *
            ((endT - curTime) / duration) +
            toColor.getGreen() * ((curTime - startT) / duration)).floatValue());
    blueColor = Math.round(Double.valueOf(target.getC().getBlue() *
            ((endT - curTime) / duration) +
            toColor.getBlue() * ((curTime - startT) / duration)).floatValue());
    target.changeC(new Color(redColor, greenColor, blueColor));
    return target;
  }

  @Override
  public String toString() {
    String r = Double.toString(toColor.getRed() * 1.0);
    String g = Double.toString(toColor.getGreen() * 1.0);
    String b = Double.toString(toColor.getBlue() * 1.0);
    return "Shape " + target.getName() + " changes color from " + target.colorStatus() + " to "
            + "(" + r + "," + g + "," + b + ")" + " from t=" + startT + "s" + " to t=" + endT + "s";
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public String toSVG(boolean enableLoop) {
    Color tC = target.getC();
    String pre = "(" + tC.getRed() + "," + tC.getGreen() + "," + tC.getBlue() + ")";
    String cur = "(" + toColor.getRed() + "," + toColor.getGreen() + "," + toColor.getBlue() + ")";
    String loop = "";
    if (enableLoop) {
      loop = "base.begin+";
    }
    return "<animate attributeName=\"fill\" begin=\"" + loop + startT * 100 + "ms\" dur=\""
            + duration * 100
            + "ms\" values=\"rgb" + pre + ";rgb" + cur + "\" fill=\"" + "freeze\"/>";
  }

  @Override
  public IMove getNew() {
    return new ChangeColor(target.getNew(), startT, endT, toColor);
  }

  @Override
  public Color getToColor() {
    return toColor;
  }
}
