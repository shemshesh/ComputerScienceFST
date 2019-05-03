package FST;

import java.math.BigDecimal;

// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalance extends Object {

    private double accountBalance;

    public BankBalance(){
        this.accountBalance=0;
    }

    public BankBalance(double b) {
        setAccountBalance(b);
    }

    private void setAccountBalance(double b) {
        if (BigDecimal.valueOf(b).scale() > 2){
            throw new IllegalArgumentException("The Account Balance can not have more than two decimal places.");
        }
        this.accountBalance = b;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    @Override
    public String toString() {
        return "" + accountBalance;
    }
}