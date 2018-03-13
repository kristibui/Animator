package cs3500.animator.model;

import java.awt.*;
import java.util.List;

/**
 * Represents an interface of the overall Animation Model. This interface represents the different
 * types of actions necessary to facilitate the creation of a simple animation, which includes being
 * able to perform all the moves in an animation, adding shapes to an animation, and adding moves to
 * an animation.
 */

public interface IAnimationModel {

  /**
   * Moves all of the moves currently in the model that occur within a given time.
   *
   * @param currentTime represents the time in which a move should occur
   */

  void moveAll(double currentTime);

  /**
   * Adds a given shape to the model, as long as there are no existing shapes in the model that have
   * the same name as the given shape.
   *
   * @param s represents the shape to be added to the model
   * @throws IllegalArgumentException if the shape already exists in the model
   */

  void addShape(IShape s);

  /**
   * Adds a given move to the model, as long as the move is valid and the move does not overlap with
   * any of the moves already inside the model.
   *
   * @param m represents a move that the client is adding to the model
   * @throws IllegalArgumentException if the move overlaps with any other moves in the model
   */

  void addMove(IMove m);

  /**
   * Retrieves either the list of moves or list of shapes in the Animation Model. This method helps
   * retrieve the data in the model that is necessary for other parts of the Animator to utilize.
   *
   * @param s corresponds to the list that is to be retrieved
   * @return a list of either the moves or shapes in the Animation Model
   * @throws IllegalArgumentException if something other than the list of moves or shapes is
   *                                  requested
   */

  List getField(String s);

  /**
   * Retrieves the background color of the animation.
   *
   * @return the background color of the animation
   */

  Color getColor();


}
