package com.tripathysamapika5.splitwise.model.split;

public abstract class Split {

    private String paidForUserId;
    private Double expenseAmount;
    private Double percent;

    public Split(String paidForUserId) {
        this.paidForUserId = paidForUserId;
    }

    public String getPaidForUserId() {
        return paidForUserId;
    }

    public void setPaidForUserId(String paidForUserId) {
        this.paidForUserId = paidForUserId;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}
