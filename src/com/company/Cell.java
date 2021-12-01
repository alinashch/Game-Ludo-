package com.company;

import java.awt.*;
import java.util.List;

import static com.company.Coordinates.*;

public class Cell {
    Color ColorCell;
    int CellNumber;
    BlockStatus CellStatus;
    int CellX;
    int CellY;
    Dot DotOnCellFirst;
    Dot DotOnCellSecond;
    Cell NextCell;
    public Cell(Color ColorCell,int CellNumber, Cell NextCell, BlockStatus CellStatus, int CellX, int CellY,Dot DotOnCellFirst,Dot DotOnCellSecond){
        this.CellNumber=CellNumber;
        this.CellStatus=CellStatus;
        this.CellX=CellX;
        this.CellY=CellY;
        this.DotOnCellFirst= DotOnCellFirst;
        this.DotOnCellSecond=DotOnCellSecond;
        this.NextCell=NextCell;
        this.ColorCell=ColorCell;
    }
    public  List<Cell> EnterNextCell(List<Cell> Cells){
        for(int i=0; i<56; i++){
            Cells.get(i).NextCell=Cells.get(i+1);
        }
        Cells.get(55).NextCell=Cells.get(0);
        for(int i=56;i<Cells.size()-1; i++ ){
            Cells.get(i).NextCell=Cells.get(i+1);
        }
        Cells.get(Cells.size()-1).NextCell=Cells.get(Cells.size()-1);
        for(int i=0; i<Cells.size(); i++){
            if(Cells.get(i).CellNumber==100){
                Cells.get(i).NextCell=null;
            }
        }
        return Cells;
    }
    protected  void CutDownDot(Dot d, List<Dot> DotsInGame,  List<Dot> Dots, List<Cell> Cells) {
        Cell c=null;
        Base base=new Base(0,0,0,0,0,0);
        for(int i=0; i<Cells.size(); i++){
            if(Cells.get(i).CellNumber==d.CellNumber){
                c=Cells.get(i);
            }
        }
        if( c!=null && CheckCutDownDot(d,DotsInGame,c)){
            for(int i=0; i<DotsInGame.size(); i++){
                if(DotsInGame.get(i).CellNumber==c.CellNumber && DotsInGame.get(i).color!=d.color ) {
                    System.out.print("Отправили на базу точку цвет ");
                    GetColor(DotsInGame.get(i));
                    System.out.println();
                    base.SendDotOnBase(DotsInGame.get(i),Dots,DotsInGame, Cells);

                }
            }
            int x=20;
            for(int i=0; i<x; i++){
                MoveDotForCells2(d,Cells);
            }
            System.out.print("Переместили точку на 20 цвет ");
            GetColor(d);
            System.out.println();
        }
    }
    private  boolean CheckCutDownDot(Dot d, List<Dot> DotsInGame, Cell c){
        for(int i=0; i<DotsInGame.size(); i++){
            if(DotsInGame.get(i).CellNumber==c.CellNumber){
                if(DotsInGame.get(i).color!=d.color && c.CellStatus==BlockStatus.Usual){
                    return true;
                }
            }
        }
        return false;
    }


