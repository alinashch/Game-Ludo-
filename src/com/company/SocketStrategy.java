package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

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
    public void MoveColorDotComputer(List<Dot> RedDots, List<Dot> DotsInGame, List<Cell> Cells, int k) {
        String answer = "";
        try {
            String command = Command.BET.getCommandString()+Command.SEPARATOR;
            out.println(command);

            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.RESP.getCommandString().equals(answerParsed[0])) {
                System.out.println("Dot's number : " + answerParsed[1]);
                int wr= Integer.parseInt(answerParsed[1]);
                    Cell cellsMethod=new Cell(null,0,null,null,0,0,null, null);
                    System.out.println("Передвинули на "+k+"цвет красный");
                    cellsMethod.Move2(RedDots.get(wr - 1),  Cells,k,DotsInGame, RedDots);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+answer);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
        }
    }
    @Override
    public int  Enter(){
        String answer = "";
        int wr;
        try {
            String command = Command.ENTER.getCommandString()+Command.SEPARATOR;
            System.out.println("To client: "+command);
            out.println(command);
            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.ENTERRESP.getCommandString().equals(answerParsed[0])) {
                 wr= Integer.parseInt(answerParsed[1]);
                System.out.println("Player enter: " + answerParsed[1]);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+answer);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
        }
        return wr;
    }
    @Override
    public void ChooseFive(List<Dot> RedDots, List<Dot> DotsInGame, List<Cell> Cells, int wr){
        RulesForMove rulesForMove=new RulesForMove();
        String answer = "";
        try {
            String command = Command.CHOOSE.getCommandString()+Command.SEPARATOR;
            System.out.println("To client: "+command);
            out.println(command);
            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.CHOOSERESP.getCommandString().equals(answerParsed[0])) {
                System.out.println("Выбрано  "+answerParsed[1]);
                int k= Integer.parseInt(answerParsed[1]);
                rulesForMove.MoveOnFive(RedDots,DotsInGame,k, wr,Cells);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+answer);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
        }
    }
    @Override
    public void ChooseSix(List<Dot> RedDots, List<Dot> DotsInGame, List<Cell> Cells, int wr){
        RulesForMove rulesForMove=new RulesForMove();
        String answer = "";
        try {
            String command = Command.CHOOSE.getCommandString()+Command.SEPARATOR;
            System.out.println("To client: "+command);
            out.println(command);
            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.CHOOSERESP.getCommandString().equals(answerParsed[0])) {
                System.out.println("Выбрано  "+answerParsed[1]);
                int k= Integer.parseInt(answerParsed[1]);
                rulesForMove.MoveOnSix(RedDots,DotsInGame,k, wr,Cells);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+answer);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
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

    public int EnterNumber(){
        String answer = "";
        int wr;
        try {
            String command = Command.ENTER.getCommandString()+Command.SEPARATOR;
            System.out.println("To client: "+command);
            out.println(command);
            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.ENTERRESP.getCommandString().equals(answerParsed[0])) {
                wr= Integer.parseInt(answerParsed[1]);
                System.out.println("Player enter: " + answerParsed[1]);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+answer);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
        }
        return wr;
    }
}
