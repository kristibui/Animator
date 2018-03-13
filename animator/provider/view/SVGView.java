package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import cs3500.animator.provider.model.PublicAnimationOperations;

/**
 * A class to represent an SVG View.
 */
public class SVGView implements IView {


  /**
   * A constructor for an SVG View.
   *
   * @param am the model to create the view for.
   */
  public SVGView(PublicAnimationOperations am) {
    this.am = am;
  }

  private PublicAnimationOperations am;

  @Override
  public void setButtonListener(ActionListener al) {
    // Do nothing
  }

  @Override
  public void setItemListener(ItemListener el) {
    // Do nothing
  }

  @Override
  public String drawInitialState() {
    String finalStr = "";

    finalStr += "<svg width=\"1000\" height=\"1000\" xmlns=\"http://www.w3.org/2000/svg\" " +
            "version=\"1.1\">";
    finalStr += "<rect>";
    finalStr += String.format("<animate id=\"start\" begin=\"0s;start.end\" dur=\"%ss\" " +
            "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>", am.getEndTime() / am
            .getSpeed());
    finalStr += "</rect>";
    finalStr += am.textSVG();
    finalStr += "</svg>";
    return finalStr;
  }

  @Override
  public String drawFrame() {
    throw new UnsupportedOperationException("SVG View does not draw frames.");
  }

  @Override
  public boolean closed() {
    return true;
  }

}
