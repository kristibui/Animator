package cs3500.animator.model;

/**
 * Represents the different types of positions that a shape can have in the Simple Animator.
 */

public enum Position {

  LL("Lower-left corner"), LR("Lower-right corner"), C("Center"),
  UL("Upper-left corner"), UR("Upper-right corner");

  private final String mess;

  /**
   * Constructor for a Position that initializes the string representation of the position by the
   * given string.
   *
   * @param mess string representation of the position of the shape
   */

  Position(String mess) {
    this.mess = mess;
  }

  /**
   * Produces a string representation of the position in the Position enum.
   *
   * @return string representation of the position
   */

  public String getMess() {
    return this.mess;
  }
}
