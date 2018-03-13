package cs3500.animator.provider.model;



import java.awt.Graphics;

/**
 * The interface for a 2-dimensional shape. The shape should always have a name, a position used for
 * reference during animations, a color represented by a array of floats, and two dimensional values
 * (one along the x-axis and one along the y-axis).
 */
public interface IShape {

  /**
   * Move the shape by the given x and y offset values. The inputs represent the amount of change
   * from the old position to the new position.
   */
  void move(float dx, float dy);

  /**
   * Change the color of the shape to the given color.
   *
   * @param c an instance of the Color class
   */
  void changeColor(float[] c);

  /**
   * Change the dimension of the shape based on the given inputs. The inputs represent the amount of
   * change the dimensions will go through. A negative input represents a decrease in that specific
   * dimension and a positive input represents an increase in that specific dimension.
   *
   * @param dx the change in the dimensions parallel to the x-axis
   * @param dy the change in the dimensions parallel to the y-axis
   */
  void changeDimension(float dx, float dy);

  /**
   * Give the string representation of the shape and its fields.
   *
   * @return string representation of the IShape implementation
   */
  String stringValue();

  /**
   * Give the string representation of a shape's dimensions, using the given input.
   *
   * @return string representation of the shape's dimensions
   */
  String stringDimensions(float xValue, float yValue);

  /**
   * Returns whether this shape appears at the given time interval.
   *
   * @param time the given time interval
   * @return the boolean value of whether the shape appears at the given time.
   */
  boolean appearsAt(int time);

  /**
   * Get the current position of the shape.
   *
   * @return the Posn value of the shape's location
   */
  IPosn getPosition();

  /**
   * Get the name of the shape.
   *
   * @return the string value of the shape's name
   */
  String getName();

  /**
   * Get the color of the shape.
   *
   * @return the Color value of the shape's name
   */
  float[] getColor();

  /**
   * Get the dimension along the x-axis.
   *
   * @return a float value
   */
  float getXValue();

  /**
   * Get the dimension along the y-axis.
   *
   * @returna float value
   */
  float getYValue();

  // Change: Added this method so that we can compare shapes with one another.

  /**
   * Checks if the given shape is equal to this shape.
   *
   * @param other an instance of AShape
   * @return whether the two shapes are equal
   */
  boolean shapeEquals(IShape other);


  /**
   * Returns a copy of this shape.
   *
   * @return IShape copy of this shape.
   */
  IShape getCopy();

  /**
   * Draw this shape onto an input graphics.
   *
   * @param g the graphic to be drawn on.
   */
  void draw(Graphics g);

  /**
   * format this shape's parameters to be in SVG format.
   *
   * @return String representation of this shape in SVG format.
   */
  String shapeTextSVG();

  /**
   * change the visibility field of a shape.
   */
  void toggleVisibility();

  /**
   * returns whether a shape is visible.
   * @return boolean value whether a shape is visible.
   */
  boolean isVisible();
}
