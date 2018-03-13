package cs3500.animator.adapters;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IMove;
import cs3500.animator.model.ShapeType;
import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.ICommand;
import cs3500.animator.provider.model.IShape;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextualView;

/**
 * Represents the adapter for the Model for the Animator. This adapter maps the functions between
 * the provided model interface and our model.
 */

public class ModelAdapter extends AnimationModel implements AnimationOperations {
  IAnimationModel ourModel;
  Boolean loop;
  int time;
  List<IShape> allS;
  IAnimationModel originalModel;

  /**
   * The constructor for the ModelAdapter.
   *
   * @param ourModel represents the animation model to be used
   */

  public ModelAdapter(IAnimationModel ourModel) {
    this.ourModel = ourModel;
    this.loop = false;
    this.time = 0;
    this.allS = getShapes();
    this.originalModel = createO(ourModel);
  }

  /**
   * Creates a new instance of our model with the original list of shapes and moves.
   *
   * @param o the provided model to create based off of
   * @return new model with the original list of shapes and moves
   */

  private IAnimationModel createO(IAnimationModel o) {
    IAnimationModel oModel = new AnimationModel();
    for (int i = 0; i < o.getField("shapes").size(); i++) {
      oModel.addShape(((cs3500.animator.model.IShape) o.getField("shapes").get(i)).getNew());
    }
    for (int i = 0; i < o.getField("moves").size(); i++) {
      oModel.addMove(((IMove) o.getField("moves").get(i)).getNew());
    }
    return oModel;
  }

  @Override
  public void createAnimation() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addShape(IShape shape) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addCommand(ICommand command) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void increment() {
    ourModel.moveAll(time);
    this.time = this.time + 1;
  }

  @Override
  public boolean isComplete() {
    return false;
  }

  @Override
  public void pause() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void resume() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void restart() {
    time = 0;
    ourModel = createO(originalModel);
    this.allS = getShapes();
  }

  @Override
  public void toggleLoop() {
    loop = !loop;
  }

  @Override
  public void setTicksPerSecond(int ticksPerSecond) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String shapesDescription() {
    IView text = new TextualView();
    return text.getStatus(ourModel.getField("shapes"), ourModel.getField("moves"), loop);
  }

  @Override
  public String commandsDescription() {
    return null;
  }

  @Override
  public String textSVG() {
    String status = "";
    List<cs3500.animator.model.IShape> allShapes = ourModel.getField("shapes");
    List<cs3500.animator.model.IMove> allMoves = ourModel.getField("moves");
    for (int i = 0; i < allShapes.size(); i++) {
      cs3500.animator.model.IShape target = allShapes.get(i);
      status = status + allShapes.get(i).toSVG(this.getLoop()) + "\n";
      for (int j = 0; j < allMoves.size(); j++) {
        if (allMoves.get(j).getTarget().sameShape(target)) {
          status = status + allMoves.get(j).toSVG(this.getLoop()) + "\n";
        }
        if (j == allMoves.size() - 1) {
          switch (target.getType()) {
            case OVAL:
              status = status + target.reset() + "</ellipse>\n";
              break;
            case RECTANGLE:
              status = status + target.reset() + "</rect>\n";
              break;
            default:
              break;
          }
        }
      }
    }
    return status;
  }

  @Override
  public ArrayList<IShape> getShapes() {
    ArrayList<IShape> newList = new ArrayList<IShape>();
    List<cs3500.animator.model.IShape> ourList = ourModel.getField("shapes");
    for (cs3500.animator.model.IShape s : ourList) {
      if (s.getType().equals(ShapeType.RECTANGLE)) {
        newList.add(new RectangleAdapter(s.getName(), s.getPos(), s.getLoc(), s.getC(),
                s.getST(), s.getET(), s.getWX(), s.getHY()));
      } else {
        newList.add(new OvalAdapter(s.getName(), s.getPos(), s.getLoc(), s.getC(),
                s.getST(), s.getET(), s.getWX(), s.getHY()));
      }
    }
    return newList;
  }

  @Override
  public int getTick() {
    return this.time;
  }

  @Override
  public boolean getLoop() {
    return loop;
  }

  @Override
  public void incrementSpeed() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void decrementSpeed() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getSpeed() {
    return 1;
  }

  @Override
  public void drawShapes(Graphics g) {
    Graphics2D graphic = (Graphics2D) g;
    graphic.setBackground(Color.WHITE);
    List<IShape> allShapes = this.getShapes();

    for (int i = 0; i < allShapes.size(); i++) {

      allShapes.get(i).draw(g);
    }
  }


  @Override
  public void changeVisibility(HashMap<String, Boolean> map) {
    for (int i = 0; i < this.getShapes().size(); i++) {
      cs3500.animator.model.IShape s = (cs3500.animator.model.IShape) allS.get(i);
      if (s.getST() <= time && s.getET() >= time && !allS.get(i).isVisible()) {
        allS.get(i).toggleVisibility();
      } else if (!(s.getST() <= time && s.getET() >= time) && allS.get(i).isVisible()) {
        allS.get(i).toggleVisibility();
      }
    }
    increment();
  }

  @Override
  public int getEndTime() {
    return Math.round(Double.valueOf(this.getLastTime()).floatValue());
  }

  private double getLastTime() {
    double lastTime = 0;
    List<cs3500.animator.model.IShape> allShapes = ourModel.getField("shapes");
    for (cs3500.animator.model.IShape s : allShapes) {
      if (s.getET() > lastTime) {
        lastTime = s.getET();
      }
    }
    return lastTime;
  }
}