    public  void GetColor(Dot Dot){
        if(Dot.color==Color.RED){
            System.out.print("Red");
        }
        if(Dot.color==Color.BLUE){
            System.out.print("Blue");
        }
        if(Dot.color==Color.GREEN){
            System.out.print("Green");
        }
        if(Dot.color==Color.YELLOW){
            System.out.print("Yellow");
        }
    }
    public void Move2(Dot d, List<Cell> Cells, int k,List<Dot> DotsInGame,  List<Dot> Dots){
        for(int i=0; i< k; i++){
            MoveDotForCells2(d,Cells);
            // System.out.println(d.CellNumber+" "+ d.X+ " "+ d.Y);
        }
        CutDownDot(d,DotsInGame,Dots,Cells);
        for(int i=0; i<Cells.size(); i++){
           // System.out.println(Cells.get(i).CellNumber+  " " + Cells.get(i).DotOnCellFirst+" "+ Cells.get(i).DotOnCellSecond);
        }
        Coordinates coordinates=new Coordinates();
        ColorDot Cd = coordinates.FindDotToColor(d.color);
    }
    private boolean CheckToMoveToStartTrack(Dot d, List<Cell> Cells, Cell c){
        int r=c.CellNumber+100;
        for(int i=0; i<Cells.size(); i++){
            if(Cells.get(i).CellNumber==r && Cells.get(i).ColorCell==d.color ){
                return true;
            }
        }
        return false;
    }
    public void  MoveDotForCells2(Dot d, List<Cell> Cells){
        Coordinates c=new Coordinates();
        for(int i=0; i<Cells.size(); i++) {
            if (Cells.get(i).CellNumber == d.CellNumber) {
                if(Cells.get(i).NextCell==null){
                    break;
                }
                if (Cells.get(i).CellStatus == BlockStatus.StartTrack) {
                    if(CheckToMoveToStartTrack(d,Cells,Cells.get(i))){
                        int r=Cells.get(i).CellNumber+100;
                        for(int j=0; j<Cells.size(); j++){
                            if(Cells.get(j).CellNumber==r  ){
                                DeleteDotFromPreviousCell(d,Cells.get(i));
                                // EnterDotOnNextCell(d,Cells.get(j));
                                d.CellNumber= Cells.get(j).CellNumber;
                                d.X= Cells.get(j).CellX;
                                d.Y= Cells.get(j).CellY;
                                ColorDot Cd=c.FindDotToColor(d.color);
                                Cd.DotsCoordinates[d.SerialNumber - 1] =  Cells.get(j).CellX;
                                Cd.DotsCoordinates[d.SerialNumber] =  Cells.get(j).CellY;
                            }
                        }
                        break;
                    }else {
                        MoveOnNextCell(d, Cells.get(i));
                    }
                    break;
                } else if (Cells.get(i).NextCell.CellStatus == BlockStatus.Safe) {
                    if(Cells.get(i).NextCell.DotOnCellSecond == null || Cells.get(i).NextCell.DotOnCellFirst == null){
                        MoveOnNextCell(d, Cells.get(i));
                    }
                    break;
                } else if (Cells.get(i).NextCell.CellStatus == BlockStatus.Usual) {
                    if(Cells.get(i).NextCell.DotOnCellFirst!=null && Cells.get(i).NextCell.DotOnCellFirst.color!=d.color){
                        MoveOnNextCell(d, Cells.get(i));
                        MoveOnNextCell(d, Cells.get(i).NextCell);
                    }else if(Cells.get(i).NextCell.DotOnCellSecond!=null && Cells.get(i).NextCell.DotOnCellSecond.color!=d.color){
                        MoveOnNextCell(d, Cells.get(i));
                        MoveOnNextCell(d, Cells.get(i).NextCell);
                    }else {
                        MoveOnNextCell(d, Cells.get(i));
                    }
                    break;
                } else if (Cells.get(i).CellStatus == BlockStatus.HomeExit) {
                    if(Cells.get(i).NextCell.DotOnCellSecond == null || Cells.get(i).NextCell.DotOnCellFirst == null){
                        MoveOnNextCell(d, Cells.get(i));
                    }
                    break;
                }else if(Cells.get(i).NextCell.CellStatus == BlockStatus.Finish){
                    MoveOnNextCell(d,Cells.get(i));
                    Coordinates coordinates=new Coordinates();
                    ColorDot Cd = coordinates.FindDotToColor(d.color);
                    Cd.DotsInGame--;
                  //  System.out.println( Cd.DotsOnFinish);

                    if(d.color==Color.RED)
                    {
                        RedBase.DotsOnFinish++;

                        System.out.println("Красная фишка на финише");
                        if(RedBase.DotsOnFinish==4){
                            status=Status.EndGame;
                            System.out.println("игра окончена. Победил Красный");

                        }

                    }
                    if(d.color==Color.BLUE)
                    {
                        BlueBase.DotsOnFinish++;
                        System.out.println("Синяя фишка на финише");
                        if(BlueBase.DotsOnFinish==4){
                            status=Status.EndGame;
                            System.out.println("игра окончена. Победил Синий");
                        }
                    }
                    if(d.color==Color.GREEN)
                    {
                        GreenBase.DotsOnFinish++;
                        System.out.println("Зеленый фишка на финише");
                        if(GreenBase.DotsOnFinish==4){
                            status=Status.EndGame;
                            System.out.println("игра окончена. Победил Зеленый");

                        }
                    }
                    if(d.color==Color.YELLOW)
                    {
                        YellowBase.DotsOnFinish++;
                        System.out.println("Желтый фишка на финише");
                        if(YellowBase.DotsOnFinish==4){
                            status=Status.EndGame;
                            System.out.println("игра окончена. Победил Желтый");

                        }
                    }


                    Cells.get(i).NextCell.DotOnCellFirst=null;

                    break;
                }else {
                    MoveOnNextCell(d, Cells.get(i));
                    break;
                }
            }
        }
    }
    private void DeleteDotFromPreviousCell(Dot d, Cell c){
        if (c.DotOnCellFirst == d) {
            c.DotOnCellFirst = null;
        } else if (c.DotOnCellSecond == d) {
            c.DotOnCellSecond = null;
        }
    }
    private void EnterDotOnNextCell(Dot d, Cell c){
        if (c.NextCell.DotOnCellFirst == null) {
            c.NextCell.DotOnCellFirst = d;
        } else if (c.NextCell.DotOnCellSecond == null) {
            c.NextCell.DotOnCellSecond = d;
        }
    }

