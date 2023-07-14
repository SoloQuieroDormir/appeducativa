package com.example.primerintento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    TextView puntos;
    ImageView imagen;
    Button op1, op2, op3, verificarerrores;
    String[] imagen_preg = {"img1", "img2", "img3", "img4", "img5", "img6", "img7", "img8", "img9", "img10", "img11", "img12", "img13", "img14", "img15", "img16", "img17", "img18", "img19", "img20", "img21", "img22", "img23", "img24", "img25", "img26", "img27", "img28", "img29", "img30"};
    String[] respuesta_preg = {"1/2", "1/3", "1/4", "1/5", "1/6", "1/7", "1/8", "1/9", "1/10", "3/2", "2/3", "4/10", "6/5", "5/7", "3/4", "6/9", "9/10", "4/4", "7/5", "6/4", "2/8", "3/5", "4/10", "8/5", "4/2", "2/6", "2/7", "2/8", "6/9", "3/8"};
    int intpunto = 0;

    ArrayList<String> imagenArr = new ArrayList<String>(Arrays.asList(imagen_preg));
    ArrayList<String> respuestaArr = new ArrayList<String>(Arrays.asList(respuesta_preg));

    int preguntasrestantes = 10;
    int numerogenerado = 0;

    String correctAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico2);
        System.out.println("Metido en basico");

        puntos = (TextView) findViewById(R.id.Puntos);
        imagen = (ImageView) findViewById(R.id.imagen);
        op1 = (Button) findViewById(R.id.btn_b2_1);
        op2 = (Button) findViewById(R.id.btn_b2_2);
        op3 = (Button) findViewById(R.id.btn_b2_3);
        verificarerrores = (Button) findViewById(R.id.VerificarErrores);

        GenerarImagenaletoria();
        verificarerrores.setVisibility(View.GONE);

        //while (preguntasrestantes > 0) {

            op1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isCorrect(op1, imagen_preg, numerogenerado);
                    verifyAnswers();
                }
            });

            op2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isCorrect(op2, imagen_preg, numerogenerado);
                    verifyAnswers();
                }
            });

            op3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isCorrect(op3, imagen_preg, numerogenerado);
                    verifyAnswers();
                }
            });


        //}

    }

    private void verifyAnswers(){
        if(preguntasrestantes == 0) {
            verificarerrores.setVisibility(View.VISIBLE);
            op1.setEnabled(false);
            op2.setEnabled(false);
            op3.setEnabled(false);
        }
    }

    private void isCorrect(Button button, String[] array1, int generatedNumber){
        String respconfirmar = button.getText().toString().toLowerCase();
        if (respconfirmar.equals(correctAnswer)) {
            intpunto = intpunto + 1;
            puntos.setText("PUNTOS: " + intpunto);
            preguntasrestantes = preguntasrestantes - 1;

            imagenArr.remove(generatedNumber);
            GenerarImagenaletoria();

        } else {
            intpunto = intpunto - 1;
            imagenArr.remove(generatedNumber);
            GenerarImagenaletoria();

        }
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

        this.setRespuestaEnPregunta(respuesta_preg, numero, op1, true);
        this.setRespuestaEnPregunta(respuesta_preg, randInt1, op2);
        this.setRespuestaEnPregunta(respuesta_preg, randInt2, op3);
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
        Intent j = new Intent(this, Errores.class);
        startActivity(j);
    }

}