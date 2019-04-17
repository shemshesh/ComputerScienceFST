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
import javafx.stage.Window;

public class LoginController {
	@FXML
	public Group withdrawDepositGroup;
	public Button transactionLogsButton;
	public Button logOutButton;
	public ListView<String> transactionLogsView;
	public Button withdrawDepositButton;
	public Label displayBalance;
	public Group transactionLogsViewGroup;
	public ComboBox<String> transactionLogsViewSortChoice;

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
		}
	}

	@FXML
	protected void handleWithdrawButtonAction (ActionEvent event) {
		Window owner = withdrawButton.getScene().getWindow();

		if (enterFundsField.getText().isEmpty() || !enterFundsField.getText().matches("^\\d+$")) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
					"Please enter an amount to withdraw");
			enterFundsField.setText("");
		}
	}
	@FXML
	protected void handleTransactionLogsButtonAction (ActionEvent event) {
		withdrawDepositGroup.setVisible(false);
		transactionLogsViewGroup.setVisible(true);
		transactionLogsView.getItems().clear();
		transactionLogsView.getItems().addAll("Item 1", "Item 2", "Item 3");
		transactionLogsView.setFocusTraversable(false);
		transactionLogsViewSortChoice.setPromptText("Sort by: ");
		transactionLogsViewSortChoice.getItems().clear();
		transactionLogsViewSortChoice.getItems().addAll("Amount", "Date");

		if (transactionLogsViewSortChoice.getValue().equals("Amount")){
			System.out.println("Sorting by amount");
		}
		if (transactionLogsViewSortChoice.getValue().equals("Date")){
			System.out.println("Sorting by date");
		}

	}

	@FXML
	protected void handleLogOutButtonAction (ActionEvent event) {
	}

	@FXML
	protected void handleWithdrawDepositButtonAction (ActionEvent event) {
		withdrawDepositGroup.setVisible(true);
		transactionLogsViewGroup.setVisible(false);

	}

	}