    private void MoveOnNextCell(Dot d, Cell c){
        Coordinates cc=new Coordinates();
        if(CheckNextCell(c)) {
            DeleteDotFromPreviousCell(d,c);
            EnterDotOnNextCell(d,c);
            d.CellNumber = c.NextCell.CellNumber;
            d.X = c.NextCell.CellX;
            d.Y = c.NextCell.CellY;
            ColorDot Cd =cc.FindDotToColor(d.color);
            Cd.DotsCoordinates[d.SerialNumber - 1] = c.NextCell.CellX;
            Cd.DotsCoordinates[d.SerialNumber] = c.NextCell.CellY;
        }
    }

    private boolean CheckNextCell( Cell c){
        if(c.NextCell.DotOnCellSecond==null || c.NextCell.DotOnCellFirst==null){
            return true;
        }
        return false;
    }


    public  void DeleteBlock(Dot d, List<Cell> Cells){
        if(CheckDeleteBlock(d,Cells)!=null) {
            Dot dot = CheckDeleteBlock(d,Cells);
            int x=6;
            for(int i=0; i<x; i++) {
                MoveDotForCells2(dot,Cells);
            }
            System.out.print("Передвинута фишка в блоке цвет ");
            GetColor(d);
            System.out.println();
        }
    }

    private  Dot CheckDeleteBlock(Dot d, List<Cell> Cells){
        Dot [] arr=new Dot[4];
        int k=0;
        for(int i=0; i<Cells.size(); i++) {
            if (Cells.get(i).DotOnCellSecond != null && Cells.get(i).DotOnCellFirst != null) {
                if (Cells.get(i).DotOnCellFirst.color == Cells.get(i).DotOnCellSecond.color && Cells.get(i).DotOnCellSecond.color == d.color) {
                    arr[k] = Cells.get(i).DotOnCellSecond;
                    k++;
                    arr[k] = Cells.get(i).DotOnCellFirst;
                    k++;
                }
            }
        }
        for(int i=0; i<arr.length; i++){
            if( arr[i]!=d){
                return arr[i];
            }
        }
        return null;
    }

}
