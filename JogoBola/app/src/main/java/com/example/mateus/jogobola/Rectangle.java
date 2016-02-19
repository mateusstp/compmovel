package com.example.mateus.jogobola;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mateus on 10/02/16.
 */

public class Rectangle extends View {
    private static final String CATEGORIA = "AppNum53";
    private Drawable imgQuadrado;
    private int xQ;
    private int yQ;
    private boolean selecionou;
    private int larguraTela,
            alturaTela,
            larguraImgQuadrado,
            alturaImgQuadrado;

    public Rectangle(Context context) {
        super(context, null);

        imgQuadrado = context.getResources().getDrawable(R.drawable.quadrado);

        //Recupera dimensoes da imagem
        larguraImgQuadrado = imgQuadrado.getIntrinsicWidth() + 150;
        alturaImgQuadrado = imgQuadrado.getIntrinsicHeight();
        setFocusable(true);

    }

    @Override
//Callback para quando a tela é iniciada ou redimensionada
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        this.alturaTela = height;
        this.larguraTela = width;
        Log.i(CATEGORIA, "onSizeChanged  largura :altura tela = " + larguraTela + ":" +alturaTela);
        Log.i(CATEGORIA, "onSizeChanged xQ:yQ = " + getxQ() + ":" + getyQ());

        setxQ(width / 2 - (larguraImgQuadrado / 2));
        setyQ(height - alturaImgQuadrado);

    }

    /**
     * desenha objetos nas posiçoes inicais
     **/
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        imgQuadrado.setBounds(getxQ(), getyQ(), getxQ() + larguraImgQuadrado, getyQ() + alturaImgQuadrado);
        imgQuadrado.draw(canvas);
        Log.i(CATEGORIA, "onDraw xQ:yQ = " + getxQ() + ":" + getyQ());
    }


    @Override
//Move a imagem
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Inicia movimento se pressionou a imagem
                selecionou = imgQuadrado.copyBounds().contains((int) x, (int) y);
                break;
            case MotionEvent.ACTION_MOVE:
                //Arrasta o boneco
                if (selecionou) {
                    //
                    if (x - larguraImgQuadrado / 2 > 0 & x + larguraImgQuadrado / 2 < larguraTela) {
                        this.setxQ((int) x - (larguraImgQuadrado / 2));
                        // this.y = (int) y - (alturaImgQuadrado / 2);
                        this.setyQ((int) (alturaTela - alturaImgQuadrado / 2) - (alturaImgQuadrado / 2));
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //Finaliza movimento
                selecionou = false;
                break;
        }
       // invalidate();
        return true;
    }





    public int getxQ() {
        return xQ;
    }

    public void setxQ(int xQ) {
        this.xQ = xQ;
    }

    public int getyQ() {
        return yQ;
    }

    public void setyQ(int yQ) {
        this.yQ = yQ;
    }
}


