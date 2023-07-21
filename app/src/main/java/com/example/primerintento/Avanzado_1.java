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

public class Avanzado_1 extends AppCompatActivity {
    TextView puntos, textopregunta, puntajefinal, felicitaciones;
    ImageView respuesta;
    Button op1, op2, op3, finalizar;
    String[] txtpreguntas = {"2/3 + 4/9", "6/7 + 8/1", "3/8 + 5/5", "7/6 + 9/4", "1/2 + 6/8", "9/4 + 2/7", "9/1 + 1", "4/5 + 5", "1/6 + 6", "5/4 + 4", "8/9 + 9", "7/2 + 2", "2/3 + 4/9", "6/7 + 8/1", "7/2 + 3/4", "1/3 + 3/2", "3/10 + 10/4", "1/6 + 6/3", "3/10 + 10/2", "1/2 + 1", "5 + 3/8", "3/15 + 6", "7/9 + 3", "4/12 + 2", "1/7 + 1", "5/2 + 5", "5/13 + 4", "1/5 + 2", "9 + 4/9", "3 + 4/6"};
    String[] txtrespuestas = {"10/9", "62/7", "11/8", "7/2", "5/4", "71/28", "10", "29/5", "37/6", "21/4", "89/9", "11/2", "10/9", "60/7", "17/4", "11/6", "14/5", "13/6", "53/10", "3/2", "43/8", "31/5", "34/9", "7/3", "8/7","15/2", "57/13", "11/5", "85/9", "11/3"};
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
        setContentView(R.layout.activity_avanzado1);
        System.out.println("Metido en medio");

        puntos = (TextView) findViewById(R.id.puntos_a1);
        puntajefinal = (TextView) findViewById(R.id.text_puntajefinal_a1);
        felicitaciones = (TextView) findViewById(R.id.text_felicitaciones_a1);
        textopregunta = (TextView) findViewById(R.id.text_pregunta_a1);
        respuesta = (ImageView) findViewById(R.id.img_respuesta_a1);
        op1 = (Button) findViewById(R.id.btn_a1_1);
        op2 = (Button) findViewById(R.id.btn_a1_2);
        op3 = (Button) findViewById(R.id.btn_a1_3);
        finalizar = (Button) findViewById(R.id.btn_finalizar_a1);

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