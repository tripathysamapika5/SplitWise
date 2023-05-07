package com.tripathysamapika5.splitwise.model.split;

public class ExactSplit extends Split{

    public ExactSplit(String paidForUserId, Double expenseAmount) {
        super(paidForUserId);
        this.setExpenseAmount(expenseAmount);
    }
}
