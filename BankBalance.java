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
        twoDecimalPlaces(initialBalance);
        this.accountBalance = initialBalance;
    }

    public void deposit(double amountToDeposit) {
        if (amountToDeposit<0){
            throw new IllegalArgumentException("You can not deposit a negative value.");
        }
        twoDecimalPlaces(amountToDeposit);
         accountBalance = accountBalance + amountToDeposit;
    }

    public void withdraw(double amountToWithdraw) {
        if (amountToWithdraw<0){
            throw new IllegalArgumentException("You can not deposit a negative value.");
        }
        twoDecimalPlaces(amountToWithdraw);
         accountBalance = accountBalance - amountToWithdraw;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    @Override
    public String toString() {
        return "" + accountBalance;
    }
    public void twoDecimalPlaces(double num){
        if (BigDecimal.valueOf(num).scale() > 2) {
            throw new IllegalArgumentException("The Input can not have more than two decimal places.");
        }
    }
    public static void main(String[] args) {
        BankBalance user = new BankBalance(100.99);
        user.withdraw(25.465);
        user.deposit(10);
        System.out.println(user.getAccountBalance());
    }
}
