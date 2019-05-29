package FST;

//
// Project name: FSTGrade11
// Program name: Account.java
// Purpose: 
// Created by not David Shemesh on 2019-04-10.
// Copyright © 2018 David not Shemesh. All rights reserved.
//Evan Rimer
//Natan Parker
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

	public static boolean signIn (String username, String password) {

		ArrayList<Account> accounts;

		try {
			var f = new FileInputStream(new File("src/FST/files/usernameAndPassword.txt"));
			var o = new ObjectInputStream(f);
			accounts = (ArrayList<Account>) o.readObject();

			f.close();
			o.close();

		} catch (Exception e) {
			throw new IllegalArgumentException("Could not read from file");
		}

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

	public static boolean createAccount (String username, String password) {

	    account = new Account(username, password);

		ArrayList<Account> accounts;
		try {
			var f = new FileInputStream(new File("src/FST/files/usernameAndPassword.txt"));
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
			var f = new FileOutputStream(new File("src/FST/files/usernameAndPassword.txt"));
			var o = new ObjectOutputStream(f);

			o.writeObject(accounts);
			f.close();
			o.close();

		} catch (Exception e) {
			throw new IllegalArgumentException("Could not write to file");
		}

		return true;

	}

	public static String returnName(){
	    return account.user;
    }


	public static void main (String[] args) {
		//for natan
        ArrayList<Account>accounts = new ArrayList<>();
        try {
            var f = new FileOutputStream(new File("src/FST/files/usernameAndPassword.txt"));
            var o = new ObjectOutputStream(f);

            o.writeObject(accounts);
            f.close();
            o.close();

        } catch (Exception e) {
            throw new IllegalArgumentException("Could not write to file");
        }
	}
}
