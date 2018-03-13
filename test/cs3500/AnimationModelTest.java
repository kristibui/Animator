package cs3500;

import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ChangeColor;
import cs3500.animator.model.ChangeLocation;
import cs3500.animator.model.ChangeScale;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Position;
import cs3500.animator.model.Rectangle;
import cs3500.animator.util.TweenModelBuilder;
import cs3500.animator.view.TextualView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Animation Model. These test various functions of the Animation Model, including
 * making sure that the model properly facilitates adding new shapes and animations and making sure
 * that the added shapes and animations are valid.
 */

public class AnimationModelTest {
  Color colorForC = new Color(0, 0, 1);
  Color colorC1 = new Color(0, 100, 0);
  Color colorForR = new Color(1, 0, 0);
  Point2D dPForR = new Point2D.Double(200.0, 200.0);
  Point2D dPForC = new Point2D.Double(500.0, 100.0);
  Point2D dPRM1 = new Point2D.Double(300.0, 300.0);
  Point2D dPOM1 = new Point2D.Double(500.0, 400.0);
  IShape r1 = new Rectangle("R", Position.LL, dPForR, colorForR,
          1, 100, 50.0, 100.0,1);
  IShape o1 = new Oval("C", Position.C, dPForC, colorForC, 6, 100, 60, 30,1);
  IMove rM1 = new ChangeLocation(r1, 10, 50, dPRM1);
  IMove oM1 = new ChangeLocation(o1, 20, 70, dPOM1);
  IMove rM2 = new ChangeLocation(r1, 70, 100, dPForR);
  IMove oC1 = new ChangeColor(o1, 20, 40, colorC1);
  IMove rS1 = new ChangeScale(r1, 51, 70, 25.0, 100.0);
  IAnimationModel model1 = new AnimationModel();
  TextualView tV1 = new TextualView();
  TweenModelBuilder<IAnimationModel> modelB = new AnimationModel.Builder();

  IAnimationModel modelWithB = modelB.build();


  /**
   * This method initializes the model by adding valid shapes and moves.
   */

  public void initialize() {
    model1.addShape(r1);
    model1.addShape(o1);
    model1.addMove(rM1);
    model1.addMove(rM2);
    model1.addMove(oM1);
    model1.addMove(oC1);
    model1.addMove(rS1);
  }

  // Testing moveAll method by checking if the string representation of this model is correct
  // the tests checks the output of the model through the textual view of this model
  @Test
  public void testMoveAll() {
    this.initialize();
    model1.moveAll(0);
    assertEquals("Shapes:\nName: R\nType: rectangle\nLower-left corner: (200.0,200.0),"
            + " Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" + "Appears at t=1.0s\n"
            + "Disappears at t=100.0s\n\nName: C\nType: oval\nCenter: (500.0,100.0), " +
            "X radius: 60.0,"
            + " Y radius: 30.0, Color: (0.0,0.0,1.0)\nAppears at t=6.0s\nDisappears at t=100.0s\n"
            + "\nShape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s\n"
            + "Shape R moves from (200.0,200.0) to (200.0,200.0) from t=70.0s to t=100.0s\n"
            + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20.0s to t=70.0s\n"
            + "Shape C changes color from (0.0,0.0,1.0) to (0.0,100.0,0.0) from t=20.0s " +
            "to t=40.0s\n"
            + "Shape R scales from Width: 50.0 Height: 100.0 to Width: 25.0 Height: 100.0 "
            + "from t=51.0s to t=70.0s", tV1.getStatus(model1.getField("shapes"),
            model1.getField("moves"), false));
    // moves all the shapes at time 15 based on the moves
    model1.moveAll(15);
    // checking does the move actually updates R's location
    assertEquals(new Point2D.Double(212.5, 212.5), r1.getLoc());

  }

  // Testing addShape method by successfully adding a shape and trying to add an overlapping shape
  // the tests checks the output of the model through the textual view of this model
  @Test(expected = IllegalArgumentException.class)
  public void testAddShape() {
    model1.addShape(o1);
    model1.addShape(o1); // can't be added since there's overlapping shapes
    assertEquals("Shapes:\n" + "Type: oval\n" + "Center: (500.0,100.0), X radius: 60.0, "
            + "Y radius: 30.0, Color: (0.0,0.0,1.0)\n" + "Appears at t=6\n"
            + "Disappears at t=100\n", tV1.getStatus(model1.getField("shapes"),
            model1.getField("moves"), false));

  }

