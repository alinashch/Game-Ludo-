package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame  {

    public Main(){
        add(new GameField());
        setMinimumSize(new Dimension(800,800));
        setLocation(0,0);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        Main main=new Main();
        GameField.Game();
    }

}
