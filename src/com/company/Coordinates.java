package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Coordinates {
   // public static   List<Cell> Cells=Cell.CreatePath();

    public static final Base RedBase=new Base(400,300,33, 21,35,0);
    public static final Base BlueBase=new Base(400,400,19, 7, 21,0);
    public static final Base GreenBase=new Base(300,300,47, 35, 49,0);
    public  static final Base YellowBase=new Base(300,400,5,49 ,7,0);

    public static Status status=Status.InGAME;

    public static ColorDot Red=new ColorDot(1,1, new int[]{300, 200, 250, 200, 200, 200, 150, 200}, Color.RED,RedBase, null);
    public static ColorDot Blue=new ColorDot(1,1,new int[]{ 525,300,525,250,525,200,525,150},Color.BLUE,BlueBase,null);
    public static   ColorDot Green=new ColorDot(1,1,new int[]{ 200,425,200,475,200,525,200,575},Color.GREEN,GreenBase,null);
    public static ColorDot Yellow=new ColorDot(1,1,new int[]{ 425,525,475,525,525,525,575,525},Color.YELLOW,YellowBase,null);



    public  ColorDot FindDotToColor(Color c){
        if(Blue.Color==c){
            return Blue;
        }
        if(Red.Color==c){
            return Red;
        }
        if(Green.Color==c){
            return Green;
        }
        if(Yellow.Color==c){
            return Yellow;
        }
        return null;
    }

}
