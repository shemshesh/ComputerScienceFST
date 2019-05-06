package FST;
// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalanceTransactions {
    public static void main(String[] args) {
        BankBalance user = new BankBalance(100);
        user.withdraw(20);
        user.deposit(10);
        user.setAnnualInterestRate(.25);
        user.monthlyInterest();
        System.out.println(user.transactionList);
    }
}
