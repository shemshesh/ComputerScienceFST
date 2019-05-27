package FST;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.Date;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalance implements Serializable {//Start of Class BankBalance

	private double accountBalance;
	private double annualInterestRate;

	private DecimalFormat df = new DecimalFormat("'$'0.00");//Decimal format that rounds to two decimal places
	public ArrayList<Transaction> transactionList = new ArrayList<>();//Creating array list of transactions
	public ArrayList<DepTransaction> depositList = new ArrayList<>();
	public ArrayList<WithTransaction> withdrawList = new ArrayList<>();

	//Constructor method to initialize object accountBalance and annualInterestRate
	BankBalance (double initialBalance) {
		accountBalance = initialBalance;
		setAccountBalance(initialBalance);
		annualInterestRate = 0;
	}

	//Set the value of the initial balance of accountBalance
	private void setAccountBalance (double initialBalance) {
		//Error for account to not have negative balance
		if (initialBalance < 0) {
			throw new IllegalArgumentException("The bank account can not have a negative balance.");
		}
		//Calls method to insure initial balance has two decimal places
		twoDecimalPlaces(initialBalance);
		this.accountBalance = initialBalance;
	}

	//Method to deposit money in the account
	public void deposit (double amountToDeposit) {
		//Insure deposit is a positive value
		if (amountToDeposit < 0) {
			throw new IllegalArgumentException("You can not deposit a negative value.");
		}
		//Calls method to insure initial deposit has two decimal places
		twoDecimalPlaces(amountToDeposit);
		//Adds deposit to accountBalance
		accountBalance = accountBalance + amountToDeposit;
		writingBalance(Account.returnName());
		//Adds the transaction to transaction list
		var transaction = new Transaction(Transaction.Type.deposit, amountToDeposit, accountBalance);
		transactionList.add(transaction);
		var transaction1 = new DepTransaction(DepTransaction.Type.deposit, amountToDeposit, accountBalance);
		depositList.add(transaction1);

//        System.out.println("deposit"+transactionList.get(0).amount);
	}

	//Method to withdraw money from the account
	public void withdraw (double amountToWithdraw) {
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
		writingBalance(Account.returnName());

		var transaction = new Transaction(Transaction.Type.withdrawal, amountToWithdraw, accountBalance);
		transactionList.add(transaction);
		var transaction1 = new WithTransaction(WithTransaction.Type.withdraw, amountToWithdraw, accountBalance);
		withdrawList.add(transaction1);

//        System.out.println("withdraw"+transactionList.get(0).amount);
	}

//    public void writingArray(String user) {
//        try {
//            ArrayList<String> fullTransactionList = new ArrayList<>();
//            FileReader fr1 = new FileReader(user + "transactionList.txt");
//            BufferedReader br1 = new BufferedReader(fr1);
//            String line = "start";
//            while (line != null) {
//                line = br1.readLine();
//                fullTransactionList.add(line);
//            }
//
//            FileWriter fw = new FileWriter(user + "transactionList.txt");
//            BufferedWriter bw = new BufferedWriter(fw);
//            PrintWriter pw = new PrintWriter(bw);
//            for (int i = 0; i < fullTransactionList.size(); i++) {
//                if(fullTransactionList.get(i) != null){
//                    pw.write(fullTransactionList.get(i));
//                    pw.write("\n");
//                }
//            }
//            for (int i = 0; i < transactionList.size(); i++) {
//                if (transactionList.get(i) != null)
//                    pw.write(String.valueOf(transactionList.get(i)));
//                pw.write("\n");
//            }
//            pw.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }

	public void writingArray (String user) throws IOException {
	    ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(user + "transactionList.txt");
            System.out.println("RIGHTHERE");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            System.out.println("Now");
            int i = 0;
            Object obj = objectIn.readObject();
            while (i<10000) {
                System.out.println("Yeehaw");
                Transaction transaction = (Transaction) obj;
                System.out.println("Nein");
                transactions.add(transaction);
                System.out.println("NONONONONO");
                System.out.println(transaction.amount);
                System.out.println("SSSSSSSSSSSS");
                i++;
                obj = objectIn.readObject();
            }

            objectIn.close();
        } catch (Exception ex) {
			System.out.println("Oof");
        }

//        System.out.println("$"+transactionList.get(0).amount);
		for (int i = 0; i < transactionList.size(); i++) {
			transactions.add(transactionList.get(i));
            System.out.println(transactions.get(i).amount);
		}

		try {
            FileOutputStream fileOut = new FileOutputStream(user + "transactionList.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		    for (int i = 0; i < transactions.size(); i++) {
		        objectOut.writeObject(transactions.get(i));
                System.out.println(transactions.get(i).amount);
                System.out.println(transactions.get(i).date);
		    }
		    objectOut.close();
		    System.out.println("The Object  was successfully written to a file");
		} catch (Exception ex) {
		    System.out.println("Uh oh");
		    ex.printStackTrace();
		}

//        System.out.println("writingArray"+transactionList.get(0).amount);
	}

	public void writingBalance (String user) {
		try {
			FileWriter fr = new FileWriter(user + "balance.txt");
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter pw = new PrintWriter(br);
			pw.write(getAccountBalance());
			pw.close();
		} catch (IOException e) {
			System.out.println(e);
		}

//        System.out.println("writing balance"+transactionList.get(0).amount);
	}

	public static String readingBalance (String user) throws IOException {
		String balance;
		try {
			FileReader fr = new FileReader(user + "balance.txt");
			BufferedReader br = new BufferedReader(fr);
			balance = br.readLine();
		} catch (Exception e) {
			balance = "$100.00";
		}
		System.out.println(balance);
		return balance;
	}

	//Method to set annual interest rate
	public void setAnnualInterestRate (double interestRate) {
		//Insure interest rate is positive value
		if (interestRate < 0) {
			throw new IllegalArgumentException("You can not have a negative interest rate.");
		}
		annualInterestRate = interestRate;
	}

	//Method to add monthly interest to account balance
	public void monthlyInterest () {
		double monthlyRate = ((annualInterestRate / 12) * accountBalance);
		accountBalance += monthlyRate;

		var transaction = new Transaction(Transaction.Type.interest, monthlyRate, accountBalance);
		transactionList.add(transaction);
	}

