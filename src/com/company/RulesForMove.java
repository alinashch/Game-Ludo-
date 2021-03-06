package com.company;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import com.company.SocketStrategy;

public class RulesForMove extends Coordinates implements Strategy{
    public void First(int b,List<Dot> BlueDots,List<Dot> DotsInGame, List<Cell> Cell ){
        if (b == 1) {
            int k=(int) (Math.random() * ((6 - 1) + 1) + 1);
            System.out.println("Выпало " + k);
            int wr=Enter();

            if(k==5){
                ChooseFive(BlueDots, DotsInGame, Cell,wr);
            }
            if(k==6){
                ChooseSix(BlueDots, DotsInGame, Cell,wr);
            }else {
                MoveColorDotComputer(BlueDots, DotsInGame, Cell, k,wr);
            }
        } else if (b == 2) {
           MoveOneColorDotComputer(BlueDots, DotsInGame, Cell);
        } else {
            System.out.println("Неправильно введено число");
            return;
        }
    }

    public void MoveColorDotComputer(List<Dot> RedDots, List<Dot> DotsInGame, List<Cell> Cells, int k, int wr) {
        Cell cellsMethod=new Cell(null,0,null,null,0,0,null, null);
        cellsMethod.Move2(RedDots.get(wr - 1),  Cells,k,DotsInGame, RedDots);
        System.out.println("Передвинули на "+k+"цвет ");
        PrintColor(RedDots);

    }

    @Override
    public void endGame() {
        if(status != Status.InGAME){
            return;
        }
    }
    @Override
     public  int  Enter(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Номер фишки для передвижения");
         int wr = scanner.nextInt();
         return wr;
     }
     @Override
    public void ChooseFive(List<Dot> RedDots, List<Dot> DotsInGame, List<Cell> Cells, int wr){
        Scanner scanner=new Scanner(System.in);
        System.out.println("1-Передвинуть фишку на 5 , 2-Создать новую фишку");
        int q = scanner.nextInt();
        MoveOnFive(RedDots,  DotsInGame, q, wr,Cells);
    }
    @Override
    public void ChooseSix(List<Dot> RedDots, List<Dot> DotsInGame, List<Cell> Cells, int wr){
        Scanner scanner=new Scanner(System.in);
        System.out.println("1-Передвинуть фишку на 7 , 2-Создать новую фишку");
        int q = scanner.nextInt();
        MoveOnSix(RedDots,  DotsInGame, q, wr,Cells);
    }

     public void MoveOn(List<Dot> RedDots,  List<Dot> DotsInGame, List<Cell> Cells){
       int wr= Enter();
       MoveOneColorDotPlayer(RedDots,DotsInGame,Cells,wr);
     }

    public void MoveOneColorDotPlayer(List<Dot> RedDots,  List<Dot> DotsInGame, List<Cell> Cells, int wr) {
        int k = Random();
        Cell cellsMethod=new Cell(null,0,null,null,0,0,null, null);
        RulesForMove rulesForMove=new RulesForMove();
            System.out.print("Двигаем фишку  номер" + wr+ " Цвет ");
            PrintColor(RedDots);
            System.out.println();

            Scanner scanner=new Scanner(System.in);
            if (k == 5) {
                System.out.println("1-Передвинуть фишку на 5 , 2-Создать новую фишку");
                int q = scanner.nextInt();
                rulesForMove.MoveOnFive(RedDots,  DotsInGame, q, wr,Cells);
            } else {
                if (rulesForMove.Check(RedDots, wr) != 2) {
                    cellsMethod.Move2(RedDots.get(wr - 1),  Cells,k,DotsInGame, RedDots);
                    System.out.print("Передвинли на " + k + " Цвет ");
                    PrintColor(RedDots);
                    System.out.println();
                }
                if (k == 6) {
                    System.out.println("1-Передвинуть фишку на 7 , 2-Создать новую фишку");
                    int q = scanner.nextInt();
                    rulesForMove.MoveOnSix(RedDots,  DotsInGame, q, wr,Cells);
                }
            }

    }
    public  void MoveOneColorDotComputer(List<Dot> Dots,  List<Dot> DotsInGame, List<Cell> Cells){
        int  k = Random();
        Cell cellsMethod=new Cell(null,0,null,null,0,0,null, null);
         int wy = 10;
            Coordinates coordinates=new Coordinates();
            ColorDot CD=coordinates.FindDotToColor(Dots.get(0).color);
            while (wy > CD.DotsInGame) {
                wy = (int) (Math.random() * ((2 - 1) + 1) + 1);
            }
            System.out.print("Двигаем фишку номер " + wy +" Цвет");
            PrintColor(Dots);
            System.out.println();

            if (k == 5) {
                int q = (int) (Math.random() * ((2 - 1) + 1) + 1);
                MoveOnFive(Dots,  DotsInGame, q, wy,Cells);

            } else {
                if (Check(Dots, wy) != 2) {
                    //cell.Move(Dots.get(wy - 1), k, Path, DotsInGame, Dots,Cells);
                    cellsMethod.Move2(Dots.get(wy - 1),  Cells,k,DotsInGame, Dots);
                    System.out.print("Передвинули на " + k + " Цвет");
                    PrintColor(Dots);
                    System.out.println();
                }
                if (k == 6) {
                    int q = (int) (Math.random() * ((2 - 1) + 1) + 1);
                    MoveOnSix(Dots,  DotsInGame, q, wy,Cells);
                }
            }
    }

