package com.company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameField  extends JPanel  implements ActionListener {
    public GameField(){
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }
    public static void Game(){
        Play.Play();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graph.Draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }


    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
        }
    }
}
