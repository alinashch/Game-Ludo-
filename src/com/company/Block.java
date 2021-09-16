package com.company;

import java.util.ArrayList;

public class Block extends GameField{
    int LeftX;
    int LeftY;
    int RightX;
    int RightY;
    int count;
    public Block(int LeftX, int LeftY, int RightX, int RightY, int count){
        this.count=count;
        this.LeftX=LeftX;
        this.LeftY=LeftY;
        this.RightX=RightX;
        this.RightY=RightY;
    }
public static ArrayList<Block> CreateBlock(){
    ArrayList<Block> Block=new ArrayList<>();
    int X=300;
    int Y=0;
    for(int j=13;j>0;j--){
    for(int i=3; i>0; i--) {
        Block.add(new Block(X, Y, X + 60, Y + 60, 0));
        X += 60;
    }
    X-=180;
    Y+=60;
    }
    X=0;
    Y=300;
    for(int j=14;j>0;j--){
        for(int i=3; i>0; i--) {
            Block.add(new Block(X, Y, X + 60, Y + 60, 0));
            Y += 60;
        }
        Y-=180;
        X+=60;
    }
    for(int i=0; i<Block.size(); i++){
        Block.get(i).count=0;
        if(Block.get(i).LeftX==0 && Block.get(i).LeftY==300){
            Block.get(i).count++;
        }
        if(Block.get(i).LeftX==480 && Block.get(i).LeftY==0){
            Block.get(i).count++;
        }
        if(Block.get(i).LeftX==780 && Block.get(i).LeftY==480){
            Block.get(i).count++;
        }
        if(Block.get(i).LeftX==300 && Block.get(i).LeftY==780){
            Block.get(i).count++;
        }
    }
return Block;
    }


public static boolean Check(Dot dot, ArrayList<Block> Block,Direction d){
    int newX=0;
    int newY=0;
        if(d==Direction.UP){
            newX= dot.X;
            newY=dot.Y-60;
        }
        if(d==Direction.DOWN){
            newX= dot.X;
            newY= dot.Y+60;
        }
        if(d==Direction.LEFT){
            newX= dot.X-60;
            newY= dot.Y;
        }
    if(d==Direction.RIGHT){
        newX= dot.X+60;
        newY= dot.Y;
    }
System.out.println(dot.X+" "+ dot.Y);
for(int i=0; i<Block.size(); i++){
    if(Math.abs(Block.get(i).RightX-newX)+Math.abs(Block.get(i).LeftX-newX)==Math.abs(Block.get(i).LeftX-Block.get(i).RightX)
    && Math.abs(Block.get(i).RightY-newY)+Math.abs(Block.get(i).LeftY-newY)==Math.abs(Block.get(i).LeftY-Block.get(i).RightY) && Block.get(i).count<2 ){
        System.out.println("start"+" "+newX+" "+newY+" "+Block.get(i).count);
        Block.get(i).count++;
        for(int j=0; j<Block.size();j++){
            if(Math.abs(Block.get(i).RightX-dot.X)+Math.abs(Block.get(i).LeftX- dot.X)==Math.abs(Block.get(i).LeftX-Block.get(i).RightX)
                    && Math.abs(Block.get(i).RightY- dot.Y)+Math.abs(Block.get(i).LeftY- dot.Y)==Math.abs(Block.get(i).LeftY-Block.get(i).RightY) && Block.get(i).count<2){
                Block.get(j).count--;
                //System.out.println("Old"+" "+dot.X+" "+dot.Y+" "+Block.get(j).count);
            }
        }
        Block.get(i - 1).count--;
        System.out.println("End"+" "+Block.get(i).LeftX+" "+Block.get(i).LeftY+" "+Block.get(i).count);
        System.out.println();
        return true;

    }
}
System.out.println("False");
    return false;
}
}
