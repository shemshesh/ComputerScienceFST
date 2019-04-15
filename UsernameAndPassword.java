package FST;

//
// Project name: FSTGrade11
// Program name: UsernameAndPassword.java
// Purpose: 
// Created by David Shemesh on 2019-04-10.
// Copyright © 2018 David Shemesh. All rights reserved.
//


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class UsernameAndPassword {
    String user;
    String password;

    public UsernameAndPassword(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public static void signIn(String username, String password){
        boolean login;

        UsernameAndPassword user1 = new UsernameAndPassword("David", "shemesh");


        FileReader fr = null;
        try {
            fr = new FileReader("usernameAndPassword.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);


        for (int i = 1; i <= 5; i++) {
            try {
                String name = br.readLine();
                if (name.equalsIgnoreCase(user1.user)) {
                    String wordOfPass = br.readLine();
                    if (wordOfPass.equalsIgnoreCase(user1.password)) {
                        login = true;
                    } else {
                        login = false;
                    }
                } else {
                    br.readLine();
                }
            }catch (Exception e){

            }
        }

    }

    public static void create() throws Exception{
        FileWriter fw = new FileWriter("usernameAndPassword.txt");
        PrintWriter pw = new PrintWriter(fw);


    }
}
