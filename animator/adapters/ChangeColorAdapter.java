package cs3500.animator.adapters;

import java.awt.Color;

import cs3500.animator.model.ChangeColor;
import cs3500.animator.model.ShapeType;
import cs3500.animator.provider.model.ICommand;
import cs3500.animator.provider.model.IShape;

/**
 * Represents the adapter for changing a color of a shape. This adapter helps map out functions
 * between the provided code's way of changing a color of shape and our way of changing the color of
 * the shape.
 */

public class ChangeColorAdapter extends ChangeColor implements ICommand {

  /**
   * The constructor for the ChangeColorAdapter.
   *
   * @param target represents the shape that is changing color
   * @param sT     represents the starting time of the changing color
   * @param eT     represents the ending tiem of the changing color
   * @param color  represents the color that the shae is being changed to
   */

  public ChangeColorAdapter(cs3500.animator.model.IShape target,
                            double sT, double eT, Color color) {
    super(target, sT, eT, color);
  }

  @Override
  public void execute() {
    target.changeC(getToColor());
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
    return null;
  }

  @Override
  public ICommand getCopy(IShape shape) {
    throw new UnsupportedOperationException();
  }
}
