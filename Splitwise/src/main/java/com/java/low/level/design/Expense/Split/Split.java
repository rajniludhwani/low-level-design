package com.java.low.level.design.Expense.Split;

import com.java.low.level.design.User.User;
import lombok.Data;

@Data
public class Split {
    private User user;
    private double amountOwe;

    public Split(User user, double amountOwe) {
        this.user = user;
        this.amountOwe = amountOwe;
    }
}
