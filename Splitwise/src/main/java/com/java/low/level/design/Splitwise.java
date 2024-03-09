package com.java.low.level.design;

import com.java.low.level.design.Expense.Split.Split;
import com.java.low.level.design.Expense.SplitType;
import com.java.low.level.design.Group.Group;
import com.java.low.level.design.Group.GroupController;
import com.java.low.level.design.User.User;
import com.java.low.level.design.User.UserController;
import java.util.ArrayList;
import java.util.List;

public class Splitwise {

  UserController userController;
  GroupController groupController;
  BalanceSheetController balanceSheetController;

  public Splitwise() {
    this.userController = new UserController();
    this.groupController = new GroupController();
    this.balanceSheetController = new BalanceSheetController();
  }

  public void demo() throws Exception {
    setUpUserAndGroup();

    //Add members to group
    Group group = groupController.getGroup("g001");
    group.addMember(userController.getUser("u002"));
    group.addMember(userController.getUser("u003"));

    //create an expense inside the group
    List<Split> splits = new ArrayList<>();
    Split split1 = new Split(userController.getUser("u001"), 300);
    Split split2 = new Split(userController.getUser("u002"), 300);
    Split split3 = new Split(userController.getUser("u003"), 300);
    splits.add(split1);
    splits.add(split2);
    splits.add(split3);
    group.createExpense("Exp1001", "Breakfast", 900, userController.getUser("u001"), splits,
        SplitType.EQUAL);

    List<Split> splits2 = new ArrayList<>();
    Split splits2_1 = new Split(userController.getUser("u001"), 400);
    Split splits2_2 = new Split(userController.getUser("u002"), 100);
    splits2.add(splits2_1);
    splits2.add(splits2_2);
    group.createExpense("Exp1002", "Lunch", 500, userController.getUser("u002"), splits2,
        SplitType.UNEQUAL);

    for (User user : userController.getAllUsers()) {
      balanceSheetController.showBalanceSheetOfUser(user);
    }

  }

  private void setUpUserAndGroup() throws Exception {
    addUsers();
    User user1 = userController.getUser("u001");

    groupController.createNewGroup("g001", "Outing with Friends", user1);
  }

  private void addUsers() {
    User user1 = new User("u001", "User1");
    User user2 = new User("u002", "User2");
    User user3 = new User("u003", "User3");

    userController.addUser(user1);
    userController.addUser(user2);
    userController.addUser(user3);
  }

}
