package com.java.low.level.design.Expense.Split;

import java.util.List;

public class EqualSplit implements ExpenseSplit {

  @Override
  public void validateSplitRequest(List<Split> splitList, double totalAmount) throws Exception {
    double amountToBePresent = totalAmount / splitList.size();
    for (Split split : splitList) {
      if (split.getAmountOwe() != amountToBePresent) {
        throw new Exception("Request Invalidated");
      }
    }
  }
}
