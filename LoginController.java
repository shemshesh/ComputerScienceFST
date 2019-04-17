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

import java.util.regex.Pattern;

public class LoginController {
	@FXML
	public Group withdrawDepositGroup;
	public Button transactionLogsButton;
	public Button logOutButton;
	public ListView transactionLogsView;
	public Button withdrawDepositButton;
	public Label displayBalance;

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
		transactionLogsView.setVisible(true);
	}

	@FXML
	protected void handleLogOutButtonAction (ActionEvent event) {
	}

	@FXML
	protected void handleWithdrawDepositButtonAction (ActionEvent event) {
		withdrawDepositGroup.setVisible(true);
		transactionLogsView.setVisible(false);

	}

	}

