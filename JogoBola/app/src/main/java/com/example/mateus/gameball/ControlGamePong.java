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
        this.rectangle = r;

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
                //delay
                Thread.sleep(3);
                collisionBallRectangle();
                collisionWall();
                ball.move();
                handler.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void collisionWall() {
        /**calc bounds x and y **/
        int boundX;
        int boundY;
        if (ball.getdirectionX() == 1) {
            boundX = ball.getxBall() + ball.getBallWidth_x() - 14;
        } else {
            boundX = ball.getxBall() + 14;
        }

        if (ball.getdirectionY() == 1) {
            boundY = ball.getyBall() + ball.getBallHeight_y() - 14;
        } else {
            boundY = ball.getyBall() + 14;
        }


        /**verify collision wall**/
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


    }


    private void collisionBallRectangle() {

        //boolean  colisao= rec.getRectangle().copyBounds().contains((int) x, (int) y);
        boolean boundCollisionY = ball.getyBall() - 14 == (int) (screenHeight_y - rectangle.getRecHeight_y() * 1.8);

        if (boundCollisionY) {

            boolean boundHigherCollisionX = ball.getxBall() + ball.getBallWidth_x() - 14 <= rectangle.getxRec() + rectangle.getRecWidth_x() * 1.2;
            boolean boundBelowCollisionX = ball.getxBall() - ball.getBallWidth_x() + 14 >= rectangle.getxRec() - rectangle.getRecWidth_x() * 0.7;

            if (boundHigherCollisionX && boundBelowCollisionX) {
                if (rectangle.getdirection() == 0) {
                    ball.setdirection(ball.getdirectionX(), -1 * ball.getdirectionY());
                } else if (rectangle.getdirection() == 1) {
                    ball.setdirection(1, -1);
                } else if (rectangle.getdirection() == -1) {
                    ball.setdirection(-1, -1);
                }
            }
        }
    }
}


