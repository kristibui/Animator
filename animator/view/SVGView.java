package cs3500.animator.view;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;

import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;

/**
 * Represents the SVG View for the Simple Animator. This view implements the IView interface and
 * produces an SVG representation of the animation, showing the creation and movement of all of the
 * shapes and moves in the animation. The output of the SVG representation can be viewed directly in
 * a web browser.
 */

public class SVGView implements IView {

  private Color background;
  private double lastTime;
  /**
   * The constructor for the SVG View for the Simple Animator.
   */

  public SVGView() {
    // Don't need any field because the method takes in the required lists
    this.background = Color.WHITE;
    this.lastTime = 100;
  }
  public SVGView(Color background, double lastTime) {
    this.background = background;
    this.lastTime = lastTime;
  }

  @Override
  public String getStatus(List<IShape> allShapes, List<IMove> allMoves, boolean enableLoop) {
    String status = "<svg width=\"800\" height=\"800\" version=\"1.1\"\n" +
            "xmlns=\"http://www.w3.org/2000/svg\">\n";
    if (enableLoop) {
      status = status + "<rect>\n <animate id=\"base\" begin=\"0;base.end\" dur=\"15000.0ms\" " +
              "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" + "</rect>\n";
    }
    Color color = background;
    String colorS = "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    status = status + "<rect id=\"Background\" x=\"0.0\" y=\"0.0\" width=\"800.0\" height=\"800.0\"" +
            " fill=\"" + colorS + "\"\n" + " visibility=\"hidden\">\n" +
            "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" begin=\"0.0ms\"\n" +
            "dur=\"" + lastTime * 100 + "ms\" fill=\"remove\"/>\n" +
            "</rect>";
    for (int i = 0; i < allShapes.size(); i++) {
      IShape target = allShapes.get(i);
      status = status + toSVGSHelper(allShapes.get(i), enableLoop) + "\n";
      for (int j = 0; j < allMoves.size(); j++) {
        if (allMoves.get(j).getTarget().sameShape(target)) {
          status = status + toSVGMHelper(allMoves.get(j), enableLoop) + "\n";
        }
        if (j == allMoves.size() - 1) {
          status = status + resetHelper(target);

        }
      }
    }
    status = status + "</svg>";
    return status;
  }

  /**
   * This method retrieves an SVG string representation of the given shape. It also resets the given
   * shape back to it's initial state.
   *
   * @param s the given shape to create a string representation of
   * @return an SVG string representation of the shape
   */

