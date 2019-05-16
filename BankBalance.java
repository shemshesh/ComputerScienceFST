package FST;
import java.util.Date;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalance {//Start of Class BankBalance

    private double accountBalance;
    private double annualInterestRate;

    private DecimalFormat df = new DecimalFormat("'$'0.00");//Decimal format that rounds to two decimal places
    public ArrayList<Transaction> transactionList = new ArrayList<>();//Creating array list of transactions

    //Constructor method to initialize object accountBalance and annualInterestRate
    BankBalance(double initialBalance) {
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
        var transaction = new Transaction(Transaction.Type.deposit, amountToDeposit, accountBalance);
        transactionList.add(transaction);
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
        accountBalance -= amountToWithdraw;
        var transaction = new Transaction(Transaction.Type.withdrawal, amountToWithdraw, accountBalance);
        transactionList.add(transaction);
        lowBalance();
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
        accountBalance += monthlyRate;

        var transaction = new Transaction(Transaction.Type.interest, monthlyRate, accountBalance);
        transactionList.add(transaction);
    }

    //Method to get account balance

//    public double getAccountBalance() {
//        return df.format(accountBalance);
//    }

    private void lowBalance(){
        if (accountBalance < 10){
            System.out.println("Your balance is running low.");
        }
    }

    public String getAccountBalance() {
        return df.format(accountBalance);
    }

    //To string method to display object accountBalance as String
    @Override
    public String toString() {
        return "" + df.format(accountBalance);
    }

    //Method to make sure the double value only has two decimal points
    private void twoDecimalPlaces(double num) {
        if (BigDecimal.valueOf(num).scale() > 2) {
            throw new IllegalArgumentException("The Input can not have more than two decimal places.");
        }
    }
}//End of Class BankBalance

class Transaction implements Comparable<Transaction> {
    private final DecimalFormat df = new DecimalFormat("'$'0.00");//Decimal format that rounds to two decimal places
    private final Date date;
    private final Type type;
    private final double amount;
    private final double balanceAfterTransaction;


    public static final Comparator<Transaction> inverseComparator = (t1, t2) -> -t1.compareTo(t2);
    public static final Comparator<Transaction> timeComparator = (t1, t2) -> -t1.date.compareTo(t2.date);

    public Transaction(Type type, double amount, double balanceAfterTransaction) {
        this.type = type;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.date = new Date();
    }

    @Override
    public int compareTo(Transaction o) {
        return Double.compare(this.amount, o.amount);
    }
    enum Type {
        deposit, withdrawal, interest
    }

    @Override
    public String toString() {
        switch (type) {
            case deposit:
                return "Deposit: " + df.format(amount) + "," + " Balance: " + df.format(balanceAfterTransaction)+" Time:"+date;
            case withdrawal:
                return "Withdraw: " + df.format(amount) + "," + " Balance: " + df.format(balanceAfterTransaction)+" Time:"+date;
            case interest:
                return "Interest: " + df.format(amount) + "," + " Balance: " + df.format(balanceAfterTransaction)+" Time:"+date;
            default:
                throw new IllegalArgumentException("Impossible");
        }
    }
}