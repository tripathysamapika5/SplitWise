package com.tripathysamapika5.splitwise.model.expense;

import com.tripathysamapika5.splitwise.model.split.Split;

import java.util.List;

public abstract class Expense {
    private String paidByUserId;
    private List<Split> splits;
    private Double totalExpenseAmount;
    private ExpenseMetadata expenseMetadata;

    public Expense(String paidByUserId, List<Split> splits, Double totalExpenseAmount) {
        this.paidByUserId = paidByUserId;
        this.splits = splits;
        this.totalExpenseAmount = totalExpenseAmount;
    }

    public String getPaidByUserId() {
        return paidByUserId;
    }

    public void setPaidByUserId(String paidByUserId) {
        this.paidByUserId = paidByUserId;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public Double getTotalExpenseAmount() {
        return totalExpenseAmount;
    }

    public void setTotalExpenseAmount(Double totalExpenseAmount) {
        this.totalExpenseAmount = totalExpenseAmount;
    }

    public ExpenseMetadata getExpenseMetadata() {
        return expenseMetadata;
    }

    public void setExpenseMetadata(ExpenseMetadata expenseMetadata) {
        this.expenseMetadata = expenseMetadata;
    }

    public abstract boolean validate();

    @Override
    public String toString() {
        return "Expense{" +
                "paidByUserId='" + paidByUserId + '\'' +
                ", splits=" + splits +
                ", totalExpenseAmount=" + totalExpenseAmount +
                ", expenseMetadata=" + expenseMetadata +
                '}';
    }
}
