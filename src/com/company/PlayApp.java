package com.company;

import java.awt.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class PlayApp extends RulesForMove  {
    public static boolean s=true;
    public static  void EnterS(){
        s=true;
    }
    public  void Play(Player ap, Player bp, Player cp, Player dp, Socket socket) {
        ColorDot ColorDot=new ColorDot(0,0,null, Color.YELLOW,null,null);
        ColorDot.CreateDotsCoordinates();
        Base base=new Base(0,0,0,0,0,0);

        java.util.List<Dot> RedDots = base.CreateListDot(Color.RED);
        java.util.List<Dot> BlueDots = base.CreateListDot(Color.BLUE);
        java.util.List<Dot> GreenDots = base.CreateListDot(Color.GREEN);
        java.util.List<Dot> YellowDots = base.CreateListDot(Color.YELLOW);
        java.util.List<Dot> DotsInGame = new ArrayList<>();


        DotsInGame.add(RedDots.get(0));
        DotsInGame.add(BlueDots.get(0));
        DotsInGame.add(GreenDots.get(0));
        DotsInGame.add(YellowDots.get(0));


        java.util.List<Cell> Cell1 = base.BasePath(Red.DotBase, Red.DotBase.FirstCell);
        base.EnterDirections(Cell1, Directions.Up, Directions.Left, Red.DotBase.BaseX, Red.DotBase.BaseY);
        java.util.List<Cell> Cell = new ArrayList<>(Cell1);

        java.util.List<Cell> Cell2=base.BasePath(Green.DotBase, Green.DotBase.FirstCell);
        base.EnterDirections(Cell2, Directions.Left, Directions.Down, Green.DotBase.BaseX, Green.DotBase.BaseY);
        Cell.addAll(Cell2);

        java.util.List<Cell> Cell3=base.BasePath(Yellow.DotBase, Yellow.DotBase.FirstCell);
        base.EnterDirections(Cell3, Directions.Down, Directions.Right, Yellow.DotBase.BaseX, Yellow.DotBase.BaseY);
        Cell.addAll(Cell3);


        List<Cell> Cell4=base.BasePath(Blue.DotBase, Blue.DotBase.FirstCell);
        base.EnterDirections(Cell4, Directions.Right, Directions.Up, Blue.DotBase.BaseX, Blue.DotBase.BaseY);
        Cell.addAll(Cell4);

        base.EnterColor(28,Cell);
        base.EnterColor(14,Cell);
        base.EnterColor(42,Cell);
        base.EnterColor(56,Cell);
        Cell cell=new Cell(null,0,null,null,0,0,null,null);
        cell.EnterNextCell(Cell);
        Base. EstablishDotsOnCells(DotsInGame, Cell);
        int a=ap.number;
        int b=bp.number;
        int c=cp.number;
        int d=dp.number;
        SocketStrategy sc=new SocketStrategy(socket);
        while (status == Status.InGAME ) {
            if (a == 1) {
                sc.MoveColorDotComputer(RedDots, DotsInGame, Cell);
            } else if (a == 2) {
                MoveOneColorDotComputer(RedDots, DotsInGame, Cell);
            } else {
                System.out.println("Неправильно введено число");
                return;
            }

            if (b == 1) {
                MoveOneColorDotPlayer(BlueDots, DotsInGame, Cell);
            } else if (b == 2) {
                MoveOneColorDotComputer(BlueDots, DotsInGame, Cell);
            } else {
                System.out.println("Неправильно введено число");
                return;
            }

            if (c == 1) {
                MoveOneColorDotPlayer(YellowDots, DotsInGame, Cell);
            } else if (c == 2) {
                MoveOneColorDotComputer(YellowDots, DotsInGame, Cell);
            } else {
                System.out.println("Неправильно введено число");
                return;
            }

            if (d == 1) {
                MoveOneColorDotPlayer(GreenDots, DotsInGame, Cell);
            } else if (d == 2) {
                MoveOneColorDotComputer(GreenDots, DotsInGame, Cell);
            } else {
                System.out.println("Неправильно введено число");
                return;
            }
        }
    }
    public boolean GameOver(){
        if(status != Status.InGAME){
            return false;
        }
        return true;
    }
}

