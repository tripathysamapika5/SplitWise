package com.tripathysamapika5.splitwise;

import com.tripathysamapika5.splitwise.model.Group;
import com.tripathysamapika5.splitwise.model.User;
import com.tripathysamapika5.splitwise.model.expense.ExpenseMetadata;
import com.tripathysamapika5.splitwise.model.expense.ExpenseType;
import com.tripathysamapika5.splitwise.model.split.EqualSplit;
import com.tripathysamapika5.splitwise.model.split.ExactSplit;
import com.tripathysamapika5.splitwise.model.split.PercentSplit;
import com.tripathysamapika5.splitwise.model.split.Split;

import java.util.ArrayList;
import java.util.List;

public class SplitWiseApplication {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();


        User u1 = new User("u1", "User1", "", "abc1@gmail.com", "23526356236");
        User u2 = new User("u2", "User2", "", "abc2@gmail.com", "23526356236");
        User u3 = new User("u3", "User3", "", "abc3@gmail.com", "23526356236");
        User u4 = new User("u4", "User4", "", "abc4@gmail.com", "23526356236");

        expenseManager.registerUser(u1);

        Group group1 = new Group("group1", "My Flat mates", u1);

        expenseManager.createGroup(group1);


        expenseManager.addUserInGroup("group1", u2);
        expenseManager.addUserInGroup("group1", u3);
        expenseManager.addUserInGroup("group1", u4);


        List<Split> splits1 = new ArrayList<>();
        splits1.add(new EqualSplit("u1"));
        splits1.add(new EqualSplit("u2"));
        splits1.add(new EqualSplit("u3"));
        splits1.add(new EqualSplit("u4"));
        ExpenseMetadata metadata1 = new ExpenseMetadata();
        metadata1.setComments("Electric Bill Payment");


        List<Split> splits2 = new ArrayList<>();
        splits2.add(new ExactSplit("u2", 370.00));
        splits2.add(new ExactSplit("u3", 880.00));
        ExpenseMetadata metadata2 = new ExpenseMetadata();
        metadata2.setComments("Flipkart BBD");


        List<Split> splits3 = new ArrayList<>();
        splits3.add(new PercentSplit("u1", 40.00));
        splits3.add(new PercentSplit("u2", 20.00));
        splits3.add(new PercentSplit("u3", 20.00));
        splits3.add(new PercentSplit("u4", 20.00));

        ExpenseMetadata metadata3 = new ExpenseMetadata();
        metadata3.setComments("Cafe charges");


        expenseManager.addExpenseToUser("u1", splits1, 1000.00, ExpenseType.EQUAL, metadata1);
        expenseManager.addExpenseToUser("u1", splits2, 1250.00, ExpenseType.EXACT, metadata2 );
        expenseManager.addExpenseToUser("u4", splits3, 1200.00, ExpenseType.PERCENT, metadata3 );

        expenseManager.showSummaryForAll();
        System.out.println("---------------------");

        expenseManager.showSummaryForUserId("u1");
        System.out.println("---------------------");

        expenseManager.showSummaryForUserId("u2");
        System.out.println("---------------------");

        expenseManager.showSummaryForUserId("u3");
        System.out.println("---------------------");

        expenseManager.showSummaryForUserId("u4");



    }
}
