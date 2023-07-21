package com.example.primerintento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Medio_3 extends AppCompatActivity {
    TextView puntos, textopregunta, puntajefinal, felicitaciones;
    ImageView respuesta;
    Button op1, op2, op3, finalizar;
    String[] txtpreguntas = {"3/2 * 2/4", "1/1 * 1/3", "2/4 * 2/1", "2/1 * 1/2", "3/1 * 2/5", "1/1 * 3/1", "3/3 * 4/3", "1/7 * 5/1", "3/2 * 4/3", "3/1 * 5/5", "7/3 * 1/3", "2/1 * 3/7", "3/1 * 1/3", "7/4 * 1/2", "3/2 * 1/2", "1/1 * 3/3", "1/2 * 7/5", "2/2 * 2/3", "1/2 * 5/5", "3/1 * 1/2", "2/2 * 2/4", "3/5 * 3/3", "5/3 * 3/3", "2/6 * 3/2", "1/7 * 2/1", "4/2 * 2/1", "3/1 * 3/13", "1/5 * 3/1", "5/3 * 1/3", "1/2 * 7/3"};
    String[] txtrespuestas = {"6/8", "1/3", "4/4", "2/2", "6/5", "3/1", "12/9", "5/7", "12/6", "15/5", "7/9", "6/7", "3/3", "7/8", "3/4", "3/3", "7/10", "4/6", "5/10", "3/2", "4/8", "9/15", "15/9", "6/12", "2/7","8/2", "9/13", "3/5", "5/9", "7/6"};
    String[] siono = {"correcto", "equivocado"};
    int intpunto = 0;

    public static int MILISEGUNDOS_ESPERA = 1000;

    ArrayList<String> preguntasArr = new ArrayList<String>(Arrays.asList(txtpreguntas));

    int preguntasrestantes = 10;
    int numerogenerado = 0;

    String correctAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medio3);
        System.out.println("Metido en medio");

        puntos = (TextView) findViewById(R.id.puntos_m3);
        puntajefinal = (TextView) findViewById(R.id.text_puntajefinal_m3);
        felicitaciones = (TextView) findViewById(R.id.text_felicitaciones_m3);
        textopregunta = (TextView) findViewById(R.id.text_pregunta_m3);
        respuesta = (ImageView) findViewById(R.id.img_respuesta_m3);
        op1 = (Button) findViewById(R.id.btn_m3_1);
        op2 = (Button) findViewById(R.id.btn_m3_2);
        op3 = (Button) findViewById(R.id.btn_m3_3);
        finalizar = (Button) findViewById(R.id.btn_finalizar_m3);

        GenerarPreguntaAleatoria();

        finalizar.setVisibility(View.GONE);
        puntajefinal.setVisibility(View.GONE);
        felicitaciones.setVisibility(View.GONE);
        respuesta.setVisibility(View.GONE);

        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCorrect(op1, op2, op3, txtpreguntas, numerogenerado);
                verifyAnswers();
            }
        });

        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCorrect(op2, op1, op3, txtpreguntas, numerogenerado);
                verifyAnswers();
            }
        });

        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCorrect(op3, op1, op2, txtpreguntas, numerogenerado);
                verifyAnswers();
            }
        });
    }
    private void verifyAnswers(){
        if(preguntasrestantes == 0) {
            finalizar.setVisibility(View.VISIBLE);
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

            preguntasArr.remove(generatedNumber);

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

            preguntasArr.remove(generatedNumber);
        }
    }

    public void espera1(int milisegundos, Button button){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                GenerarPreguntaAleatoria();
                respuesta.setVisibility(View.GONE);
                button.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.botones));
            }
        },milisegundos);
    }

    public void espera2(int milisegundos, Button button1, Button button2){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                GenerarPreguntaAleatoria();
                respuesta.setVisibility(View.GONE);
                button1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.botones));
                button2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.botones));
            }
        },milisegundos);
    }

    private void establecer_pregunta(int numero) {
        if (txtpreguntas[numero] != null) {
            String id = txtpreguntas[numero];
            textopregunta.setText(id);

        }
    }


    public void GenerarPreguntaAleatoria(){
        Random rand = new Random();
        int randInt = rand.nextInt(preguntasArr.size());

        establecer_pregunta(randInt);
        establecer_respuestas(randInt);
        numerogenerado = randInt;

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
            this.setRespuestaEnPregunta(txtrespuestas, numero, op1, true);
            this.setRespuestaEnPregunta(txtrespuestas, randInt1, op2);
            this.setRespuestaEnPregunta(txtrespuestas, randInt2, op3);
        } else if (correctResponse == 1) {
            this.setRespuestaEnPregunta(txtrespuestas, numero, op2, true);
            this.setRespuestaEnPregunta(txtrespuestas, randInt1, op3);
            this.setRespuestaEnPregunta(txtrespuestas, randInt2, op1);
        } else {
            this.setRespuestaEnPregunta(txtrespuestas, numero, op3, true);
            this.setRespuestaEnPregunta(txtrespuestas, randInt1, op1);
            this.setRespuestaEnPregunta(txtrespuestas, randInt2, op2);
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