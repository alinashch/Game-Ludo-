package com.company;

public enum Directions {
    Left,
    Right,
    Up,
    Down;
    public  Directions GetOppositeDirection(Directions d){
        Directions b=Directions.Down;
        if(d==Directions.Down){
            b=Directions.Up;
        }
        if(d==Directions.Left){
            b=Directions.Right;
        }
        if(d==Directions.Right) {
            b=Directions.Left;
        }
        if(d==Directions.Up){
            b=Directions.Down;
        }
        return b;
    }
}
