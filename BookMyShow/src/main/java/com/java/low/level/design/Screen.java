package com.java.low.level.design;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Screen {

  private String screenId;
  private List<Seat> seats;

  public Screen() {
    this.seats = new ArrayList<>();
  }
}
