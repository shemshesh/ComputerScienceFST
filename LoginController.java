package FST;

// Project name: ComSciFST
// Program name: LoginController.java
// Purpose: 
// Created by Natan Parker on Wednesday April 17 2019.
// Copyright © 2019 Natan Parker. All rights reserved.

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.concurrent.atomic.AtomicInteger;

public class LoginController {//} extends Login {
	private final BankBalance user1 = new BankBalance(100);

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

	@FXML
	void initializeBalance () {
		displayBalance.setText(user1.getAccountBalance());
	}

	@FXML
	private TextField enterFundsField;

	@FXML
	private Button depositButton;

	@FXML
	private Button withdrawButton;

	@FXML
	protected void handleRefreshBalanceButtonAction (ActionEvent event) {
		initializeBalance();
	}

	@FXML
	protected void handleDepositButtonAction (ActionEvent event) {
		Window owner = depositButton.getScene().getWindow();
		if (enterFundsField.getText().isEmpty() || !enterFundsField.getText().matches("(\\+|-)?\\d+(\\.\\d{1,2})?")) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
					"Please enter an amount to deposit");
			enterFundsField.setText("");
		} else {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Deposit:",
					enterFundsField.getText() + " dollars deposited.");
			user1.deposit(Double.parseDouble(enterFundsField.getText()));
			enterFundsField.setText("");
		}
		initializeBalance();
	}

	@FXML
	protected void handleWithdrawButtonAction (ActionEvent event) {
		Window owner = withdrawButton.getScene().getWindow();

		if (enterFundsField.getText().isEmpty() || !enterFundsField.getText().matches("(\\+|-)?\\d+(\\.\\d{1,2})?")) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
					"Please enter an amount to withdraw");
			enterFundsField.setText("");
		} else if (Double.parseDouble(enterFundsField.getText()) > Double.parseDouble(user1.getAccountBalance().replace("$", ""))) {
		}/* else if (Double.parseDouble(enterFundsField.getText()) > Double.parseDouble(user1.getAccountBalance())) {
>>>>>>> 23bc4c8797b5942c10dd446f65d5e11af7d22a6e
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
					"You do not have enough money to withdraw that amount!");
			enterFundsField.setText("");
		}*/ else {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Withdrawal:",
					enterFundsField.getText() + " dollars withdrawn.");
			user1.withdraw(Double.parseDouble(enterFundsField.getText()));
			enterFundsField.setText("");
		}
		initializeBalance();
	}

	@FXML
	protected void handleTransactionLogsButtonAction (ActionEvent event) {
		refreshBalanceButton.setVisible(false);

		withdrawDepositGroup.setVisible(false);
		transactionLogsViewGroup.setVisible(true);
		transactionLogsView.setFocusTraversable(false);
		transactionLogsViewSortChoice.setPromptText("Sort by: ");
//		transactionLogsViewSortChoice.getItems().clear();
		transactionLogsViewSortChoice.getItems().addAll("Amount", "Date");
		transactionLogsViewSortChoice.setOnAction(e -> {
			transactionLogsView.getItems().clear();
			user1.transactionList.sort(Transaction.timeComparator);
			for (int i = 0; i < user1.transactionList.size(); i++) {
				transactionLogsView.getItems().add(user1.transactionList.get(i).toString());
			}
			if (transactionLogsViewSortChoice.getValue().equals("Date")) {
				if (!depositsOnly.isSelected() && !withdrawalsOnly.isSelected()) {
					System.out.println("Neither selected");
					transactionLogsView.getItems().clear();
				} else if (depositsOnly.isSelected() && !withdrawalsOnly.isSelected()) {
					System.out.println("Deposits only");
					for (int i = 0; i < user1.transactionList.size(); i++) {
						if (user1.transactionList.get(i).toString().contains("Deposit")) {
							transactionLogsView.getItems().add(user1.transactionList.get(i).toString());
						}
					}
				} else if (!depositsOnly.isSelected() && withdrawalsOnly.isSelected()) {
					System.out.println("Withdrawals only");
					transactionLogsView.getItems().clear();
					for (int i = 0; i < user1.transactionList.size(); i++) {
						if (user1.transactionList.get(i).toString().contains("Withdraw")) {
							transactionLogsView.getItems().add(user1.transactionList.get(i).toString());
						}
					}
				} else {
					System.out.println("Withdrawals and deposits");
					transactionLogsView.getItems().clear();
					for (int i = 0; i < user1.transactionList.size(); i++) {
						transactionLogsView.getItems().add(user1.transactionList.get(i).toString());
					}

				}
				System.out.println("Sorting by date");
				transactionLogsView.getItems().clear();
				user1.transactionList.sort(Transaction.timeComparator);
				for (int i = 0; i < user1.transactionList.size(); i++) {
					transactionLogsView.getItems().add(user1.transactionList.get(i).toString());
				}

			} else {
				System.out.println("Sorting by amount");
				transactionLogsView.getItems().clear();
				user1.transactionList.sort(Transaction.inverseComparator);
				for (int i = 0; i < user1.transactionList.size(); i++) {
					transactionLogsView.getItems().add(user1.transactionList.get(i).toString());
				}
			}
		});

	}

	@FXML
	public void handleLogOutButtonAction (ActionEvent event) {
		// get a handle to the stage
		Stage stage = (Stage) logOutButton.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	@FXML
	protected void handleWithdrawDepositButtonAction (ActionEvent event) {
		withdrawDepositGroup.setVisible(true);
		transactionLogsViewGroup.setVisible(false);

	}

}

