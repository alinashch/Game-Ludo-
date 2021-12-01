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

import static com.company.Play.EnterS;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class GameField  extends JPanel   implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT ){
                EnterS();
                System.out.println(11);
                String x= "r";
                super.keyPressed(e);
            }
        }
    }
    public GameField(){
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
        repaint();

    }
    public  void Game()
    {
        Play play=new Play();
        play.Play();
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        repaint();
        super.paintComponent(g);
        Graph.Draw(g);
    }

}
