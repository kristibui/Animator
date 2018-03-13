package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JPanel;

import java.util.List;

import cs3500.animator.model.IShape;

/**
 * Represents an AnimationPanel, utilized in the Visual Animation View for the Simple Animator. This
 * panel represents the region where the animation drawn and performed.
 */

public class AnimationPanel extends JPanel {

  private List<IShape> allShapes;
  private double time;
  private Color bC;

  /**
   * The constructor for the Animation Panel.
   *
   * @param allShapes the shapes to be drawn in the animation
   * @param currTime  the current time of the animation
   */

  public AnimationPanel(List<IShape> allShapes, double currTime, Color bC) {
    this.allShapes = allShapes;
    this.time = currTime;
    this.bC = bC;
  }

  /**
   * Updates the model and time stored in this Animation Panel with the given model and time. This
   * method is called when the Visual Animation View refreshes, so that this panel can keep track of
   * the current time of the Animation and state of the model at that given time.
   *
   * @param allShapes the shapes to be drawn in the animation
   * @param t         time of the animation to be updated to
   */

  public void updateModel(List<IShape> allShapes, double t) {
    this.allShapes = allShapes;
    this.time = t;

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D graphic = (Graphics2D) g;
    graphic.setBackground(this.bC);


    for (int i = 0; i < allShapes.size(); i++) {

      if (allShapes.get(i).getST() <= this.time && allShapes.get(i).getET() >= this.time) {

        IShape s = allShapes.get(i);
        Double x = s.getLoc().getX();
        Double y = s.getLoc().getY();
        Double w = s.getWX();
        Double h = s.getHY();
        switch (s.getType()) {
          case OVAL:

            g.setColor(s.getC());
            g.fillOval(x.intValue(), y.intValue(), w.intValue(), h.intValue());
            break;

          case RECTANGLE:

            g.setColor(s.getC());
            g.fillRect(x.intValue(), y.intValue(), w.intValue(), h.intValue());
            break;

          default:
            break;
        }
      }
    }
  }


}
