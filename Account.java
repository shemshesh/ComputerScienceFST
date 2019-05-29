package FST;

//
// Project name: FSTGrade11
// Program name: Account.java
// Purpose: 
// Created by David Shemesh on 2019-04-10.
// Copyright © 2018 David Shemesh. All rights reserved.
//Evan Rimer
//Natan Parker
//David Shemesh was dead weight. He sucks at coding.
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Account implements Serializable {
	public String user;
	String password;
	public static Account account;

	public Account (String user, String password) {
		this.user = user;
		this.password = password;
	}

	//Called when login with existing account to check if proper username and password are given
	//username and password are given by the user, and may be incorrect
	public static boolean signIn (String username, String password) {

		ArrayList<Account> accounts;

		try {
			//adds accounts on file to ArrayList accounts
			var f = new FileInputStream(new File("src/FST/usernameAndPassword.txt"));
			var o = new ObjectInputStream(f);
			accounts = (ArrayList<Account>) o.readObject();

			f.close();
			o.close();

		} catch (Exception e) {
			throw new IllegalArgumentException("Could not read from file");
		}

		//Goes though every account in accounts and checks if any username stored matches the one the user gave
		//If yes, checks if the passwords match
		//If yes, returns true allowing the login and creates global account called account which is used to check current username
		for (var curAccount : accounts) {
			if (username.equalsIgnoreCase(curAccount.user)) {
				if (password.equals(curAccount.password)) {
					// Correct username and password
                    account = new Account(username, password);
					return true;
				}
				// Incorrect password for username
				return false;
			}
		}

		// Username does not exist
		return false;

	}

	//Called when creating new account to be saved
	public static boolean createAccount (String username, String password) {
		//global account to access current username
	    account = new Account(username, password);

		ArrayList<Account> accounts;
		try {
			//adds all current accounts to ArrayList called accounts
			var f = new FileInputStream(new File("src/FST/usernameAndPassword.txt"));
			var o = new ObjectInputStream(f);

			accounts = (ArrayList<Account>) o.readObject();


			f.close();
			o.close();

		} catch (Exception e) {
			throw new IllegalArgumentException("Could not read from file");
		}

		//Goes through every account stored and checks if any username matches username given by user
		//If yes, username is taken and account can't be created so return false
		for (var a : accounts) {
			if (account.user.equalsIgnoreCase(a.user)) {
				System.out.println("Already exists");
				return false;
			}
		}
		//If username doesn't exist, add new account to ArrayList accounts, which now contains all existing accounts
		accounts.add(account);

		try {
			//writes all accounts to file
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

	//return current username for naming/reading files
	public static String returnName(){
	    return account.user;
    }


	public static void main (String[] args) {
        ArrayList<Account>accounts = new ArrayList<>();
        try {
            var f = new FileOutputStream(new File("src/FST/usernameAndPassword.txt"));
            var o = new ObjectOutputStream(f);

            o.writeObject(accounts);
            f.close();
            o.close();

        } catch (Exception e) {
            throw new IllegalArgumentException("Could not write to file");
        }
	}
}
