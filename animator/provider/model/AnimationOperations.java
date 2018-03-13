package cs3500.animator.provider.model;

/**
 * The interface for the  model of an animation. The model contains
 * a collection of shapes and collection of commands. Each shape
 * represents one shape that appears in the animation, and each command
 * represents a command that will be executed over the course of the animation.
 */
public interface AnimationOperations extends PublicAnimationOperations {

  /**
   * Start a new animation with an empty canvas (no shapes and no animations). The time interval is
   * initialized to 0.
   */
  void createAnimation();

  // Change: Adding methods that allow the model to take in individual shapes and animation
  // commands and also remove a shape from the model.

  /**
   * Add a shape to the model.
   */
  void addShape(IShape shape);

  /**
   * Add a animation command to the model.
   */
  void addCommand(ICommand command);

  /**
   * Increment the model to the next state.
   */
  void increment();

  /**
   * Checks if the animation if over that the current state.
   */
  boolean isComplete();
}
