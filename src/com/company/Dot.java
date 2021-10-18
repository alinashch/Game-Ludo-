package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Dot{
    Color color;
    int X;
    int Y;
    int SerialNumber;
    int CellNumber;
    Status status;
    public Dot(Color color, int X, int Y,int SerialNumber, int CellNumber,Status status){
        this.color=color;
        this.X=X;
        this.Y=Y;
        this.SerialNumber=SerialNumber;
        this.CellNumber=CellNumber;
        this.status=status;
    }
    public static class DotBuilder{
        private Color color;
        private int X;
        private int Y;
        private   int SerialNumber;
        private int CellNumber;
        private Status status;
        public DotBuilder withColor(Color color){
            this.color=color;
            return this;
        }
        public DotBuilder withX(int X){
            this.X=X;
            return this;
        }
        public DotBuilder withY(int Y){
            this.Y=Y;
            return this;
        }
        public DotBuilder withSerialNumber(int SerialNumber){
            this.SerialNumber=SerialNumber;
            return this;
        }
        public  DotBuilder withCellNumber(int CellNumber) {
        this.CellNumber=CellNumber;
        return this;
        }
        public Dot build(){
            return new Dot(this.color, this.X, this.Y,this.SerialNumber,this.CellNumber, Status.InGAME);
        }

    }

}
