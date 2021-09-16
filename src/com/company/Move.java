package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Move extends GameField {
    public static void Move(Dot dot, int i, ArrayList<Block> block){
            for (int k = i; k > 0; k--) {
                Direction d = dot.stack.pop();
                if(Block.Check(dot,block,d)==true) {
                    MoveOneStep(d, dot);
                }else{
                    break;
                }
            }
            if (dot.stack.empty()) {
                status = Status.WIN;
            }
    }
    public static Stack<Direction> CreateStackGreen(){
        Stack<Direction> stack=new Stack<>();
        stack.push(Direction.DOWN);
        AddDirection(stack,Direction.DOWN);
        stack.push(Direction.RIGHT);
        AddDirection(stack,Direction.UP);
        AddDirection(stack,Direction.RIGHT);
        AddSmallDirection(stack,Direction.UP);
        AddDirection(stack,Direction.LEFT);
        AddDirection(stack,Direction.UP);
        AddSmallDirection(stack,Direction.LEFT);
        AddDirection(stack,Direction.DOWN);
        AddDirection(stack,Direction.LEFT);
        AddSmallDirection(stack,Direction.DOWN);
        AddDirection(stack,Direction.RIGHT);
        AddDirection(stack,Direction.DOWN);
        return stack;
    }
    public static Stack<Direction> CreateStackRed(){
        Stack<Direction> stack=new Stack<>();
        stack.push(Direction.UP);
        AddDirection(stack,Direction.UP);
        stack.push(Direction.LEFT);
        AddDirection(stack,Direction.DOWN);
        AddDirection(stack,Direction.LEFT);
        AddSmallDirection(stack,Direction.DOWN);
        AddDirection(stack,Direction.RIGHT);
        AddDirection(stack,Direction.DOWN);
        AddSmallDirection(stack,Direction.RIGHT);
        AddDirection(stack,Direction.UP);
        AddDirection(stack,Direction.RIGHT);
        AddSmallDirection(stack,Direction.UP);
        AddDirection(stack,Direction.LEFT);
        AddDirection(stack,Direction.UP);
        return stack;
    }
    public static Stack<Direction> CreateStackBlue(){
        Stack<Direction> stack=new Stack<>();
        stack.push(Direction.RIGHT);
        AddDirection(stack,Direction.RIGHT);
        stack.push(Direction.UP);
        AddDirection(stack,Direction.LEFT);
        AddDirection(stack,Direction.UP);
        AddSmallDirection(stack,Direction.LEFT);
        AddDirection(stack,Direction.DOWN);
        AddDirection(stack,Direction.LEFT);
        AddSmallDirection(stack,Direction.DOWN);
        AddDirection(stack,Direction.RIGHT);
        AddDirection(stack,Direction.DOWN);
        AddSmallDirection(stack,Direction.RIGHT);
        AddDirection(stack,Direction.UP);
        AddDirection(stack,Direction.RIGHT);
        return stack;
    }


    private static void MoveOneStep(Direction d, Dot dot){

        if(d==Direction.UP){
            Move.MoveUp(dot);
        }
        if(d==Direction.DOWN){
            Move.MoveDown(dot);
        }
        if(d==Direction.LEFT){
            Move.MoveLeft(dot);
        }
        if(d==Direction.RIGHT){
            Move.MoveRight(dot);
        }
    }


    private static void MoveUp(Dot dot){
        if(dot.color==Color.RED){
            RedY-=60;
            dot.Y-=60;
        }
        if(dot.color==Color.GREEN){
            GreenY-=60;
            dot.Y-=60;
        }
        if(dot.color==Color.BLUE){
            BlueY-=60;
            dot.Y-=60;
        }
        if(dot.color==Color.YELLOW){
            YellowY-=60;
            dot.Y-=60;
        }
    }
    private static void MoveDown(Dot dot){
        if(dot.color==Color.RED){
            RedY+=60;
            dot.Y+=60;
        }
        if(dot.color==Color.GREEN){
            GreenY+=60;
            dot.Y+=60;
        }
        if(dot.color==Color.BLUE){
            BlueY+=60;
            dot.Y+=60;
        }
        if(dot.color==Color.YELLOW){
            YellowY+=60;
            dot.Y+=60;
        }
    }
    private static void MoveLeft(Dot dot){
        if(dot.color==Color.RED){
            RedX-=60;
            dot.X-=60;
        }
        if(dot.color==Color.GREEN){
            GreenX-=60;
            dot.X-=60;
        }
        if(dot.color==Color.BLUE){
            BlueX-=60;
            dot.X-=60;
        }
        if(dot.color== Color.YELLOW){
            YellowX-=60;
            dot.X-=60;
        }
    }
    private static void MoveRight(Dot dot){
        if(dot.color==Color.RED){
            RedX+=60;
            dot.X+=60;
        }
        if(dot.color==Color.GREEN){
            GreenX+=60;
            dot.X+=60;
        }
        if(dot.color==Color.BLUE){
            BlueX+=60;
            dot.X+=60;
        }
        if(dot.color==Color.YELLOW){
            YellowX+=60;
            dot.X+=60;
        }
    }
    private static Stack AddDirection(Stack stack, Direction d){
        for(int i=5; i>0; i--) {
            stack.push(d);
        }
        return stack;
    }
    private static Stack AddSmallDirection(Stack stack, Direction d){
        for(int i=2; i>0; i--) {
            stack.add(d);
        }
        return stack;
    }

}
