package cs3500.animator.adapters;

import cs3500.animator.model.ChangeScale;
import cs3500.animator.model.ShapeType;
import cs3500.animator.provider.model.ICommand;
import cs3500.animator.provider.model.IShape;

/**
 * Represents the adapter for changing the dimensions of a shape. This adapter helps map out
 * functions between the provided code's way of changing a shape's dimensions and our way of
 * changing the dimensions of a shape.
 */

public class ChangeScaleAdapter extends ChangeScale implements ICommand {

  /**
   * The constructor for ChangeScaleAdapter.
   *
   * @param target represents the shape that is being changed
   * @param sT     represents the starting time of the changing of dimensions
   * @param eT     represents the ending time of the changing of dimensions
   * @param toWX   represents the width or x-radius the shape is being changed to
   * @param toHY   represents the height or y-radius the shape is being changed to
   */

  public ChangeScaleAdapter(cs3500.animator.model.IShape target,
                            double sT, double eT, double toWX, double toHY) {
    super(target, sT, eT, toWX, toHY);
  }

  @Override
  public void execute() {
    target.scale(getTo("WX"), getTo("HY"));
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
