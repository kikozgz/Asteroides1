package com.example.asteroides1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VistaJuego extends View {

    // //// ASTEROIDES //////
    //private Vector Asteroides; // Vector con los Asteroides
    private List<Grafico> Asteroides;   //Lista con los Asteroides
    private int numAsteroides= 5; // Número inicial de asteroides
    private int numFragmentos= 3; // Fragmentos en que se divide

    // //// NAVE //////
    private Grafico nave;// Gráfico de la nave
    private int giroNave; // Incremento de dirección
    private float aceleracionNave; // aumento de velocidad
    // Incremento estándar de giro y aceleración
    private static final int PASO_GIRO_NAVE = 5;
    private static final float PASO_ACELERACION_NAVE = 0.5f;


    public VistaJuego(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable drawableNave, drawableAsteroide, drawableMisil;
        drawableAsteroide = context.getResources().getDrawable(
                R.drawable.asteroide1,this.getContext().getTheme());

        drawableNave = context.getResources().getDrawable(
                R.drawable.nave, this.getContext().getTheme());

        nave = new Grafico(this, drawableNave);

        Asteroides = new ArrayList<Grafico>();
        for (int i = 0; i < numAsteroides; i++) {
            Grafico asteroide = new Grafico(this, drawableAsteroide);
            asteroide.setIncY(Math.random() * 4 - 2);
            asteroide.setIncX(Math.random() * 4 - 2);
            asteroide.setAngulo((int) (Math.random() * 360));
            asteroide.setRotacion((int) (Math.random() * 8 - 4));
            Asteroides.add(asteroide);
        }
    }
    @Override protected void onSizeChanged(int ancho, int alto,
                                           int ancho_anter, int alto_anter) {
        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);
        // Una vez que conocemos nuestro ancho y alto.

        //Se posicionan los asteroides en posiciones aleatorias
        for (Grafico asteroide: Asteroides) {
            do{
                asteroide.setPosX(Math.random()*(ancho-asteroide.getAncho()));
                asteroide.setPosY(Math.random()*(alto-asteroide.getAlto()));
            } while(asteroide.distancia(nave) < (ancho+alto)/5);
        }

        //Se posiciona la nave en el centro de la vista
        nave.setPosX(ancho/2);
        nave.setPosY(alto/2);
    }
    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Se dibujan los asteroides en el Canvas
        for (Grafico asteroide: Asteroides) {
            asteroide.dibujaGrafico(canvas);
        }
        //Se dibuja la nave en el Canvas
        nave.dibujaGrafico(canvas);
    }
}
