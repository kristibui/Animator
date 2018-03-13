package cs3500.animator.view;

import java.util.List;

import cs3500.animator.adapters.HybridAdapter;
import cs3500.animator.adapters.ModelAdapter;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;

/**
 * Represents a View Creator. This class creates a particular type of view for the client to show
 * the animation. The types of views for an animation can be either a textual animation view, an SVG
 * animation view, or a visual animation view.
 */

public class ViewCreator {

  private static IAnimationModel model;

  /**
   * This constructor constructs the viewCreator with the given model as it's field.
   *
   * @param model IAnimationModel
   */
  public ViewCreator(IAnimationModel model) {
    this.model = model;
  }

  /**
   * Creates a new view. This method can create either a TextualView, an SVGView, or a
   * VisualAnimationView, based on the given string that corresponds to the type of view to be
   * made.
   *
   * @param view type of view to be made
   * @throws IllegalArgumentException if the string given does not correspond to any of the view
   *                                  types
   */

  public static IView createView(String view) {

    List<IShape> allShapes = model.getField("shapes");
    List<IMove> allMoves = model.getField("moves");
    switch (view) {
      case "text":
        return new TextualView();

      case "visual":
        return new VisualAnimationView(allShapes, allMoves);

      case "svg":
        return new SVGView();

      case "interactive":
        return new HybridView(allShapes, allMoves, model.getColor());
      case "provider":
        ModelAdapter providerView = new ModelAdapter(model);
        return new HybridAdapter(providerView);

      default:
        throw new IllegalArgumentException("Invalid view");
    }

  }
}

