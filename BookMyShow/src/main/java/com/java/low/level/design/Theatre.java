package com.java.low.level.design;

import com.java.low.level.design.enums.City;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Theatre {

  String theatreId;
  String address;
  City city;
  List<Screen> screenList;
  List<Show> showList;

  public Theatre() {
    this.screenList = new ArrayList<>();
    this.showList = new ArrayList<>();
  }
}
