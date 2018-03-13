package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import cs3500.animator.adapters.AnimationControllerAdapter;
import cs3500.animator.adapters.ModelAdapter;
import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.IController;
import cs3500.animator.controller.VisualController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.util.TweenModelBuilder;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.ViewCreator;

/**
 * Represents the Easy Animator, which allows the client to create shapes and moves to an animation
 * and then run the animation and see the outcome. The way the animation is produced is also
 * determined by the client: the animation can be produced in a text format, SVG format, or visual
 * animation format.
 */

public class EasyAnimator {

  /**
   * Represents the main method of the Easy Animator. The method creates the model and a particular
   * type of view, depending on the input of the client.
   *
   * @param args inputs from the client corresponding to the creation of the model and view
   */

  public static void main(String[] args) {

    String fileName = "";
    String viewType = "";
    int speed = 1;
    String output = "out";
    for (int i = 0; i < args.length - 1; i++) {

      switch (args[i]) {
        case "-if":
          fileName = args[i + 1];

          break;
        case "-iv":
          viewType = args[i + 1];

          break;
        case "-o":
          output = args[i + 1];

          break;
        case "-speed":
          speed = Integer.parseInt(args[i + 1]);

          break;
        default:
          new JOptionPane("error");
          break;
      }
    }

    AnimationFileReader reader = new AnimationFileReader();
    TweenModelBuilder<IAnimationModel> builder = new AnimationModel.Builder();

    try {
      reader.readFile(fileName, builder);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(e);
    }

    IAnimationModel model = builder.build();

    // Initializing the view
    IView view = new SVGView();


    IController controller = new AnimationController(model, view);

    ViewCreator createView = new ViewCreator(model);

    switch (viewType) {
      case "svg":
        controller = new AnimationController(model,createView.createView(viewType));
        break;
      case "text":
        controller = new AnimationController(model,createView.createView(viewType));
        break;
      case "visual":
        controller = new VisualController(model, view,"visual", speed, model.getColor());

        break;
      case "interactive":

        controller = new VisualController(model, view, "hybrid",speed,model.getColor());

        break;
      case "provider":
        ModelAdapter providerView = new ModelAdapter(model);
        controller = new AnimationControllerAdapter(providerView,
                (cs3500.animator.provider.view.IView)createView.createView(viewType),speed);

        break;
      default:
        new JOptionPane("error");
        break;
    }

    if (output.equals("out.svg")) {
      try {
        FileWriter writer = new FileWriter(output);
        writer.write(controller.getString());
        writer.close();
      } catch (IOException e) {
        e.getMessage();
      }
    } else {
      controller.action();
    }

  }
}
