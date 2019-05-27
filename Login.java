package FST;
// Program name: Login.java
// Purpose:
// Created by Natan Parker on Saturday April 06 2019.
// Copyright © 2019 Natan Parker. All rights reserved.

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

public class Login extends Application {

	private static Scene login;
	private Scene loginPage;
	private Scene createAccountPage;
	private Stage primaryStage;

	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void start (Stage primaryStage) throws Exception {

		primaryStage.setTitle("De Bank");
		primaryStage.show();



		Parent root = FXMLLoader.load(getClass().getResource("GUIfxml.fxml"));
		Scene accountView = new Scene(root, 800, 450);

		//primaryStage.setScene(accountView);

		GridPane welcomeGrid = new GridPane();
		welcomeGrid.setAlignment(Pos.CENTER);
		welcomeGrid.setHgap(10);
		welcomeGrid.setVgap(10);
//		welcomeGrid.setPadding(new Insets(25, 25, 25, 25));

		Text welcomeText = new Text("Welcome To De Bank!");
		welcomeText.setFont(Font.font("Comic sans MS", FontWeight.NORMAL, 20));
		welcomeGrid.add(welcomeText, 0, 0, 2, 1);

		Button signInButton = new Button("Sign In");

		Button createAccountButton = new Button("Create Account");

		HBox horizontalButtonStack = new HBox(10);
		horizontalButtonStack.setAlignment(Pos.BASELINE_CENTER);
		horizontalButtonStack.getChildren().addAll(signInButton, createAccountButton);
		welcomeGrid.add(horizontalButtonStack, 1, 4);

		final Text actionTarget = new Text();
		welcomeGrid.add(actionTarget, 1, 6);

		final Text actionTarget1 = new Text();

		signInButton.setOnAction(e -> primaryStage.setScene(loginPage));

		createAccountButton.setOnAction(e -> primaryStage.setScene(createAccountPage));

		login = new Scene(welcomeGrid, 800, 450);

		primaryStage.setScene(login);

		GridPane signInGrid = new GridPane();
		signInGrid.setAlignment(Pos.CENTER);
		signInGrid.setHgap(100);
		signInGrid.setVgap(10);
//		signInGrid.setPadding(new Insets(25, 25, 25, 25));

		Text signInTitle = new Text("Log in:");
		signInTitle.setFont(Font.font("ARIAL", FontWeight.NORMAL, 20));
		signInGrid.add(signInTitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		signInGrid.add(userName, 0, 1);

		TextField userNameField = new TextField();
		signInGrid.add(userNameField, 1, 1);

		Label password = new Label("Password:");
		signInGrid.add(password, 0, 2);

		PasswordField passwordBox = new PasswordField();
		signInGrid.add(passwordBox, 1, 2);

		Button backButton = new Button("Back");
		signInGrid.add(backButton, 0, 4);

		Button continueButton = new Button("Continue");
		HBox horizontalButtonStack2 = new HBox(10);
		horizontalButtonStack2.setAlignment(Pos.BOTTOM_RIGHT);
		horizontalButtonStack2.getChildren().add(continueButton);
		signInGrid.add(horizontalButtonStack2, 1, 4);

		signInGrid.add(actionTarget, 1, 6);

		continueButton.setOnAction(e -> {
			actionTarget.setFill(Color.RED);
			actionTarget.setText("");

			if (passwordBox.getText().equals("")) {
				actionTarget.setText("No password entered");
			}
			if (userNameField.getText().equals("")) {
				actionTarget.setText("No username entered");
			} else if (userNameField.getText().equals("") || passwordBox.getText().equals("")) {
				actionTarget.setText("No information entered");
			} else if (Account.signIn(userNameField.getText(), passwordBox.getText())) {
				primaryStage.setScene(accountView);
			} else actionTarget.setText("Incorrect username or password");

		});

		backButton.setOnAction(e -> {
			primaryStage.setScene(login);
			actionTarget.setText("");
			userNameField.setText("");
			passwordBox.setText("");
		});

		loginPage = new Scene(signInGrid, 800, 450);

		GridPane createAccountGrid = new GridPane();
		createAccountGrid.setAlignment(Pos.CENTER);
		createAccountGrid.setHgap(100);
		createAccountGrid.setVgap(10);

		Label userName2 = new Label("User Name:");
		createAccountGrid.add(userName2, 0, 1);

		TextField userNameField2 = new TextField();
		createAccountGrid.add(userNameField2, 1, 1);

		Label password2 = new Label("Password:");
		createAccountGrid.add(password2, 0, 2);

		PasswordField passwordBox2 = new PasswordField();
		createAccountGrid.add(passwordBox2, 1, 2);

		PasswordField confirmPasswordBox = new PasswordField();
		createAccountGrid.add(confirmPasswordBox, 1, 3);

		Label confirmPassword = new Label("Confirm Password:");
		createAccountGrid.add(confirmPassword, 0, 3);

		Button backButton2 = new Button("Back");
		createAccountGrid.add(backButton2, 0, 4);
		backButton2.setOnAction(e -> {
			primaryStage.setScene(login);
			actionTarget1.setText("");
			userNameField2.setText("");
			passwordBox2.setText("");
			confirmPasswordBox.setText("");
		});

		Button continueButton2 = new Button("Continue");
		HBox horizontalButtonStack3 = new HBox(10);
		horizontalButtonStack3.setAlignment(Pos.BOTTOM_RIGHT);
		horizontalButtonStack3.getChildren().add(continueButton2);
		createAccountGrid.add(horizontalButtonStack3, 1, 4);

		createAccountGrid.add(actionTarget1, 1, 6);

		Text createAccountTitle = new Text("Create Account:");
		createAccountTitle.setFont(Font.font("ARIAL", FontWeight.NORMAL, 20));
		createAccountGrid.add(createAccountTitle, 0, 0, 2, 1);

		continueButton2.setOnAction(e -> {
			actionTarget1.setFill(Color.RED);
			actionTarget1.setText("");

			if (passwordBox2.getText().equals("")) {
				actionTarget1.setText("No password entered");
			}
			if (userNameField2.getText().equals("")) {
				actionTarget1.setText("No username entered");
			} else if (!passwordBox2.getText().equals(confirmPasswordBox.getText())) {
				actionTarget1.setText("Passwords do not match!");
			} else {
				if (Account.createAccount(userNameField2.getText(), passwordBox2.getText())) {
					primaryStage.setScene(accountView);
				} else actionTarget1.setText("That account already exists!");

			}
		});

		createAccountPage = new Scene(createAccountGrid, 800, 450);
	}

	public void logOutButtonPressed () {
		System.out.println("Logout action");
		primaryStage.setScene(login);
		System.out.println("hello");
	}

}

