package com.company;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Play extends RulesForMove {
    public static void Play() {
        ColorDot.CreateDotsCoordinates();
        List<Dot> RedDots = Base.CreateListDot(Color.RED);
        List<Dot> BlueDots = Base.CreateListDot(Color.BLUE);
        List<Dot> GreenDots = Base.CreateListDot(Color.GREEN);
        List<Dot> YellowDots = Base.CreateListDot(Color.YELLOW);

        List<Dot> DotsInGame = new ArrayList<>();
        DotsInGame.add(RedDots.get(0));
        DotsInGame.add(BlueDots.get(0));
        DotsInGame.add(GreenDots.get(0));
        DotsInGame.add(YellowDots.get(0));
        Cell.EstablishDotsOnCells(DotsInGame);
        String str = "r";
        Scanner scanner = new Scanner(System.in);
        List<Integer> RedPath = Cell.CreateRedPath();
        List<Integer> YellowPath = Cell.CreateYellowPath();
        List<Integer> GreenPath = Cell.CreateGreenPath();
        List<Integer> BluePath = Cell.CreateBluePath();

        while (status == Status.InGAME) {
            System.out.println("Для начала хода введите r ");
            String x = scanner.nextLine();
            if (x.equals(str)) {
                RulesForMove.MoveOneColorDotRed(RedDots,RedPath,DotsInGame);
                }
                RulesForMove.MoveOneColorDot(BlueDots, BluePath, DotsInGame);
                RulesForMove.MoveOneColorDot(YellowDots, YellowPath, DotsInGame);
                RulesForMove.MoveOneColorDot(GreenDots, GreenPath, DotsInGame);

            }
        }
    }


