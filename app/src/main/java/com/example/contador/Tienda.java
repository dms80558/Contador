package com.example.contador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;

public class Tienda extends AppCompatActivity {
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        boton = (Button) findViewById(R.id.button3);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras.isEmpty()){
            //completar en su dia
        }
        else{
            String num = extras.getString("num");
            int costo = extras.getInt("costo");
            int incremento = extras.getInt("incremento");

            BigInteger numero = new BigInteger(num);
            BigInteger precio = new BigInteger(String.valueOf(costo));



            boton.setText(costo +"jades");
        }
    }



/*

    public void volver(View v){
        Intent i = new Intent(this, MainActivity.class);
        finish();
    }
        */
}
