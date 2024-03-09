package com.java.low.level.design.Expense.Split;

import java.util.List;

public interface ExpenseSplit {

  void validateSplitRequest(List<Split> splitList, double totalAmount) throws Exception;
}
