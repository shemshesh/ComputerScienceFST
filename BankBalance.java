package FST;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalance implements Serializable {//Start of Class BankBalance

	private double accountBalance;
	private double annualInterestRate;
	private ArrayList<Transaction> allTransactions= new ArrayList<>();

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
	}

	//Method to put old and new transactions into a single ArrayList called allTransactions
	public void readingArray() throws IOException{
		ArrayList<Transaction> transactions = new ArrayList<>();

		try {
			//reads all old transactions already stored and adds to array called transactions
			FileInputStream fileIn = new FileInputStream(Account.returnName()+ "transactionList.txt");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Object obj = objectIn.readObject();
			int i=0;
			while (i < 1000000000) {
				Transaction transaction = (Transaction) obj;
				transactions.add(transaction);
				obj = objectIn.readObject();
				i++;
			}

			objectIn.close();
		} catch (Exception ex) { }

		try {
			//if refresh button is pressed without adding new transactions, allTransactions isn't clear
			//if it isn't clear that means that the transactions are already being displayed
			//if it is clear it will send to catch and add the transactions to allTransactions
			allTransactions.get(0);
		}catch (Exception eeeee) {
			//If transactionList is larger than transactions, it already contains transactions
			//If it is smaller or the same size it means that it only contains the new transactions and the old ones must be added
			if(transactionList.size() <= transactions.size()) {
				allTransactions.addAll(transactions);
			}

			//Add new transactions
			allTransactions.addAll(transactionList);
			transactionList.clear();
			//Add transactions back to transactionList because this ArrayList is used to display
			transactionList.addAll(allTransactions);

			//Goes through all transactions and adds all deposits to depositList and all withdrawals to withdrawList for sorting
			for (int i = 0; i < transactionList.size(); i++) {
				if(transactionList.get(i).type == Transaction.Type.deposit){
					var transaction = new DepTransaction(DepTransaction.Type.deposit, transactionList.get(i).amount, transactionList.get(i).balanceAfterTransaction);
					transaction.date = transactionList.get(i).date;
					depositList.add(transaction);
				}else if(transactionList.get(i).type == Transaction.Type.withdrawal){
					var transaction = new WithTransaction(WithTransaction.Type.withdraw, transactionList.get(i).amount, transactionList.get(i).balanceAfterTransaction);
					transaction.date = transactionList.get(i).date;
					withdrawList.add(transaction);
				}
			}
		}
	}

	//Method to write transactions as Transactions (Objects) to file for current user
	public void writingArray (String user) throws IOException{
        readingArray();

		try {
			//Initializes file with name of user followed by transactionList.txt
            FileOutputStream fileOut = new FileOutputStream(user + "transactionList.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            //Writes all transactions stored in allTransactions ArrayList to the file
		    for (int i = 0; i < allTransactions.size(); i++) {
		        objectOut.writeObject(allTransactions.get(i));
		    }
		    objectOut.close();
		} catch (Exception ex) {
		    System.out.println("Uh oh");
		    ex.printStackTrace();
		}

	}


	//writes balance when called to file called user followed by balance.txt
    public void writingBalance (String user) {
		try {
			FileWriter fr = new FileWriter(user + "balance.txt");
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter pw = new PrintWriter(br);
			pw.write(getAccountBalance());
			pw.close();
		} catch (IOException e) { }
	}

	//reads balance stored. If new user has no stored balance, balance is set to 100 automatically
	public static String readingBalance (String user) throws IOException {
		String balance;
		try {
			FileReader fr = new FileReader(user + "balance.txt");
			BufferedReader br = new BufferedReader(fr);
			balance = br.readLine();
		} catch (Exception e) {
			balance = "$100.00";
		}
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