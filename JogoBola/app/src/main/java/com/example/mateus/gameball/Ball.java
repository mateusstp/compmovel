package com.example.mateus.gameball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.example.mateus.jogobola.R;



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
    /**{1,1}   -> +x,+y
     * {-1,-1} -> -x,-y
     * {-1,1}  -> -x,+y
     * {1,-1}  -> +x,-y**/
    private int[] direction;



    public Ball(Context context) {

        super(context, null);
        setBall(context.getResources().getDrawable(R.drawable.bola));

        //Recupera dimensoes da imagem
        setBallWidth_x(getBall().getIntrinsicWidth());
        setBallHeight_y(getBall().getIntrinsicHeight());
        direction=new int[2];
        direction[0] =  1;
        direction[1] = -1;
    }

    public void drawBall(Canvas canvas){
        getBall().setBounds(xBall, yBall, xBall + ballWidth_x, yBall + ballHeight_y);
        getBall().draw(canvas);
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

    public int getdirectionX() {
        return direction[0];
    }
    public int getdirectionY() {
        return direction[1];
    }
    /**{1,1}   -> +x,+y
     * {-1,-1} -> -x,-y
     * {-1,1}  -> -x,+y
     * {1,-1}  -> +x,-y**/
    public void setdirection(int xUnitario,int yUnitario ) {
        if(xUnitario != 1 && xUnitario != -1 || yUnitario != 1 && yUnitario != -1 ){
            return;
        }
        this.direction[0] = xUnitario;
        this.direction[1] = yUnitario;
    }

    public void move(){
        if(direction[0]==1 && direction[1]==1){
            incrementX();
            incrementY();
        }else if(direction[0]==-1 && direction[1]==-1){
            decrementX();
            decrementY();
        }else if(direction[0]==-1 && direction[1]==1){
            decrementX();
            incrementY();
        }else if(direction[0]==1 && direction[1]==-1){
            incrementX();
            decrementY();
        }
    }

    public Drawable getBall() {
        return ball;
    }

    public void setBall(Drawable ball) {
        this.ball = ball;
    }
}