  // Testing addMove method by successfully adding a move and trying to add an overlapping move
  // the tests checks the output of the model through the textual view of this model
  @Test(expected = IllegalArgumentException.class)
  public void testAddMove() {
    model1.addMove(rM1);
    model1.addMove(rM1); // Illegal move cuz it overlaps
    assertEquals("Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50",
            tV1.getStatus(model1.getField("shapes"), model1.getField("moves"), false));
  }

  // Testing getField method by checking the output arraylist size with the correct size.
  @Test
  public void testGetField() {
    model1.addMove(rM1);
    assertEquals(0, model1.getField("shapes").size());
    assertEquals(1, model1.getField("moves").size());
    assertEquals(rM1, model1.getField("moves").get(0));
  }

  // Testing addRectangle method through the builder throws an Illegal Argument exception if this
  // shape is an overlapping shape
  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangle() {
    modelB.addRectangle("R", 6, 7, 9, 10, 1
            , 0, 0, 2, 3,1);
    modelB.addRectangle("R", 7, 8, 10, 111, 0, 0, 1
            , 30, 100,1); // can't add this since it's an overlapping rectangle
    assertEquals(1, modelWithB.getField("shapes").size());
  }

  // Testing addOval method through the builder throws an Illegal Argument exception if this
  // shape is an overlapping shape
  @Test(expected = IllegalArgumentException.class)
  public void testAddOval() {
    modelB.addOval("C", 6, 7, 9, 10, 1
            , 0, 0, 2, 3,1);
    modelB.addOval("L", 6, 7, 9, 10, 1
            , 0, 0, 2, 3,1);
    modelB.addOval("C", 7, 8, 10, 111, 0, 0, 1
            , 30, 100,1); // can't add this since it's an overlapping Oval
    assertEquals(2, modelWithB.getField("shapes").size());

  }

  // Testing addMove method through the builder throws an Illegal Argument exception if this
  // move is an overlapping move
  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveB() {
    modelB.addRectangle("R", 6, 7, 9, 10, 1
            , 0, 0, 2, 3,1);
    modelB.addMove("R", 6, 7, 8, 9, 1
            , 1);
    modelB.addMove("R", 6, 7, 8, 9, 1
            , 1); // throws error since it's an overlapping move
    modelB.addMove("M2", 6, 7, 8, 9, 1
            , 1); // throws error since no such shape exists
    assertEquals(1, modelWithB.getField("moves").size());

  }

  // Testing addColorChange method through the builder throws an Illegal Argument exception if this
  // move is an overlapping move
  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChange() {
    modelB.addRectangle("R", 6, 7, 9, 10, 1
            , 0, 0, 2, 3,1);
    modelB.addColorChange("R", 1, 1, 1, 0, 0, 1
            , 10, 100);
    modelB.addColorChange("R", 1, 1, 1, 0, 0, 1
            , 15, 80); // throws error since it's an overlapping move
    modelB.addColorChange("R3", 1, 1, 1, 0, 0, 1
            , 10, 100); // throws error since no such shape exists
    modelB.addColorChange("R", 1, 0, 0, 1, 0, 1
            , 1, 5);
    assertEquals(2, modelWithB.getField("moves").size());


  }

  // Testing addScaleToChange method through the builder throws an Illegal Argument exception if
  // this move is an overlapping move
  @Test(expected = IllegalArgumentException.class)
  public void testAddScaleToChange() {
    modelB.addOval("C", 6, 7, 9, 10, 1
            , 0, 0, 2, 3,1);
    modelB.addScaleToChange("C", 6, 7, 8, 9, 1
            , 10);
    modelB.addScaleToChange("C", 8, 9, 100, 50, 11
            , 58);
    modelB.addScaleToChange("C", 6, 7, 8, 9, 1
            , 5); // throws error since it's an overlapping move
    modelB.addScaleToChange("R", 6, 7, 8, 9, 1
            , 1); // throws error since no such shape exists
    assertEquals(2, modelWithB.getField("moves").size());
  }


}
