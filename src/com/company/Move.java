package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Move extends Coordinates {
    public static Status status = Status.InGAME;
    public static final int OneStep = 50;

    public static int CanMoveOnNextCell(List<Dot> Dots, Cell c, Dot d, List<Integer> List) {
        for (int i = 0; i < Dots.size(); i++) {
            if (c.DotOnCellFirst == null || c.DotOnCellSecond == null) {
                if (c.CellStatus == BlockStatus.Usual) {
                    if (c.DotOnCellFirst == null) {
                        if (c.DotOnCellSecond == null) {
                            return 1;
                        } else {
                            if (Dots.get(i).CellNumber == c.CellNumber && c.DotOnCellSecond.color == d.color) {
                                return 1;
                            } else if (Dots.get(i).CellNumber == c.CellNumber && c.DotOnCellSecond.color != d.color) {
                                return 2;
                            }
                        }
                    } else if (c.DotOnCellSecond == null) {
                        if (c.DotOnCellFirst == null) {
                            return 1;
                        } else {
                            if (Dots.get(i).CellNumber == c.CellNumber && c.DotOnCellFirst.color == d.color) {
                                return 1;
                            } else if (Dots.get(i).CellNumber == c.CellNumber && c.DotOnCellFirst.color != d.color) {
                                return 1;
                            }
                        }
                    }
                } else if (c.CellStatus == BlockStatus.HomeExit) {
                    if (c.DotOnCellFirst == null) {
                        if (c.DotOnCellSecond == null) {
                            return 1;
                        } else {
                            if (Dots.get(i).CellNumber == c.CellNumber && c.DotOnCellSecond.color == d.color) {
                                return 1;

                            } else if (Dots.get(i).CellNumber == c.CellNumber && c.DotOnCellSecond.color != d.color) {
                                return 1;
                            }
                        }
                    } else if (c.DotOnCellSecond == null) {
                        if (c.DotOnCellFirst == null) {
                            return 1;
                        } else {
                            if (Dots.get(i).CellNumber == c.CellNumber && c.DotOnCellFirst.color == d.color) {
                                // System.out.println("21");
                                return 1;

                            } else if (Dots.get(i).CellNumber == c.CellNumber && c.DotOnCellFirst.color != d.color) {
                                // System.out.println("22");
                                return 1;
                            }
                        }
                    }
                } else if (c.CellStatus == BlockStatus.Safe) {
                    return 1;
                } else if (c.CellStatus == BlockStatus.Finish) {
                    ColorDot Cd = Coordinates.FindDotToColor(d.color);
                    Cd.DotsInGame--;
                    Cd.DotsOnFinish++;
                    c.DotOnCellSecond = null;
                    c.DotOnCellFirst = null;
                    for (int k = 0; k < Dots.size(); k++) {
                        if (Dots.get(k).color == Cd.Color) {
                            int x = 20;
                            for (int l = 0; l < x; l++) {
                                x = Cell.MoveDotForCells(d, 1, List, Dots, x);
                            }
                        }
                    }
                    if (Cd.DotsOnFinish == 4) {
                        status = Status.EndGame;
                        System.out.println("All dots on finish color ");
                        Cell.GetColor(d);
                        System.out.println();
                    }
                    return 1;
                }
            } else {
                //System.out.println("00000");
                return 0;
            }
        }
        return 1;
    }
}


