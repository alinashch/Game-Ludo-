package com.company;

import java.awt.*;

public class Graph extends GameField{
    public static void Draw(Graphics g){
        Color newColor = new Color(213, 0, 64);
        g.setColor(newColor);
        g.fillRect(300,720,60,60);
        g.fillRect(0,480,300,300);
        DrawShort(1,360,420,g);


        Color newColor1 = new Color(75, 79, 213);
        g.setColor(newColor1);
        g.fillRect(0,0,300,300);
        g.fillRect(0,300,60,60);
        DrawShort(2,60,360,g);


        Color newColor2 = new Color(129, 213, 89);
        g.setColor(newColor2);
        g.fillRect(480,0,300,300);
        g.fillRect(420,0,60,60);
        DrawShort(1,360,60,g);

        Color newColor3 = new Color(213, 210, 19);
        g.setColor(newColor3);
        g.fillRect(720,420,60,60);
        g.fillRect(480,480,300,300);
        DrawShort(2,420,360,g);

        int x=300;
        int y=0;
        g.setColor(Color.black);
        g.fillRect(360,360,60,60);
        DrawLong(1,x,y,g);
        x=0;
        y=300;
        DrawLong(2,x,y,g);

        g.setColor(Color.RED);
        g.fillOval(RedX, RedY,DOT_SIZE,DOT_SIZE);
        g.setColor(Color.blue);
        g.fillOval(BlueX,BlueY,DOT_SIZE,DOT_SIZE);
        g.setColor(Color.green);
        g.fillOval(GreenX,GreenY,DOT_SIZE,DOT_SIZE);
        g.setColor(Color.yellow);
        g.fillOval(YellowX,YellowY,DOT_SIZE,DOT_SIZE);
    }
    private static void DrawLong(int h,int x, int y,Graphics g){
        for(int i=13; i>0; i--){
            if(i!=7) {
                for(int j=3; j>0;j--) {
                    g.drawRect(x, y, 60, 60);
                    if(h==1){
                        x+=60;
                    }else{
                        y+=60;
                    }
                }
                if(h==1) {
                    x-=180;
                    y += 60;
                }else{
                    x+=60;
                    y-=180;
                }
            }else{
                if(h==1) {
                    y += 60;
                }else{
                    x+=60;
                }
            }


        }
    }
    private static void DrawShort(int h, int x, int y,Graphics g){
        for(int i=5;i>0; i--){
            g.fillRect(x, y, 60, 60);
            if(h==1) {
                y += 60;
            }else{
                x+=60;
            }
        }
    }
}
