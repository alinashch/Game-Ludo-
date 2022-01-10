package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Base extends Coordinates {
    int BaseX;
    int BaseY;
    int CellNumber;
    int FirstCell;
    int LastCell;
    int DotsOnFinish;
    public Base(int BaseX, int BaseY,int CellNumber,int FirstCell, int LastCell, int DotsOnFinish){
        this.BaseX=BaseX;
        this.BaseY=BaseY;
        this.CellNumber=CellNumber;
        this.FirstCell=FirstCell;
        this.LastCell=LastCell;
        this.DotsOnFinish=DotsOnFinish;
    }

    public  List<Cell> BasePath(Base base, int k){
        List<Cell> Cells =new ArrayList<>();
        if(base.LastCell< base.FirstCell){
            for(int i=base.FirstCell; i<=56; i++){
                Cell c= new Cell(Color.white,k, null,BlockStatus.Usual,0, 0, null, null );
                k++;
                Cells.add(c);
            }
            k=1;
            for(int i=1; i<=base.LastCell-1; i++){
                Cell c= new Cell(Color.white,k, null,BlockStatus.Usual,0, 0, null, null );
                k++;
                Cells.add(c);
            }
        }else {
            for (int i = base.FirstCell; i <= base.LastCell - 1; i++) {
                Cell c = new Cell(Color.white,k, null, BlockStatus.Usual, 0, 0, null, null);
                k++;
                Cells.add(c);
            }
        }
        for(int i=0; i<Cells.size(); i++){
           if(base.FirstCell>base.LastCell) {
               if(Cells.get(i).CellNumber==base.FirstCell-7){
                   Cells.get(i).CellStatus=BlockStatus.HomeExit;
                   Cells.get(i).ColorCell=Color.YELLOW;
               }
           }else if(Cells.get(i).CellNumber==base.FirstCell+12){
                   Cells.get(i).CellStatus=BlockStatus.HomeExit;
                   if(Cells.get(i).CellNumber==33){
                       Cells.get(i).ColorCell=Color.RED;
                   }else if(Cells.get(i).CellNumber==19){
                       Cells.get(i).ColorCell=Color.BLUE;
                   }else if(Cells.get(i).CellNumber==47){
                       Cells.get(i).ColorCell=Color.GREEN;
                   }
           }
        }

        return Cells;
    }

    public  List<Cell> EnterDirections(List<Cell> Cell, Directions Long, Directions Small, int X, int Y){
        int z=0;
        for(int i=0; i<6; i++){
            Cell.get(z).CellX=X;
            Cell.get(z).CellY=Y;
            z++;
            X= ChangeCoordinatesX(Long, X);
            Y= ChangeCoordinatesY(Long,  Y);
        }
        for(int i=0; i<2; i++){
            Cell.get(z).CellX=X;
            Cell.get(z).CellY=Y;
            z++;
            X= ChangeCoordinatesX(Small, X);
            Y= ChangeCoordinatesY(Small,  Y);
        }
        Directions directions=Directions.Down;
        Directions d=directions.GetOppositeDirection(Long);

        for(int i=0; i<6; i++){
            Cell.get(z).CellX=X;
            Cell.get(z).CellY=Y;
            z++;
            X= ChangeCoordinatesX(d, X);
            Y= ChangeCoordinatesY(d,  Y);
        }
        return Cell;
    }

    public  List<Cell> EnterColor(int k, List<Cell> Cell){
        Color color=Color.white;
        int l=0;
        int X=0;
        int Y=0;
        Directions d=Directions.Left;
        for(int i=0; i<Cell.size(); i++){
            if(Cell.get(i).CellNumber==k){
                X=Cell.get(i).CellX;
                Y=Cell.get(i).CellY;
                if(k==56) {
                    Cell.get(i).ColorCell =Color.YELLOW;
                    color=Color.YELLOW;
                    d=Directions.Up;
                    Cell.get(i).CellStatus=BlockStatus.StartTrack;
                    l=156;
                }else if(k==14){
                    Cell.get(i).ColorCell=Color.BLUE;
                    color=Color.BLUE;
                    Cell.get(i).CellStatus=BlockStatus.StartTrack;
                    d=Directions.Left;
                    l=114;
                }else if(k==28){
                    Cell.get(i).ColorCell=Color.RED;
                    Cell.get(i).CellStatus=BlockStatus.StartTrack;
                    color=Color.RED;
                    d=Directions.Down;
                    l=128;
                }else if(k==42){
                    Cell.get(i).ColorCell=Color.GREEN;
                    color=Color.GREEN;
                    Cell.get(i).CellStatus=BlockStatus.StartTrack;
                    d=Directions.Right;
                    l=142;
                }

            }

        }
        for(int j=0; j<6; j++){
            X=ChangeCoordinatesX(d,X);
            Y=ChangeCoordinatesY(d,Y);
            Cell c= new Cell(color,l,null,BlockStatus.Safe,X, Y, null, null);
            Cell.add(c);
            l++;
        }
        Cell c= new Cell(Color.BLACK,100,null,BlockStatus.Finish,350, 350, null, null);
        Cell.add(c);
        for(int i=0; i<Cell.size(); i++){
            if(Cell.get(i).CellNumber==Cell.size()-1){
                Cell.get(i).NextCell=c;
            }
        }
        return Cell;

    }
    public static void EstablishDotsOnCells(List<Dot> DotsInGame, List<Cell> Cells) {
        for(int i=0; i<DotsInGame.size(); i++){
            for(int j=0; j< Cells.size(); j++){
                if(DotsInGame.get(i).CellNumber==Cells.get(j).CellNumber){
                    Cells.get(j).DotOnCellFirst=DotsInGame.get(i);
                }
            }
        }
    }

        private  int ChangeCoordinatesY(Directions d, int Y){
        if(d==Directions.Down){
            Y+=50;
        }

        if(d==Directions.Up){
            Y-=50;
        }
        return Y;
    }
    private  int ChangeCoordinatesX(Directions d, int X){

        if(d==Directions.Left){
            X-=50;
        }
        if(d==Directions.Right){
            X+=50;
        }

        return X;
    }


    public  Dot CreateDot(Color c, List<Dot> DotsInGame, List<Cell> Cells){
        ColorDot CD= FindDotToColor(c);
        int SerialNumber=CD.DotsOnBase.pop();
        Dot dot=new Dot(c,0,0,SerialNumber,0, Status.InGAME);
        CD.SerialNumber=CD.SerialNumber+2;
        CD.DotsInGame++;
        if(c==Color.RED){
            dot.CellNumber=33;
        }
        if(c==Color.BLUE){
            dot.CellNumber=19;
        }
        if(c==Color.GREEN){
            dot.CellNumber=47;
        }
        if(c==Color.YELLOW){
            dot.CellNumber=5;
        }
        int X=0;
        int Y=0;
        for(int i=0; i<Cells.size(); i++){
            if(Cells.get(i).CellNumber==dot.CellNumber){
                X=Cells.get(i).CellX;
                Y=Cells.get(i).CellY;
            }
        }
        CD.DotsCoordinates[SerialNumber-1]=X;
      //  System.out.println( CD.DotsCoordinates[SerialNumber-1]);
        CD.DotsCoordinates[SerialNumber]=Y;
        //System.out.println(CD.DotsCoordinates[SerialNumber-1]+" "+CD.DotsCoordinates[SerialNumber] );

        dot.X=CD.DotsCoordinates[SerialNumber-1];
        dot.Y=CD.DotsCoordinates[SerialNumber];
        for(int i=0; i<Cells.size(); i++){
            if(Cells.get(i).CellNumber==dot.CellNumber){
                if( Cells.get(i).DotOnCellFirst==null) {
                    Cells.get(i).DotOnCellFirst = dot;
                }else  if(Cells.get(i).DotOnCellSecond==null){
                    Cells.get(i).DotOnCellSecond = dot;
                }
            }
        }
        DotsInGame.add(dot);
        return dot;
    }
    public  List<Dot> CreateListDot(Color c){
        List<Dot> Dots = new ArrayList<>();
        if(c==Color.RED) {
            Dot Red1 = new Dot.DotBuilder().withColor(Color.RED).withX(300).withY(200).withSerialNumber(1).withCellNumber(33).build();
            Dot Red2 = new Dot.DotBuilder().withColor(Color.RED).withX(Red.DotsCoordinates[2]).withY(Red.DotsCoordinates[3]).withSerialNumber(3).withCellNumber(33).build();
            Dot Red3 = new Dot.DotBuilder().withColor(Color.RED).withX(Red.DotsCoordinates[4]).withY(Red.DotsCoordinates[5]).withSerialNumber(5).withCellNumber(33).build();
            Dot Red4 = new Dot.DotBuilder().withColor(Color.RED).withX(Red.DotsCoordinates[6]).withY(Red.DotsCoordinates[7]).withSerialNumber(7).withCellNumber(33).build();
            Dots.add(Red1);
            Dots.add(Red2);
            Dots.add(Red3);
            Dots.add(Red4);
            return Dots;
        }
        if(c==Color.BLUE){
            Dot Blue1 = new Dot.DotBuilder().withColor(Color.BLUE).withX(525).withY(300).withSerialNumber(1).withCellNumber(19).build();
            Dot Blue2 = new Dot.DotBuilder().withColor(Color.BLUE).withX(Blue.DotsCoordinates[2]).withY(Blue.DotsCoordinates[3]).withSerialNumber(3).withCellNumber(19).build();
            Dot Blue3 = new Dot.DotBuilder().withColor(Color.BLUE).withX(Blue.DotsCoordinates[4]).withY(Blue.DotsCoordinates[5]).withSerialNumber(5).withCellNumber(19).build();
            Dot Blue4 = new Dot.DotBuilder().withColor(Color.BLUE).withX(Blue.DotsCoordinates[6]).withY(Blue.DotsCoordinates[7]).withSerialNumber(7).withCellNumber(19).build();
            Dots.add(Blue1);
            Dots.add(Blue2);
            Dots.add(Blue3);
            Dots.add(Blue4);
            return Dots;
        }
        if(c==Color.GREEN){
            Dot Green1 = new Dot.DotBuilder().withColor(Color.GREEN).withX(200).withY(425).withSerialNumber(1).withCellNumber(47).build();
            Dot Green2 = new Dot.DotBuilder().withColor(Color.GREEN).withX(Green.DotsCoordinates[2]).withY(Green.DotsCoordinates[3]).withSerialNumber(3).withCellNumber(47).build();
            Dot Green3 = new Dot.DotBuilder().withColor(Color.GREEN).withX(Green.DotsCoordinates[4]).withY(Green.DotsCoordinates[5]).withSerialNumber(5).withCellNumber(47).build();
            Dot Green4 = new Dot.DotBuilder().withColor(Color.GREEN).withX(Green.DotsCoordinates[6]).withY(Green.DotsCoordinates[7]).withSerialNumber(7).withCellNumber(47).build();
            Dots.add(Green1);
            Dots.add(Green2);
            Dots.add(Green3);
            Dots.add(Green4);
            return Dots;
        }
        if(c==Color.YELLOW){
            Dot Yellow1 = new Dot.DotBuilder().withColor(Color.YELLOW).withX(425).withY(525).withSerialNumber(1).withCellNumber(5).build();
            Dot Yellow2 = new Dot.DotBuilder().withColor(Color.YELLOW).withX(Yellow.DotsCoordinates[2]).withY(Yellow.DotsCoordinates[3]).withSerialNumber(3).withCellNumber(5).build();
            Dot Yellow3 = new Dot.DotBuilder().withColor(Color.YELLOW).withX(Yellow.DotsCoordinates[4]).withY(Yellow.DotsCoordinates[5]).withSerialNumber(5).withCellNumber(5).build();
            Dot Yellow4 = new Dot.DotBuilder().withColor(Color.YELLOW).withX(Yellow.DotsCoordinates[6]).withY(Yellow.DotsCoordinates[7]).withSerialNumber(7).withCellNumber(5).build();
            Dots.add(Yellow1);
            Dots.add(Yellow2);
            Dots.add(Yellow3);
            Dots.add(Yellow4);
            return Dots;
        }
        return Dots;
    }
    protected  boolean CanCreateDot(List<Dot> Dots, List<Cell> Cells){
        Coordinates coordinates=new Coordinates();
        ColorDot CD=coordinates.FindDotToColor(Dots.get(0).color);
        if(CD.SerialNumber<=7 && !CD.DotsOnBase.empty()){
            for(int i=0; i<Cells.size(); i++){
                if(Cells.get(i).CellNumber==CD.DotBase.CellNumber) {
                    if (Cells.get(i).DotOnCellFirst == null && Cells.get(i).DotOnCellSecond == null) {
                        return true;
                    } else {
                        if (Cells.get(i).DotOnCellSecond != null) {
                            if (Cells.get(i).DotOnCellFirst == null && Cells.get(i).DotOnCellSecond.color != CD.Color) {
                                return true;
                            }
                        }
                        if (Cells.get(i).DotOnCellFirst != null) {
                            if (Cells.get(i).DotOnCellSecond == null && Cells.get(i).DotOnCellFirst.color != CD.Color) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    protected    void SendDotOnBase(Dot d, List<Dot> dots,List<Dot> dotsInGame, List<Cell> Cells){
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
        Coordinates coordinates=new Coordinates();
        ColorDot Cd=coordinates.FindDotToColor(d.color);
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
        //Cd.DotBase.dotsOnBase++;
    }

}
