package FST;

//
// Project name: FSTGrade11
// Program name: UsernameAndPassword.java
// Purpose: 
// Created by David Shemesh on 2019-04-10.
// Copyright © 2018 David Shemesh. All rights reserved.
//


public class UsernameAndPassword {
    String user;
    String password;

    public UsernameAndPassword(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
