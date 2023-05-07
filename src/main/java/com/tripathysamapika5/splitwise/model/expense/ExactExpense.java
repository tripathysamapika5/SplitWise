package com.tripathysamapika5.splitwise.model.expense;

import com.tripathysamapika5.splitwise.model.split.ExactSplit;
import com.tripathysamapika5.splitwise.model.split.Split;

import java.util.List;

public class ExactExpense extends Expense{

    public ExactExpense(String paidByUserId, List<Split> splits, Double totalExpenseAmount) {
        super(paidByUserId, splits, totalExpenseAmount);
    }

    @Override
    public boolean validate() {
        double totalSplitAmount = 0;
        for(Split split : this.getSplits()){
            if(!(split instanceof ExactSplit)){
                return false;
            }
            totalSplitAmount += split.getExpenseAmount();
        }

        if(totalSplitAmount != this.getTotalExpenseAmount()){
            return false;
        }

        return true;
    }
}
