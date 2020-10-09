package com.aiqi.entity;

import java.util.List;

public class TopTenSkiers {
  private List<SkierVertical> topTenSkiers;

  public List<SkierVertical> getTopTenList() {
    return topTenSkiers;
  }
  public TopTenSkiers(List<SkierVertical> topTenList) {
    this.topTenSkiers = topTenList;
  }
}
