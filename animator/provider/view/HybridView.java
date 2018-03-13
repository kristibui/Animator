package cs3500.animator.provider.view;


import java.awt.Graphics;

import java.awt.event.ActionListener;

import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Checkbox;
import java.awt.Font;
import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;


import cs3500.animator.provider.model.IShape;
import cs3500.animator.provider.model.PublicAnimationOperations;

/**
 * A class to represent a hybrid view.
 */
public class HybridView extends JFrame implements IView {


  /**
   * Constructor for a hybrid view with a visual and SVG component. Sets up the design of the user
   * interface.
   *
   * @param am the model to construct a view for.
   */
  public HybridView(PublicAnimationOperations am) {
    super();

    JPanel buttonPanel = new JPanel();
    JPanel shapePanel = new JPanel();

    this.closed = false;

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

    shapePanel = new JPanel();
    shapePanel.setPreferredSize(new Dimension(400, 2000));
    shapePanel.setBackground(Color.lightGray);

    //shapePanel.setLayout(new BorderLayout());

    ArrayList<IShape> shapes = am.getShapes();
    for (IShape s : shapes) {
      shapesToBoolean.put(s.getName(), Boolean.TRUE);
      Checkbox c = new Checkbox(s.getName());
      //c.addItemListener(checkboxListener);
      c.setFont(new Font("Helvetica", Font.BOLD, 35));
      c.setBackground(Color.white);
      shapePanel.add(c, BorderLayout.NORTH);
      shapeBoxes.add(c);
    }

    JScrollPane shapeScrollPane = new JScrollPane(shapePanel);
    this.add(shapeScrollPane, BorderLayout.EAST);
    shapeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    shapeScrollPane.setPreferredSize(new Dimension(850, 900));


    buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(1400, 100));
    this.add(buttonPanel, BorderLayout.SOUTH);


    this.startButton = new JButton("Start");
    startButton.setActionCommand("Start");
    startButton.setPreferredSize(new Dimension(165, 75));
    startButton.setFont(new Font("Helvetica", Font.BOLD, 25));
    buttonPanel.add(startButton);


    this.pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause");
    pauseButton.setPreferredSize(new Dimension(165, 75));
    pauseButton.setFont(new Font("Helvetica", Font.BOLD, 25));
    buttonPanel.add(pauseButton);

    this.restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart");
    restartButton.setPreferredSize(new Dimension(165, 75));
    restartButton.setFont(new Font("Helvetica", Font.BOLD, 25));
    buttonPanel.add(restartButton);

    this.loopButton = new JButton("Loop: OFF");
    loopButton.setActionCommand("Loop");
    loopButton.setPreferredSize(new Dimension(165, 75));
    loopButton.setFont(new Font("Helvetica", Font.BOLD, 25));
    buttonPanel.add(loopButton);

    this.speedIncreaseButton = new JButton("+Speed");
    speedIncreaseButton.setActionCommand("+Speed");
    speedIncreaseButton.setPreferredSize(new Dimension(165, 75));
    speedIncreaseButton.setFont(new Font("Helvetica", Font.BOLD, 25));
    buttonPanel.add(speedIncreaseButton);

    this.speedDecreaseButton = new JButton("-Speed");
    speedDecreaseButton.setActionCommand("-Speed");
    speedDecreaseButton.setPreferredSize(new Dimension(165, 75));
    speedDecreaseButton.setFont(new Font("Helvetica", Font.BOLD, 25));
    buttonPanel.add(speedDecreaseButton);

    speedText = new JLabel("Speed: " + am.getSpeed() + " ticks/sec");
    speedText.setPreferredSize(new Dimension(250, 75));
    speedText.setFont(new Font("Helvetica", Font.BOLD, 25));
    buttonPanel.add(speedText);

    this.addWindowListener(new WindowListener() {
      @Override
      public void windowOpened(WindowEvent e) {
        // Do nothing
      }

      @Override
      public void windowClosing(WindowEvent e) {
        closed = true;
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e1) {
          e1.printStackTrace();
        }
      }

      @Override
      public void windowClosed(WindowEvent e) {
        // Do nothing
      }

      @Override
      public void windowIconified(WindowEvent e) {
        //Do nothing
      }

      @Override
      public void windowDeiconified(WindowEvent e) {
        // Do nothing
      }

      @Override
      public void windowActivated(WindowEvent e) {
        // Do nothing
      }

      @Override
      public void windowDeactivated(WindowEvent e) {
        // Do nothing
      }
    });
    this.am = am;

    this.pack();
    this.setVisible(true);
  }

  public boolean closed;
  private AnimationPanel animationScreen;
  private PublicAnimationOperations am;

  private int changeInSpeed;
  private JLabel speedText;
  private ArrayList<String> shapesToExclude = new ArrayList<>();
  public HashMap<String, Boolean> shapesToBoolean = new HashMap<>();

  ArrayList<Checkbox> shapeBoxes = new ArrayList<>();
  JButton startButton;
  JButton pauseButton;
  JButton restartButton;
  JButton loopButton;
  JButton speedIncreaseButton;
  JButton speedDecreaseButton;

  ///////////////////////////////////////////////////

  /**
   * A class to represent an animation panel on which
   * the animation will be painted.
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
    startButton.addActionListener(al);
    pauseButton.addActionListener(al);
    restartButton.addActionListener(al);
    loopButton.addActionListener(al);
    speedIncreaseButton.addActionListener(al);
    speedDecreaseButton.addActionListener(al);
  }

  @Override
  public void setItemListener(ItemListener el) {
    for (Checkbox c : shapeBoxes) {
      c.addItemListener(el);
    }
  }

  /**
   * Play the animation from start to finish.
   */
  @Override
  public String drawInitialState() {

    am.restart();
    am.changeVisibility(shapesToBoolean);

    String finalStr = "";

    finalStr += "<svg width=\"1000\" height=\"1000\" xmlns=\"http://www.w3.org/2000/svg\" " +
            "version=\"1.1\">";
    finalStr += "<rect>";

    if (am.getLoop()) {
      finalStr += String.format("<animate id=\"start\" begin=\"0s;start.end\" dur=\"%ss\" " +
              "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>", am.getEndTime() / am
              .getSpeed());
    } else {
      finalStr += String.format("<animate id=\"start\" begin=\"0s\" dur=\"%ss\" " +
              "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>", am.getEndTime() / am
              .getSpeed());
    }

    finalStr += "</rect>";
    finalStr += am.textSVG();
    finalStr += "</svg>";

    return finalStr;
  }

  /**
   * Draw the frame of the current tick given by the given model.
   */
  @Override
  public String drawFrame() {

    //this.am.pause();
    this.am.changeVisibility(shapesToBoolean);

    this.animationScreen.repaint();
    //this.am.resume();
    speedText.setText("Speed: " + am.getSpeed() + " ticks/sec");
    if (am.getLoop()) {
      loopButton.setText("Loop: ON");
    } else {
      loopButton.setText("Loop: OFF");
    }


    return "";
  }

  @Override
  public boolean closed() {
    return closed;
  }


}


