package cs3500.animator.adapters;

import java.util.List;

import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;
import cs3500.animator.provider.model.PublicAnimationOperations;
import cs3500.animator.provider.view.SVGView;
import cs3500.animator.view.IView;

/**
 * Represents the adapter for the SVG view. This adapter maps out the functions between our SVG view
 * and the provided interface for the views of the Animator.
 */

public class SVGAdapter extends SVGView implements IView {

  /**
   * The constructor for the SVGAdapter.
   *
   * @param am represents the model that is being utilized in order to construct the animation
   */

  public SVGAdapter(PublicAnimationOperations am) {
    super(am);
  }

  @Override
  public String getStatus(List<IShape> allShapes, List<IMove> allMoves, boolean enableLoop) {
    return drawInitialState();
  }

  @Override
  public void updateSpeed(int speed) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
