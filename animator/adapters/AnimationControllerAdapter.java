package cs3500.animator.adapters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import cs3500.animator.model.IAnimationModel;

/**
 * Represents the adapter for the controller for the Animator. This adapter helps map functions
 * between our provided code and our code, so that this controller can facilitate interactions
 * between our providers' model and view.
 */

public class AnimationControllerAdapter implements cs3500.animator.controller.IController {

  IAnimationModel model;
  cs3500.animator.provider.view.IView view;
  int speed;
  Timer t;
  int cur;

  /**
   * The constructor for the AnimationControllerAdapter.
   *
   * @param model our model to be used in the Animator
   * @param view  the provided view to be used in the Animator
   * @param speed the speed of the animation
   */

  public AnimationControllerAdapter(IAnimationModel model,
                                    cs3500.animator.provider.view.IView view, int speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
    this.cur = 0;
    ActionListener listener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cur = cur + 1;
        view.drawFrame();
        if (cur > ((ModelAdapter) model).getEndTime()
                && ((ModelAdapter) model).getLoop()) {
          cur = 0;
          ((ModelAdapter) model).restart();
          view.drawInitialState();
          t.restart();
          t.start();
        }

      }
    };
    ActionListener buttonListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        helper(e);
      }
    };

    view.setButtonListener(buttonListener);

    this.t = new Timer(100 / speed, listener);
  }

  @Override
  public void action() {
    // Do nothing because this method is used to start the animation but in this case the animation
    // starts when we initialize the constructor.
  }

  /**
   * This helper method helps facilitate the commands that the user performs in the Hybrid View.
   *
   * @param e the command that the user performs
   */

  private void helper(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Start":
        t.start();
        break;
      case "Pause":
        t.stop();
        break;
      case "Restart":
        ((ModelAdapter) model).restart();
        view.drawInitialState();
        t.restart();
        break;
      case "+Speed":
        speed = speed + 1;
        t.setDelay(100 / this.speed);
        break;
      case "-Speed":
        speed = speed - 1;
        t.setDelay(100 / this.speed);
        break;
      case "Loop":
        ((ModelAdapter) model).toggleLoop();
        break;
      default:
        throw new IllegalArgumentException("Invalid command.");
    }
  }

  @Override
  public String getString() {
    throw new UnsupportedOperationException();
  }
}
