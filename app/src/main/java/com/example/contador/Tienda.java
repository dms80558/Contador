package com.example.contador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Tienda extends AppCompatActivity {
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        boton = (Button) findViewById(R.id.button3);
        Bundle datos = new Bundle();
        if(boton.callOnClick())
        boton.setText(datos.getInt("costo_jades"));
    }



    public void volver(View v){
        Intent i = new Intent(this, MainActivity.class);
        finish();
    }
}
