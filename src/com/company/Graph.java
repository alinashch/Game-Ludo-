package com.company;

import java.awt.*;

public class Graph extends Move{
    public static final int DOT_SIZE = 25;

    public static void Draw(Graphics g){
        Color newColor = new Color(213, 0, 64);
        g.setColor(newColor);
        g.fillRect(300,200,50,50);
        g.fillRect(0,0,300,300);
        DrawShort(1,350,50,g);


        Color newColor1 = new Color(75, 79, 213);
        g.setColor(newColor1);
        g.fillRect(450,0,300,300);
        g.fillRect(500,300,50,50);
        DrawShort(2,400,350,g);


        Color newColor2 = new Color(129, 213, 89);
        g.setColor(newColor2);
        g.fillRect(0,450,300,300);
        g.fillRect(200,400,50,50);
        DrawShort(2,50,350,g);

        Color newColor3 = new Color(213, 210, 19);
        g.setColor(newColor3);
        g.fillRect(400,500,50,50);
        g.fillRect(450,450,300,300);
        DrawShort(1,350,400,g);

        int x=300;
        int y=0;
        g.setColor(Color.black);
        g.fillRect(350,350,50,50);
        DrawLong(1,x,y,g);
        x=0;
        y=300;
        DrawLong(2,x,y,g);

        g.setColor(Color.RED);
        for(int i=1; i<Red.DotsCoordinates.length; i++) {
            g.fillOval(Red.DotsCoordinates[i-1], Red.DotsCoordinates[i], DOT_SIZE, DOT_SIZE);
            i++;
        }
        g.setColor(Color.BLUE);
        for(int i=1; i<Blue.DotsCoordinates.length; i++) {
            g.fillOval(Blue.DotsCoordinates[i-1], Blue.DotsCoordinates[i], DOT_SIZE, DOT_SIZE);
            i++;
        }
        g.setColor(Color.GREEN);
        for(int i=1; i<Green.DotsCoordinates.length; i++) {
            g.fillOval(Green.DotsCoordinates[i-1], Green.DotsCoordinates[i ], DOT_SIZE, DOT_SIZE);
            i++;
        }
        g.setColor(Color.YELLOW);
        for(int i=1; i<Yellow.DotsCoordinates.length; i++) {
            g.fillOval(Yellow.DotsCoordinates[i-1], Yellow.DotsCoordinates[i], DOT_SIZE, DOT_SIZE);
            i++;
        }
    }

    private static void DrawLong(int h,int x, int y,Graphics g){
        for(int i=15; i>0; i--){
            if(i!=8) {
                for(int j=3; j>0;j--) {
                    g.drawRect(x, y, 50, 50);
                    if(h==1){
                        x+=50;
                    }else{
                        y+=50;
                    }
                }
                if(h==1) {
                    x-=150;
                    y += 50;
                }else{
                    x+=50;
                    y-=150;
                }
            }else{
                if(h==1) {
                    y += 50;
                }else{
                    x+=50;
                }
            }


        }
    }
    private static void DrawShort(int h, int x, int y,Graphics g){
        for(int i=6;i>0; i--){
            g.fillRect(x, y, 50, 50);
            if(h==1) {
                y += 50;
            }else{
                x+=50;
            }
        }
    }
}
