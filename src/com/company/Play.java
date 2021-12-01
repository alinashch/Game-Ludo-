package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import static com.company.Coordinates.*;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Play extends RulesForMove  {
    public static boolean s=true;
    public static  void EnterS(){
        s=true;
    }
    public  void Play() {
        ColorDot ColorDot=new ColorDot(0,0,null,Color.YELLOW,null,null);
        ColorDot.CreateDotsCoordinates();
        Base base=new Base(0,0,0,0,0,0);

        List<Dot> RedDots = base.CreateListDot(Color.RED);
        List<Dot> BlueDots = base.CreateListDot(Color.BLUE);
        List<Dot> GreenDots = base.CreateListDot(Color.GREEN);
        List<Dot> YellowDots = base.CreateListDot(Color.YELLOW);
        List<Dot> DotsInGame = new ArrayList<>();


        DotsInGame.add(RedDots.get(0));
        DotsInGame.add(BlueDots.get(0));
        DotsInGame.add(GreenDots.get(0));
        DotsInGame.add(YellowDots.get(0));

        String str = "r";
        Scanner scanner = new Scanner(System.in);

        List<Cell> Cell1 = base.BasePath(Red.DotBase, Red.DotBase.FirstCell);
        base.EnterDirections(Cell1, Directions.Up, Directions.Left, Red.DotBase.BaseX, Red.DotBase.BaseY);
        List<Cell> Cell = new ArrayList<>(Cell1);

        List<Cell> Cell2=base.BasePath(Green.DotBase, Green.DotBase.FirstCell);
        base.EnterDirections(Cell2, Directions.Left, Directions.Down, Green.DotBase.BaseX, Green.DotBase.BaseY);
        Cell.addAll(Cell2);

        List<Cell> Cell3=base.BasePath(Yellow.DotBase, Yellow.DotBase.FirstCell);
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
        System.out.println("1-ходит игрок, 2-ходит компьютер. Порядок : Красный, Синий, Желтый, Зеленый ");
        int a=scanner.nextInt();
        int b=scanner.nextInt();
        int c=scanner.nextInt();
        int d=scanner.nextInt();
        RulesForMove rulesForMove=new RulesForMove();
        while (status == Status.InGAME ) {
                System.out.println("Для начала хода введите r ");
                 String x = scanner.nextLine();
                 if (x.equals(str)) {
                rulesForMove.MoveAllDots(a, b, c, d, YellowDots, BlueDots, RedDots, GreenDots, DotsInGame, Cell);
            }
        }
    }


}


