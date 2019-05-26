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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {//} extends Login {

    private final BankBalance user1 = new BankBalance(100.00);

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

    public LoginController() throws IOException {
    }

    @FXML
    void initializeBalance() {
        usernameLabel.setText("User: " + Account.returnName());
        displayBalance.setText(user1.getAccountBalance());
    }

    @FXML
    private TextField enterFundsField;

    @FXML
    private Button depositButton;

    @FXML
    private Button withdrawButton;

    @FXML
    protected void handleRefreshBalanceButtonAction(ActionEvent event) {
        initializeBalance();
    }

    @FXML
    public void handleRefreshButtonTransactionList(ActionEvent event) {

        if (!depositsOnly.isSelected() && !withdrawalsOnly.isSelected()) {
            transactionLogsView.getItems().clear();
        } else if (depositsOnly.isSelected() && !withdrawalsOnly.isSelected()) {
            transactionLogsView.getItems().clear();
            if (transactionLogsViewSortChoice.getValue().equals("Date")) {
                user1.depositList.sort(DepTransaction.timeComparator);
            } else user1.depositList.sort(DepTransaction.inverseComparator);

            for (int i = 0; i < user1.depositList.size(); i++) {
                transactionLogsView.getItems().add(user1.depositList.get(i).toString());
            }
        } else if (!depositsOnly.isSelected() && withdrawalsOnly.isSelected()) {
            transactionLogsView.getItems().clear();
            if (transactionLogsViewSortChoice.getValue().equals("Date")) {
                user1.withdrawList.sort(WithTransaction.timeComparator);
            } else user1.withdrawList.sort(WithTransaction.inverseComparator);
            for (int i = 0; i < user1.withdrawList.size(); i++) {
                transactionLogsView.getItems().add(user1.withdrawList.get(i).toString());
            }
        } else {
            transactionLogsView.getItems().clear();
            if (transactionLogsViewSortChoice.getValue().equals("Date")) {
                user1.transactionList.sort(Transaction.timeComparator);
            } else user1.transactionList.sort(Transaction.inverseComparator);
            for (int i = 0; i < user1.transactionList.size(); i++) {
                transactionLogsView.getItems().add(user1.transactionList.get(i).toString());
            }
        }
    }

    @FXML
    protected void handleDepositButtonAction(ActionEvent event) {
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
    protected void handleWithdrawButtonAction(ActionEvent event) {
        Window owner = withdrawButton.getScene().getWindow();

        if (enterFundsField.getText().isEmpty() || !enterFundsField.getText().matches("(\\+|-)?\\d+(\\.\\d{1,2})?")) {
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
    protected void handleTransactionLogsButtonAction(ActionEvent event) {
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
    public void handleLogOutButtonAction(ActionEvent event) {
        user1.writingBalance(Account.returnName());
        user1.writingArray(Account.returnName());
        // get a handle to the stage
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    protected void handleWithdrawDepositButtonAction(ActionEvent event) {
        refreshBalanceButton.setVisible(true);
        withdrawDepositGroup.setVisible(true);
        transactionLogsViewGroup.setVisible(false);
    }

}

