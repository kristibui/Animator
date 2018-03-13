package cs3500.animator.view;

import java.util.Comparator;

import cs3500.animator.model.IShape;

public class LayerComparator implements Comparator<IShape> {

  @Override
  public int compare(IShape o1, IShape o2) {
    return o1.getLayer() - o2.getLayer();
  }
}
