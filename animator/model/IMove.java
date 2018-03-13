package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Represents an interface of all the different types of moves that can be performed to a shape in a
 * simple animation. It includes moving a shape to a different position, changing the color of the
 * shape, and scaling the shape, all within a particular time frame.
 */

public interface IMove {

  /**
   * Changes the shape. What is changed about the shape is dependent upon the type of move it is.
   * For example, if the move is changing the color of the shape, it will change the color of that
   * particular shape.
   *
   * @return changed shape based on the move
   */

  IShape change(double curTime);

  /**
   * Produces a string representation of the move.
   *
   * @return representation of the move
   */

  String toString();

  /**
   * Retrieves the starting time of the move.
   *
   * @return start time of the move
   */

  double getST();

  /**
   * Retrieves the ending time of the move.
   *
   * @return end time of the move
   */

  double getET();

  /**
   * Retrieves the shape that the move is being performed upon.
   *
   * @return the shape associated with the move
   */

  IShape getTarget();

  /**
   * Determines whether a move is valid, by comparing the move to the given list of moves.
   *
   * @param m the list of moves that the move is being compared against
   * @return whether or not the move is valid
   */

  Boolean validMove(List<IMove> m);

  /**
   * Determines if there are overlapping moves between this move and the given list of moves, by
   * comparing the time intervals of all of the moves.
   *
   * @param m the list of moves that the move is being compared against
   * @return whether or not the move oerlaps with any of the moves in the list
   */

  Boolean overlapT(IMove m);

  /**
   * Retrieves the type of move this particular move is.
   *
   * @return the type of move the move is
   */

  String getType();

  /**
   * Produces an SVG string representation of the move.
   *
   * @return SVG string representation of the move
   */

  String toSVG(boolean enableLoop);

  /**
   * Updates the shape associated with the move with the given shape.
   *
   * @param s the shape to be updated to
   */

  void updateTarget(IShape s);

  /**
   * Returns a new move with the same attributes as the current move.
   *
   * @return new move
   */

  IMove getNew();

  /**
   * Retrieves the new location that this move is changing.
   * @return Point2D of the changed to location
   */
  Point2D getToLoc();

  /**
   * Retrieves the new dimension that the target is being changed to.
   * @param s String representing either width or height
   * @return a double
   */
  Double getTo(String s);

  /**
   * Retrieves the new Color htat the target is being changed to.
   * @return a Color
   */
  Color getToColor();

}
