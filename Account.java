package FST;

//
// Project name: FSTGrade11
// Program name: Account.java
// Purpose: 
// Created by David Shemesh on 2019-04-10.
// Copyright © 2018 David Shemesh. All rights reserved.
//


import java.io.*;

public class Account {
    String user;
    String password;

    public Account(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public static boolean signIn(String username, String password){
        boolean login = false;

        Account user1 = new Account(username, password);


        FileReader fr = null;
        try {
            fr = new FileReader("usernameAndPassword");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);


        while(true){
            try {
                String name = br.readLine();
                if (name.equalsIgnoreCase(username)) {
                    String wordOfPass = br.readLine();
                    if (wordOfPass.equalsIgnoreCase(password)) {
                        login = true;
                        break;
                    } else {
                        login = false;
                    }
                } else {
                    br.readLine();
                }
            }catch (Exception e){
                break;
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return login;
    }

    public static boolean createAccount(String username, String password){
        boolean properValues = false;

        FileWriter fw = null;
        try {
            fw = new FileWriter("usernameAndPassword");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);


        FileReader fr = null;
        try {
            fr = new FileReader("usernameAndPassword");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);

        while(true){
            try{
                String existing = br.readLine();
                if(existing.equals(username)){
                    properValues = false;
                    break;
                }else{
                    properValues = true;
                }
            }catch (Exception e){
                break;
            }
        }

        pw.write(username);
        pw.write(password);
        pw.close();

        return properValues;
    }

    public static void main(String[] args) {
        System.out.println(signIn("David", "Shemesh"));
    }
}
