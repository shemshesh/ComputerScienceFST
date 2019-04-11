package FST;
// Program name: BankBalance.java
// Purpose:
// Created by Evan Rimer on Saturday April 11 2019.
// Copyright © 2019 Evan Rimer. All rights reserved.
public class BankBalance {

    private double acountBalance;

    public BankBalance(double acountBalance) {
        this.acountBalance = acountBalance;
    }

    @Override
    public String toString() {
        return "" + acountBalance;
    }
}
