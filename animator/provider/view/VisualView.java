package cs3500.animator.provider.view;


import java.awt.Graphics;

import java.awt.event.ActionListener;

import java.awt.event.ItemListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.PublicAnimationOperations;

/**
 * A class to represent a visual view.
 */
public class VisualView extends JFrame implements IView {

  /**
   * Constructor for a visual view. Sets up the animation screen.
   *
   * @param am the model to construct a view for.
   */
  public VisualView(PublicAnimationOperations am) {
    super();

    this.setTitle("Animation!");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    animationScreen = new AnimationPanel();
    animationScreen.setPreferredSize(new Dimension(800, 1100));
    this.add(animationScreen, BorderLayout.CENTER);

    JScrollPane mainScrollPane = new JScrollPane(animationScreen);
    mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    mainScrollPane.setPreferredSize(new Dimension(850, 900));
    this.add(mainScrollPane, BorderLayout.CENTER);

    this.am = am;

    this.pack();
    this.setVisible(true);

    ArrayList<IShape> shapes = am.getShapes();
    for (IShape s : shapes) {
      shapesToBoolean.put(s.getName(), Boolean.TRUE);
    }
  }

  public HashMap<String, Boolean> shapesToBoolean = new HashMap<>();
  private AnimationPanel animationScreen;
  private PublicAnimationOperations am;

  ///////////////////////////////////////////////////

  /**
   * Represents an Animation Panel on which the animation is drawn.
   */
  public class AnimationPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      am.drawShapes(g);

    }
  }
  ////////////////////////////////////////////////////


  @Override
  public void setButtonListener(ActionListener al) {
    // Do nothing
  }

  @Override
  public void setItemListener(ItemListener el) {
    // Do nothing
  }

  /**
   * Play the animation from start to finish.
   */
  @Override
  public String drawInitialState() {
    throw new UnsupportedOperationException("Visual View does not draw initial state.");
  }

  /**
   * Draw the frame of the current tick given by the given model.
   */
  @Override
  public String drawFrame() {

    am.changeVisibility(shapesToBoolean);
    this.animationScreen.repaint();

    return "";
  }

  @Override
  public boolean closed() {
    return false;
  }
}
