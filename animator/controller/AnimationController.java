package cs3500.animator.controller;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.view.IView;

/**
 * Represents a controller for the Easy Animator. This controller implements the IController
 * Interface. This controller facilitates interactions between the model and the view.
 */

public class AnimationController implements IController {

  private IAnimationModel model;
  private IView view;

  /**
   * The constructor for the Animation controller.
   *
   * @param model the model to be utilized in the simple animation
   * @param view  the view to be utilized in the simple animation
   */

  public AnimationController(IAnimationModel model, IView view) {
    this.model = model;
    this.view = view;

  }

  @Override
  public void action() {

    // Make sure that everything is initialized properly
    this.properlyInitialized(model, view);
    System.out.println(this.view.getStatus(model.getField("shapes"), model.getField("moves"),
            false));
  }

  @Override
  public String getString() {
    return this.view.getStatus(model.getField("shapes"), model.getField("moves"),false);
  }

  /**
   * Checks to make sure that the model, view and readable utilized in the controller have been
   * properly initialized.
   *
   * @param model the model to be utilized in the simple animation
   * @param view  the view to be utilized in the simple animation
   */

  private void properlyInitialized(IAnimationModel model, IView view) {

    // Check to make sure that the model isn't void
    if (this.model == null) {
      throw new IllegalArgumentException("Invalid animation: model cannot be null.");
    }

    if (this.view == null) {
      throw new IllegalArgumentException("Invalid animation: view cannot be null.");
    }

  }

}