    private  void PrintColor(List<Dot> Dots){
        if(Dots.get(0).color== Color.RED){
            System.out.print(" Красный");
        }
        if(Dots.get(0).color==Color.BLUE){
            System.out.print(" Синий");
        }
        if(Dots.get(0).color==Color.GREEN){
            System.out.print(" Зеленый");
        }
        if(Dots.get(0).color==Color.YELLOW){
            System.out.print(" Желтый");
        }
    }
    public int Check(List<Dot> Dots, int wr){
        Coordinates coordinates=new Coordinates();

        ColorDot CD = coordinates.FindDotToColor(Dots.get(0).color);
        if(Dots.get(wr-1).status==Status.InGAME){
            return 1;
        }
        if(CD.DotsInGame==0){
            return 2;
        }
        return 0;
    }

    public void MoveOnFive(List<Dot> Dots, List<Dot> DotsInGame, int q, int wr, List<Cell> Cells){
        Coordinates coordinates=new Coordinates();
        ColorDot CD = coordinates.FindDotToColor(Dots.get(0).color);
        Cell cellsMethod=new Cell(null,0,null,null,0,0,null, null);

        Base base=new Base(0,0,0,0,0,0);
        if(Check(Dots, wr)==2){
            base.CreateDot(CD.Color, DotsInGame,Cells);

        }else {
            while (Check(Dots, wr) == 0) {
                int wy = 10;
                while (wy > CD.DotsInGame) {
                    wy = (int) (Math.random() * ((2 - 1) + 1) + 1);
                }
            }
            if (q == 1) {
                cellsMethod.Move2(Dots.get(wr - 1),  Cells,5,DotsInGame, Dots);
                System.out.print("Передвинли фишку на 5 ");
                PrintColor(Dots);
                System.out.println();
            }
            if (q == 2) {
                if(base.CanCreateDot(Dots, Cells)) {
                    base.CreateDot(CD.Color, DotsInGame,Cells);
                    System.out.print("Создали новую фишку ");
                    PrintColor(Dots);
                    System.out.println();
                }else {
                    cellsMethod.Move2(Dots.get(wr - 1),  Cells,5,DotsInGame, Dots);
                    System.out.print(" Нельзя создать фишку.  Передвинли фишку на 5 ");
                    PrintColor(Dots);
                    System.out.println();
                }
            }
        }
    }

    public void MoveOnSix(List<Dot> Dots, List<Dot> DotsInGame, int q, int wr, List<Cell> Cells){
        Coordinates coordinates=new Coordinates();
        ColorDot CD = coordinates.FindDotToColor(Dots.get(0).color);
        Cell cellsMethod=new Cell(null,0,null,null,0,0,null, null);
        Base base=new Base(0,0,0,0,0,0);
        cellsMethod.DeleteBlock(Dots.get(wr - 1),Cells);
        if(Check(Dots, wr)==2){
            base.CreateDot(CD.Color, DotsInGame,Cells);
        }else {
            if (q == 1) {
                cellsMethod.Move2(Dots.get(wr - 1),  Cells,7,DotsInGame, Dots);
                System.out.print(" Передвинли фишку на  7 ");
                PrintColor(Dots);
                System.out.println();

            }
            if (q == 2) {
                if(base.CanCreateDot(Dots,Cells)) {
                    base.CreateDot(CD.Color,DotsInGame,Cells);
                    System.out.print("Создали новую фишку ");
                    PrintColor(Dots);
                    System.out.println();

                }else {
                    cellsMethod.Move2(Dots.get(wr - 1),  Cells,7,DotsInGame, Dots);
                    System.out.print(" Нельзя создать фишку.  Передвинли фишку на 7 ");
                    PrintColor(Dots);
                    System.out.println();

                }
            }
        }
    }

    public int Random() {
        int K = (int) (Math.random() * ((6 - 1) + 1) + 1);
        System.out.println(K);
        return K;
    }



}
