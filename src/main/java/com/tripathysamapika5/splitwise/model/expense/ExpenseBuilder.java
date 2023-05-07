package com.tripathysamapika5.splitwise.model.expense;

import com.tripathysamapika5.splitwise.exception.InvalidExpenseException;
import com.tripathysamapika5.splitwise.model.split.Split;

import java.util.List;

public class ExpenseBuilder {

    private String paidByUserId;
    private List<Split> splits;
    private Double totalExpenseAmount;
    private ExpenseMetadata expenseMetadata;
    private ExpenseType expenseType;


    private ExpenseBuilder(){}

    public static ExpenseBuilder newInstance() {
        return new ExpenseBuilder();
    }

    public ExpenseBuilder setPaidByUserId(String paidByUserId) {
        this.paidByUserId = paidByUserId;
        return this;
    }

    public ExpenseBuilder setSplits(List<Split> splits) {
        this.splits = splits;
        return this;

    }

    public ExpenseBuilder setTotalExpenseAmount(Double totalExpenseAmount) {
        this.totalExpenseAmount = totalExpenseAmount;
        return this;

    }

    public ExpenseBuilder setExpenseMetadata(ExpenseMetadata expenseMetadata) {
        this.expenseMetadata = expenseMetadata;
        return this;

    }

    public ExpenseBuilder setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
        return this;
    }

    public Expense builder(){
        Expense expense;
        if(this.expenseType.equals(ExpenseType.PERCENT)){
            expense = new PercentExpense(this.paidByUserId, this.splits, this.totalExpenseAmount);
            expense.setExpenseMetadata(this.expenseMetadata);
        }else if(this.expenseType.equals(ExpenseType.EXACT)){
            expense = new ExactExpense(this.paidByUserId, this.splits, this.totalExpenseAmount);
            expense.setExpenseMetadata(this.expenseMetadata);
        }else{
            expense = new EqualExpense(this.paidByUserId, this.splits, this.totalExpenseAmount);
            expense.setExpenseMetadata(this.expenseMetadata);
        }

        if(!expense.validate()){
            throw new InvalidExpenseException("Invalid Exception : " + expense );
        }

        return expense;
    }

}

