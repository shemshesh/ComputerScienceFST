package FST;

import java.math.BigDecimal;
import java.text.DecimalFormat;

// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalance {

    private double accountBalance;
    private double annualInterestRate;

    DecimalFormat decimal = new DecimalFormat("##.00");

    public BankBalance(double initialBalance) {
        accountBalance = initialBalance;
        setAccountBalance(initialBalance);
        annualInterestRate = 0;
    }

    private void setAccountBalance(double initialBalance) {
        twoDecimalPlaces(initialBalance);
        this.accountBalance = initialBalance;
    }

    public void deposit(double amountToDeposit) {
        if (amountToDeposit < 0) {
            throw new IllegalArgumentException("You can not deposit a negative value.");
        }
        twoDecimalPlaces(amountToDeposit);
        accountBalance = accountBalance + amountToDeposit;
    }

    public void withdraw(double amountToWithdraw) {
        if (amountToWithdraw < 0) {
            throw new IllegalArgumentException("You can not withdraw a negative value.");
        }
        twoDecimalPlaces(amountToWithdraw);
        accountBalance = accountBalance - amountToWithdraw;
    }

    public void setAnnualInterestRate(double interestRate) {
        annualInterestRate = interestRate;
    }

    public void monthlyInterest() {
        accountBalance = accountBalance + ((annualInterestRate / 12) * accountBalance);
    }

    public double getAccountBalance() {
        accountBalance = Double.parseDouble(decimal.format(accountBalance));
        return accountBalance;
    }

    @Override
    public String toString() {
        return "" + accountBalance;
    }

    private void twoDecimalPlaces(double num) {
        if (BigDecimal.valueOf(num).scale() > 2) {
            throw new IllegalArgumentException("The Input can not have more than two decimal places.");
        }
    }
}