  private String resetHelper(IShape s) {
    Color color = s.getC();
    String colorS = "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    switch (s.getType()) {
      case OVAL:
        return "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cx\" "
                + "to=\"" + s.getLoc().getX() + "\" fill=\"freeze\"/>\n"
                + "<animate attributeType=\"xml\" "
                + "begin=\"base.end\" dur=\"1ms\" attributeName=\"cy\" to=\"" + s.getLoc().getY()
                + "\" fill=\"freeze\"/>\n<animate attributeName=\"fill\"" +
                " begin=\"base.end\" dur=\"1ms\""
                + " values=\"" + colorS + "\" fill=\"freeze\"/>\n"
                + "<animate attributeType=\"xml\" "
                + "begin=\"base.end\" dur=\"1ms\" attributeName=\"rx\" " +
                "to=\"" + s.getWX() + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" " +
                "begin=\"base.end\" dur=\"1ms\" attributeName=\"ry\" to=\"" + s.getHY()
                + "\" fill=\"freeze\"/>\n" + "</ellipse>\n";
      case RECTANGLE:
        return "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"x\" "
                + "to=\"" + s.getLoc().getX() + "\" fill=\"freeze\"/>\n"
                + "<animate attributeType=\"xml\" " +
                "begin=\"base.end\" dur=\"1ms\" attributeName=\"y\" to=\"" + s.getLoc().getY()
                + "\" fill=\"freeze\"/>\n<animate attributeName=\"fill\" " +
                "begin=\"base.end\" dur=\"1ms\"" + " values=\"" + colorS + "\" fill=\"freeze\"/>\n"
                + "<animate attributeType=\"xml\" "
                + "begin=\"base.end\" dur=\"1ms\" attributeName=\"width\" " +
                "to=\"" + s.getWX() + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" " +
                "begin=\"base.end\" dur=\"1ms\" attributeName=\"height\" to=\"" + s.getHY()
                + "\" fill=\"freeze\"/>\n" + "</rect>\n";
      default:
        throw new IllegalArgumentException("Not a Shape");
    }

  }


  /**
   * Helper method that help returns the SVG string representation of the given IShape.
   *
   * @param s          IShape
   * @param enableLoop boolean representing whether to loop or not
   * @return an SVG string representation of the given shape
   */
  private String toSVGSHelper(IShape s, Boolean enableLoop) {
    Color color = s.getC();
    Double duration = s.getET() - s.getST();
    String colorS = "(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    String loop = "";
    switch (s.getType()) {
      case OVAL:
        if (enableLoop) {
          loop = "repeatCount=\"indefinite\"";
        }
        return "<ellipse id=\"" + s.getName() + "\" cx=\"" + s.getLoc().getX() + "\" cy=\""
                + s.getLoc().getY() + "\" rx=\""
                + s.getWX() + "\" ry=\"" + s.getHY() + "\" fill=\"rgb" + colorS + "\" visibility=\""
                + "hidden\" >\n<set attributeName=\"visibility\" attributeType=\"xml\"" +
                " to=\"visible\" " + "begin=\"" + s.getST() * 100 + "ms\" dur=\"" + duration * 100
                + "ms\" fill=\"remove\" " + loop + "/>";
      case RECTANGLE:
        if (enableLoop) {
          loop = "repeatCount=\"indefinite\"";
        }
        return "<rect id=\"" + s.getName() + "\" x=\"" + s.getLoc().getX() + "\" y=\""
                + s.getLoc().getY() + "\" width=\"" + s.getWX() + "\" height=\"" + s.getHY()
                + "\" fill=\"rgb" + colorS + "\"" + " visibility=\""
                + "hidden\" >\n<set attributeName=\"visibility\" attributeType=\"xml\""
                + " to=\"visible\" " + "begin=\"" + s.getST() * 100 + "ms\" dur=\"" + duration * 100
                + "ms\" fill=\"remove\" "
                + loop + "/>";


      default:
        throw new IllegalArgumentException("Not a Shape");
    }
  }

  /**
   * Helper method that help returns the SVG string representation of the given IMove.
   *
   * @param m          IMove
   * @param enableLoop boolean representing whether to loop or not
   * @return an SVG string representation of the given move
   */
  private String toSVGMHelper(IMove m, Boolean enableLoop) {

    Double duration = m.getET() - m.getST();
    switch (m.getType()) {
      case "Change Location":
        return changeLocationSVG(m, enableLoop);
      case "Change Color":
        Color tC = m.getTarget().getC();
        String pre = "(" + tC.getRed() + "," + tC.getGreen() + "," + tC.getBlue() + ")";
        String cur = "(" + m.getToColor().getRed() + "," + m.getToColor().getGreen() + ","
                + m.getToColor().getBlue() + ")";
        String loop = "";
        if (enableLoop) {
          loop = "base.begin+";
        }
        return "<animate attributeName=\"fill\" begin=\"" + loop + m.getST() * 100 + "ms\" dur=\""
                + duration * 100
                + "ms\" values=\"rgb" + pre + ";rgb" + cur + "\" fill=\"" + "freeze\"/>";
      case "Change Scale":
        return changeScaleSVG(m, enableLoop);
      default:
        throw new IllegalArgumentException("Not a type of Move");

    }
  }

  /**
   * Retrieve the SVG string representation of the given changeLocation move.
   *
   * @param m          IMove
   * @param enableLoop boolean representing enable loop or not
   * @return String representation of the given changeLocation move
   */
  private String changeLocationSVG(IMove m, Boolean enableLoop) {
    Double duration = m.getET() - m.getST();
    Point2D pre = m.getTarget().getLoc();
    String svg = "";
    String loop = "";
    if (enableLoop) {
      loop = "base.begin+";
    }
    switch (m.getTarget().getType()) {
      case RECTANGLE:
        svg = "<animate attributeType=\"xml\" begin=\"" + loop + m.getST() * 100 + "ms\" dur=\""
                + duration * 100
                + "ms\" attributeName=\"x\" from=\"" + pre.getX() + "\" to=\"" + m.getToLoc().getX()
                + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" begin=\"" + loop
                + m.getST() * 100
                + "ms\" dur=\"" + duration * 100 + "ms\" attributeName=\"y\" from=\"" + pre.getY()
                + "\" to=\"" + m.getToLoc().getY() + "\" fill=\"freeze\"/>";
        break;
      case OVAL:
        svg = "<animate attributeType=\"xml\" begin=\"" + loop + m.getST() * 100 + "ms\" dur=\""
                + duration * 100
                + "ms\" attributeName=\"cx\" from=\"" + pre.getX() + "\" to=\""
                + m.getToLoc().getX()
                + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" begin=\"" + loop
                + m.getST() * 100
                + "ms\" dur=\"" + duration * 100 + "ms\" attributeName=\"cy\" from=\"" + pre.getY()
                + "\" to=\"" + m.getToLoc().getY() + "\" fill=\"freeze\"/>";
        break;
      default:
        break;
    }
    return svg;
  }

  /**
   * Retrieve the SVG string representation of the given changeScale move.
   *
   * @param m          IMove
   * @param enableLoop boolean representing enable loop or not
   * @return String representation of the given changeScale move
   */
  private String changeScaleSVG(IMove m, Boolean enableLoop) {
    Double duration = m.getET() - m.getST();
    IShape pre = m.getTarget();
    String svg = "";
    String loop = "";
    if (enableLoop) {
      loop = "base.begin+";
    }
    switch (m.getTarget().getType()) {
      case RECTANGLE:
        svg = "<animate attributeType=\"xml\" begin=\"" + loop + m.getST() * 100 + "ms\" dur=\""
                + duration * 100
                + "ms\" attributeName=\"width\" from=\"" + pre.getWX() + "\" to=\""
                + m.getTo("WX")
                + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" begin=\"" + loop
                + m.getST() * 100
                + "ms\" dur=\"" + duration * 100 + "ms\" attributeName=\"height\" from=\""
                + pre.getHY()
                + "\" to=\"" + m.getTo("HY") + "\" fill=\"freeze\"/>";
        break;
      case OVAL:
        svg = "<animate attributeType=\"xml\" begin=\"" + loop + m.getST() * 100 + "ms\" dur=\""
                + duration * 100
                + "ms\" attributeName=\"rx\" from=\"" + pre.getWX() + "\" to=\"" + m.getTo("WX")
                + "\" fill=\"freeze\"/>\n" + "<animate attributeType=\"xml\" begin=\"" + loop
                + m.getST() * 100
                + "ms\" dur=\"" + duration * 100 + "ms\" attributeName=\"ry\" from=\"" + pre.getHY()
                + "\" to=\"" + m.getTo("HY") + "\" fill=\"freeze\"/>";
        break;
      default:
        break;

    }

    return svg;
  }

  @Override
  public void updateSpeed(int speed) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Shouldn't need to update speed");
  }

}
