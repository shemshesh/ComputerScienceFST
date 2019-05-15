package FST;

import javafx.scene.control.Label;

import java.util.Collections;

// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalanceTransactions {
    public static void main(String[] args) {
        BankBalance user = new BankBalance(100);
        user.withdraw(95);
        user.deposit(10);
        user.deposit(100);
        user.setAnnualInterestRate(.25);
        user.monthlyInterest();
        System.out.println(user.transactionList);
        Collections.sort(user.transactionList);
        System.out.println(user.transactionList);
        user.transactionList.sort(Transaction.timeComparator);
        System.out.println(user.transactionList);
        LoginController setbalance = new LoginController();
        setbalance.setDisplayBalance("Balance" + user.getAccountBalance());
    }
}