package com.company;

import java.awt.*;
import java.util.Stack;

public class Dot extends GameField{
    Color color;
    int X;
    int Y;
    Stack<Direction> stack;
    public Dot(Color color, int X, int Y, Stack<Direction> stack){
        this.color=color;
        this.X=X;
        this.Y=Y;
        this.stack=stack;
    }

}
