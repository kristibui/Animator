package cs3500.animator.provider.model;


/**
 * The interface for an animation. An animation is defined by the change of a shape (instance of the
 * IShape interface) over a period of time.
 */
public interface ICommand {

  /**
   * Modify the shape so that the shape follows the animations at the next interval, and then
   * increment the current time interval by 1. This will return an error if the current interval is
   * already at the end interval, since the animation has ended.
   */
  void execute();

  /**
   * Get the start time interval of the animation.
   *
   * @return the start time value
   */
  int getStartTime();

  /**
   * Get the end time interval of the animation.
   *
   * @return the end time value
   */
  int getEndTime();

  /**
   * Get the subject of this animation, which is represented as a Shape object.
   *
   * @return an instance of a Shape
   */
  IShape getSubject();

  /**
   * Give the string representation of the animation.
   *
   * @return string representation of the ICommand implementation
   */
  String stringValue();

  /**
   * Formats a command into an SVG format.
   *
   * @return String representation of command in SVG format.
   */
  String commandTextSVG(int ticksPerSecond);

  /**
   * Get a copy of the given shape's command.
   * @return ACommand
   */
  ICommand getCopy(IShape shape);

}
