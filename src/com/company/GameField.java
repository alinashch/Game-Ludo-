package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameField  extends JPanel implements ActionListener {
    public static final int DOT_SIZE = 30;

    public static int RedX=300;
    public static int RedY=750;
    public static int BlueX=0;
    public static int BlueY=300;
    public static int GreenX=450;
    public static int GreenY=30;
    public static int YellowX=750;
    public static int YellowY=420;
    public static Status status=Status.InGAME;

    public static int K=1;

    public GameField(){
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }
    public static void Game(){
        ArrayList<Block> block=Block.CreateBlock();
        Dot Red=new Dot(Color.RED,RedX,RedY,Move.CreateStackRed());
        Dot Blue=new Dot(Color.BLUE,BlueX,BlueY,Move.CreateStackBlue());
        Dot Green=new Dot(Color.GREEN,GreenX, GreenY,Move.CreateStackGreen());
        Move.Move(Red,20,block);
        Move.Move(Blue,8,block);
        Move.Move(Green,40,block);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graph.Draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(status==Status.InGAME){

        }
        repaint();
    }
    private static int Random(){
        K= (int)( Math.random()*((6-1)+1)+1);
        return K;
    }
    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key==KeyEvent.VK_LEFT){
               K=GameField.Random();
               System.out.println(K);
            }
        }
    }
}
