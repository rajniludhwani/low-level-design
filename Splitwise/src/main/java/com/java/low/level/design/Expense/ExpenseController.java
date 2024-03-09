package com.java.low.level.design.Expense;

import com.java.low.level.design.BalanceSheetController;
import com.java.low.level.design.Expense.Split.ExpenseSplit;
import com.java.low.level.design.Expense.Split.Split;
import com.java.low.level.design.User.User;
import java.util.List;

public class ExpenseController {

  BalanceSheetController balanceSheetController;

  public ExpenseController() {
    this.balanceSheetController = new BalanceSheetController();
  }

  public Expense createExpense(String expenseId, String description, double expenseAmount,
      User paidByUser, List<Split> splitDetails, SplitType splitType) throws Exception {
    ExpenseSplit expenseSplit = SplitFactory.getSplitObject(splitType);
    expenseSplit.validateSplitRequest(splitDetails, expenseAmount);

    Expense expense = new Expense(expenseId, description, expenseAmount, paidByUser, splitDetails,
        splitType);

    balanceSheetController.updateUserExpenseBalanceSheet(paidByUser, splitDetails, expenseAmount);

    return expense;
  }
}
