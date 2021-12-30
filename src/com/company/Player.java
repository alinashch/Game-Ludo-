package com.company;

public class Player {
    public final int number;
    public final com.company.Strategy Strategy;
    public Player( Strategy strategy, int number) {
        this.Strategy=strategy;
        this.number=number;
    }
}
