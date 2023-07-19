package com.example.primerintento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Medio_4 extends AppCompatActivity {
    TextView puntos, textopregunta, puntajefinal, felicitaciones;
    Button op1, op2, op3, finalizar;
    String[] txtpreguntas = {"9/3", "36/9", "25/5", "30/6", "56/7", "24/4", "14/7", "15/5", "16/4", "18/3", "14/2", "81/9", "20/5", "12/4", "21/3", "45/9", "48/6", "84/7", "10/2", "35/5", "36/9", "104/8", "12/4", "45/3", "3/3", "32/4", "27/3", "32/2", "8/2", "33/3"};
    String[] txtrespuestas = {"3", "4", "5", "5", "8", "6", "2", "3", "4", "6", "7", "9", "4", "3", "7", "5", "8", "12", "5", "7", "4", "13", "3", "15", "1","8", "9", "16", "4", "11"};
    int intpunto = 0;

    ArrayList<String> preguntasArr = new ArrayList<String>(Arrays.asList(txtpreguntas));
    ArrayList<String> respuestasArr = new ArrayList<String>(Arrays.asList(txtrespuestas));

    int preguntasrestantes = 10;
    int numerogenerado = 0;

    String correctAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medio4);
        System.out.println("Metido en medio");

        puntos = (TextView) findViewById(R.id.puntos_m4);
        puntajefinal = (TextView) findViewById(R.id.text_puntajefinal_m4);
        felicitaciones = (TextView) findViewById(R.id.text_felicitaciones_m4);
        textopregunta = (TextView) findViewById(R.id.text_pregunta_m4);
        op1 = (Button) findViewById(R.id.btn_m4_1);
        op2 = (Button) findViewById(R.id.btn_m4_2);
        op3 = (Button) findViewById(R.id.btn_m4_3);
        finalizar = (Button) findViewById(R.id.btn_finalizar_m4);

        GenerarPreguntaAleatoria();

        finalizar.setVisibility(View.GONE);
        puntajefinal.setVisibility(View.GONE);
        felicitaciones.setVisibility(View.GONE);

        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCorrect(op1, txtpreguntas, numerogenerado);
                verifyAnswers();
            }
        });

        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCorrect(op2, txtpreguntas, numerogenerado);
                verifyAnswers();
            }
        });

        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCorrect(op3, txtpreguntas, numerogenerado);
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

    private void isCorrect(Button button, String[] array1, int generatedNumber){
        String respconfirmar = button.getText().toString().toLowerCase();
        if (respconfirmar.equals(correctAnswer)) {
            intpunto = intpunto + 1;
            puntos.setText("Puntos: " + intpunto);
            preguntasrestantes = preguntasrestantes - 1;

            preguntasArr.remove(generatedNumber);
            GenerarPreguntaAleatoria();

        } else {
            preguntasrestantes = preguntasrestantes - 1;
            preguntasArr.remove(generatedNumber);
            GenerarPreguntaAleatoria();

        }
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