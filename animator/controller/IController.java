package cs3500.animator.controller;

/**
 * Represents an interface for a controller for the Easy Animator. This interface has methods that
 * help facilitate interactions between the view and model.
 */

public interface IController {

  /**
   * Performs the animation, by utilizing the data from the model and passing the information to the
   * view given to the controller.
   */

  void action();

  /**
   * Performs the animation by utilizing the data from the model and passing it to the view, which
   * produces a string representation of the animation.
   *
   * @return string representation of the animation
   */

  String getString();

}
