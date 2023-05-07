package com.tripathysamapika5.splitwise.model.split;

public class PercentSplit extends Split {


    public PercentSplit(String paidForUserId, Double percent) {
        super(paidForUserId);
        this.setPercent(percent);
    }
}
