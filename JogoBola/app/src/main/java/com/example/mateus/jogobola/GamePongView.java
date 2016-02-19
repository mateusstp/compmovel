package com.example.mateus.jogobola;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

//import java.util.logging.Handler;

/**
 * Created by mateus on 10/02/16.
 */

public class GamePongView extends View {

    private static final String CATEGORIA = "AppNum53";
    private Drawable imgQuadrado;

    int xQ, yQ;

    private boolean selecionou;
    private int larguraTela,
            alturaTela,
            larguraImgQuadrado,
            alturaImgQuadrado;

    private Handler handler;
    ControlBall controlBall;
    private Ball ball;

    public GamePongView(Context context) {
        super(context, null);

        ball = new Ball(context);
        ball.setY(200);

        imgQuadrado = context.getResources().getDrawable(R.drawable.quadrado);

        //Recupera dimensoes da imagem
        larguraImgQuadrado = imgQuadrado.getIntrinsicWidth() + 150;
        alturaImgQuadrado = imgQuadrado.getIntrinsicHeight();

/*        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) { //chamo um método para melhor organização.
                updateUI(msg);
            }
        };

        ControlBall cb = new ControlBall(handler,larguraTela,alturaTela,ball);
        cb.start();
*/
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

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) { //chamo um método para melhor organização.
                updateUI(msg);
            }
        };

        controlBall = new ControlBall(handler,larguraTela,alturaTela,ball);
        controlBall.start();


        xQ = width / 2 - (larguraImgQuadrado / 2);
        yQ = height - alturaImgQuadrado;

        ball.setxBall(width / 2 - (ball.getBallWidth_x() / 2));
        ball.setyBall(height/ 2 - (ball.getBallHeight_y() / 2));
        //Log.i(CATEGORIA, "onSizeChanged xC:yC = " + xC + ":" + yC);
    }

    /**
     * desenha objetos nas posiçoes inicais
     **/
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        imgQuadrado.setBounds(xQ, yQ, xQ + larguraImgQuadrado, yQ + alturaImgQuadrado);
        imgQuadrado.draw(canvas);
        Log.i(CATEGORIA, "onDraw xQ:yQ = " + xQ + ":" + yQ);
        ball.drawBall(canvas);
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


