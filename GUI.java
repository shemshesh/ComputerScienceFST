package FST;

// Project name: untitled
// Program name: GUI.java
// Purpose: 
// Created by Natan Parker on Wednesday April 03 2019.
// Copyright © 2019 Natan Parker. All rights reserved.

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI extends JFrame implements ActionListener {

	private final JButton signInButton;
	private final JButton createAccountButton;
	private final JButton backButton;
	private final JLabel welcomeText;
	private final JLabel copyrights;
	private JTextField insertUsername;
	private JPasswordField insertPassword;
	private JLabel usernameLabel;
	private JLabel passwordLabel;

	private GUI () {
		setSize(800, 450);

		signInButton = new JButton("Sign in");
		createAccountButton = new JButton("Create account");
		backButton = new JButton("Back");

		signInButton.setBounds(300, 120, 120, 30);
		signInButton.addActionListener(this);

		createAccountButton.setBounds(300, 160, 120, 30);
		createAccountButton.addActionListener(this);

		backButton.setBounds(10, 10, 100, 20);
		backButton.addActionListener(this);

		welcomeText = new JLabel("Welcome to de bank", SwingConstants.TRAILING);
		welcomeText.setBounds(300, 30, 150, 30);

		copyrights = new JLabel("Copyright 2019 de bank corporate");
		copyrights.setBounds(550, 350, 200, 30);

		add(welcomeText);
		add(copyrights);
		add(signInButton);
		add(createAccountButton);
		add(backButton);
		setLayout(null);
		setVisible(true);
	}

	public void actionPerformed (ActionEvent e) {
		welcomeText.setVisible(false);
		signInButton.setVisible(false);
		createAccountButton.setVisible(false);
		if (e.getSource() == signInButton) {
			System.out.println("Sign in button selected");

			insertUsername = new JTextField();
			insertUsername.setBounds(300, 120, 250, 30);
			usernameLabel = new JLabel("Enter Username:");
			usernameLabel.setBounds(300, 90, 250, 30);
			passwordLabel = new JLabel("Enter Password:");
			passwordLabel.setBounds(300, 150, 250, 30);
			insertPassword = new JPasswordField();
			insertPassword.setBounds(300, 180, 250, 30);

			add(insertUsername);
			add(usernameLabel);
			add(passwordLabel);
			add(insertPassword);
			insertUsername.setVisible(true);
			usernameLabel.setVisible(true);
			passwordLabel.setVisible(true);
			insertPassword.setVisible(true);

			insertUsername.addActionListener(enterKeyPressed);
			insertPassword.addActionListener(enterKeyPressed);

		}
		if (e.getSource() == createAccountButton) {
			System.out.println("Create account button selected");
			insertUsername = new JTextField();
			insertUsername.setBounds(300, 120, 250, 30);
			usernameLabel = new JLabel("Enter Username:");
			usernameLabel.setBounds(300, 90, 250, 30);
			passwordLabel = new JLabel("Enter Password:");
			passwordLabel.setBounds(300, 150, 250, 30);
			insertPassword = new JPasswordField();
			insertPassword.setBounds(300, 180, 250, 30);

			insertUsername.addActionListener(enterKeyPressed);
			insertPassword.addActionListener(enterKeyPressed);

			add(insertUsername);
			add(usernameLabel);
			add(passwordLabel);
			add(insertPassword);
			insertUsername.setVisible(true);
			usernameLabel.setVisible(true);
			passwordLabel.setVisible(true);
			insertPassword.setVisible(true);
		}
		if (e.getSource() == backButton) {
			goBack();
		}
	}

	private final Action enterKeyPressed = new AbstractAction() {
		@Override
		public void actionPerformed (ActionEvent enter) {
			if (enter.getSource() == insertUsername) {
				System.out.println("Username received: " + insertUsername.getText());
			}
			if (enter.getSource() == insertPassword) {
				System.out.println("Password received: " + new String(insertPassword.getPassword()));
			}
		}
	};

	private void goBack () {
		welcomeText.setVisible(true);
		signInButton.setVisible(true);
		createAccountButton.setVisible(true);
		insertUsername.setVisible(false);
		usernameLabel.setVisible(false);
		passwordLabel.setVisible(false);
		insertPassword.setVisible(false);
	}

	public static void main (String[] args) {
		new GUI();
	}

}