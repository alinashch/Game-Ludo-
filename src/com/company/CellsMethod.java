package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CellsMethod extends Coordinates {
    protected static void CutDownDot(Dot d, List<Dot> DotsInGame, List<Integer> List, List<Dot> Dots) {
        Cell c=null;
        for(int i=0; i<Cells.size(); i++){
            if(Cells.get(i).CellNumber==d.CellNumber){
                c=Cells.get(i);
            }
        }
        if( c!=null && CheckCutDownDot(d,DotsInGame,c)==true){
            for(int i=0; i<DotsInGame.size(); i++){
                if(DotsInGame.get(i).CellNumber==c.CellNumber && DotsInGame.get(i).color!=d.color ) {
                    System.out.print("Отправили на базу точку цвет ");
                    GetColor(DotsInGame.get(i));
                    System.out.println();
                    Base.SendDotOnBase(DotsInGame.get(i),Dots,DotsInGame);

                }
            }
            int x=20;
            for(int i=0; i<x; i++){
                x=MoveDotForCells(d, 1, List, DotsInGame, x);
            }
            System.out.print("Переместили точку на 20 цвет ");
            GetColor(d);
            System.out.println();
        }
    }
    private static boolean CheckCutDownDot(Dot d, List<Dot> DotsInGame, Cell c ){
        for(int i=0; i<DotsInGame.size(); i++){
            if(DotsInGame.get(i).CellNumber==c.CellNumber){
                if(DotsInGame.get(i).color!=d.color && c.CellStatus==BlockStatus.Usual){
                    return true;
                }
            }
        }
        return false;
    }

    private static void passCells(int w, Dot d){
        for(int i=0; i<Cells.size(); i++){
            if(Cells.get(i).CellNumber==w){
                if(Cells.get(i).DotOnCellFirst==d){
                    Cells.get(i).DotOnCellFirst=null;
                }
                if(Cells.get(i).DotOnCellSecond==d){
                    Cells.get(i).DotOnCellSecond=null;
                }
            }
        }
    }
    public static void GetColor(Dot Dot){
        if(Dot.color==Color.RED){
            System.out.print("Red");
        }
        if(Dot.color==Color.BLUE){
            System.out.print("Blue");
        }
        if(Dot.color==Color.GREEN){
            System.out.print("Green");
        }
        if(Dot.color==Color.YELLOW){
            System.out.print("Yellow");
        }
    }
    protected static int MoveDotForCells(Dot d, int k, List<Integer> List, List<Dot> DotsInGame, int x){
        int z=0;
        if(d.CellNumber==28 && d.color== Color.RED){
            d.CellNumber=100;
            z=52;
            passCells(28,d);
        }
        else if(d.CellNumber==14 && d.color== Color.BLUE){
            d.CellNumber=130;
            z=52;
            passCells(14,d);

        }
        else if(d.CellNumber==42 && d.color== Color.GREEN){
            d.CellNumber=120;
            z=52;
            passCells(42,d);

        }
        else if(d.CellNumber==56 && d.color== Color.YELLOW){
            d.CellNumber=110;
            z=52;
            passCells(56,d);

        }else {
            for (int i = 0; i < List.size(); i++) {
                if (List.get(i) == d.CellNumber) {
                    z = i;
                }
            }
        }
        z += k;

        Cell c = FindNextCell(Cells, z, k, List, d);
        if (Move.CanMoveOnNextCell(DotsInGame, c, d,List) == 1) {
            if (c.DotOnCellFirst == null) {
                c.DotOnCellFirst = d;
            } else if (c.DotOnCellSecond == null) {
                c.DotOnCellSecond = d;
            }
        } else if (Move.CanMoveOnNextCell(DotsInGame, c, d,List) == 2) {
            c = FindNextCell(Cells, z + 1, k, List, d);
            if (c.DotOnCellFirst == null) {
                c.DotOnCellFirst = d;
            } else if (c.DotOnCellSecond == null) {
                c.DotOnCellSecond = d;
            }
            x++;
        } else if (Move.CanMoveOnNextCell(DotsInGame, c, d,List) == 0) {
            return z;
        }

        ColorDot Cd = Coordinates.FindDotToColor(d.color);

        // System.out.println(d.CellNumber + " " + c.CellNumber + " " + z + " ");

        for (int i = 0; i < Cells.size(); i++) {
            if (Cells.get(i).CellNumber == d.CellNumber) {
                if (Cells.get(i).DotOnCellFirst == d) {
                    Cells.get(i).DotOnCellFirst = null;
                }
                if (Cells.get(i).DotOnCellSecond == d) {
                    Cells.get(i).DotOnCellSecond = null;
                }
            }
        }
        if (c.CellNumber - 1 == Cd.DotBase.CellNumber && d.color == Cd.Color) {
            for (int i = 0; i < Cells.size(); i++) {
                if (Cells.get(i).CellNumber == c.CellNumber - 1) {
                    if (Cells.get(i).DotOnCellFirst != null) {
                        Cells.get(i).DotOnCellFirst = null;
                    }
                    if (Cells.get(i).DotOnCellSecond != null) {
                        Cells.get(i).DotOnCellSecond = null;
                    }
                }

            }
        }
        d.CellNumber += k;
        d.X = c.CellX;
        d.Y = c.CellY;
        Cd.DotsCoordinates[d.SerialNumber - 1] = c.CellX;
        Cd.DotsCoordinates[d.SerialNumber] = c.CellY;
        return x;
    }
    private static Cell FindNextCell(List<Cell> Cell, int z, int k, List<Integer> List, Dot d) {
        Cell c=Cell.get(0);
        for (int i = 0; i < Cell.size()-1; i++) {
            if (Cell.get(i).CellNumber == List.get(z)) {
                c = Cell.get(i);
            }
        }
        if (z == 57) {
            c.CellNumber = 136;
        }
        if (c.CellNumber - d.CellNumber < 0 && d.CellNumber != 56) {
            d.CellNumber = 1;
            for (int i = 0; i < List.size(); i++) {
                if (List.get(i) == d.CellNumber) {
                    z = i;
                }
            }
            z += k;
            c = Cell.get(List.get(z) - 1);
        }
        return c;
    }
    protected static void DeleteBlock(Dot d, List<Integer> List, List<Dot> DotsInGame){
        if(CheckDeleteBlock(d, DotsInGame)!=null) {
            Dot dot = CheckDeleteBlock(d, DotsInGame);
            int x=6;
            for(int i=0; i<x; i++) {
                x= MoveDotForCells(dot,1,List,DotsInGame,x);
            }
            System.out.print("Передвинута фишка в блоке цвет ");
            GetColor(d);
            System.out.println();
        }
    }

    private static Dot CheckDeleteBlock(Dot d, List<Dot> DotsInGame){
        Dot [] arr=new Dot[4];
        int k=0;
        for(int i=0; i<Cells.size(); i++) {
            if (Cells.get(i).DotOnCellSecond != null && Cells.get(i).DotOnCellFirst != null) {
                if (Cells.get(i).DotOnCellFirst.color == Cells.get(i).DotOnCellSecond.color && Cells.get(i).DotOnCellSecond.color == d.color) {
                    arr[k] = Cells.get(i).DotOnCellSecond;
                    k++;
                    arr[k] = Cells.get(i).DotOnCellFirst;
                    k++;
                }
            }
        }
        for(int i=0; i<arr.length; i++){
            if( arr[i]!=d){
                return arr[i];
            }
        }
        return null;
    }
    protected static void CompareDotsAndCells(List<Dot> DotsInGame){
        List<Dot> List=new ArrayList<>();
        for(int i=0; i<Cells.size(); i++){
            if(Cells.get(i).DotOnCellFirst!=null ){
                Dot d=Cells.get(i).DotOnCellFirst;
                List.add(d);
            }
            if( Cells.get(i).DotOnCellSecond!= null){
                Dot d=Cells.get(i).DotOnCellSecond;
                List.add(d);
            }
        }
        for(int i=0; i<List.size(); i++){
            for(int j=0; j<DotsInGame.size(); j++){
                if(!DotsInGame.contains(List.get(i))){
                    DotsInGame.add(List.get(i));
                    ColorDot Cd=Coordinates.FindDotToColor(List.get(i).color);
                    Cd.DotsCoordinates[List.get(i).SerialNumber-1]=List.get(i).X;
                    Cd.DotsCoordinates[List.get(i).SerialNumber]=List.get(i).Y;
                }
            }
        }
        CompareDotsAndCoordinates(List);
    }
    private static void CompareDotsAndCoordinates(List<Dot> DotsInGame){
        for(int i=0; i<DotsInGame.size(); i++) {
            ColorDot Cd = Coordinates.FindDotToColor(DotsInGame.get(i).color);
            if(Cd.DotsCoordinates[DotsInGame.get(i).SerialNumber-1] ==DotsInGame.get(i).X ){
                ///System.out.println("1111111");
                // System.out.println(Cd.DotsCoordinates[DotsInGame.get(i).SerialNumber-1]);
                //System.out.println(DotsInGame.get(i).X);
                Cd.DotsCoordinates[DotsInGame.get(i).SerialNumber-1]=DotsInGame.get(i).X;
                //System.out.println(Cd.DotsCoordinates[DotsInGame.get(i).SerialNumber-1]);

            }
            if(Cd.DotsCoordinates[DotsInGame.get(i).SerialNumber] !=DotsInGame.get(i).Y ){
                Cd.DotsCoordinates[DotsInGame.get(i).SerialNumber]=DotsInGame.get(i).Y;
            }
        }
    }


    private static void CheckDotsAndCells( List<Dot> DotsInGame) {
        for (int i = 0; i < Cells.size(); i++) {
            for (int j = 0; j < DotsInGame.size(); j++) {
                if (Cells.get(i).CellX == DotsInGame.get(j).X && Cells.get(i).CellY == DotsInGame.get(j).Y && Cells.get(i).CellStatus!=BlockStatus.Finish) {
                    if (Cells.get(i).CellNumber != DotsInGame.get(j).CellNumber) {
                        if (Cells.get(i).DotOnCellFirst != DotsInGame.get(j) && Cells.get(i).DotOnCellSecond != DotsInGame.get(j)) {
                            if(Cells.get(i).DotOnCellFirst==null){
                                for(int k=0; k< Cells.size(); k++) {
                                    if (Cells.get(k).DotOnCellFirst == DotsInGame.get(j)) {
                                        Cells.get(k).DotOnCellFirst = null;
                                        Cells.get(i).DotOnCellFirst = DotsInGame.get(j);
                                    } else if (Cells.get(k).DotOnCellSecond == DotsInGame.get(j)) {
                                        Cells.get(k).DotOnCellFirst = null;
                                        Cells.get(i).DotOnCellFirst = DotsInGame.get(j);
                                    }
                                }
                            }else if(Cells.get(i).DotOnCellSecond==null){
                                for(int k=0; k< Cells.size(); k++) {
                                    if (Cells.get(k).DotOnCellFirst == DotsInGame.get(j)) {
                                        Cells.get(k).DotOnCellFirst = null;
                                        Cells.get(i).DotOnCellSecond=DotsInGame.get(j);
                                    } else if (Cells.get(k).DotOnCellSecond == DotsInGame.get(j)) {
                                        Cells.get(k).DotOnCellFirst = null;
                                        Cells.get(i).DotOnCellSecond=DotsInGame.get(j);
                                    }
                                }
                                // Cells.get(i).DotOnCellSecond=DotsInGame.get(j);
                            }
                        }

                    }
                }
            }
        }
    }

}
