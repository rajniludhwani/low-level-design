package com.java.low.level.design;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Booking {

  private Show show;
  private List<Seat> seatsToBook;
  private Payment payment;

  public Booking() {
    this.seatsToBook = new ArrayList<>();
  }
}
