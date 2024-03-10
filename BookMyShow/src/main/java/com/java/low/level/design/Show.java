package com.java.low.level.design;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Show {

  private String showId;
  private Movie movie;
  private Screen screen;
  private int startTime;
  private List<Integer> bookedSeatIds;

  public Show() {
    this.bookedSeatIds = new ArrayList<>();
  }
}
