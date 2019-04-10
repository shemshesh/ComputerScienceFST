package FST;

//
// Project name: FSTGrade11
// Program name: UsernameAndPassword.java
// Purpose: 
// Created by David Shemesh on 2019-04-10.
// Copyright © 2018 David Shemesh. All rights reserved.
//


import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsernameAndPassword {
    String user;
    String password;

    public UsernameAndPassword(String user, String password) {
        this.user = user;
        this.password = password;
    }



    public static void main(String[] args) {
        Path user = Paths.get("/Users/davidshemesh/IdeaProjects/firstProject", "username");
        Path password = Paths.get("/Users/davidshemesh/IdeaProjects/firstProject", "password");
        RandomAccessFile rafU = new RandomAccessFile("username", "rw");
        RandomAccessFile rafP = new RandomAccessFile("password", "rw");
    }
}
