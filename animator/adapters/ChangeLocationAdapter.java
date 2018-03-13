package cs3500.animator.adapters;

import java.awt.geom.Point2D;

import cs3500.animator.model.ChangeLocation;
import cs3500.animator.model.ShapeType;
import cs3500.animator.provider.model.ICommand;
import cs3500.animator.provider.model.IShape;

/**
 * Represents the adapter for changing the location of a shape. This adapter helps map out functions
 * between the provided code's way of changing the location of a shape and our way of changing the
 * location of a shape.
 */

public class ChangeLocationAdapter extends ChangeLocation implements ICommand {

  /**
   * The constructor for the ChangeLocationAdapter.
   *
   * @param target represents the shape that is changing location
   * @param sT     represents the starting time of the changing of location
   * @param eT     represents the ending time of the changing of location
   * @param to     the final position that the shape is moving to
   */

  public ChangeLocationAdapter(cs3500.animator.model.IShape target,
                               double sT, double eT, Point2D to) {
    super(target, sT, eT, to);
  }

  @Override
  public void execute() {
    target.moveLoc(getToLoc().getX(), getToLoc().getY());
  }

  @Override
  public int getStartTime() {

    return Math.round(Double.valueOf(getST()).floatValue());
  }

  @Override
  public int getEndTime() {
    return Math.round(Double.valueOf(getET()).floatValue());
  }

  @Override
  public IShape getSubject() {
    if (target.getType().equals(ShapeType.RECTANGLE)) {
      return new RectangleAdapter(target.getName(), target.getPos(), target.getLoc(), target.getC(),
              target.getST(), target.getET(), target.getWX(), target.getHY());
    } else {
      return new OvalAdapter(target.getName(), target.getPos(), target.getLoc(), target.getC(),
              target.getST(), target.getET(), target.getWX(), target.getHY());
    }
  }

  @Override
  public String stringValue() {
    return this.toString();
  }

  @Override
  public String commandTextSVG(int ticksPerSecond) {
    return "";
  }

  @Override
  public ICommand getCopy(IShape shape) {
    throw new UnsupportedOperationException();
  }
}
