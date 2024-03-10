package com.java.low.level.design;

import com.java.low.level.design.enums.SeatCategory;
import lombok.Data;

@Data
public class Seat {

  int seatId;
  int row;
  SeatCategory seatCategory;

}
