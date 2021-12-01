package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame  {

    public Main(){
        add(new GameField());
        setMinimumSize(new Dimension(1000,850));
        setLocation(0,0);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        repaint();

    }

    public static void main(String[] args) throws   Exception {
        Main main=new Main();
        GameField gameField=new GameField();
        gameField.Game();
        TimeUnit.SECONDS.sleep(3);
        System.exit(0);
    }

}
