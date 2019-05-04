package FST;

import java.math.BigDecimal;

// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalance {

    private double accountBalance;

    public BankBalance(double initialBalance) {
        accountBalance = initialBalance;
        setAccountBalance(initialBalance);
    }
    private void setAccountBalance(double initialBalance) {
        if (BigDecimal.valueOf(initialBalance).scale() > 2) {
            throw new IllegalArgumentException("The Account Balance can not have more than two decimal places.");
        }
        this.accountBalance = initialBalance;
    }

    public void deposit(double amountToDeposit) {
         accountBalance = accountBalance + amountToDeposit;
    }

    public void withdraw(double amountToWithdraw) {
         accountBalance = accountBalance - amountToWithdraw;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    @Override
    public String toString() {
        return "" + accountBalance;
    }

    public static void main(String[] args) {
        BankBalance user = new BankBalance(100.99);
        user.withdraw(25.46);
        user.deposit(-2000);
        System.out.println(user.getAccountBalance());
    }
}
