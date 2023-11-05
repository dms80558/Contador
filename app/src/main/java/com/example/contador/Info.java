package com.example.contador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contador.practicas.MainActivity2;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }



    public void volver(View v){
        Intent i = new Intent(this, PantallaInicio.class);
        finish();
    }

    public void goRecycle(View v){
       Intent p = new Intent(this, com.example.contador.practicas.MainActivity2.class );
       startActivity(p);
    }
}
