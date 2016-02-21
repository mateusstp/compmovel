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

public class Rectangle extends View {
    private static final String CATEGORIA = "AppNum53";

    private int xRec;
    private int yRec;
    private Drawable rectangle;
    private int recWidth_x;
    private int recHeight_y;
    /**-1 -> left
     * 0  -> stoped
     * 1  -> right **/
    private int direction;



    public Rectangle(Context context) {
        super(context, null);

        setRectangle(context.getResources().getDrawable(R.drawable.quadrado));

        //Recupera dimensoes da imagem
        setRecWidth_x(getRectangle().getIntrinsicWidth() + 150);
        setRecHeight_y(getRectangle().getIntrinsicHeight());
        setdirection(1);
    }

    public static String getCATEGORIA() {
        return CATEGORIA;
    }

    public void drawRectangle(Canvas canvas){
        getRectangle().setBounds(getxRec(), getyRec(), getxRec() + getRecWidth_x(), getyRec() + getRecHeight_y());
        getRectangle().draw(canvas);
    }

    public void rectangleToString(){
        Log.i(getCATEGORIA(), "drawBall xRec : yRec " + getxRec() + " : " + getyRec());
    }


    public int getxRec() {   return xRec;   }

    public void setxRec(int xRec) {
        this.xRec = xRec;
    }

    public int getyRec() {
        return yRec;
    }

    public void setyRec(int yRec) {  this.yRec = yRec;  }


    /**-1 -> left
     * 0  -> stoped
     * 1  -> right **/
    public void setdirection(int d ) {
        if(d != 1 && d != -1 ){
            this.direction=0;
         }
        this.direction=d;
    }

    public int getdirection() {return  this.direction; }


    public Drawable getRectangle() {
        return rectangle;
    }

    public void setRectangle(Drawable rectangle) {
        this.rectangle = rectangle;
    }

    public void setRecWidth_x(int recWidth_x) {
        this.recWidth_x = recWidth_x;
    }

    public int getRecWidth_x() { return recWidth_x;}


    public int getRecHeight_y() { return recHeight_y;    }

    public void setRecHeight_y(int recHeight_y) { this.recHeight_y = recHeight_y;}


}


