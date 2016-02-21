package com.example.mateus.gameball;

import android.os.Message;
//import java.util.logging.Handler;
import android.os.Handler;
import android.util.Log;


/**
 * Created by mateus on 10/02/16.
 */

public class ControlGamePong extends Thread {

    private static final String CATEGORIA = "AppNum53";

    Ball ball;
    Rectangle rectangle;
    private int screenWidth_x, screenHeight_y;

    private int boundtop;
    private int bounddown;
    private int boundleft;
    private int boundright;

    private Handler handler;

    public ControlGamePong(Handler h, int screenWidth_x, int screenHeight_y, Ball ball, Rectangle r) {

        this.handler = h;

        this.screenWidth_x = screenWidth_x;
        this.screenHeight_y = screenHeight_y;

        this.ball = ball;
        this.rectangle=r;

        boundtop = 0;
        bounddown = screenHeight_y;
        boundleft = 0;
        boundright = screenWidth_x;
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
                Thread.sleep(3);
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
                //trajectoryBall();
                checkBoundAndDefineDirection();
                ball.move();
             //   ball.ballToString();
                handler.sendMessage(message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Note que mudei o código de controle.
        // O controle dessa mensagem será diferente.
    }

    public void trajectoryBall() {
        int boundBelowX = ball.getxBall() - ball.getBallWidth_x() + 7;
        int boundHigherX = ball.getxBall() + ball.getBallWidth_x() - 7;
     //   Log.i(CATEGORIA, "Displacement boundBelowX : boundHigherX | screenWidth_x -> " + boundBelowX + " : " + boundHigherX + " | " + screenWidth_x);
        if (boundBelowX > 0 & boundHigherX < screenWidth_x) {
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

    public void checkBoundAndDefineDirection() {

        int boundX;
        int boundY;



        if (collision()){
       //     Log.i("COLI", "TRUE: xQ:yQ | xB:yB = " + rectangle.getxRec() + ":" + rectangle.getyRec()+" | "+ ball.getxBall() + ":" + ball.getyBall());

            ball.setdirection(-1*ball.getdirectionX(), -1*ball.getdirectionY());
            /*if (ball.getdirectionX() == -1 && ball.getdirectionY() == 1) {

                if (rectangle.getdirection()==1) {
                ball.setdirection(-1, 1);
                } else  {
                ball.setdirection(-1, -1);
                }
            } else if (ball.getdirectionX() == 1 && ball.getdirectionY() == 1) {
                  if (rectangle.getdirection()==0 || rectangle.getdirection()==1) {
                ball.setdirection(1, -1);
                 } else  {
                  ball.setdirection(-1, -1);
                }
            }*/
            //ball.move();
        }



        if (ball.getdirectionX() == 1) {
              boundX = ball.getxBall() + ball.getBallWidth_x() -14;
          //  boundX = ball.getxBall() + 7;
        } else {
            //boundX  = ball.getxBall() + ball.getBallWidth_x() +7;
            boundX  = ball.getxBall()+14;
            //boundX = ball.getxBall() - 7;
        }

        if (ball.getdirectionY() == 1) {
            boundY = ball.getyBall() + ball.getBallHeight_y()-14;
            //boundY = ball.getyBall() + 7;
        } else {
            //boundY = ball.getyBall() - ball.getBallHeight_y() + 7;
            boundY = ball.getyBall()+14;
        }
        if(boundX<0){
            boundX=0;
        }

        if(boundY<0){
            boundY=0;
        }


            if (boundX >= boundright) {

                if (ball.getdirectionX() == 1 && ball.getdirectionY() == -1) {
                    ball.setdirection(-1, -1);
                } else {
                    ball.setdirection(-1, 1);
                }
            } else if (boundY <= boundtop) {
                if (ball.getdirectionX() == 1 && ball.getdirectionY() == -1) {
                    ball.setdirection(1, 1);
                } else {
                    ball.setdirection(-1, 1);
                }
            } else if (boundX <= boundleft) {
                if (ball.getdirectionX() == -1 && ball.getdirectionY() == -1) {
                    ball.setdirection(1, -1);
                } else {
                    ball.setdirection(1, 1);
                }
            } else if (boundY >= bounddown) {
                if (ball.getdirectionX() == 1 && ball.getdirectionY() == 1) {
                    ball.setdirection(1, -1);
                } else {
                    ball.setdirection(-1, -1);
                }

            }

        //Log.i("TRAJ", "top : down : reght: left-> " +boundtop + ":" + bounddown + " | " + boundright + ":" + boundleft);
        //Log.i("TRAJ", "bX:bY | dx:dy | t:d | r:l-> \" ->  " + boundX + ":" + boundY + " | " + ball.getdirectionX()+ " : " + ball.getdirectionY()+ " | " +boundtop + ":" + bounddown + " | " + boundright + ":" + boundleft);
    }

    private boolean collision(){
        boolean boundCollisionY = ball.getyBall()-14 == (int) (screenHeight_y-rectangle.getRecHeight_y()*1.8);
        //int boundBelowCollisionX = rectangle.getxRec() +rectangle.getRecWidth_x()/2 ;
        //int boundHigherCollisionX = rectangle.getxRec() +rectangle.getRecWidth_x()/2 ;
        //int bCY = ball.getyBall() == (int) (screenHeight_y-rectangle.getRecHeight_y()*1.8);
        int bBCX = rectangle.getxRec() +rectangle.getRecWidth_x()/2 ;
        int bHCX = rectangle.getxRec() +rectangle.getRecWidth_x()/2 ;

        if(boundCollisionY){
             Log.i("COLIY", "TRUE: yQ|yB = " +rectangle.getyRec()+"|"+ ball.getxBall());
            boolean boundHigherCollisionX = ball.getxBall() + ball.getBallWidth_x()-14 <= rectangle.getxRec()+rectangle.getRecWidth_x()*1.2;
            boolean boundBelowCollisionX  = ball.getxBall() - ball.getBallWidth_x()+14 >= rectangle.getxRec()-rectangle.getRecWidth_x()*0.7;
            if(boundHigherCollisionX && boundBelowCollisionX  ){
             //   Log.i("COLIX", "TRUE: xQ|xB = " +rectangle.getxRec()+"|"+ ball.getxBall());
                    return  true;
            }
          //  return true;
        }
        return false;
    }
}


