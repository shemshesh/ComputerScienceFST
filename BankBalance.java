package FST;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalance {//Start of Class BankBalance

    private double accountBalance;
    private double annualInterestRate;

    private DecimalFormat df = new DecimalFormat("##.00");//Decimal format that rounds to two decimal places
    ArrayList<String> transactionList = new ArrayList<>();//Creating array list of transactions

    //Constructor method to initialize object accountBalance and annualInterestRate
    public BankBalance(double initialBalance) {
        accountBalance = initialBalance;
        setAccountBalance(initialBalance);
        annualInterestRate = 0;
    }

    //Set the value of the initial balance of accountBalance
    private void setAccountBalance(double initialBalance) {
        //Error for account to not have negative balance
        if (initialBalance < 0) {
            throw new IllegalArgumentException("The bank account can not have a negative balance.");
        }
        //Calls method to insure initial balance has two decimal places
        twoDecimalPlaces(initialBalance);
        this.accountBalance = initialBalance;
    }

    //Method to deposit money in the account
    public void deposit(double amountToDeposit) {
        //Insure deposit is a positive value
        if (amountToDeposit < 0) {
            throw new IllegalArgumentException("You can not deposit a negative value.");
        }
        //Calls method to insure initial deposit has two decimal places
        twoDecimalPlaces(amountToDeposit);
        //Adds deposit to accountBalance
        accountBalance = accountBalance + amountToDeposit;
        //Adds the transaction to transaction list
        transactionList.add("Deposit of " + "$" + amountToDeposit + "," + "Balance: " + "$" + accountBalance + ".");
    }

    //Method to withdraw money from the account
    public void withdraw(double amountToWithdraw) {
        //Insure withdraw is a positive value
        if (amountToWithdraw < 0) {
            throw new IllegalArgumentException("You can not withdraw a negative value.");
        }
        //Insure that you can not withdraw more money that what is in the account
        if (amountToWithdraw > accountBalance) {
            throw new IllegalArgumentException("You do not have enough money in your account." + "Balance: " + accountBalance + "." + " Amount to Withdraw: " + amountToWithdraw);
        }
        //Calls method to insure initial withdraw has two decimal places
        twoDecimalPlaces(amountToWithdraw);
        //Subtracts withdraw from account
        accountBalance = accountBalance - amountToWithdraw;
        //Adds the transaction to transaction list
        transactionList.add("Withdraw of " + "$" + amountToWithdraw + "," + "Balance: " + "$" + accountBalance + ".");
    }

    //Method to set annual interest rate
    public void setAnnualInterestRate(double interestRate) {
        //Insure interest rate is positive value
        if (interestRate < 0) {
            throw new IllegalArgumentException("You can not have a negative interest rate.");
        }
        annualInterestRate = interestRate;
    }

    //Method to add monthly interest to account balance
    public void monthlyInterest() {
        double monthlyRate = ((annualInterestRate / 12) * accountBalance);
        monthlyRate = Double.valueOf(df.format(monthlyRate));
        accountBalance = accountBalance + monthlyRate;
        transactionList.add("Interest of " + "$" + monthlyRate + "," + "Balance: " + "$" + accountBalance + ".");
    }

    //Method to get account balance
    public double getAccountBalance() {
        accountBalance = Double.valueOf(df.format(accountBalance));
        return accountBalance;
    }

    //To string method to display object accountBalance as String
    @Override
    public String toString() {
        return "" + accountBalance;
    }

    //Method to make sure the double value only has two decimal points
    private void twoDecimalPlaces(double num) {
        if (BigDecimal.valueOf(num).scale() > 2) {
            throw new IllegalArgumentException("The Input can not have more than two decimal places.");
        }
    }
}//End of Class BankBalance
