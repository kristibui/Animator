package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.util.TweenModelBuilder;

/**
 * This class implements the IAnimationModel interface. This class represents a simple animation and
 * it implements all the methods from the IAnimationModel interface, facilitating the creation of
 * new shapes and animations to the full animation.
 */

public class AnimationModel implements IAnimationModel {

  // To represent all the shapes in the animation
  private List<IShape> allShapes;

  // To represent all of the moves in the animation
  private List<IMove> allMoves;

  private Color bColor;

  /**
   * Represents a builder that facilitates a client's creation of a simple animation, so that the
   * client does not call the model's constructor directly. The builder handles the client's
   * creation of new shapes and new moves to be added to the animation.
   */

  public static final class Builder implements TweenModelBuilder<IAnimationModel> {

    // To represent all the shapes in the animation
    private List<IShape> allShapes = new ArrayList<>();

    // To represent all of the moves in the animation
    private List<IMove> allMoves = new ArrayList<>();

    private Color bColor = Color.WHITE;
    @Override
    public TweenModelBuilder<IAnimationModel> addOval(String name, float cx, float cy,
                                                      float xRadius, float yRadius, float red,
                                                      float green, float blue,
                                                      int startOfLife, int endOfLife, int layer) {

      IShape newOval = new Oval(name, Position.C, new Point2D.Double(cx, cy),
              new Color(red, green, blue), startOfLife, endOfLife, xRadius, yRadius, layer);

      if (newOval.canAdd(allShapes)) {
        allShapes.add(newOval);
      } else {
        throw new IllegalArgumentException("Can't add shape.");
      }

      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addRectangle(String name, float lx, float ly,
                                                           float width, float height, float red,
                                                           float green, float blue,
                                                           int startOfLife, int endOfLife, int layer) {

      IShape newRectangle = new Rectangle(name, Position.C, new Point2D.Double(lx, ly),
              new Color(red, green, blue), startOfLife, endOfLife, width, height, layer);

      if (newRectangle.canAdd(allShapes)) {
        allShapes.add(newRectangle);
      } else {
        throw new IllegalArgumentException("Can't add shape.");
      }

      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addMove(String name, float moveFromX, float moveFromY,
                                                      float moveToX, float moveToY,
                                                      int startTime, int endTime) {

      IShape curShape = this.getShape(name).updateLoc(moveFromX, moveFromY);

      IMove newMove = new ChangeLocation(curShape, startTime, endTime,
              new Point2D.Double(moveToX, moveToY));

      if (newMove.validMove(allMoves)) {
        this.allMoves.add(newMove);
      } else {
        throw new IllegalArgumentException("Can't add move.");
      }

      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addColorChange(String name, float oldR, float oldG,
                                                             float oldB, float newR, float newG,
                                                             float newB, int startTime,
                                                             int endTime) {

      int redColor = Double.valueOf(oldR).intValue();
      int greenColor = Double.valueOf(oldG).intValue();
      int blueColor = Double.valueOf(oldB).intValue();

      IShape curShape = this.getShape(name).updateColor(redColor, greenColor, blueColor);
      IMove newChangeColor = new ChangeColor(curShape, startTime, endTime,
              new Color(newR, newG, newB));

      if (newChangeColor.validMove(allMoves)) {
        this.allMoves.add(newChangeColor);
      } else {
        throw new IllegalArgumentException("Can't add move.");
      }

      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addScaleToChange(String name, float fromSx,
                                                               float fromSy, float toSx,
                                                               float toSy, int startTime,
                                                               int endTime) {

      IShape curShape = this.getShape(name).updateSize(fromSx, fromSy);

      IMove newChangeScale = new ChangeScale(curShape, startTime, endTime, toSx, toSy);

      if (newChangeScale.validMove(allMoves)) {
        this.allMoves.add(newChangeScale);
      } else {
        throw new IllegalArgumentException("Can't add move.");
      }

      return this;
    }

    @Override
    public IAnimationModel build() {

      return new AnimationModel(this);
    }

    @Override
    public void addColor(Color color) {
      this.bColor = color;
    }

    /**
     * Retrieves the shape from the shapes in the animation, based on the unique name of the shape.
     *
     * @param name name of the shape
     * @return shape that corresponds to the given name
     */

    private IShape getShape(String name) {

      // Initialize
      int pos = -1;

      for (int i = 0; i < allShapes.size(); i++) {

        if (allShapes.get(i).getName().equals(name)) {
          pos = i;
        }
      }

      if (pos == -1) {
        throw new IllegalArgumentException("Shape does not exist.");
      } else {
        return allShapes.get(pos);
      }
    }
  }

  /**
   * This is an empty constructor of the model where it initializes the field with empty arrays. The
   * client will not directly access this constructor, however; rather, the client will create a new
   * model through the builder.
   */

  public AnimationModel() {
    this.allShapes = new ArrayList<>();
    this.allMoves = new ArrayList<>();

  }

  /**
   * The constructor for an Animation Model. This constructor is called through the builder.
   *
   * @param b the builder that constructs the instance of the model and sets the shapes and moves
   */

  private AnimationModel(Builder b) {
    this.allShapes = b.allShapes;
    this.allMoves = b.allMoves;
    this.bColor = b.bColor;

  }

  @Override
  public void moveAll(double currentTime) {

    for (IMove curMove : allMoves) {
      int index = this.getIndexOfT(curMove.getTarget());
      if (curMove.getST() <= currentTime && curMove.getET() >= currentTime) {
        curMove.updateTarget(allShapes.get(index));
        allShapes.set(index, curMove.change(currentTime));
      } // if it's not in the time frame then you do nothing
    }
  }

  /**
   * Retrieves the index of the given IShape in allShapes.
   *
   * @param s the IShape associated with the index to be found in allShapes
   * @return the index of this shape in allShapes
   * @throws IllegalArgumentException if the given shape is not in allShapes
   */

  private int getIndexOfT(IShape s) {

    int i = 0;
    int index = -1;

    while (index == -1 || i < allShapes.size()) {

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
      throw new IllegalArgumentException("This shape is already in the model.");
    }
  }


  @Override
  public void addShape(IShape s) {
    boolean valid = s.canAdd(allShapes);
    if (valid) {
      allShapes.add(s);
    } else {
      throw new IllegalArgumentException("Invalid: the shapes overlap.");
    }
  }

  @Override
  public void addMove(IMove m) {
    if (m.validMove(allMoves)) {
      this.allMoves.add(m);
    } else {
      throw new IllegalArgumentException("Invalid: the moves overlap.");
    }
  }

  @Override
  public List getField(String s) {
    switch (s) {
      case "shapes":
        return this.allShapes;
      case "moves":
        return this.allMoves;
      default:
        throw new IllegalArgumentException("Invalid field given.");
    }
  }

  @Override
  public Color getColor() {
    return this.bColor;
  }


}
