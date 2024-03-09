package com.java.low.level.design.User;

import com.java.low.level.design.UserExpenseBalanceSheet;
import lombok.Data;

@Data
public class User {

  private String userId;
  private String userName;
  private UserExpenseBalanceSheet userExpenseBalanceSheet;

  public User(String userId, String userName) {
    this.userId = userId;
    this.userName = userName;
    userExpenseBalanceSheet = new UserExpenseBalanceSheet();
  }
}
