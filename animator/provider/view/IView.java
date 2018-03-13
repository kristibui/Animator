package cs3500.animator.provider.view;


import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * Represents the view interface for the animator.
 */
public interface IView {

  /**
   * Set the given ActionListener to the buttons of the Hybrid View.
   *
   * @param al an instance of ActionListener
   */
  public void setButtonListener(ActionListener al);

  /**
   * Set the given ItemListener to the checkboxes of the Hybrid View.
   *
   * @param el an instance of ItemListener
   */
  public void setItemListener(ItemListener el);

  /**
   * Play the animation from start to finish.
   */
  public String drawInitialState();


  /**
   * Draw the frame of the current tick given by the given model.
   */
  public String drawFrame();

  /**
   * a method to check if the window has been exited.
   * @return boolean to represent if window has been exited.
   */
  public boolean closed();

}
