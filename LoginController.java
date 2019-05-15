package FST;

// Project name: ComSciFST
// Program name: LoginController.java
// Purpose: 
// Created by Natan Parker on Wednesday April 17 2019.
// Copyright © 2019 Natan Parker. All rights reserved.

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController {//} extends Login {
	@FXML
	public Group withdrawDepositGroup;
	public Button transactionLogsButton;
	private javafx.scene.control.Button logOutButton;
	public ListView<String> transactionLogsView;
	public Button withdrawDepositButton;
	public Label displayBalance;
	public Group transactionLogsViewGroup;
	public ComboBox<String> transactionLogsViewSortChoice;

	public void setDisplayBalance (String balance) {
		displayBalance.setText(balance);
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

		if (enterFundsField.getText().isEmpty() || !enterFundsField.getText().matches("^\\d+$")) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
					"Please enter an amount to deposit");
			enterFundsField.setText("");
		} else
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Deposit:",
					enterFundsField.getText() + " dollars deposited.");
		enterFundsField.setText("");
	}

	@FXML
	protected void handleWithdrawButtonAction (ActionEvent event) {
		Window owner = withdrawButton.getScene().getWindow();

		if (enterFundsField.getText().isEmpty() || !enterFundsField.getText().matches("^\\d+$")) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
					"Please enter an amount to withdraw");
			enterFundsField.setText("");
		} else
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Withdrawal:",
					enterFundsField.getText() + " dollars withdrawn.");
		enterFundsField.setText("");
	}

	@FXML
	protected void handleTransactionLogsButtonAction (ActionEvent event) {
		BankBalance user1 = new BankBalance(100);
		user1.deposit(10);
		user1.withdraw(20);
		user1.setAnnualInterestRate(0.25);
		withdrawDepositGroup.setVisible(false);
		transactionLogsViewGroup.setVisible(true);
		transactionLogsView.getItems().clear();
		transactionLogsView.getItems().addAll(user1.transactionList.toString());
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

