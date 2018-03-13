package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import cs3500.animator.provider.model.PublicAnimationOperations;

/**
 * A class to represent a Text View of an animation.
 */
public class TextView implements IView {

  public TextView(PublicAnimationOperations am) {
    this.am = am;
  }

  private PublicAnimationOperations am;

  @Override
  public void setButtonListener(ActionListener al) {
    // Do nothing
  }

  @Override
  public void setItemListener(ItemListener el) {
    // Do nothing
  }

  @Override
  public String drawInitialState() {
    return am.shapesDescription();
  }

  @Override
  public String drawFrame() {
    return am.commandsDescription();
  }

  @Override
  public boolean closed() {
    return true;
  }
}
