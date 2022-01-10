package com.company;

import java.awt.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.company.Coordinates.*;

public class GameSession implements Runnable {
    private final Play game;

    public GameSession(Socket socket) {
        game = new Play();

        Player p1=new Player(new SocketStrategy(socket),1,1);
        Player p2=new Player(new RulesForMove(),2,0);
        Player p3=new Player(new RulesForMove(),2,0);
        Player p4=new Player(new RulesForMove(),2,0);
        game.PlayApp(p1, p2,p3,p4);

        while (game.GameOver()) {
            game.MakeStep(socket);
        }
    }

    public void run() {
        while (game.GameOver()) {
          game.MakeStep();
        }
        System.out.println("Game Over!");
    }
}
