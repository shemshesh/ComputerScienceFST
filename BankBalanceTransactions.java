package FST;

// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalanceTransactions{

    public static void main(String[] args) {
        BankBalance user = new BankBalance(100);
        Account.signIn("Evan", "123");
        user.withdraw(95);
        user.deposit(100);
        user.withdraw(100);
        System.out.println(user.withdrawList);
        user.deposit(100);
        user.writingBalance(Account.returnName());
        user.writingArray("Evan");
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
