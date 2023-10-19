package com.example.contador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaInicio extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallainico);
    }

    public void irMainActivity (View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);


    }

    public void irInfo (View v){
        Intent i = new Intent(this, Info.class);
        startActivity(i);


    }
    public void irOpciones(View v){
        Intent i = new Intent(this, Info.class);
        startActivity(i);
    }

    public void volver(View v){
        Intent i = new Intent(this, PantallaInicio.class);
        finish();
    }

    public void salir(View v){
        finishAffinity();
        System.exit(0);



    }

    }
