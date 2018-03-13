package cs3500.animator.controller;

import java.awt.*;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualAnimationView;

/**
 * Represents the controller for the Visual representations of the animations.
 */

public class VisualController implements IController {

  private IAnimationModel model;
  private IView view;
  private String type;
  private int speed;
  private  Color bC;

  /**
   * The controller for the Visual controller.
   *
   * @param model the model to be used in the animation
   * @param view  the view of the animation
   * @param type  the type of visual animation to be utilized
   * @param speed the speed of the animation
   */

  public VisualController(IAnimationModel model, IView view, String type, int speed, Color color) {
    this.model = model;
    this.view = view;
    this.type = type;
    this.speed = speed;
    this.bC = color;
  }

  @Override
  public void action() {
    switch (type) {
      case "visual":
        this.view = new VisualAnimationView(model.getField("shapes"), model.getField("moves"));
        view.updateSpeed(speed);
        break;
      case "hybrid":
        this.view = new HybridView(model.getField("shapes"), model.getField("moves"), bC);
        view.updateSpeed(speed);
        break;
      default:
        throw new IllegalArgumentException("wrong view");
    }

  }

  @Override
  public String getString() {
    return null;
  }
}
