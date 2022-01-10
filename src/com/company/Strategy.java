package com.company;

import java.util.List;

public interface Strategy {

     int  Enter();

    void endGame();

    void ChooseFive(List<Dot> RedDots, List<Dot> DotsInGame, List<Cell> Cells, int wr);

    void ChooseSix(List<Dot> RedDots, List<Dot> DotsInGame, List<Cell> Cells, int wr);
}
