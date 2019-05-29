package FST;

// Project name: ComSciFST
// Program name: LoginController.java
// Purpose: This class sets actions to all of the FXML elements contained in GUIfxml
// Created by Natan Parker on Wednesday April 17 2019.
// Copyright © 2019 Natan Parker. All rights reserved.

import javafx.beans.binding.When;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoginController {


	private BankBalance user1;

	//The following lines create the controls and containers referenced in the FXML file
	@FXML
	public Group withdrawDepositGroup;
	public Button transactionLogsButton;
	public javafx.scene.control.Button logOutButton;
	public ListView<String> transactionLogsView;
	public Button withdrawDepositButton;
	public Label displayBalance;
	public Group transactionLogsViewGroup;
	public ComboBox<String> transactionLogsViewSortChoice;
	public Button refreshBalanceButton;
	public CheckBox withdrawalsOnly;
	public CheckBox depositsOnly;
	public Label usernameLabel;
	public Button refreshTransactionList;


	@FXML
	void initializeBalance () throws IOException { //This method initializes the balance value and also updates the transaction list
		ArrayList<Transaction> transactions = new ArrayList<>();
		try {
			for (int i = 0; i < user1.transactionList.size(); i++) {
				transactions.add(user1.transactionList.get(i));
			}
		}catch (Exception e){ }
		user1 = new BankBalance(Double.parseDouble(BankBalance.readingBalance(Account.returnName()).replace("$", ""))); //Updates the balance
		try {
			for (int i = 0; i < transactions.size(); i++) {
				user1.transactionList.add(transactions.get(i));
			}
		}catch (Exception e){}
		usernameLabel.setText("User: " + Account.returnName()); //Sets a label to isplay the username
		displayBalance.setText(BankBalance.readingBalance(Account.returnName())); //Sets a label to display the balance
		user1.writingBalance(Account.returnName()); //Writes current balance to a file
	}

	@FXML
	private TextField enterFundsField;

	@FXML
	private Button depositButton;

	@FXML
	private Button withdrawButton;

	@FXML
	protected void handleRefreshBalanceButtonAction (ActionEvent event) throws IOException { //Refresh button adds in balance and username on initial run, since the initialize() method cannot be called from another class
		initializeBalance();
		refreshBalanceButton.setVisible(false);
	}

	@FXML
	public void handleRefreshButtonTransactionList (ActionEvent event) { //Updates the transaction list according to selected sorting and filtering
		try {
			user1.writingArray(Account.returnName());
		} catch (IOException e) { }
		if (!depositsOnly.isSelected() && !withdrawalsOnly.isSelected()) { //Clears table if neither box is checked
			transactionLogsView.getItems().clear();
		} else if (depositsOnly.isSelected() && !withdrawalsOnly.isSelected()) { //Clears the table, then sorts array of only deposits based on user selection
			transactionLogsView.getItems().clear();
			if (transactionLogsViewSortChoice.getValue().equals("Date")) {
				user1.depositList.sort(DepTransaction.timeComparator);
			} else user1.depositList.sort(DepTransaction.inverseComparator);

			for (int i = 0; i < user1.depositList.size(); i++) {
				transactionLogsView.getItems().add(user1.depositList.get(i).toString()); //Adding sorted and filtered array to table
			}
		} else if (!depositsOnly.isSelected() && withdrawalsOnly.isSelected()) {  //Clears the table, then sorts array of only withdrawals based on user selection
			transactionLogsView.getItems().clear();
			if (transactionLogsViewSortChoice.getValue().equals("Date")) {
				user1.withdrawList.sort(WithTransaction.timeComparator);
			} else user1.withdrawList.sort(WithTransaction.inverseComparator);
			for (int i = 0; i < user1.withdrawList.size(); i++) { //Adding sorted and filtered array to table
				transactionLogsView.getItems().add(user1.withdrawList.get(i).toString());
			}
		} else {
			transactionLogsView.getItems().clear();
			if (transactionLogsViewSortChoice.getValue().equals("Date")) {
				user1.transactionList.sort(Transaction.timeComparator);
			} else user1.transactionList.sort(Transaction.inverseComparator);
			for (int i = 0; i < user1.transactionList.size(); i++) {
				transactionLogsView.getItems().add(user1.transactionList.get(i).toString()); //Adding all transactions to the table, sorted based on user selection
			}
		}
	}

	@FXML
	protected void handleDepositButtonAction (ActionEvent event) throws IOException { /*Takes string value from text field, converts it to a double and passes the value
	                                                                                    into the BankBalance class for processing, after checking for proper user input */
		Window owner = depositButton.getScene().getWindow();
		if (enterFundsField.getText().isEmpty() || !enterFundsField.getText().matches("(\\+|-)?\\d+(\\.\\d{1,2})?") || Double.parseDouble(enterFundsField.getText().replace("$", "")) < 0) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
					"Please enter an amount to deposit");
			enterFundsField.setText("");
		} else {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Deposit:",
					enterFundsField.getText() + " dollars deposited.");
			user1.deposit(Double.parseDouble(enterFundsField.getText()));
			enterFundsField.setText("");
		}
		System.out.println(usernameLabel.getText().replace("User: ", ""));
		initializeBalance();
	}

	@FXML
	protected void handleWithdrawButtonAction (ActionEvent event) throws IOException {/*Takes string value from text field, converts it to a double and passes the value
	                                                                                    into the BankBalance class for processing, after checking for proper user input */
		Window owner = withdrawButton.getScene().getWindow();

		if (enterFundsField.getText().isEmpty() || !enterFundsField.getText().matches("(\\+|-)?\\d+(\\.\\d{1,2})?")|| Double.parseDouble(enterFundsField.getText().replace("$", "")) < 0) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
					"Please enter an amount to withdraw");
			enterFundsField.setText("");
		} else if (Double.parseDouble(enterFundsField.getText()) > Double.parseDouble(user1.getAccountBalance().replace("$", ""))) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
					"You do not have enough money to withdraw that amount!");
			enterFundsField.setText("");
		} else {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Withdrawal:",
					enterFundsField.getText() + " dollars withdrawn.");
			user1.withdraw(Double.parseDouble(enterFundsField.getText()));
			enterFundsField.setText("");
		}
		initializeBalance();
	}

	@FXML
	protected void handleTransactionLogsButtonAction (ActionEvent event) { /*Sets actions for the transaction logs button. Makes the withdraw/deposit
																			 controls invisible, makes the transaction log table visible */
		refreshBalanceButton.setVisible(false);

		withdrawDepositGroup.setVisible(false);
		transactionLogsViewGroup.setVisible(true);
		transactionLogsView.setFocusTraversable(false);
		transactionLogsViewSortChoice.getItems().clear();
		transactionLogsViewSortChoice.setValue(null);
		transactionLogsViewSortChoice.setPromptText("Sort by: ");

		transactionLogsViewSortChoice.getItems().addAll("Amount", "Date");

		transactionLogsViewSortChoice.setOnAction(e -> {
			transactionLogsView.getItems().clear();
		});
	}

	@FXML
	public void handleLogOutButtonAction (ActionEvent event) throws IOException { /* When the logout button is pressed, the balance will write to a file, the
																					transaction list will write to a file, and the window will close */
		user1.writingBalance(Account.returnName());
		user1.writingArray(Account.returnName());
		// get a handle to the stage
		Stage stage = (Stage) logOutButton.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	@FXML
	protected void handleWithdrawDepositButtonAction (ActionEvent event) { /* When the withdraw/deposit button is pressed, the refresh button becomes invisible, the
																			  withdraw/deposit controls become visible, and the transaction list becomes invisible */
		refreshBalanceButton.setVisible(false);
		withdrawDepositGroup.setVisible(true);
		transactionLogsViewGroup.setVisible(false);
	}

}

