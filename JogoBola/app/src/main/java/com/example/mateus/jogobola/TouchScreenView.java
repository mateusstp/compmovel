package com.example.mateus.jogobola;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

//import java.util.logging.Handler;
import android.os.Handler;

/**
 * Created by mateus on 10/02/16.
 */

public class TouchScreenView extends View {

    private static final String CATEGORIA = "AppNum53";

    private Drawable imgCirculo;
    private Drawable imgQuadrado;

    int xQ, yQ;
    int xC, yC;

    private boolean selecionou;
    private int larguraTela,
            alturaTela,
            larguraImgCirculo,
            alturaImgCirculo,
            larguraImgQuadrado,
            alturaImgQuadrado;

    private Handler handler;

    public TouchScreenView(Context context) {
        super(context, null);


        imgCirculo = context.getResources().getDrawable(R.drawable.bola);
        imgQuadrado = context.getResources().getDrawable(R.drawable.quadrado);

        //Recupera dimensoes da imagem
        larguraImgCirculo = imgCirculo.getIntrinsicWidth();
        alturaImgCirculo = imgCirculo.getIntrinsicHeight();

        larguraImgQuadrado = imgQuadrado.getIntrinsicWidth() + 150;
        alturaImgQuadrado = imgQuadrado.getIntrinsicHeight();


        //  movinentaBola();
        setFocusable(true);
    }

    @Override
//Callback para quando a tela é iniciada ou redimensionada
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        this.alturaTela = height;
        this.larguraTela = width;
       // Log.i(CATEGORIA, "onSizeChanged  largura :altura tela = " + larguraTela + ":" + alturaTela);
        //Log.i(CATEGORIA, "onSizeChanged xQ:yQ = " + xQ + ":" + yQ);

        xQ = width / 2 - (larguraImgQuadrado / 2);
        yQ = height - alturaImgQuadrado;

        xC = width / 2 - (larguraImgCirculo / 2);
        yC = (height + height / 3) - (alturaImgCirculo / 2);
        //Log.i(CATEGORIA, "onSizeChanged xC:yC = " + xC + ":" + yC);
    }

    /**
     * desenha objetos nas posiçoes inicais
     **/
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint pincel = new Paint();
        //pincel.setColor(Color.WHITE);
        //canvas.drawRect(0, 0, larguraTela,alturaTela, pincel);
        //Definição da área passível de desenho

        imgQuadrado.setBounds(xQ, yQ, xQ + larguraImgQuadrado, yQ + alturaImgQuadrado);
        imgQuadrado.draw(canvas);
        Log.i(CATEGORIA, "onDraw xQ:yQ = " + xQ + ":" + yQ);

        imgCirculo.setBounds(xC, yC, xC + larguraImgCirculo, yC + alturaImgCirculo);
        imgCirculo.draw(canvas);
        Log.i(CATEGORIA, "onDraw xC:yC = " + xC + ":" + yC);
    }


    @Override
//Move a imagem
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.i(CATEGORIA, "onTouchEvent largura :altura tela = " + larguraTela + ":" + alturaTela);
        Log.i(CATEGORIA, "onTouchEvent:x:y = " + x + ":" + y);

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
                        this.xQ = (int) x - (larguraImgQuadrado / 2);
                        // this.y = (int) y - (alturaImgQuadrado / 2);
                        this.yQ = (int) (alturaTela - alturaImgQuadrado / 2) - (alturaImgQuadrado / 2);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //Finaliza movimento

                selecionou = false;
                break;
        }
        invalidate();
        return true;
    }


    /**
     * Método responsável pelo controle de Message do Handler * * @param msg Message
     */
    private void updateUI(Message msg) {
        if (msg.what == 1) { //Converto o object para string (pois foi o que eu passei)
           // String texto = (String) msg.obj;
            //defino no meu TextView o texto.
            invalidate();
        } else if (msg.what == 2) {
            //finalizo a activity


        }
    }


}


