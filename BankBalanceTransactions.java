package FST;

import java.io.Serializable;

// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalanceTransactions implements Serializable {

    public static void main(String[] args) {
        BankBalance user = new BankBalance(100);
        user.withdraw(95);
        user.deposit(10);
        user.writingBalance("");
        user.writingArray("");
//        user.deposit(100);
//        user.setAnnualInterestRate(.25);
//        user.monthlyInterest();
//        System.out.println(user.transactionList);
//        Collections.sort(user.transactionList);
//        System.out.println(user.transactionList);
//        user.transactionList.sort(Transaction.inverseComparator);
//        System.out.println(user.transactionList);
    }
}
