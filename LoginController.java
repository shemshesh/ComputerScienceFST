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

import java.util.Arrays;
import java.util.LinkedHashSet;

public class LoginController {//} extends Login {
	public BankBalance user1 = new BankBalance(100);

	@FXML
	public Group withdrawDepositGroup;
	public Button transactionLogsButton;
	public javafx.scene.control.Button logOutButton;
	public ListView<String> transactionLogsView;
	public Button withdrawDepositButton;
	public Label displayBalance;
	public Group transactionLogsViewGroup;
	public ComboBox<String> transactionLogsViewSortChoice;
	public Label usernameLabel;

	@FXML
	private void initialize () {
		displayBalance.setText(user1.getAccountBalance());
		usernameLabel.setText("User: " + "This part needs fixing");
	}

	@FXML
	private TextField enterFundsField;

	@FXML
	private Button depositButton;

	@FXML
	private Button withdrawButton;

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
		initialize();
	}

	@FXML
	protected void handleWithdrawButtonAction (ActionEvent event) {
		Window owner = withdrawButton.getScene().getWindow();

		if (enterFundsField.getText().isEmpty() || !enterFundsField.getText().matches("(\\+|-)?\\d+(\\.\\d{1,2})?")) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
					"Please enter an amount to withdraw");
			enterFundsField.setText("");
		}/* else if (Double.parseDouble(enterFundsField.getText()) > Double.parseDouble(user1.getAccountBalance())) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
					"You do not have enough money to withdraw that amount!");
			enterFundsField.setText("");
		}*/ else {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Withdrawal:",
					enterFundsField.getText() + " dollars withdrawn.");
			user1.withdraw(Double.parseDouble(enterFundsField.getText()));
			enterFundsField.setText("");
		}
		initialize();
	}

	@FXML
	protected void handleTransactionLogsButtonAction (ActionEvent event) {

		withdrawDepositGroup.setVisible(false);
		transactionLogsViewGroup.setVisible(true);

		LinkedHashSet<java.util.ArrayList<Transaction>> linkedHashSet = new LinkedHashSet<>(Arrays.asList(user1.transactionList));

		transactionLogsView.getItems().clear();
		for (int i = 0; i < user1.transactionList.size(); i++) {
			transactionLogsView.getItems().add(user1.transactionList.get(i).toString());
		}

		transactionLogsView.setFocusTraversable(false);
		transactionLogsViewSortChoice.setPromptText("Sort by: ");
		transactionLogsViewSortChoice.getItems().clear();
		transactionLogsViewSortChoice.getItems().addAll("Amount", "Date");
		transactionLogsViewSortChoice.setOnAction(e -> System.out.println(transactionLogsViewSortChoice.getValue()));

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

