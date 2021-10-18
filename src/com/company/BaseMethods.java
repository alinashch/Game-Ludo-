package com.company;

import java.awt.*;
import java.util.List;

public class BaseMethods extends Coordinates {

    protected static boolean CanCreateDot(List<Dot> Dots){
        ColorDot CD=Coordinates.FindDotToColor(Dots.get(0).color);
        if(CD.SerialNumber<=7 && !CD.DotsOnBase.empty()){
            for(int i=0; i<Cells.size(); i++){
                if(Cells.get(i).CellNumber==CD.DotBase.CellNumber) {
                    System.out.println("1");
                    if (Cells.get(i).DotOnCellFirst == null && Cells.get(i).DotOnCellSecond == null) {
                        return true;
                    } else {
                        if (Cells.get(i).DotOnCellSecond != null) {
                            System.out.println("2");
                            if (Cells.get(i).DotOnCellFirst == null && Cells.get(i).DotOnCellSecond.color != CD.Color) {
                                System.out.println("3");
                                return true;
                            }
                        }
                        if (Cells.get(i).DotOnCellFirst != null) {
                            System.out.println("4");
                            if (Cells.get(i).DotOnCellSecond == null && Cells.get(i).DotOnCellFirst.color != CD.Color) {
                                System.out.println("5");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    protected static void SendDotOnBase(Dot d, List<Dot> dots,List<Dot> dotsInGame){
        for(int i=0; i<Cells.size(); i++){
            if(Cells.get(i).CellNumber==d.CellNumber){
                if(Cells.get(i).DotOnCellFirst==d){
                    Cells.get(i).DotOnCellFirst=null;
                }
                if(Cells.get(i).DotOnCellSecond==d){
                    Cells.get(i).DotOnCellSecond=null;
                }
            }
        }
        ColorDot Cd=Move.FindDotToColor(d.color);
        if(d.color== Color.RED) {
            d.X = 250;
            d.Y = 200;
        }
        if(d.color==Color.GREEN) {
            d.X = 250;
            d.Y = 500;
        }
        if(d.color==Color.YELLOW) {
            d.X = 500;
            d.Y = 550;
        }
        if(d.color==Color.BLUE) {
            d.X = 500;
            d.Y = 250;
        }
        Cd.DotsOnBase.add(d.SerialNumber);
        dots.remove(d);
        dotsInGame.remove(d);
        d.CellNumber=Cd.DotBase.CellNumber;
        Cd.DotsCoordinates[d.SerialNumber-1]=d.X;
        Cd.DotsCoordinates[d.SerialNumber]=d.Y;
        Cd.SerialNumber-=2;
        Cd.DotsInGame--;
        Cd.DotBase.dotsOnBase++;
    }
}