//
//    private void lowBalance() {
//        if (accountBalance < 10) {
//            System.out.println("Your balance is running low.");
//        }
//    }

	public String getAccountBalance () {
		return df.format(accountBalance);
	}

	//To string method to display object accountBalance as String
	@Override
	public String toString () {
		return "" + df.format(accountBalance);
	}

	//Method to make sure the double value only has two decimal points
	private void twoDecimalPlaces (double num) {
		if (BigDecimal.valueOf(num).scale() > 2) {
			throw new IllegalArgumentException("The Input can not have more than two decimal places.");
		}
	}
}//End of Class BankBalance

class Transaction implements Comparable<Transaction>, Serializable {
	private final DecimalFormat df = new DecimalFormat("'$'0.00");//Decimal format that rounds to two decimal places
	public Date date;
	public Type type;
	public final double amount;
	public final double balanceAfterTransaction;

	public static Comparator<Transaction> inverseComparator = (t1, t2) -> -t1.compareTo(t2);
	public static Comparator<Transaction> timeComparator = (t1, t2) -> -t1.date.compareTo(t2.date);

	public Transaction (Type type, double amount, double balanceAfterTransaction) {
		this.type = type;
		this.amount = amount;
		this.balanceAfterTransaction = balanceAfterTransaction;
		this.date = new Date();
	}

	@Override
	public int compareTo (Transaction o) {
		return Double.compare(this.amount, o.amount);
	}

	enum Type {
		deposit, withdrawal, interest
	}

	@Override
	public String toString () {
		switch (type) {
			case deposit:
				return "Deposit: " + df.format(amount) + "," + " Balance: " + df.format(balanceAfterTransaction) + ", Time: " + date;
			case withdrawal:
				return "Withdraw: " + df.format(amount) + "," + " Balance: " + df.format(balanceAfterTransaction) + ", Time: " + date;
			case interest:
				return "Interest: " + df.format(amount) + "," + " Balance: " + df.format(balanceAfterTransaction) + ", Time: " + date;
			default:
				throw new IllegalArgumentException("Impossible");
		}
	}
}

class DepTransaction implements Comparable<DepTransaction>, Serializable {
	private final DecimalFormat df = new DecimalFormat("'$'0.00");//Decimal format that rounds to two decimal places
	public Date date;
	public Type type;
	public double amount;
	public double balanceAfterTransaction;

	public static final Comparator<DepTransaction> inverseComparator = (t1, t2) -> -t1.compareTo(t2);
	public static final Comparator<DepTransaction> timeComparator = (t1, t2) -> -t1.date.compareTo(t2.date);

	public DepTransaction (Type type, double amount, double balanceAfterTransaction) {
		this.type = type;
		this.amount = amount;
		this.balanceAfterTransaction = balanceAfterTransaction;
		this.date = new Date();
	}

	@Override
	public int compareTo (DepTransaction o) {
		return Double.compare(this.amount, o.amount);
	}

	enum Type {
		deposit
	}

	@Override
	public String toString () {
		switch (type) {
			case deposit:
				return "Deposit: " + df.format(amount) + "," + " Balance: " + df.format(balanceAfterTransaction) + ", Time: " + date;
			default:
				throw new IllegalArgumentException("Impossible");
		}
	}
}

class WithTransaction implements Comparable<WithTransaction>, Serializable {
	private final DecimalFormat df = new DecimalFormat("'$'0.00");//Decimal format that rounds to two decimal places
	public Date date;
	public Type type;
	public double amount;
	public double balanceAfterTransaction;

	public static final Comparator<WithTransaction> inverseComparator = (t1, t2) -> -t1.compareTo(t2);
	public static final Comparator<WithTransaction> timeComparator = (t1, t2) -> -t1.date.compareTo(t2.date);

	public WithTransaction (Type type, double amount, double balanceAfterTransaction) {
		this.type = type;
		this.amount = amount;
		this.balanceAfterTransaction = balanceAfterTransaction;
		this.date = new Date();
	}

	@Override
	public int compareTo (WithTransaction o) {
		return Double.compare(this.amount, o.amount);
	}

	enum Type {
		withdraw
	}

	@Override
	public String toString () {
		switch (type) {
			case withdraw:
				return "Withdraw: " + df.format(amount) + "," + " Balance: " + df.format(balanceAfterTransaction) + ", Time: " + date;
			default:
				throw new IllegalArgumentException("Impossible");
		}
	}
}