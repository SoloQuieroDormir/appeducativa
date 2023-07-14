package com.example.primerintento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basico2);

        puntos = (TextView) findViewById(R.id.Puntos);
        imagen = (ImageView) findViewById(R.id.imagen);
        op1 = (Button) findViewById(R.id.btn_b2_1);
        op2 = (Button) findViewById(R.id.btn_b2_2);
        op3 = (Button) findViewById(R.id.btn_b2_3);
        verificarerrores = (Button) findViewById(R.id.VerificarErrores);

        GenerarImagenaletoria();
        verificarerrores.setVisibility(View.GONE);

        while (preguntasrestantes > 0) {

            op1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String respconfirmar = op1.getText().toString().toLowerCase();
                    if (respconfirmar.equals(imagen_preg[numerogenerado])) {
                        intpunto = intpunto + 1;
                        puntos.setText("Aciertos: " + intpunto);
                        preguntasrestantes = preguntasrestantes - 1;

                        imagenArr.remove(numerogenerado);
                        respuestaArr.remove(numerogenerado);
                        GenerarImagenaletoria();

                    } else {
                        preguntasrestantes = preguntasrestantes - 1;

                        imagenArr.remove(numerogenerado);
                        respuestaArr.remove(numerogenerado);
                        GenerarImagenaletoria();

                    }
                }
            });

            op2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String respconfirmar = op2.getText().toString().toLowerCase();
                    if (respconfirmar.equals(imagen_preg[numerogenerado])) {
                        intpunto = intpunto + 1;
                        puntos.setText("Aciertos: " + intpunto);
                        preguntasrestantes = preguntasrestantes - 1;

                        imagenArr.remove(numerogenerado);
                        GenerarImagenaletoria();

                    } else {
                        preguntasrestantes = preguntasrestantes - 1;

                        imagenArr.remove(numerogenerado);
                        GenerarImagenaletoria();

                    }
                }
            });

            op3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String respconfirmar = op3.getText().toString().toLowerCase();
                    if (respconfirmar.equals(imagen_preg[numerogenerado])) {
                        intpunto = intpunto + 1;
                        puntos.setText("Aciertos: " + intpunto);
                        preguntasrestantes = preguntasrestantes - 1;

                        imagenArr.remove(numerogenerado);
                        GenerarImagenaletoria();

                    } else {
                        preguntasrestantes = preguntasrestantes - 1;

                        imagenArr.remove(numerogenerado);
                        GenerarImagenaletoria();

                    }
                }
            });


        }

        if(preguntasrestantes == 0){
            verificarerrores.setVisibility(View.VISIBLE);
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
        numerogenerado=randInt;
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

        int[] auxiliar = {numero, randInt1, randInt2};
        ArrayList<Integer> auxiliarArr = new ArrayList(Arrays.asList(auxiliar));

        Random randaux = new Random();
        int randaux2 = randaux.nextInt(auxiliarArr.size());
        int id1 = getResources().getIdentifier(respuesta_preg[randaux2],"string",getPackageName());
        op1.setText(id1);
        //auxiliarArr.remove(randaux2);

        if(randaux2 == auxiliar[0]) {
            int id2 = getResources().getIdentifier(respuesta_preg[auxiliar[1]], "string", getPackageName());
            op2.setText(id2);

            int id3 = getResources().getIdentifier(respuesta_preg[auxiliar[2]], "string", getPackageName());
            op3.setText(id3);
        } else if (randaux2 == auxiliar[1]) {
            int id2 = getResources().getIdentifier(respuesta_preg[auxiliar[0]], "string", getPackageName());
            op2.setText(id2);

            int id3 = getResources().getIdentifier(respuesta_preg[auxiliar[2]], "string", getPackageName());
            op3.setText(id3);
        } else {
            int id2 = getResources().getIdentifier(respuesta_preg[auxiliar[0]], "string", getPackageName());
            op2.setText(id2);

            int id3 = getResources().getIdentifier(respuesta_preg[auxiliar[1]], "string", getPackageName());
            op3.setText(id3);
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