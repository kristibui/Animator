package cs3500.animator.provider.controller;

/**
 * An interface for animation controllers.
 */
public interface IController {

  /**
   * plays the animation.
   *
   * @return String (NOTE: Visual Controller's playMovie just returns an empty string).
   */
  String playMovie() throws InterruptedException;
}