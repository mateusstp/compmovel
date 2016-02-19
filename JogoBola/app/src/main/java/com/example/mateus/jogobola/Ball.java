package com.example.mateus.jogobola;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

//import java.util.logging.Handler;

/**
 * Created by mateus on 10/02/16.
 */

public class Ball extends View{

    private static final String CATEGORIA = "AppNum53";

    private int xBall;
    private int yBall;
    private Drawable ball;
    private int ballWidth_x;
    private int ballHeight_y;
    private int direcao;



    public Ball(Context context) {

        super(context, null);
        ball = context.getResources().getDrawable(R.drawable.bola);

        //Recupera dimensoes da imagem
        setBallWidth_x(ball.getIntrinsicWidth());
        setBallHeight_y(ball.getIntrinsicHeight());
        setDirecao(1);

    }

    public void drawBall(Canvas canvas){
        ball.setBounds(xBall, yBall, xBall + ballWidth_x, yBall + ballHeight_y);
        ball.draw(canvas);
        Log.i("XY", "drawBall xBall : yBall " + xBall + " : " + yBall);
    }

    public void ballToString(){
        Log.i(CATEGORIA,"drawBall xBall : yBall "+xBall+ " : "+ yBall);
    }


    public void incrementX(){
        this.xBall++;
    }

    public void decrementX(){
        this.xBall--;
    }

    public void incrementY(){
        this.yBall++;
    }

    public void decrementY(){
        this.yBall--;
    }
    public int getxBall() {
        return xBall;
    }

    public void setxBall(int xBall) {
        this.xBall = xBall;
    }

    public int getyBall() {
        return yBall;
    }

    public void setyBall(int yBall) {
        this.yBall = yBall;
    }

    public int getBallWidth_x() {
        return ballWidth_x;
    }

    public void setBallWidth_x(int ballWidth_x) {
        this.ballWidth_x = ballWidth_x;
    }

    public int getBallHeight_y() {
        return ballHeight_y;
    }

    public void setBallHeight_y(int ballHeight_y) {
        this.ballHeight_y = ballHeight_y;
    }

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }
}


