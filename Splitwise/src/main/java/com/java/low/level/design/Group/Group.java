package com.java.low.level.design.Group;

import com.java.low.level.design.Expense.Expense;
import com.java.low.level.design.Expense.ExpenseController;
import com.java.low.level.design.Expense.Split.Split;
import com.java.low.level.design.Expense.SplitType;
import com.java.low.level.design.User.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Group {
    private String groupId;
    private String groupName;
    private List<User> members;
    private List<Expense> expenses;
    private ExpenseController expenseController;

    public Group() {
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.expenseController = new ExpenseController();
    }

    public void addMember(User member) {
        members.add(member);
    }

    public Expense createExpense(String expenseId, String description, double expenseAmount, User paidByUser, List<Split> splitDetails, SplitType splitType) throws Exception {
        Expense expense = expenseController.createExpense(expenseId, description, expenseAmount, paidByUser, splitDetails, splitType);
        expenses.add(expense);
        return expense;
    }

}
