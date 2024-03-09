package com.java.low.level.design;

import com.java.low.level.design.User.User;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserExpenseBalanceSheet {
    private Map<String, Balance> userVsBalance;
    private double totalMyExpenses;
    private double totalPayment;
    private double totalOwedAmount;
    private double totalAmountToGetBack;

    public UserExpenseBalanceSheet() {
        this.userVsBalance = new HashMap<>();
        this.totalMyExpenses = 0;
        this.totalOwedAmount = 0;
        this.totalAmountToGetBack = 0;
    }
}
