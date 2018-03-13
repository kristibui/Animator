package cs3500.animator.provider.model;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An interface for views that hides some of the model's functionality and fields from the view and
 * user.
 */
public interface PublicAnimationOperations {

  /**
   * pause the ticking of the animation.
   */
  void pause();

  /**
   * continue the animation.
   */
  void resume();

  /**
   * restart the animation from the beginning at any point during the animation.
   */
  void restart();

  /**
   * change whether the animation should loop or not.
   */
  void toggleLoop();

  /**
   * set the tempo of the animation through ticks per second.
   */
  void setTicksPerSecond(int ticksPerSecond);

  /**
   * gets the descriptions of shapes for text view.
   *
   * @return Returns the string representation of the shapes.
   */
  String shapesDescription();

  /**
   * gets the descriptions of commands for text view.
   *
   * @return Returns the String representation of all commands that start at the current tick.
   */
  String commandsDescription();

  /**
   * get the text description for an SVG format.
   *
   * @return String representation of the SVG output.
   */
  String textSVG();


  /**
   * gets a copy of the shapes in the model.
   *
   * @return an array list of copies of shapes in the model.
   */
  ArrayList<IShape> getShapes();

  /**
   * Returns the current tick.
   */
  int getTick();

  /**
   * Returns the loop toggle.
   */
  boolean getLoop();

  /**
   * increase the speed of the animation by one tick per second.
   */
  void incrementSpeed();

  /**
   * decrease the speed of the animation by one tick per second.
   */
  void decrementSpeed();

  /**
   * get the speed of the animation.
   */
  int getSpeed();

  /**
   * draw a shape onto the given graphic.
   *
   * @param g the graphic to be drawn on.
   */
  void drawShapes(Graphics g);

  /**
   * set the visibility of the shapes in the animation.
   * @param map the hash table of shapes to their visibilities.
   */
  void changeVisibility(HashMap<String, Boolean> map);

  /**
   * gets the end time of the animation.
   * @return    end time of animation as an integer.
   */
  int getEndTime();
}
