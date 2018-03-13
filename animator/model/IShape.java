package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Represents the different types of shapes that can be added and used in an Animation Model.
 */

public interface IShape {

  /**
   * Retrieves the name of the shape.
   *
   * @return name of the shape
   */

  String getName();

  /**
   * Produces a string representation of the shape.
   *
   * @return representation of the shape
   */

  String toString();

  /**
   * Retrieves the width or x-radius field of the shape.
   *
   * @return the width or x-radius of the shape
   */

  double getWX();

  /**
   * Retrieves the height or y-radius field of the shape.
   *
   * @return the height or y-radius of the shape
   */

  double getHY();

  /**
   * Retrieves the current position of the shape.
   *
   * @return current position of the shape
   */

  Point2D getLoc();

  /**
   * Retrieves the color of the shape.
   *
   * @return color of the shape
   */

  Color getC();

  /**
   * Determines if the shape is the same as the given shape.
   *
   * @param s given shape that this shape is being compared to
   * @return whether or not the two shapes are the same
   */

  boolean sameShape(IShape s);

  /**
   * Produces a string representation of the time of appearance and disappearance of the shape.
   *
   * @return string representation of the appear and disappear time of the shape
   */

  String timeStatus();

  /**
   * Produces a string representation of the color of the shape.
   *
   * @return string representation of the color of the shape
   */

  String colorStatus();

  /**
   * Changes the color of the shape, based on the given RGB color increments.
   *
   * @param newC the new color that the shape is to be changed to
   */

  void changeC(Color newC);

  /**
   * Scales the shape, based on the given width and height or x-radius and y-radius increments.
   *
   * @param newWX the width or x-radius to be changed to
   * @param newHY the height or y-radius to be changed to
   */

  void scale(double newWX, double newHY);

  /**
   * Updates the location of the shape, based on the given x and y position increments.
   *
   * @param newX the new x position of the shape
   * @param newY the new y position of the shape
   */

  void moveLoc(double newX, double newY);

  /**
   * Retrieves the starting appear time of a shape.
   *
   * @return starting appear time of a shape
   */

  Double getST();

  /**
   * Retrieves the ending time of a shape.
   *
   * @return ending time of a shape
   */

  Double getET();

  /**
   * Retrieves the type of the shape.
   *
   * @return type of shape
   */

  ShapeType getType();

  /**
   * Produces an SVG string representation of the shape.
   *
   * @return SVG string representation of the shape
   */

  String toSVG(boolean enableLoop);

  /**
   * Determines if a shape is valid and therefore can be added to a given list of shapes.
   *
   * @param l the list of shapes that a new shape is to be added to
   * @return whether or not the shape can be added to the list of shapes
   */

  boolean canAdd(List<IShape> l);

  /**
   * Updates the dimensions of the shape to the given dimensions.
   *
   * @param wX the width or x-radius to be updated to
   * @param hY the height or y-radius to be updated to
   * @return the shape with the updated dimensions
   */

  IShape updateSize(double wX, double hY);

  /**
   * Updates the color of the shape to the given color.
   *
   * @param redColor   the red component of the color to be updated to
   * @param greenColor the green component of the color to be updated to
   * @param blueColor  the blue component of the color to be updated to
   * @return the shape with the updated color
   */

  IShape updateColor(int redColor, int greenColor, int blueColor);

  /**
   * Updates the location of the shape to the given x and y positions.
   *
   * @param xPosition the x position to be updated to
   * @param yPosition the y position to be updated to
   * @return the shape with the updated position
   */

  IShape updateLoc(double xPosition, double yPosition);

  /**
   * Returns a new shape with the same attributes as the current shape.
   *
   * @return new shape
   */

  IShape getNew();

  /**
   * Resets the shape to the shape's original properties in SVG format.
   *
   * @return SVG output of original shape property
   */

  String reset();

  /**
   * Retrieves the position representation of this shape.
   * @return position of the shape
   */

  Position getPos();

  /**
   * Retrieves the layer of the shape.
   *
   * @return layer of the shape
   */

  int getLayer();
}
