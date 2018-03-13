package cs3500.animator.view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cs3500.animator.model.IMove;
import cs3500.animator.model.IShape;


/**
 * Represents the Hybrid View. This view for the Easy Animator produces a visual representation of
 * the animation utilizing Java Swing, and offers functions for the user that they can perform on
 * the animation, which includes starting, pausing, speeding up and allowing the user to choose
 * which shapes to be displayed. This view also allows for the user to save the animation as an SVG
 * file, which can hence be viewed in a browser.
 */

public class HybridView extends AView {


  private List<IShape> oShape;
  private Timer t;
  private boolean enableLoop;
  private JTextField input;
  private List<IMove> oMove;
  private java.awt.Color bC;

  /**
   * The constructor for the Hybrid View. This constructor sets up all of the buttons for the
   * different types of functions offered in the Hybrid View.
   *
   * @param allShapes all of the possible shapes that can be displayed in the animation
   * @param allMoves  all of the possible moves that can be displayed in the animation
   */

  public HybridView(List<IShape> allShapes, List<IMove> allMoves, java.awt.Color color) {
    super(allShapes, allMoves);
    this.oShape = this.getOShapes(allShapes);
    enableLoop = true;
    this.oMove = getOMove(allMoves);
    this.speed = 1;
    this.bC = color;
    t = new Timer(100 / speed, listener);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setSize(1000, 800);
    this.curTimeInSec = 0;

    this.animationPanel = new AnimationPanel(allShapes, curTimeInSec, bC);
    this.setLayout(new BorderLayout());
    animationPanel.setPreferredSize(new Dimension(500, 500));
    animationPanel.setBackground(bC);

    //button panel
    Box buttonPanel = new Box(BoxLayout.Y_AXIS);
    buttonPanel.setSize(100, 100);
    // start button
    JButton start = new JButton("Start");
    start.addActionListener((ActionEvent e) -> {
      t.start();
    });
    buttonPanel.add(start);

    // restart button
    JButton restart = new JButton("Restart");
    restart.addActionListener((ActionEvent e) -> {
      this.initialize();
    });

    buttonPanel.add(restart);

    // pause button
    JButton pause = new JButton("Pause");
    pause.addActionListener((ActionEvent e) -> {
      t.stop();
    });
    buttonPanel.add(pause);

    // begin the loop button
    JButton loop = new JButton("Enable Loop");
    loop.addActionListener((ActionEvent e) -> {
      enableLoop = true;
    });
    buttonPanel.add(loop);

    // end the loop button
    JButton endLoop = new JButton("Disable Loop");
    endLoop.addActionListener((ActionEvent e) -> {
      enableLoop = false;
    });
    buttonPanel.add(endLoop);

    // Slider
    JSlider speedBar = new JSlider(JSlider.HORIZONTAL, 1, 48, speed);
    speedBar.setMajorTickSpacing(10);
    speedBar.setPaintLabels(true);
    speedBar.setPaintTicks(true);
    speedBar.setLabelTable(speedBar.createStandardLabels(4));
    speedBar.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        speed = speedBar.getValue();
        t.setDelay(100 / speed);
      }
    });
    buttonPanel.add(speedBar);

    // increase speed
    JButton increaseSpeed = new JButton("Increase Speed");
    increaseSpeed.addActionListener((ActionEvent e) -> {
      this.speed = this.speed + 1;
      t.setDelay(100 / this.speed);
      speedBar.setValue(this.speed);
    });
    buttonPanel.add(increaseSpeed);

    // decrease speed
    JButton decreaseSpeed = new JButton("Decrease Speed");

    decreaseSpeed.addActionListener((ActionEvent e) -> {
      if ((speed - 1) >= 1) {
        this.speed = this.speed - 1;
        t.setDelay(100 / this.speed);
        speedBar.setValue(this.speed);
      }
    });
    buttonPanel.add(decreaseSpeed);

    //Background color
    JButton background = new JButton("Set Background Color");
    background.addActionListener((ActionEvent e) -> {
      t.stop();
      popUpColor();
    });
    buttonPanel.add(background);


    //Text field
    input = new JTextField(15);
    buttonPanel.add(input);

    // export file
    JButton export = new JButton("Export");
    export.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        refreshOShape();
        IView svg = new SVGView(bC, getLastTime());
        if (invalidFileName(input.getText())) {

          JOptionPane.showMessageDialog(new JFrame(), "Invalid file name. " +
                  "Please try again.", "Error!", JOptionPane.ERROR_MESSAGE);

        } else {
          try {
            FileWriter writer = new FileWriter(input.getText());
            writer.write(svg.getStatus(oShape, oMove, enableLoop));
            writer.close();
          } catch (IOException i) {
            i.getMessage();
          }
          input.setText("");
        }
      }
    });

    buttonPanel.add(export);

    // quitting button
    JButton quit = new JButton("Quit");
    quit.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    buttonPanel.add(quit);


    JScrollPane scrollBar = new JScrollPane(animationPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.add(scrollBar, BorderLayout.CENTER);

    buttonPanel.setSize(new Dimension(100, 800));
    this.add(buttonPanel, BorderLayout.EAST);
    this.chooseSubsetOfShapes();
  }

  /**
   * Determines if the user inputs an illegal file name for the SVG file.
   *
   * @param fileName the user's file name
   * @return whether or not the file name is illegal
   */

  private boolean invalidFileName(String fileName) {

    // base case: if file name is an empty string, it is invalid
    if (fileName.length() == 0 || fileName.length() <= 4) {
      return true;
    }

    // case to make sure that the file has ".svg" at the end of it
    if (!(fileName.substring(fileName.length() - 4, fileName.length()).equals(".svg"))) {
      return true;
    }

    // case to make sure that an illegal character is not in the user's file name
    for (int i = 0; i < fileName.length(); i++) {

      String curr = Character.toString(fileName.charAt(i));

      if (curr.equals("/") || curr.equals("<") || curr.equals(">") || curr.equals(":") ||
              curr.equals("\"") || curr.equals("\\") || curr.equals("|") || curr.equals("?") ||
              curr.equals("*")) {
        return true;
      }
    }

    return false;
  }

  /**
   * Helps facilitate the subset of shapes that a user picks to be displayed on their animation, and
   * displays the choices of shapes to the user in a dialog box that appears at the start of the
   * program.
   */

  private void chooseSubsetOfShapes() {
    List<IShape> selectedShapes = new ArrayList<IShape>();
    List<IMove> selectedMoves = allMoves;
    allMoves = new ArrayList<IMove>();
    JPanel boxPanel = new JPanel();
    boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
    JScrollPane subsetScroll = new JScrollPane(boxPanel);
    subsetScroll.setPreferredSize(new Dimension(300, 300));
    JOptionPane optionPane = new JOptionPane();
    optionPane.setSize(new Dimension(100, 100));

    for (IShape s : allShapes) {

      JCheckBox box = new JCheckBox(s.getName());
      box.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
          if (e.getStateChange() == ItemEvent.SELECTED) {
            selectedShapes.add(s);
            for (int i = 0; i < selectedMoves.size(); i++) {
              if (selectedMoves.get(i).getTarget().sameShape(s)) {
                allMoves.add(selectedMoves.get(i));
              }
            }
          } else {
            if (selectedShapes.contains(s)) {
              selectedShapes.remove(s);
              for (int i = 0; i < selectedMoves.size(); i++) {
                if (allMoves.contains(selectedMoves.get(i))) {
                  allMoves.remove(selectedMoves.get(i));
                }
              }
            }
          }
        }
      });
      boxPanel.add(box);
    }

    allShapes = selectedShapes;
    optionPane.showMessageDialog(boxPanel, subsetScroll, "Select Shapes",
            JOptionPane.PLAIN_MESSAGE);
  }

  private void popUpColor() {

    int iR = Integer.parseInt(JOptionPane.showInputDialog("Enter Red Value"));
    int iG = Integer.parseInt(JOptionPane.showInputDialog("Enter Green Value"));
    int iB = Integer.parseInt(JOptionPane.showInputDialog("Enter Blue Value"));
    Color c = new Color(iR, iG, iB);
    this.bC = c;
    animationPanel.setBackground(bC);
    t.start();

  }

  @Override
  protected void refresh() {
    if (curTimeInSec > this.getLastTime() && enableLoop) {
      this.initialize();
    }
    refreshOShape();

    this.incrementTime();
    this.moveAll(curTimeInSec);
    this.animationPanel.updateModel(allShapes, curTimeInSec);
    this.animationPanel.repaint();
  }

  /**
   * Resets the shapes in allShapes to the original shapes of the animation.
   */

  private void refreshOShape() {

    if (curTimeInSec == 0) {
      oShape = this.getOShapes(allShapes);
    }

  }

  /**
   * Resets the list of shapes and moves to the original lists and resets the time in the
   * animation.
   */

  private void initialize() {
    curTimeInSec = 0;
    this.allShapes = this.getOShapes(oShape);
    animationPanel.updateModel(oShape, 0);
    t.restart();
  }

  /**
   * Retrieves the final time of all of the moves in the animation.
   *
   * @return final time of all of the moves in the animation
   */

  private double getLastTime() {
    double lastTime = 0;
    for (IShape s : allShapes) {
      if (s.getET() > lastTime) {
        lastTime = s.getET();
      }
    }
    return lastTime;
  }

  /**
   * Creates a new copy of the current list of shapes in the animation with all the same values, but
   * different references.
   *
   * @param allShapes all of the possible shapes in the animation
   * @return shapes
   */

  private List<IShape> getOShapes(List<IShape> allShapes) {
    List<IShape> newO = new ArrayList<IShape>();
    for (int i = 0; i < allShapes.size(); i++) {
      newO.add(allShapes.get(i).getNew());
    }
    return newO;
  }

  /**
   * Creates a new copy of the current list of moves in the animation with all of the same values,
   * but different references.
   *
   * @param allMove all of the possible moves in the animation
   * @return moves
   */

  private List<IMove> getOMove(List<IMove> allMove) {
    List<IMove> newO = new ArrayList<IMove>();
    for (int i = 0; i < allMove.size(); i++) {
      newO.add(allMove.get(i).getNew());
    }
    return newO;
  }


}
