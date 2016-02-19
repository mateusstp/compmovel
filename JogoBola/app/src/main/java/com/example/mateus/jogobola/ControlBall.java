package com.example.mateus.jogobola;

import android.os.Message;
//import java.util.logging.Handler;
import android.os.Handler;
import android.util.Log;


/**
 * Created by mateus on 10/02/16.
 */

public class ControlBall extends Thread {

    private static final String CATEGORIA = "AppNum53";

    //private int xBall;
    //private int yBall;
    Ball ball;
    private int screenWidth_x, screenHeight_y;
    //private int ballWidth_x, ballHeight_y;
    float m = (float)Math.tan(Math.PI/3);

    private Handler handler;

    private int runBall;

    public ControlBall(Handler h, int screenWidth_x, int screenHeight_y, Ball ball) {

        this.handler = h;
        runBall =1;

        this.screenWidth_x  = screenWidth_x;
        this.screenHeight_y = screenHeight_y;
        this.ball=ball;
        this.ball.setY(200);
        //this.ball.set(200);
    }


    public void run() {
        for (; ; ) {
            try {
                Message message = new Message();
                //defino um codigo para controle.
                message.what = 1;
                //aqui posso passar qualquer objeto.
                // No caso estou passando uma
                // String
                //message.obj = runBall;


                //simula processamento de 1seg
                Thread.sleep(25);
               /* int boundBelowX  = ball.getxBall() - ball.getBallWidth_x() / 2;
                int boundHigherX = ball.getxBall() + ball.getBallWidth_x()-7;
                Log.i(CATEGORIA,"boundBelowX : boundHigherX | +screenWidth_x -> " + boundBelowX +" : "+boundHigherX +" | "+screenWidth_x);
                if ((ball.getxBall() - ball.getBallWidth_x() / 2) > 0 & ball.getxBall() + ball.getBallWidth_x()-7 < screenWidth_x) {
                    // xC = ((int) xC - (larguraImgCirculo / 2)) + 1;
                    // this.y = (int) y - (alturaImgQuadrado / 2);
                    //yC = ((int) yC + alturaImgCirculo / 2) + 1;
                    ball.incrementX();

                    // yC++;
                } /*else {
                    ball.decrementX();
                }*/
                horizontalDisplacement();
                ball.ballToString();
                handler.sendMessage(message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Note que mudei o código de controle.
        // O controle dessa mensagem será diferente.
    }

    public void horizontalDisplacement(){
        int boundBelowX  = ball.getxBall() - ball.getBallWidth_x()+7;
        int boundHigherX = ball.getxBall() + ball.getBallWidth_x()-7;
        Log.i(CATEGORIA,"Displacement boundBelowX : boundHigherX | screenWidth_x -> " + boundBelowX +" : "+boundHigherX +" | "+screenWidth_x);
        if (boundBelowX > 0 & boundHigherX  < screenWidth_x) {
            /*if (ball.getxBall()+1 > ball.getxBall() ){
                ball.incrementX();
                return;
            }
            ball.decrementX();*/
            ball.incrementX();
            ball.decrementY();
        //    ball.setY(m*ball.getX());
        }
    }

}


