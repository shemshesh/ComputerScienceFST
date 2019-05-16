package FST;

//
// Project name: FSTGrade11
// Program name: Account.java
// Purpose: 
// Created by David Shemesh on 2019-04-10.
// Copyright © 2018 David Shemesh. All rights reserved.
//Evan Rimer

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Account implements Serializable {
	private static Account account;
	String user;
	String password;

	public Account (String user, String password) {
		this.user = user;
		this.password = password;
	}

	public static boolean signIn (String username, String password) {

		ArrayList<Account> accounts;

		account = new Account(username, password);

		try {
			var f = new FileInputStream(new File("src/FST/usernameAndPassword.txt"));
			var o = new ObjectInputStream(f);

			accounts = (ArrayList<Account>) o.readObject();

			f.close();
			o.close();

		} catch (Exception e) {
			throw new IllegalArgumentException("Could not read from file");
		}

		for (var account : accounts) {
			if (username.equalsIgnoreCase(account.user)) {
				if (password.equals(account.password)) {
					// Correct username and password
					return true;
				}
				// Incorrect password for username
				return false;
			}
		}

		// Username does not exist
		return false;

	}

	public static boolean createAccount (String username, String password) {

		account = new Account(username, password);

		ArrayList<Account> accounts;
		try {
			var f = new FileInputStream(new File("src/FST/usernameAndPassword.txt"));
			var o = new ObjectInputStream(f);

			accounts = (ArrayList<Account>) o.readObject();

			f.close();
			o.close();

		} catch (Exception e) {
			throw new IllegalArgumentException("Could not read from file");
		}

		System.out.println("account.user: " + account.user);
		for (var a : accounts) {
			System.out.println("a.user: " + a.user);
			if (account.user.equalsIgnoreCase(a.user)) {
				System.out.println("Already exists");
				return false;
			}
		}
		accounts.add(account);

		try {
			var f = new FileOutputStream(new File("src/FST/usernameAndPassword.txt"));
			var o = new ObjectOutputStream(f);

			o.writeObject(accounts);
			f.close();
			o.close();

		} catch (Exception e) {
			throw new IllegalArgumentException("Could not write to file");
		}

		return true;

	}

	public String returnUsername () {
		System.out.println(user);
		return user;
	}

	public static void main (String[] args) {
//        ArrayList<Account>accounts = new ArrayList<>();
//        try {
//            var f = new FileOutputStream(new File("src/FST/usernameAndPassword.txt"));
//            var o = new ObjectOutputStream(f);
//
//            o.writeObject(accounts);
//            f.close();
//            o.close();
//
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Could not write to file");
//        }
	}
}
