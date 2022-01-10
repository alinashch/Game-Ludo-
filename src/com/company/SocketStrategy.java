package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class SocketStrategy implements Strategy{
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;


    public SocketStrategy(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot connect to client", ex);
        }
    }
    @Override
    public void MoveColorDotComputer(List<Dot> RedDots, List<Dot> DotsInGame, List<Cell> Cells) {
        String answer = "";
        try {
            String command = Command.BET.getCommandString()+Command.SEPARATOR;
            System.out.println("To client: "+command);
            out.println(command);
            out.flush();
            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.RESP.getCommandString().equals(answerParsed[0])) {
                System.out.println("Dot's number : " + answerParsed[1]);
                int wr= Integer.parseInt(answerParsed[1]);
                MoveOneColorDotPlayer(RedDots,DotsInGame,Cells,wr, socket);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+answer);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
        }
    }
    public void MoveOneColorDotPlayer(List<Dot> RedDots,  List<Dot> DotsInGame, List<Cell> Cells, int wr, Socket socket) {
        RulesForMove rulesForMove=new RulesForMove();
        int k = rulesForMove.Random();
        Cell cellsMethod=new Cell(null,0,null,null,0,0,null, null);

        System.out.print("Двигаем фишку  номер" + wr+ " Цвет Красный");
        if (k == 5) {
            System.out.println("1-Передвинуть фишку на 5 , 2-Создать новую фишку");
            String answer = "";
            while (true) {
                try {
                    if (!((answer = in.readLine()) == null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("From client: "+answer);
            int q=1;
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.RESP.getCommandString().equals(answerParsed[0])) {
                q= Integer.parseInt(answerParsed[1]);
            }
            rulesForMove.MoveOnFive(RedDots,  DotsInGame, q, wr,Cells);
        } else {
            if (rulesForMove.Check(RedDots, wr) != 2) {
                cellsMethod.Move2(RedDots.get(wr - 1),  Cells,k,DotsInGame, RedDots);
                System.out.print("Передвинли на " + k + " Цвет Красный");
                System.out.println();
            }
            if (k == 6) {
                System.out.println("1-Передвинуть фишку на 7 , 2-Создать новую фишку");
                String answer = "";
                while (true) {
                    try {
                        if (!((answer = in.readLine()) == null)) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("From client: "+answer);
                int q=1;
                String[] answerParsed = answer.split(Command.SEPARATOR);
                if (Command.RESP.getCommandString().equals(answerParsed[0])) {
                    q= Integer.parseInt(answerParsed[1]);
                }
                rulesForMove.MoveOnSix(RedDots,  DotsInGame, q, wr,Cells);
            }
        }

    }
        @Override
    public void endGame() {
        try {
            String command = Command.END.getCommandString();
            System.out.println("To client:"+command);
            out.println(command);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Cannot close socket: "+e.getMessage());
            }
        }
    }
}
