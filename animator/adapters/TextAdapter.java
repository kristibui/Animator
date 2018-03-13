package cs3500.animator.adapters;

import java.util.List;

import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;
import cs3500.animator.provider.model.PublicAnimationOperations;
import cs3500.animator.provider.view.TextView;
import cs3500.animator.view.IView;

/**
 * Represents the adapter for the Textual view. This adapter maps out the functions between our
 * Textual view and the provided interface for the views of the Animator.
 */

public class TextAdapter extends TextView implements IView {

  /**
   * The constructor for the TextAdapter.
   *
   * @param am represents the model that is being utilized in order to construct the animation
   */

  public TextAdapter(PublicAnimationOperations am) {
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
