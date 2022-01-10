package com.company;

import java.awt.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.company.Coordinates.*;

public class GameSession implements Runnable {
    private final PlayApp game;

    public GameSession(Socket socket) {
        game = new PlayApp();
        Player p1=new Player(new SocketStrategy(socket),1);
        Player p2=new Player(new RulesForMove(),2);
        Player p3=new Player(new RulesForMove(),2);
        Player p4=new Player(new RulesForMove(),2);
        game.Play(p1, p2,p3,p4,socket);
    }

    public void run() {
        System.out.println(6784);
        ColorDot ColorDot=new ColorDot(0,0,null,Color.YELLOW,null,null);
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
        while (game.GameOver()) {
            game.MoveColorDotComputer(RedDots, DotsInGame,Cell);
            game.MoveColorDotComputer(YellowDots, DotsInGame,Cell);
            game.MoveColorDotComputer(BlueDots, DotsInGame,Cell);
            game.MoveColorDotComputer(GreenDots, DotsInGame,Cell);

        }
        System.out.println("Game Over!");
    }
}
