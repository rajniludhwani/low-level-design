package com.java.low.level.design.Expense;

import com.java.low.level.design.Expense.Split.Split;
import com.java.low.level.design.User.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Expense {
    private String expenseId;
    private String description;
    private double expenseAmount;
    private User paidByUser;
    private List<Split> splitDetails = new ArrayList<>();
    private SplitType splitType;

    public Expense(String expenseId, String description, double expenseAmount, User paidByUser, List<Split> splitDetails, SplitType splitType) {
        this.expenseId = expenseId;
        this.description = description;
        this.expenseAmount = expenseAmount;
        this.paidByUser = paidByUser;
        this.splitDetails.addAll(splitDetails);
        this.splitType = splitType;
    }
}
