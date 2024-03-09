package com.java.low.level.design.Expense;

import com.java.low.level.design.Expense.Split.EqualSplit;
import com.java.low.level.design.Expense.Split.ExpenseSplit;
import com.java.low.level.design.Expense.Split.PercentageSplit;
import com.java.low.level.design.Expense.Split.UnequalSplit;

public class SplitFactory {

  public static ExpenseSplit getSplitObject(SplitType splitType) {
    switch (splitType) {
      case EQUAL:
        return new EqualSplit();
      case UNEQUAL:
        return new UnequalSplit();
      case PERCENTAGE:
        return new PercentageSplit();
      default:
        return null;
    }
  }
}
