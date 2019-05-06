package FST;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalance {

    private double accountBalance;
    private double annualInterestRate;

    DecimalFormat df = new DecimalFormat("##.00");
    ArrayList<String> transactionList=new ArrayList<>();//Creating arraylist of transactions


    public BankBalance(double initialBalance) {
        accountBalance = initialBalance;
        setAccountBalance(initialBalance);
        annualInterestRate = 0;
    }

    private void setAccountBalance(double initialBalance) {
        if (initialBalance < 0){
            throw new IllegalArgumentException("The bank account can not have a negative balance.");
        }
        twoDecimalPlaces(initialBalance);
        this.accountBalance = initialBalance;
    }

    public void deposit(double amountToDeposit) {
        if (amountToDeposit < 0) {
            throw new IllegalArgumentException("You can not deposit a negative value.");
        }
        twoDecimalPlaces(amountToDeposit);
        accountBalance = accountBalance + amountToDeposit;
        transactionList.add("Deposit of "+"$"+amountToDeposit+","+"Balance: "+"$"+accountBalance+".");
    }

    public void withdraw(double amountToWithdraw) {
        if (amountToWithdraw < 0) {
            throw new IllegalArgumentException("You can not withdraw a negative value.");
        }
        if (amountToWithdraw > accountBalance){
            throw new IllegalArgumentException("You do not have enough money in your account."+"Balance: "+accountBalance+"."+" Amount to Withdraw: "+amountToWithdraw);

        }
        twoDecimalPlaces(amountToWithdraw);
        accountBalance = accountBalance - amountToWithdraw;
        transactionList.add("Withdraw of "+"$"+amountToWithdraw+","+"Balance: "+"$"+accountBalance+".");
    }

    public void setAnnualInterestRate(double interestRate) {
        if (interestRate < 0){
            throw new IllegalArgumentException("You can not have a negative interest rate.");
        }
        annualInterestRate = interestRate;
    }

    public void monthlyInterest() {
        double monthlyRate = ((annualInterestRate / 12) * accountBalance);
        monthlyRate = Double.valueOf(df.format(monthlyRate));
        accountBalance = accountBalance + monthlyRate;
        transactionList.add("Interest of "+"$"+monthlyRate+","+"Balance: "+"$"+accountBalance+".");
    }

    public double getAccountBalance() {
        accountBalance = Double.valueOf(df.format(accountBalance));
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
