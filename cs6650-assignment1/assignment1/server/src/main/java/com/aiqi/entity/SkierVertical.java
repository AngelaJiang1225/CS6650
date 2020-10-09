package com.aiqi.entity;

public class SkierVertical {
  private int skierID;
  private int VertcialTotal;

  public SkierVertical(int skierID, int verticalTotal) {
    this.skierID = skierID;
    this.VertcialTotal = verticalTotal;
  }

  public int getVerticalTotal() {
    return VertcialTotal;
  }
  public int getSkierID() {
    return skierID;
  }

}
