package com.example.primerintento;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class OpcionBasico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_basico);
    }


    public void IrAMenu (View view){
        onBackPressed();
    }

    public void IrABasico1 (View view){
        Intent j = new Intent(this, Basico_1.class);
        startActivity(j);
    }

    public void IrABasico2 (View view){
        Intent k = new Intent(this, Basico_2.class);
        startActivity(k);
    }

}