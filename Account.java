package FST;

//
// Project name: FSTGrade11
// Program name: Account.java
// Purpose: 
// Created by David Shemesh on 2019-04-10.
// Copyright © 2018 David Shemesh. All rights reserved.
//


import java.io.*;
import java.util.ArrayList;

public class Account implements Serializable {
    String user;
    String password;

    public Account(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public static boolean signIn(String username, String password){

        ArrayList<Account> accounts;

        try {
            var f = new FileInputStream(new File("usernameAndPassword.txt"));
            var o = new ObjectInputStream(f);

            accounts = (ArrayList<Account>) o.readObject();

            f.close();
            o.close();

        } catch (Exception e) {
            throw new IllegalArgumentException("Could not read from file");
        }

        for (var account: accounts) {
            if (username.equalsIgnoreCase(account.user)) {
                if (password.equals(account.password)) {
                    return true;
                }
                return false;
            }
        }

        return false;

    }

    public static boolean createAccount(String username, String password) {

        var account = new Account(username, password);

        ArrayList<Account> accounts;
        try {
            var f = new FileInputStream(new File("usernameAndPassword.txt"));
            var o = new ObjectInputStream(f);

            accounts = (ArrayList<Account>) o.readObject();

            f.close();
            o.close();

        } catch (Exception e) {
            throw new IllegalArgumentException("Could not read from file");
        }

        for (var a: accounts) {
            if (username.equalsIgnoreCase(a.user)) {
                return false;
            }
        }

        accounts.add(account);

        try {
            var f = new FileOutputStream(new File("usernameAndPassword.txt"));
            var o = new ObjectOutputStream(f);

            o.writeObject(accounts);

            f.close();
            o.close();

        } catch (Exception e) {
            throw new IllegalArgumentException("Could not write to file");
        }

        return true;

    }

    public static void main(String[] args) {
        System.out.println(signIn("David", "Shemesh"));
    }
}
