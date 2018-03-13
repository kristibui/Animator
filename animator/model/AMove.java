package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * This is an abstract class abstracting the code from all the classes that implements IMove. This
 * class also abstracts fields to reduce redundant code. The class represents the commonalities
 * associated with a type of move that can be performed in a simple animation.
 */

public abstract class AMove implements IMove {

  protected IShape target;
  protected double startT;
  protected double endT;
  protected double duration;

  /**
   * The constructor for all the moves that extends from AMove, initializing the fields with the
   * given input.
   *
   * @param target represents an IShape that the move is performed on
   * @param sT     represents the starting time of the move
   * @param eT     represents the ending time of the move
   */

  public AMove(IShape target, double sT, double eT) {
    this.target = target;
    this.startT = sT;
    this.endT = eT;
    this.duration = eT - sT;
  }


  @Override
  public double getST() {
    return this.startT;
  }

  @Override
  public double getET() {
    return this.endT;
  }

  @Override
  public IShape getTarget() {
    return this.target;
  }

  @Override
  public Boolean overlapT(IMove m) {
    return (m.getST() < this.endT) && (this.startT < m.getET());
  }

  @Override
  public Boolean validMove(List<IMove> m) {
    boolean valid = true;
    for (int i = 0; i < m.size(); i++) {
      IMove compareM = m.get(i);
      if (target.sameShape(compareM.getTarget()) && compareM.getType() == this.getType()) {
        valid = valid && !compareM.overlapT(this);
      } // skip this move in the list because it's not the same move on the same shape
    }
    return valid;
  }

  @Override
  public void updateTarget(IShape s) {
    this.target = s;
  }

  @Override
  public Point2D getToLoc() {
    throw new IllegalArgumentException("Not a change location move");
  }

  @Override
  public Double getTo(String s) {
    throw new IllegalArgumentException("Not a change scale move");
  }

  @Override
  public Color getToColor() {
    throw new IllegalArgumentException("Not a change color move");
  }


}
