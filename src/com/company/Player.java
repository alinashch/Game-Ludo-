package com.company;

public class Player {
    public final int number;
    public final com.company.Strategy Strategy;
    public final int st;
    public Player( Strategy strategy, int number, int st) {
        this.Strategy=strategy;
        this.number=number;
        this.st=st;
    }
}
