package com.example.mateus.gameball;

import android.content.Context;
import android.graphics.Canvas;
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
    int xQ;
    private boolean selecionou;
    private int larguraTela, alturaTela;

    private Handler handler;
    private ControlGamePong controlBall;
    private Ball ball;
    private  Rectangle rec;

    public GamePongView(Context context) {
        super(context, null);

        ball = new Ball(context);
        rec = new Rectangle(context);
        setFocusable(true);

    }

    @Override
//Callback para quando a tela é iniciada ou redimensionada
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        this.alturaTela = height;
        this.larguraTela = width;

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) { //chamo um método para melhor organização.
                updateUI(msg);
            }
        };


        rec.setyRec(height - rec.getRecHeight_y());
        rec.setxRec(width / 2 - (rec.getRecWidth_x() / 2));
        xQ=rec.getxRec();

        ball.setxBall(width / 2 - (ball.getBallWidth_x() / 2));
        ball.setyBall(height / 2 - (ball.getBallHeight_y() / 2));

        controlBall = new ControlGamePong(handler,larguraTela,alturaTela,ball,rec);
        controlBall.start();

    }

    /**
     * desenha objetos nas posiçoes inicais
     **/
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rec.drawRectangle(canvas);
        ball.drawBall(canvas);
    }


    @Override
//Move a imagem
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Inicia movimento se pressionou a imagem
                selecionou = rec.getRectangle().copyBounds().contains((int) x, (int) y);
                break;
            case MotionEvent.ACTION_MOVE:
                //Arrasta o boneco
                if (selecionou) {
                    //
                    if (x - rec.getRecWidth_x()/ 2 > 0 & x + rec.getRecWidth_x()/ 2 < larguraTela) {
                      //  this.xQ = (int) x - (rec.getRecWidth_x() / 2);

                        if((xQ-rec.getxRec())>0){
                            rec.setdirection(1);
                        }else if((xQ-rec.getxRec())<0){
                            rec.setdirection(-1);
                        }else{
                            rec.setdirection(0);
                        }

                        xQ=rec.getxRec();
                        rec.setxRec(this.xQ = (int) x - (rec.getRecWidth_x() / 2));

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
        }
    }



}


