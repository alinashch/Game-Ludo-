package com.company;

import java.awt.*;
import java.util.List;
import java.util.Stack;

public class ColorDot extends Coordinates{
    int DotsInGame;
    int SerialNumber;
    int [] DotsCoordinates;
    java.awt.Color Color;
    Base DotBase;
    Stack<Integer>  DotsOnBase;

    public ColorDot(int DotsInGame,  int SerialNumber,int [] DotsCoordinates,Color Color, Base DotBase, Stack<Integer>  DotsOnBase){
        this.DotsCoordinates=DotsCoordinates;
        this.SerialNumber=SerialNumber;
        this.DotsInGame=DotsInGame;
        this.Color=Color;
        this.DotBase=DotBase;
        this. DotsOnBase=DotsOnBase;
    }
    public  void CreateDotsCoordinates(){


        Red.DotsCoordinates=new int[]{ 300,200,250,200,200,200,150,200};
        Blue.DotsCoordinates= new int[]{ 525,300,525,250,525,200,525,150};
        Green.DotsCoordinates=new int[]{ 200,425,200,475,200,525,200,575};
        Yellow.DotsCoordinates=new int[]{ 425,525,475,525,525,525,575,525};
        Stack<Integer> s=new Stack<>();
        s.add(1);
        s.add(3);
        s.add(5);
        s.add(7);
        Red.DotsOnBase=s;
        Blue.DotsOnBase=s;
        Green.DotsOnBase=s;
        Yellow.DotsOnBase=s;
    }
}
