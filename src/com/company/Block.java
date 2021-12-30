package com.company;

import java.awt.*;
import java.util.ArrayList;

import static com.company.BlockStatus.*;

public class Block extends Move{
    int LeftX;
    int LeftY;
    int RightX;
    int RightY;
    int count;
    BlockStatus BlockStatus;
    public Block(int LeftX, int LeftY, int RightX, int RightY, int count,BlockStatus BlockStatus){
        this.count=count;
        this.LeftX=LeftX;
        this.LeftY=LeftY;
        this.RightX=RightX;
        this.RightY=RightY;
        this.BlockStatus=BlockStatus;
    }

    public static ArrayList<Block> CreateBlock(){
    ArrayList<Block> Block=new ArrayList<>();
    int X=300;
    int Y=0;
    for(int j=15;j>0;j--){
    for(int i=3; i>0; i--) {
        Block.add(new Block(X, Y, X+50, Y+50, 0 , Usual));
        X += 50;
    }
    X-=150;
    Y+=50;
    }
    X=0;
    Y=300;
    for(int j=15;j>0;j--){
        for(int i=3; i>0; i--) {
            Block.add(new Block(X, Y, X+50, Y+50, 0 , Usual));
            Y += 50;
        }
        Y-=150;
        X+=50;
    }
    for(int i=0; i<Block.size(); i++){
        Block.get(i).count=0;
        if(Block.get(i).LeftX==250 && Block.get(i).LeftY==400){
            Block.get(i).count++;
            Block.get(i).BlockStatus=HomeExit;
        }
        if(Block.get(i).LeftX==300 && Block.get(i).LeftY==250){
            Block.get(i).count++;
            Block.get(i).BlockStatus=HomeExit;
        }
        if(Block.get(i).LeftX==500 && Block.get(i).LeftY==300){
            Block.get(i).count++;
            Block.get(i).BlockStatus=HomeExit;
        }
        if(Block.get(i).LeftX==400 && Block.get(i).LeftY==500){
            Block.get(i).count++;
            Block.get(i).BlockStatus=HomeExit;

        }
        if(Block.get(i).LeftX==350 && Block.get(i).LeftY==350){
            Block.get(i).BlockStatus=Finish;
        }
       // System.out.println(Block.get(i).LeftX+" "+ Block.get(i).LeftY);
    }
return Block;
    }

public static int Check(Dot dot, ArrayList<Block> Block, Direction d){
    int newX=0;
    int newY=0;
        if(d==Direction.UP){
            newX= dot.X;
            newY=dot.Y-50;
        }
        if(d==Direction.DOWN){
            newX= dot.X;
            newY= dot.Y+50;
        }
        if(d==Direction.LEFT){
            newX= dot.X-50;
            newY= dot.Y;
        }
    if(d==Direction.RIGHT){
        newX= dot.X+50;
        newY= dot.Y;
    }
    Block b= FindBlock(newX, newY, Block);
    int StepCount=0;
   // System.out.println(newX+" " +newY);
    System.out.println(b.count+" "+ dot.X+" "+ dot.Y+ " ");
    if(b.count==0 && b.BlockStatus== Usual){
        b.count++;
        Block N=FindBlock(dot.X ,dot.Y,Block);
        N.count--;
        StepCount=1;
    }else if(b.count==1 && b.BlockStatus== Usual){
        Color c=CheckColor(dot);
        if(c==dot.color) {
            b.count++;
            Block N=FindBlock(dot.X ,dot.Y,Block);
            N.count--;
            StepCount=1;
        }else {
            b.count++;
            Block N = FindBlock(dot.X, dot.Y, Block);
            N.count--;
            StepCount = 2;
        }
    }else if(b.count<2 && b.BlockStatus== HomeExit){
        b.count++;
        Block N=FindBlock(dot.X ,dot.Y,Block);
        N.count--;
        StepCount=1;
    }else if(b.count>2 && b.BlockStatus==HomeExit){
        StepCount=-1;
    }else if(b.count>2 && b.BlockStatus==Usual){
        StepCount=-1;
    }else if(b.BlockStatus==Finish){
        Block N = FindBlock(dot.X, dot.Y, Block);
        N.count--;
        StepCount=1;
    }
    return StepCount;
}
public static Color CheckColor(Dot dot){
        Color c =null;
    for(int i=0; i<7; i++){
        if(Red.DotsCoordinates[i]==dot.X && Red.DotsCoordinates[i+1]== dot.Y){
            c=Color.RED;
        }
    }
    for(int i=0; i<7; i++){
        if(Blue.DotsCoordinates[i]==dot.X && Blue.DotsCoordinates[i+1]== dot.Y){
            c=Color.BLUE;
        }
    }
    for(int i=0; i<7; i++){
        if(Green.DotsCoordinates[i]==dot.X && Green.DotsCoordinates[i+1]== dot.Y){
            c=Color.GREEN;
        }
    }
    for(int i=0; i<7; i++){
        if(Yellow.DotsCoordinates[i]==dot.X && Yellow.DotsCoordinates[i+1]== dot.Y){
            c=Color.YELLOW;
        }
    }
    return c;
}
    public static Block FindBlock(int x, int y, ArrayList<Block> Block){
        Block b=new Block(0,0,0,0,0,Usual);
        for(int i=0; i<Block.size(); i++){
            if(Math.abs(Block.get(i).RightX-x)+Math.abs(Block.get(i).LeftX-x)==Math.abs(Block.get(i).LeftX-Block.get(i).RightX)
                    && Math.abs(Block.get(i).RightY- y)+Math.abs(Block.get(i).LeftY- y)==Math.abs(Block.get(i).LeftY-Block.get(i).RightY)){
                b=Block.get(i);
            }
        }
        return b;
    }
}
