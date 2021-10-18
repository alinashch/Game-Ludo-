package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Base extends BaseMethods {
    public static final int RedX=300;
    public static final int RedY=200;

    public static final int BlueX=525;
    public static final int BlueY=300;

    public static final int GreenX=200;
    public static final int GreenY=425;

    public static final int YellowX=425;
    public static final int YellowY=525;



    int dotsOnBase;
    int BaseX;
    int BaseY;
    int CellNumber;
    public Base(int dotsOnBase , int BaseX, int BaseY,int CellNumber){
        this.dotsOnBase=dotsOnBase;
        this.BaseX=BaseX;
        this.BaseY=BaseY;
        this.CellNumber=CellNumber;
    }

    public static Dot CreateDot(Color c,  List<Dot> DotsInGame){
        ColorDot CD= FindDotToColor(c);
        int SerialNumber=CD.DotsOnBase.pop();
        Dot dot=new Dot(c,0,0,SerialNumber,0, Status.InGAME);
        CD.SerialNumber=CD.SerialNumber+2;
        CD.DotsInGame++;
        CD.DotsCoordinates[SerialNumber-1]=CD.DotBase.BaseX;
      //  System.out.println( CD.DotsCoordinates[SerialNumber-1]);
        CD.DotsCoordinates[SerialNumber]=CD.DotBase.BaseY;
        //System.out.println(CD.DotsCoordinates[SerialNumber-1]+" "+CD.DotsCoordinates[SerialNumber] );
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
    public static List<Dot> CreateListDot(Color c){
        List<Dot> Dots = new ArrayList<>();
        if(c==Color.RED) {
            Dot Red1 = new Dot.DotBuilder().withColor(Color.RED).withX(RedX).withY(RedY).withSerialNumber(1).withCellNumber(33).build();
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
            Dot Blue1 = new Dot.DotBuilder().withColor(Color.BLUE).withX(BlueX).withY(BlueY).withSerialNumber(1).withCellNumber(19).build();
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
            Dot Green1 = new Dot.DotBuilder().withColor(Color.GREEN).withX(GreenX).withY(GreenY).withSerialNumber(1).withCellNumber(47).build();
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
            Dot Yellow1 = new Dot.DotBuilder().withColor(Color.YELLOW).withX(YellowX).withY(YellowY).withSerialNumber(1).withCellNumber(5).build();
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

}
