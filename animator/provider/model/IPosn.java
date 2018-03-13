package cs3500.animator.provider.model;


/**
 * Representation of a position coordinate on the animation canvas.
 */
public interface IPosn {

  /**
   * Get the x-value of the position.
   *
   * @return the x-coordinate
   */
  float getX();

  /**
   * Get the y-value of the position.
   *
   * @return the y-coordinate
   */
  float getY();

  /**
   * Return a copy instance of this Position.
   *
   * @return an instance of IPosn
   */
  IPosn getCopy();

  /**
   * Move the x-coordinate of the Position by the given float value, where a positive value
   * represents a move to the right, and a negative value represents a move to the left.
   *
   * @param dx the amount the x-coordinate is moved.
   */
  void moveX(float dx);

  /**
   * Move the y-coordinate of the Position by the given float position, where a positive value
   * represents a move up, and a negative value represents a move down.
   * @param dy the amount the y-coordinate is moved.
   */
  void moveY(float dy);

}
