package cs3500.animator.model;

import cs3500.animator.provider.model.IPosn;

/**
 * The Posn class that implements the IPosn interface. This class represents the position of the
 * shape.
 */

public class Posn implements IPosn {

  float xValue;
  float yValue;

  /**
   * The constructor for a Posn.
   *
   * @param xValue represents the x-coordinate of the shape
   * @param yValue represents the y-coordinate of the shape
   */

  public Posn(float xValue, float yValue) {
    this.xValue = xValue;
    this.yValue = yValue;
  }

  @Override
  public float getX() {
    return this.xValue;
  }

  @Override
  public float getY() {
    return this.yValue;
  }

  @Override
  public IPosn getCopy() {
    return new Posn(this.xValue, this.yValue);
  }

  @Override
  public void moveX(float dx) {
    this.xValue = this.xValue + dx;
  }

  @Override
  public void moveY(float dx) {
    this.yValue = this.yValue + dx;
  }
}
