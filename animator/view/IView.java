package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;

/**
 * Represents an interface for a view for the Simple Animator. This particular view is designed for
 * a string/textual representation of the Simple Animator and facilitates retrieving the status, or
 * the current representation of an animation, and updates the speed in which the view is performing
 * the animation at.
 */

public interface IView {

  /**
   * Produces a string representation of the current state of the animation.
   *
   * @return string representation of the current state of the aniamtion.
   */

  String getStatus(List<IShape> allShapes, List<IMove> allMoves, boolean enableLoop);

  /**
   * Updates the speed in which the animation view is performing at with the given speed.
   *
   * @param speed given speed to update the animation view with
   */

  void updateSpeed(int speed) throws UnsupportedOperationException;



}
