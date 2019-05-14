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

public class LoginController{//} extends Login {
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

		transactionLogsViewSortChoice.setOnAction(e -> System.out.println(transactionLogsViewSortChoice.getValue()));

	}

	@FXML
	public void handleLogOutButtonAction (ActionEvent event) {
//		Login.logOutButtonPressed();
	}

	@FXML
	protected void handleWithdrawDepositButtonAction (ActionEvent event) {
		withdrawDepositGroup.setVisible(true);
		transactionLogsViewGroup.setVisible(false);

	}

}

