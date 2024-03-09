package com.java.low.level.design.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {

  List<User> userList;

  public UserController() {
    this.userList = new ArrayList<>();
  }

  public void addUser(User user) {
    userList.add(user);
  }

  public User getUser(String userId) throws Exception {
    for (User user : userList) {
      if (user.getUserId() == userId) {
        return user;
      }
    }
    throw new Exception("User not found");
  }

  public List<User> getAllUsers() {
    return userList;
  }
}
