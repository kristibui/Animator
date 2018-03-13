package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;

/**
 * Represents the Textual View for the Simple Animator. This view implements the IView interface and
 * produces a textual representation of the animation, showing the creation and movement of all of
 * the shapes and moves in the animation.
 */

public class TextualView implements IView {

  /**
   * The constructor for the Textual View for the Simple Animator.
   */

  public TextualView() {
    // Don't need any field because the method takes in the required lists
  }

  @Override
  public String getStatus(List<IShape> allShapes, List<IMove> allMoves, boolean enableLoop) {
    String status = "";

    for (int i = 0; i < allShapes.size(); i++) {
      if (i == 0) {
        status = "Shapes:\n" + toStringSHelper(allShapes.get(i)) + "\n\n";
      } else if (i == (allShapes.size() - 1)) {
        status = status + toStringSHelper(allShapes.get(i)) + "\n\n";
      } else {
        status = status + toStringSHelper(allShapes.get(i)) + "\n\n";
      }
    }
    for (int i = 0; i < allMoves.size(); i++) {
      if (i == (allMoves.size() - 1)) {
        status = status + toStringMHelper(allMoves.get(i));
      } else {
        status = status + toStringMHelper(allMoves.get(i)) + "\n";
      }
    }
    return status;
  }


  @Override
  public void updateSpeed(int speed) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Shouldn't need to update speed");
  }

  /**
   * Retrieves the string representation of the given shape.
   *
   * @param s a type of IShape
   * @return a String representation of the Shape
   */
  private String toStringSHelper(IShape s) {
    switch (s.getType()) {
      case OVAL:
        return "Name: " + s.getName() + "\n" + "Type: oval\n" + s.getPos().getMess() + ": ("
                + s.getLoc().getX()
                + "," + s.getLoc().getY() + ")" + ", " + "X radius: " + s.getWX() + ", Y radius: "
                + s.getHY()
                + ", Color: " + s.colorStatus() + "\n" + s.timeStatus();
      case RECTANGLE:
        return "Name: " + s.getName() + "\n" + "Type: rectangle\n" + s.getPos().getMess()
                + ": (" + s.getLoc().getX()
                + "," + s.getLoc().getY() + ")" + ", " + "Width: " + s.getWX() + ", Height: "
                + s.getHY() + ", Color: " + s.colorStatus() + "\n" + s.timeStatus();
      default:
        throw new IllegalArgumentException("This is not a shape");
    }
  }

  /**
   * Retrieves the string representation of the given move.
   *
   * @param m a type of IMove
   * @return a String representation of the Move
   */
  private String toStringMHelper(IMove m) {
    switch (m.getType()) {
      case "Change Location":
        return "Shape " + m.getTarget().getName() + " moves from (" + m.getTarget().getLoc().getX()
                + "," + m.getTarget().getLoc().getY() + ") to (" + m.getToLoc().getX()
                + "," + m.getToLoc().getY() + ") from t="
                + m.getST() + "s" + " to t=" + m.getET() + "s";
      case "Change Color":
        String r = Double.toString(m.getToColor().getRed() * 1.0);
        String g = Double.toString(m.getToColor().getGreen() * 1.0);
        String b = Double.toString(m.getToColor().getBlue() * 1.0);
        return "Shape " + m.getTarget().getName() + " changes color from "
                + m.getTarget().colorStatus() + " to "
                + "(" + r + "," + g + "," + b + ")" + " from t=" + m.getST() + "s" + " to t="
                + m.getET() + "s";
      case "Change Scale":
        return "Shape " + m.getTarget().getName() + " scales from Width: " + m.getTarget().getWX()
                + " Height: "
                + m.getTarget().getHY() + " to Width: " + m.getTo("WX") + " Height: "
                + m.getTo("HY") + " from t="
                + m.getST() + "s" + " to t=" + m.getET() + "s";
      default:
        throw new IllegalArgumentException("Not a type of Move");

    }
  }


}
