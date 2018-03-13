package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;

/**
 * Represents an abstract view. This view abstracts over the commonalities found in the views that
 * produce a visual representation of the animation made with the Easy Animator.
 */

public abstract class AView extends JFrame implements IView {

  protected List<IShape> allShapes;
  protected List<IMove> allMoves;
  protected AnimationPanel animationPanel;
  protected int speed;
  protected double curTimeInSec;

  /**
   * The constructor for AView.
   *
   * @param allShapes represents the shapes in the animation
   * @param allMoves  represents the moves in the animation
   */

  public AView(List<IShape> allShapes, List<IMove> allMoves) {
    allShapes.sort(new LayerComparator());
    this.allShapes = allShapes;
    this.allMoves = allMoves;
  }

  ActionListener listener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

      refresh();

    }
  };

  /**
   * Performs all of the moves that occur within a given time.
   *
   * @param currentTime represents the time in which a move should occur
   */

  protected void moveAll(double currentTime) {
    for (IMove curMove : allMoves) {
      int index = this.getIndex(curMove.getTarget());
      if (curMove.getST() <= currentTime && curMove.getET() >= currentTime) {
        curMove.updateTarget(allShapes.get(index));
        allShapes.set(index, curMove.change(currentTime));
      }
    }
  }


  /**
   * Retrieves the index of the given IShape in allShapes.
   *
   * @param s the IShape associated with the index to be found in allShapes
   * @return the index of this shape in allShapes
   * @throws IllegalArgumentException if the given shape is not in allShapes
   */

  protected int getIndex(IShape s) {

    int i = 0;
    int index = -1;

    while (index == -1 && i < allShapes.size()) {

      if (allShapes.get(i).getName().equals(s.getName())) {
        index = i;
        break;
      } else {
        i = i + 1;
      }
    }

    if (index != -1) {
      return index;
    } else {
      throw new IllegalArgumentException("This shape is not in the model.");
    }
  }

  /**
   * Increments the time used in the animation.
   */

  protected void incrementTime() {
    this.curTimeInSec = this.curTimeInSec + 1;
  }

  /**
   * Refreshes the animation which entails moving all animations, updating the animation panel and
   * repainting the animations.
   */

  protected void refresh() {
    incrementTime();
    this.moveAll(curTimeInSec);
    this.animationPanel.updateModel(allShapes, curTimeInSec);
    this.animationPanel.repaint();
  }

  @Override
  public void updateSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public String getStatus(List<IShape> allShapes, List<IMove> allMoves, boolean enableLoop) {
    return "";
  }



}
