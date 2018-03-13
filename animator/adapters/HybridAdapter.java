package cs3500.animator.adapters;

import java.util.List;

import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;
import cs3500.animator.provider.model.PublicAnimationOperations;
import cs3500.animator.provider.view.HybridView;
import cs3500.animator.view.IView;

/**
 * The adapter for the Hybrid view. This adapter helps map out the functions between the provided
 * hybrid view and our hybrid view.
 */

public class HybridAdapter extends HybridView implements IView {

  /**
   * The constructor for the HybridAdapter.
   *
   * @param am represents the model that is being utilized in order to construct the animation
   */

  public HybridAdapter(PublicAnimationOperations am) {
    super(am);
  }

  @Override
  public String getStatus(List<IShape> allShapes, List<IMove> allMoves, boolean enableLoop) {
    return null;
  }

  @Override
  public void updateSpeed(int speed) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

}
