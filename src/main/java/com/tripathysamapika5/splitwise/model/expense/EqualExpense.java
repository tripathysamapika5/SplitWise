package com.tripathysamapika5.splitwise.model.expense;

import com.tripathysamapika5.splitwise.model.split.EqualSplit;
import com.tripathysamapika5.splitwise.model.split.Split;

import java.util.List;

public class EqualExpense extends Expense{
    public EqualExpense(String paidByUserId, List<Split> splits, Double totalExpenseAmount) {
        super(paidByUserId, splits, totalExpenseAmount);
    }

    public boolean validate(){
        int noOfUsers = this.getSplits().size();
        for(Split split : this.getSplits()){
            if(!(split instanceof EqualSplit)){
                return false;
            }
            split.setExpenseAmount(this.getTotalExpenseAmount()/noOfUsers);
        }

        return true;
    }

}
