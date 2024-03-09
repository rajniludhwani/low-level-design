package com.java.low.level.design;

import com.java.low.level.design.Expense.Split.Split;
import com.java.low.level.design.User.User;
import java.util.List;
import java.util.Map;

public class BalanceSheetController {

  public void updateUserExpenseBalanceSheet(User expensePaidBy, List<Split> splitLists,
      double totalExpenseAmount) {
    UserExpenseBalanceSheet paidByUserBalanceSheet = expensePaidBy.getUserExpenseBalanceSheet();
    paidByUserBalanceSheet.setTotalPayment(
        paidByUserBalanceSheet.getTotalPayment() + totalExpenseAmount);

    for (Split split : splitLists) {
      User userOwe = split.getUser();
      UserExpenseBalanceSheet owedByUserBalanceSheet = userOwe.getUserExpenseBalanceSheet();
      double oweAmount = split.getAmountOwe();

      if (expensePaidBy.getUserId().equals(userOwe.getUserId())) {
        paidByUserBalanceSheet.setTotalMyExpenses(
            paidByUserBalanceSheet.getTotalMyExpenses() + oweAmount);
      } else {
        //update the balance of paid user
        paidByUserBalanceSheet.setTotalAmountToGetBack(
            paidByUserBalanceSheet.getTotalAmountToGetBack() + oweAmount);

        Balance userOweBalance;
        if (paidByUserBalanceSheet.getUserVsBalance().containsKey(userOwe.getUserId())) {
          userOweBalance = paidByUserBalanceSheet.getUserVsBalance().get(userOwe.getUserId());
        } else {
          userOweBalance = new Balance();
          paidByUserBalanceSheet.getUserVsBalance().put(userOwe.getUserId(), userOweBalance);
        }
        userOweBalance.setAmountGetBack(userOweBalance.getAmountGetBack() + oweAmount);

        //update the balance of owe user
        owedByUserBalanceSheet.setTotalOwedAmount(
            owedByUserBalanceSheet.getTotalOwedAmount() + oweAmount);
        owedByUserBalanceSheet.setTotalMyExpenses(
            owedByUserBalanceSheet.getTotalMyExpenses() + oweAmount);

        Balance userPaidBalance;
        if (owedByUserBalanceSheet.getUserVsBalance().containsKey(expensePaidBy.getUserId())) {
          userPaidBalance = owedByUserBalanceSheet.getUserVsBalance()
              .get(expensePaidBy.getUserId());
        } else {
          userPaidBalance = new Balance();
          owedByUserBalanceSheet.getUserVsBalance().put(expensePaidBy.getUserId(), userPaidBalance);
        }
        userPaidBalance.setAmountOwe(userOweBalance.getAmountOwe() + oweAmount);
      }
    }
  }

  public void showBalanceSheetOfUser(User user) {
    System.out.println("---------------------------");
    System.out.println("Balance sheet of user : " + user.getUserId());

    UserExpenseBalanceSheet userExpenseBalanceSheet = user.getUserExpenseBalanceSheet();
    System.out.println("TotalMyExpenses : " + userExpenseBalanceSheet.getTotalMyExpenses());
    System.out.println(
        "TotalAmountToGetBack : " + userExpenseBalanceSheet.getTotalAmountToGetBack());
    System.out.println("TotalOwedAmount: " + userExpenseBalanceSheet.getTotalOwedAmount());
    System.out.println("TotalPaymentMade: " + userExpenseBalanceSheet.getTotalPayment());

    for (Map.Entry<String, Balance> entry : userExpenseBalanceSheet.getUserVsBalance().entrySet()) {
      String userId = entry.getKey();
      Balance balance = entry.getValue();

      System.out.println(
          "UserId : " + userId + " YouGetBack : " + balance.getAmountGetBack() + " YouOwe : "
              + balance.getAmountOwe());
    }
    System.out.println("---------------------------");
  }
}
