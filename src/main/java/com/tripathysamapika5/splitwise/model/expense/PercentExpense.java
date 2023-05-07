package com.tripathysamapika5.splitwise.model.expense;

import com.tripathysamapika5.splitwise.model.split.ExactSplit;
import com.tripathysamapika5.splitwise.model.split.PercentSplit;
import com.tripathysamapika5.splitwise.model.split.Split;

import java.util.List;

public class PercentExpense extends Expense{

    public PercentExpense(String paidByUserId, List<Split> splits, Double totalExpenseAmount) {
        super(paidByUserId, splits, totalExpenseAmount);
    }

    @Override
    public boolean validate() {
        double totalPercent = 0;
        for(Split split : this.getSplits()){
            if(!(split instanceof PercentSplit)){
                return false;
            }
            totalPercent += split.getPercent();
            split.setExpenseAmount(this.getTotalExpenseAmount() * split.getPercent() /100);
        }

        if(totalPercent != 100){
            return false;
        }

        return true;
    }
}
