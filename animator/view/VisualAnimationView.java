package cs3500.animator.view;

import java.awt.*;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.util.List;

import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;

/**
 * Represents the Visual Animation View for the Simple Animator. This view implements the
 * IAnimationView interface and utilizes Java Swing to draw all of the moves and shapes in the
 * animation.
 */

public class VisualAnimationView extends AView {

  /**
   * The constructor for the Visual Animation View.
   *
   * @param allShapes represents the shapes in the animation
   * @param allMoves  represents the moves in the animation
   */

  public VisualAnimationView(List<IShape> allShapes, List<IMove> allMoves) {
    super(allShapes, allMoves);
    this.speed = 1;
    Timer t = new Timer(100 / speed, listener);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setSize(1000, 800);
    this.curTimeInSec = 0;

    this.animationPanel = new AnimationPanel(allShapes, curTimeInSec, Color.WHITE);
    this.setLayout(new BorderLayout());
    animationPanel.setPreferredSize(new Dimension(500, 500));

    JScrollPane scrollBar = new JScrollPane(animationPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    this.add(scrollBar, BorderLayout.CENTER);

    t.start();



  }

}
