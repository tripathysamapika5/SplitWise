package com.tripathysamapika5.splitwise;

import com.tripathysamapika5.splitwise.exception.GroupNotFoundException;
import com.tripathysamapika5.splitwise.model.Group;
import com.tripathysamapika5.splitwise.model.User;
import com.tripathysamapika5.splitwise.model.expense.Expense;
import com.tripathysamapika5.splitwise.model.expense.ExpenseBuilder;
import com.tripathysamapika5.splitwise.model.expense.ExpenseMetadata;
import com.tripathysamapika5.splitwise.model.expense.ExpenseType;
import com.tripathysamapika5.splitwise.model.split.Split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    Map<String, User> users = new HashMap<>();
    Map<String, Group> groups = new HashMap<>();

    Map<String, Expense> expenses = new HashMap<>();
    Map<String, Map<String, Double>> balanceSheet = new HashMap<>();

    public void registerUser(User user) {
        user.setOwner(true);
        users.put(user.getUserId(), user);
    }

    public void createGroup(Group group) {
        groups.put(group.getGroupId(), group);
    }

    public void addUserInGroup(String groupId, User user) {
        if (!groups.containsKey(groupId)) {
            throw new GroupNotFoundException("Group with" + groupId + " does not exist");
        }
        groups.get(groupId).addUserToGroup(user);
        users.put(user.getUserId(), user);

    }

    public void addExpenseToUser(String paidByUserId,
                                 List<Split> splits,
                                 Double totalExpenseAmount,
                                 ExpenseType expenseType,
                                 ExpenseMetadata expenseMetadata) {
        Expense expense = ExpenseBuilder
                .newInstance()
                .setPaidByUserId(paidByUserId)
                .setExpenseType(expenseType)
                .setSplits(splits)
                .setTotalExpenseAmount(totalExpenseAmount)
                .setExpenseMetadata(expenseMetadata)
                .builder();

        expenses.put(paidByUserId, expense);

        if (!balanceSheet.containsKey(paidByUserId)) {
            balanceSheet.put(paidByUserId, new HashMap<>());
        }


        for (Split split : expense.getSplits()) {
            if (paidByUserId != split.getPaidForUserId()) {
                if (!balanceSheet.containsKey(split.getPaidForUserId())) {
                    balanceSheet.put(split.getPaidForUserId(), new HashMap<>());
                }

                balanceSheet.get(paidByUserId).put(split.getPaidForUserId(),
                        balanceSheet.get(paidByUserId).getOrDefault(split.getPaidForUserId(), 0.0) + split.getExpenseAmount());


                balanceSheet.get(split.getPaidForUserId()).put(paidByUserId,
                        balanceSheet.get(split.getPaidForUserId()).getOrDefault(paidByUserId, 0.0) - split.getExpenseAmount());

            }
        }

    }


    public void showSummaryForAll() {
        for (String userId : users.keySet()) {
            printBalanceForUserId(userId);

        }
    }

    public void printBalanceForUserId(String userId) {

        String PaidByUserName = users.get(userId).getFirstName() + " " + users.get(userId).getLastName();

        for (Map.Entry<String, Double> splitMap : balanceSheet.get(userId).entrySet()) {
            String paidForUserid = splitMap.getKey();
            String paidForUserName = users.get(paidForUserid).getFirstName() + " " + users.get(paidForUserid).getLastName();

            if (splitMap.getValue() > 0) {
                System.out.println(paidForUserName + " owes " + PaidByUserName + " amount : " + splitMap.getValue());
            }
        }

    }


    public void showSummaryForUserId(String userId) {

        String PaidByUserName = users.get(userId).getFirstName() + " " + users.get(userId).getLastName();

        for (Map.Entry<String, Double> splitMap : balanceSheet.get(userId).entrySet()) {
            String paidForUserid = splitMap.getKey();
            String paidForUserName = users.get(paidForUserid).getFirstName() + " " + users.get(paidForUserid).getLastName();

            if (splitMap.getValue() > 0) {
                System.out.println(paidForUserName + " owes " + PaidByUserName + " amount : " + splitMap.getValue());
            }else if(splitMap.getValue() < 0){
                System.out.println(PaidByUserName + " owes " + paidForUserName + " amount : " + Math.abs(splitMap.getValue()));
            }
        }

    }


}

