package com.company;

public enum Command {

    ENTER("ENTER"),
    ENTERRESP("ENTERRESP"),
    CHOOSE("CHOOSE"),
    CHOOSERESP("CHOSERESP"),
    BET("BET"),
    RESP("RESP"),
    END("END");

    public static final String SEPARATOR = ":";

    private final String commandString;

    Command(String commandString) {
        this.commandString = commandString;
    }

    public String getCommandString() {
        return commandString;
    }
}