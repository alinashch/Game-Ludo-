package com.company;

import java.util.List;

public interface Strategy {

    default void MoveColorDotComputer(List<Dot> RedDots, List<Dot> DotsInGame, List<Cell> Cells){

    }

    void endGame();
}
