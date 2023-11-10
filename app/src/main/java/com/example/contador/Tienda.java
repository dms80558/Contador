package com.example.contador;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import org.w3c.dom.Text;

import java.math.BigInteger;
import java.util.Random;

public class Tienda extends AppCompatActivity {
    public static final String KEY_NAME = "NAME";
    public static final String KEY_JADES ="JADES";
    public static final String KEY_INCREMENTAR = "INCREMENTAR";
    public static final String KEY_TICKETS = "TICKETS";
    public static final String KEY_COSTO = "COSTO";
    public static final String KEY_HILOS = "HILOS";

    Button botonAutoClick;
    Button botonTickets;
    TextView textvales;
    TextView jades;
    int num_jades = 0;
    int[] iconos;
    int tickets = 0;
    int costo = 12;
    int incrementar = 1;

    int iconInt = R.drawable.jade;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        textvales = (TextView) findViewById(R.id.textvales);
        botonTickets = findViewById(R.id.bticono);
        botonAutoClick = (Button) findViewById(R.id.button3);
        botonTickets = (Button) findViewById(R.id.bticono);
        jades = (TextView) findViewById(R.id.cantidad_jades);

        //RECOGER DATOS
        Intent intent = getIntent();
        num_jades = Integer.parseInt(intent.getExtras().getString("num_jades", "0"));
        tickets = intent.getIntExtra("tickets",0);
        incrementar = intent.getExtras().getInt("incrementar");
        costo = intent.getIntExtra("costo", 12);

        //INSERTAR DATOS
        jades.setText("" + num_jades);
        botonAutoClick.setText(costo + " jades");
        botonTickets.setText("10 tickets");
        textvales.setText("" + tickets);


        botonTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tickets == 1){
                    iconInt = getIconint();
                    tickets = 0;
                }
                Intent resultado = new Intent();
                resultado.putExtra(KEY_NAME,iconInt);
                resultado.putExtra(KEY_JADES,num_jades);
                resultado.putExtra(KEY_TICKETS,tickets);
                setResult(RESULT_OK,resultado);
                finish();
            }
        });

        botonAutoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restar();
                cambiarview(v);
                boolean hilos = true;
                Intent operacion = new Intent();
                operacion.putExtra(KEY_JADES,num_jades);
                operacion.putExtra(KEY_INCREMENTAR,incrementar);
                operacion.putExtra(KEY_TICKETS,tickets);
                operacion.putExtra(KEY_COSTO,costo);
                operacion.putExtra(KEY_HILOS,hilos);
                setResult(RESULT_OK,operacion);
                finish();
            }
        });
    }

    public void cambiarview(View v){
        jades.setText("" + num_jades);
        botonAutoClick.setText(costo + " jades");
        textvales.setText("" + tickets);
    }


    public int getIconint() {
        int r =0;
        //cambiar icono
        iconos = new int[]{R.drawable.sampo2, R.drawable.asta, R.drawable.dandinero, R.drawable.tingyun,
                R.drawable.danheng2, R.drawable.topaz, R.drawable.jade, R.drawable.sietedemarzo, R.drawable.pompom, R.drawable.pompom, R.drawable.yanqing};
        Random random = new Random();
        int randomIndex = random.nextInt(iconos.length);
        r = iconos[randomIndex];
        return r;
    }


    public void restar() {
        if (num_jades >= costo) {
            num_jades = num_jades - costo;
            incrementar++;
            costo += 20;
            tickets++;
        }
    }


    public void goBack(View v) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("num", num_jades);
        i.putExtra("incrementar", incrementar);
        finish();
    }
}
