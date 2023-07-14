package com.example.primerintento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OpcionMedio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_medio);
    }


    public void IrAMenu (View view){
        onBackPressed();
    }

    public void IrAMedio5(View view){
        Intent i = new Intent(this, Medio_5.class);
        startActivity(i);
    }

}