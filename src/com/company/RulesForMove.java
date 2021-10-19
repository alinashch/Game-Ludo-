package com.company;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class RulesForMove extends Move {

    protected static void MoveOneColorDotRed(List<Dot> RedDots,List<Integer> RedPath ,List<Dot> DotsInGame) {
        int k = Random();

            int wr = RulesForMove.EnterDotNumber();
            while (Check(RedDots, wr) == 0) {
                System.out.println("Данная фишка достигла финиша. Введите другой номер");
                wr = RulesForMove.EnterDotNumber();
            }
            System.out.println("Двигаем фишку красную номер" + wr);

            Scanner scanner=new Scanner(System.in);
            if (k == 5) {
                System.out.println("1-Передвинуть фишку на 5 , 2-Создать новую фишку");
                int q = scanner.nextInt();
                RulesForMove.MoveOnFive(RedDots, RedPath, DotsInGame, q, wr);
            } else {
                if (RulesForMove.Check(RedDots, wr) != 2) {
                    Cell.Move(RedDots.get(wr - 1), k, RedPath, DotsInGame, RedDots);
                    System.out.println("Передвинли на " + k + " Красный ");
                }
                if (k == 6) {
                    System.out.println("1-Передвинуть фишку на 7 , 2-Создать новую фишку");
                    int q = scanner.nextInt();
                    RulesForMove.MoveOnSix(RedDots, RedPath, DotsInGame, q, wr);
                }
            }

    }
    protected static void MoveOneColorDot(List<Dot> Dots,List<Integer> Path ,List<Dot> DotsInGame ){
       int  k = Random();

            int wy = 10;
            ColorDot CD=Coordinates.FindDotToColor(Dots.get(0).color);
            while (wy > CD.DotsInGame) {
                wy = (int) (Math.random() * ((2 - 1) + 1) + 1);
            }
            System.out.print("Двигаем фишку номер " + wy +" Цвет");
            PrintColor(Dots);
            System.out.println();

            if (k == 5) {
                int q = (int) (Math.random() * ((2 - 1) + 1) + 1);
                MoveOnFive(Dots, Path, DotsInGame, q, wy);

            } else {
                if (Check(Dots, wy) != 2) {
                    Cell.Move(Dots.get(wy - 1), k, Path, DotsInGame, Dots);
                    System.out.print("Передвинули на " + k + " Цвет");
                    PrintColor(Dots);
                    System.out.println();
                }
                if (k == 6) {
                    int q = (int) (Math.random() * ((2 - 1) + 1) + 1);
                    MoveOnSix(Dots, Path, DotsInGame, q, wy);
                }
            }

    }

    private static void PrintColor(List<Dot> Dots){
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
    private static int Check(List<Dot> Dots, int wr){
        ColorDot CD = Coordinates.FindDotToColor(Dots.get(0).color);
        if(Dots.get(wr-1).status==Status.InGAME){
            return 1;
        }
        if(CD.DotsInGame==0){
            return 2;
        }
        return 0;
    }

    private static void MoveOnFive(List<Dot> Dots, List<Integer> Path, List<Dot> DotsInGame, int q, int wr){
        ColorDot CD = Coordinates.FindDotToColor(Dots.get(0).color);
        if(Check(Dots, wr)==2){

        }else {
            while (Check(Dots, wr) == 0) {
                int wy = 10;
                while (wy > CD.DotsInGame) {
                    wy = (int) (Math.random() * ((2 - 1) + 1) + 1);
                }
            }
            if (q == 1) {
                Cell.Move(Dots.get(wr - 1), 5, Path, DotsInGame,Dots);
                System.out.print("Передвинли фишку на 5 ");
                PrintColor(Dots);
                System.out.println();
            }
            if (q == 2) {
                if(Base.CanCreateDot(Dots)==true) {
                    Base.CreateDot(CD.Color, DotsInGame);
                    System.out.print("Создали новую фишку ");
                    PrintColor(Dots);
                    System.out.println();
                }else {
                    Cell.Move(Dots.get(wr - 1), 5, Path, DotsInGame,Dots);
                    System.out.print(" Нельзя создать фишку.  Передвинли фишку на 5 ");
                    PrintColor(Dots);
                    System.out.println();
                }
            }
        }
    }

    private static void MoveOnSix(List<Dot> Dots, List<Integer> Path, List<Dot> DotsInGame, int q, int wr){
        ColorDot CD = Coordinates.FindDotToColor(Dots.get(0).color);
        if(Check(Dots, wr)==2){
            Base.CreateDot(CD.Color, DotsInGame);
        }else {
            if (q == 1) {
                Cell.Move(Dots.get(wr - 1), 7, Path, DotsInGame,Dots);
                System.out.print(" Передвинли фишку на  7 ");
                PrintColor(Dots);
                System.out.println();

            }
            if (q == 2) {
                if(Base.CanCreateDot(Dots)==true) {
                    Base.CreateDot(CD.Color,DotsInGame);
                    System.out.print("Создали новую фишку ");
                    PrintColor(Dots);
                    System.out.println();

                }else {
                    Cell.Move(Dots.get(wr - 1), 7, Path, DotsInGame,Dots);
                    System.out.print(" Нельзя создать фишку.  Передвинли фишку на 7 ");
                    PrintColor(Dots);
                    System.out.println();

                }
            }
        }
    }

    private static int Random() {
        int K = (int) (Math.random() * ((6 - 1) + 1) + 1);
        System.out.println(K);
        return K;
    }

    private static int EnterDotNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Номер фишки для передвижения");
        int wr = 10;
        while (wr > Red.DotsInGame) {
            wr = scanner.nextInt();
        }
        return wr;
    }

}
