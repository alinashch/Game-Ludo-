package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.util.*;
import java.util.List;
import com.company.SocketStrategy;

import static com.company.Coordinates.*;


public class Play  {
    public static boolean s=true;
    public static  void EnterS(){
        s=true;
    }

    private  Base base;
    private List<Dot> RedDots;
    private List<Dot> BlueDots ;
    private List<Dot> GreenDots ;
    private List<Dot> YellowDots;
    private List<Dot> DotsInGame;
   private List<Cell> Cell;
    Cell cell;
    Player p1;
    Player p2;
    Player p3;
    Player p4;

    public boolean GameOver(){
        if(status != Status.InGAME){
            return false;
        }
        return true;
    }
    public  void PlayApp(Player ap, Player bp, Player cp, Player dp) {
        ColorDot ColorDot=new ColorDot(0,0,null, Color.YELLOW,null,null);
        ColorDot.CreateDotsCoordinates();
        base=new Base(0,0,0,0,0,0);
        p1=ap;
        p2=bp;
        p3=cp;
        p4=dp;

         RedDots = base.CreateListDot(Color.RED);
         BlueDots = base.CreateListDot(Color.BLUE);
         GreenDots = base.CreateListDot(Color.GREEN);
         YellowDots = base.CreateListDot(Color.YELLOW);
         DotsInGame = new ArrayList<>();


        DotsInGame.add(RedDots.get(0));
        DotsInGame.add(BlueDots.get(0));
        DotsInGame.add(GreenDots.get(0));
        DotsInGame.add(YellowDots.get(0));


        java.util.List<Cell> Cell1 = base.BasePath(Red.DotBase, Red.DotBase.FirstCell);
        base.EnterDirections(Cell1, Directions.Up, Directions.Left, Red.DotBase.BaseX, Red.DotBase.BaseY);
        Cell = new ArrayList<>(Cell1);

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
        cell=new Cell(null,0,null,null,0,0,null,null);
        cell.EnterNextCell(Cell);
        Base. EstablishDotsOnCells(DotsInGame, Cell);
    }
    public void MakeStep(){
        int a=p1.number;
        int b=p2.number;
        int c=p3.number;
        int d=p4.number;
        RulesForMove rulesForMove=new RulesForMove();

        rulesForMove.First(a,RedDots,DotsInGame,Cell);


        rulesForMove.First(b,BlueDots,DotsInGame,Cell);
        rulesForMove.First(c,YellowDots,DotsInGame, Cell);
        rulesForMove.First(d,GreenDots,DotsInGame,Cell);
    }


    public void MakeStep(Socket socket){
        int a=p1.number;
        int b=p2.number;
        int c=p3.number;
        int d=p4.number;
        RulesForMove rulesForMove=new RulesForMove();
        if (a == 1) {
                  SocketStrategy sc = new SocketStrategy(socket);
                  int k=(int) (Math.random() * ((6 - 1) + 1) + 1);
                  System.out.println("Выпало "+ k);
                  if(k==5){
                      int wr=sc.Enter();
                      sc.ChooseFive(RedDots, DotsInGame, Cell,wr);
                  }
                  if(k==6){
                      int wr=sc.Enter();
                      sc.ChooseSix(RedDots, DotsInGame, Cell,wr);
                  }else {
                      sc.MoveColorDotComputer(RedDots, DotsInGame, Cell, k);
                  }
        } else if (a == 2) {
            rulesForMove.  MoveOneColorDotComputer(RedDots, DotsInGame, Cell);
        } else {
            System.out.println("Неправильно введено число");
            return;
        }
        rulesForMove.First(b,BlueDots,DotsInGame,Cell);
        rulesForMove.First(c,YellowDots,DotsInGame, Cell);
        rulesForMove.First(d,GreenDots,DotsInGame,Cell);
    }
}


