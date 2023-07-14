package com.example.primerintento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IrAOpcionBasico (View view){
        Intent i = new Intent(this, OpcionBasico.class);
        startActivity(i);
    }

    public void IrAOpcionMedio (View view){
        Intent j = new Intent(this, OpcionMedio.class);
        startActivity(j);
    }

    public void IrAOpcionAvanzado (View view){
        Intent k = new Intent(this, OpcionAvanzado.class);
        startActivity(k);
    }

    public void IrAInforme (View view){
        Intent l = new Intent(this, InfoDeAp.class);
        startActivity(l);
    }

}