package com.example.primerintento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OpcionAvanzado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_avanzado);
    }

    public void IrAMenu (View view){
        onBackPressed();
    }

    public void IrAAvanzado1 (View view){
        Intent i = new Intent(this, Avanzado_1.class);
        startActivity(i);
    }
    public void IrAAvanzado2 (View view){
        Intent i = new Intent(this, Avanzado_2.class);
        startActivity(i);
    }
    public void IrAAvanzado3 (View view){
        Intent i = new Intent(this, Avanzado_3.class);
        startActivity(i);
    }
    public void IrAAvanzado4 (View view){
        Intent i = new Intent(this, Avanzado_4.class);
        startActivity(i);
    }
    public void IrAAvanzado5 (View view){
        Intent i = new Intent(this, Avanzado_5.class);
        startActivity(i);
    }

}