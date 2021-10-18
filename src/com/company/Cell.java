package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell extends CellsMethod {
    public static final int  OneStep=50;

    int CellNumber;
    BlockStatus CellStatus;
    int CellX;
    int CellY;
    Dot DotOnCellFirst;
    Dot DotOnCellSecond;
    public Cell(int CellNumber, BlockStatus CellStatus, int CellX, int CellY,Dot DotOnCellFirst,Dot DotOnCellSecond){
        this.CellNumber=CellNumber;
        this.CellStatus=CellStatus;
        this.CellX=CellX;
        this.CellY=CellY;
        this.DotOnCellFirst= DotOnCellFirst;
        this.DotOnCellSecond=DotOnCellSecond;
    }
    public static List<Cell> CreatePath() {
        Dot d = new Dot.DotBuilder().withColor(Color.RED).withX(0).withY(0).withSerialNumber(0).withCellNumber(0).build();
        List<Cell>  Cells=new ArrayList<>();
        int k=1;
        int X=400;
        int Y=700;
        for(int i=0; i<6; i++){
            Cell c=new Cell(k,BlockStatus.Usual,X,Y,null,null);
            Cells.add(c);
            Y-=OneStep;
            k++;
        }
        for(int i=0; i<=5; i++){
            Cell c=new Cell(k,BlockStatus.Usual,X,Y,null,null);
            Cells.add(c);
            X+=OneStep;
            k++;
        }
        for(int i=0; i<=1; i++){
            Cell c=new Cell(k,BlockStatus.Usual,X,Y,null,null);
            Cells.add(c);
            Y-=OneStep;
            k++;
        }
        for(int i=0; i<=5; i++) {
            Cell c = new Cell(k, BlockStatus.Usual, X, Y,null,null);
            Cells.add(c);
            X -= OneStep;
            k++;
        }
        for(int i=0; i<=5; i++) {
            Cell c = new Cell(k, BlockStatus.Usual, X, Y,null,null);
            Cells.add(c);
            Y -= OneStep;
            k++;
        }
        for(int i=0; i<=1; i++) {
            Cell c = new Cell(k, BlockStatus.Usual, X, Y,null,null);
            Cells.add(c);
            X -= OneStep;
            k++;
        }
        for(int i=0; i<=5; i++) {
            Cell c = new Cell(k, BlockStatus.Usual, X, Y,null,null);
            Cells.add(c);
            Y += OneStep;
            k++;
        }
        for(int i=0; i<=5; i++) {
            Cell c = new Cell(k, BlockStatus.Usual, X, Y,null,null);
            Cells.add(c);
            X -= OneStep;
            k++;
        }
        for(int i=0; i<=1; i++) {
            Cell c = new Cell(k, BlockStatus.Usual, X, Y,null,null);
            Cells.add(c);
            Y += OneStep;
            k++;
        }
        for(int i=0; i<=5; i++) {
            Cell c = new Cell(k, BlockStatus.Usual, X, Y,null,null);
            Cells.add(c);
            X += OneStep;
            k++;
        }
        for(int i=0; i<=5; i++) {
            Cell c = new Cell(k, BlockStatus.Usual, X, Y,null,null);
            Cells.add(c);
            Y += OneStep;
            k++;
        }
        for(int i=0; i<=1; i++) {
            Cell c = new Cell(k, BlockStatus.Usual, X, Y,null,null);
            Cells.add(c);
            X+= OneStep;
            k++;
        }
        X=350;
        Y=50;
        k=100;
        for(int i=0; i<6; i++) {
            Cell c = new Cell(k, BlockStatus.Safe, X, Y,null,null);
            Cells.add(c);
            Y += OneStep;
            k++;
        }

        Y=650;
        k=110;
        for(int i=0; i<6; i++) {
            Cell c = new Cell(k, BlockStatus.Safe, X, Y,null,null);
            Cells.add(c);
            Y -= OneStep;
            k++;
        }

        X=50;
        Y=350;
        k=120;
        for(int i=0; i<6; i++) {
            Cell c = new Cell(k, BlockStatus.Safe, X, Y,null,null);
            Cells.add(c);
            X += OneStep;
            k++;
        }

        X=650;
        k=130;
        for(int i=0; i<6; i++) {
            Cell c = new Cell(k, BlockStatus.Safe, X, Y,null,null);
            Cells.add(c);
            X -= OneStep;
            k++;
        }

        Cell c =new Cell(k,BlockStatus.Finish,350,350,null,null);
        Cells.add(c);

        return Cells;
    }
    public static void EstablishDotsOnCells(List<Dot> DotsInGame){
        for(int i=0; i<Cells.size(); i++){
            //     System.out.println(Cells.get(i).CellX+" "+ Cells.get(i).CellY+" "+ Cells.get(i).CellNumber);
            if(Cells.get(i).CellX==200 && Cells.get(i).CellY==400){
                for(int j=0; j<DotsInGame.size(); j++){
                    if(DotsInGame.get(j).color==Color.GREEN){
                        Cells.get(i).DotOnCellFirst=DotsInGame.get(j);
                    }
                }
                Cells.get(i).CellStatus=BlockStatus.HomeExit;
            }
            if(Cells.get(i).CellX==300 && Cells.get(i).CellY==200){
                Cells.get(i).CellStatus=BlockStatus.HomeExit;
                for(int j=0; j<DotsInGame.size(); j++){
                    if(DotsInGame.get(j).color==Color.RED){
                        Cells.get(i).DotOnCellFirst=DotsInGame.get(j);
                    }
                }
            }
            if(Cells.get(i).CellX==500 && Cells.get(i).CellY==300){
                Cells.get(i).CellStatus=BlockStatus.HomeExit;
                for(int j=0; j<DotsInGame.size(); j++){
                    if(DotsInGame.get(j).color==Color.BLUE){
                        Cells.get(i).DotOnCellFirst=DotsInGame.get(j);
                    }
                }

            }
            if(Cells.get(i).CellX==400 && Cells.get(i).CellY==500){
                Cells.get(i).CellStatus=BlockStatus.HomeExit;
                for(int j=0; j<DotsInGame.size(); j++){
                    if(DotsInGame.get(j).color==Color.YELLOW){
                        Cells.get(i).DotOnCellFirst=DotsInGame.get(j);
                    }
                }

            }
            //System.out.println(Cells.get(i).d,d.size());
        }
    }

    public static void Move(Dot d,  int x,List<Integer> List,List<Dot> DotsInGame,List<Dot> Dots ){
        if(d.status==Status.InGAME){
            if(x==6){
                DeleteBlock(d,List,DotsInGame);
            }
        for(int i=0; i<x; i++) {
           // System.out.println("111");
            x = MoveDotForCells(d, 1, List, DotsInGame, x);
            if (d.X == 350 && d.Y == 350) {
                DotsInGame.remove(d);
                Dots.remove(d);
                return;
            }
            if(i==x-1){
                CutDownDot(d,DotsInGame,List,Dots);
            }
            CompareDotsAndCells(DotsInGame);
        }
        }
        System.out.println( );
    }




    public static List<Integer> CreateRedPath(){
        List<Integer> arr=new ArrayList<>();
        for(int i=33; i<=56; i++){
            arr.add(i);
        }
        for(int i=1; i<=28; i++){
            arr.add(i);
        }
        for(int i=100; i<=105; i++){
            arr.add(i);
        }
        arr.add(136);
        return arr;
    }
    public static List<Integer> CreateBluePath(){
        List<Integer> arr=new ArrayList<>();
        for(int i=19; i<=56; i++){
            arr.add(i);
        }
        for(int i=1; i<=14; i++){
            arr.add(i);
        }
        for(int i=130; i<=125; i++){
            arr.add(i);
        }
        arr.add(136);
        return arr;
    }
    public static List<Integer> CreateGreenPath(){
        List<Integer> arr=new ArrayList<>();
        for(int i=47; i<=56; i++){
            arr.add(i);
        }
        for(int i=1; i<43; i++){
            arr.add(i);
        }
        for(int i=120; i<=135; i++){
            arr.add(i);
        }
        arr.add(136);
        return arr;
    }
    public static List<Integer> CreateYellowPath(){
        List<Integer> arr=new ArrayList<>();
        for(int i=5; i<=56; i++){
            arr.add(i);
        }
        for(int i=110; i<=115; i++){
            arr.add(i);
        }
        arr.add(136);
        return arr;
    }
}
