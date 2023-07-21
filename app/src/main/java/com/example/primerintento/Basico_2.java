package com.example.primerintento;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Basico_2 extends AppCompatActivity {
    TextView puntos, puntajefinal, felicitaciones;
    ImageView imagen, respuesta;
    Button op1, op2, op3, verificarerrores;
    String[] imagen_preg = {"img1", "img2", "img3", "img4", "img5", "img6", "img7", "img8", "img9", "img10", "img11", "img12", "img13", "img14", "img15", "img16", "img17", "img18", "img19", "img20", "img21", "img22", "img23", "img24", "img25", "img26", "img27", "img28", "img29", "img30"};
    String[] respuesta_preg = {"1/2", "1/3", "1/4", "1/5", "1/6", "1/7", "1/8", "1/9", "1/10", "3/2", "2/3", "4/10", "6/5", "5/7", "3/4", "6/9", "9/10", "4/4", "7/5", "6/4", "2/8", "3/5", "4/10", "8/5", "4/2", "2/6", "2/7", "2/8", "6/9", "3/8"};
    String[] siono = {"correcto", "equivocado"};
    int intpunto = 0;

    public static int MILISEGUNDOS_ESPERA = 1000;

    ArrayList<String> imagenArr = new ArrayList<String>(Arrays.asList(imagen_preg));

    int preguntasrestantes = 10;
    int numerogenerado = 0;

    String correctAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico2);
        System.out.println("Metido en basico");

        puntos = (TextView) findViewById(R.id.Puntos);
        puntajefinal = (TextView) findViewById(R.id.puntaje_final);
        felicitaciones = (TextView) findViewById(R.id.Felicitaciones);
        imagen = (ImageView) findViewById(R.id.imagen);
        respuesta = (ImageView) findViewById(R.id.img_respuesta_b2);
        op1 = (Button) findViewById(R.id.btn_b2_1);
        op2 = (Button) findViewById(R.id.btn_b2_2);
        op3 = (Button) findViewById(R.id.btn_b2_3);
        verificarerrores = (Button) findViewById(R.id.VerificarErrores);

        GenerarImagenaletoria();
        verificarerrores.setVisibility(View.GONE);
        puntajefinal.setVisibility(View.GONE);
        felicitaciones.setVisibility(View.GONE);
        respuesta.setVisibility(View.GONE);

            op1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isCorrect(op1, op2, op3, imagen_preg, numerogenerado);
                    verifyAnswers();
                }
            });

            op2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isCorrect(op2, op1, op3, imagen_preg, numerogenerado);
                    verifyAnswers();
                }
            });

            op3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isCorrect(op3, op1, op2, imagen_preg, numerogenerado);
                    verifyAnswers();
                }
            });

    }

    private void verifyAnswers(){
        if(preguntasrestantes == 0) {
            verificarerrores.setVisibility(View.VISIBLE);
            op1.setEnabled(false);
            op2.setEnabled(false);
            op3.setEnabled(false);
        }
    }

    private void isCorrect(Button button, Button alternativa1, Button alternativa2, String[] array1, int generatedNumber){
        String respconfirmar = button.getText().toString().toLowerCase();
        if (respconfirmar.equals(correctAnswer)) {
            int id = getResources().getIdentifier(siono[0], "drawable", getPackageName());
            respuesta.setImageResource(id);
            respuesta.setVisibility(View.VISIBLE);
            intpunto = intpunto + 1;
            puntos.setText("Puntos: " + intpunto);
            preguntasrestantes = preguntasrestantes - 1;
            button.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.green));

            espera1(MILISEGUNDOS_ESPERA, button);

            imagenArr.remove(generatedNumber);


        } else {
            int id = getResources().getIdentifier(siono[1], "drawable", getPackageName());
            respuesta.setImageResource(id);
            respuesta.setVisibility(View.VISIBLE);
            preguntasrestantes = preguntasrestantes - 1;
            button.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.red));
            if(alternativa1.getText().toString().toLowerCase().equals(correctAnswer)){
                alternativa1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.green));
                espera2(MILISEGUNDOS_ESPERA, button, alternativa1);
            } else {
                alternativa2.setBackgroundColor(ContextCompat.getColor(getBaseContext(),R.color.green));
                espera2(MILISEGUNDOS_ESPERA, button, alternativa2);
            }

            imagenArr.remove(generatedNumber);

        }
    }

    public void espera1(int milisegundos, Button button){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                GenerarImagenaletoria();
                respuesta.setVisibility(View.GONE);
                button.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.botones));
            }
        },milisegundos);
    }

    public void espera2(int milisegundos, Button button1, Button button2){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                GenerarImagenaletoria();
                respuesta.setVisibility(View.GONE);
                button1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.botones));
                button2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.botones));
            }
        },milisegundos);
    }

    private void establecer_imagen(int numero) {
        if (imagen_preg[numero] != null) {
            int id = getResources().getIdentifier(imagen_preg[numero], "drawable", getPackageName());
            imagen.setImageResource(id);

        }
    }
    public void GenerarImagenaletoria(){
        Random rand = new Random();
        int randInt=rand.nextInt(imagenArr.size());

        establecer_imagen(randInt);
        establecer_respuestas(randInt);
        numerogenerado = randInt;
        respuesta.setVisibility(View.GONE);
    }

    public void establecer_respuestas(int numero){

        int randInt1;
        int randInt2;

        if(numero>2){
            randInt1 = numero -1;
            randInt2 = numero -2;
        } else {
            randInt1 = numero +4;
            randInt2 = numero +5;
        }
        int arrayOptions[] = {numero, randInt1,randInt2};

        Random random = new Random();
        int correctResponse = random.nextInt(arrayOptions.length);

        if (correctResponse == 0){
            this.setRespuestaEnPregunta(respuesta_preg, numero, op1, true);
            this.setRespuestaEnPregunta(respuesta_preg, randInt1, op2);
            this.setRespuestaEnPregunta(respuesta_preg, randInt2, op3);
        } else if (correctResponse == 1) {
            this.setRespuestaEnPregunta(respuesta_preg, numero, op2, true);
            this.setRespuestaEnPregunta(respuesta_preg, randInt1, op3);
            this.setRespuestaEnPregunta(respuesta_preg, randInt2, op1);
        } else {
            this.setRespuestaEnPregunta(respuesta_preg, numero, op3, true);
            this.setRespuestaEnPregunta(respuesta_preg, randInt1, op1);
            this.setRespuestaEnPregunta(respuesta_preg, randInt2, op2);
        }


    }

    private boolean IsInArray(String[] arrayString, int value){
        if(arrayString[value] != null){
            return true;
        }
        return false;
    }

    private void setRespuestaEnPregunta(String[] arrayString, int value, Button button){
        if(this.IsInArray(arrayString, value)){
            String text = arrayString[value];
            button.setText(text);
        }
    }

    private void setRespuestaEnPregunta(String[] arrayString, int value, Button button, boolean isCorrect){
        if(this.IsInArray(arrayString, value) && isCorrect){
            String text = arrayString[value];
            button.setText(text);
            correctAnswer = text;
        }
    }

    public void IrAMenu (View view){
        onBackPressed();
    }
    public void IrAErrores (View view){
        puntajefinal.setVisibility(View.VISIBLE);
        felicitaciones.setVisibility(View.VISIBLE);
        puntajefinal.setText("Puntaje Final: " + intpunto);

        if(intpunto == 10){
            felicitaciones.setText("Excelente trabajo");
        } else if (intpunto == 9 || intpunto == 8) {
            felicitaciones.setText("Buen trabajo, pero puedes mejorar");
        } else if (intpunto == 7 || intpunto == 6) {
            felicitaciones.setText("Nada mal, pero no te confies");
        } else {
            felicitaciones.setText("Esfuérzate más, necesitas repasar los temas");
        }

    }

}