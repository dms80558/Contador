package com.example.contador;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

    //Button botonAutoClick;

    LinearLayout botonAutoClick;
    Button botonJades;
    Button botonTickets;
    TextView textvales;
    TextView jades;
    BigInteger num_jades = BigInteger.ZERO;
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
        botonAutoClick = (LinearLayout) findViewById(R.id.botonAutoClick);
        botonTickets = (Button) findViewById(R.id.bticono);
        botonJades = (Button) findViewById(R.id.button3);
        jades = (TextView) findViewById(R.id.cantidad_jades);

        //RECOGER DATOS
        Intent intent = getIntent();
        num_jades = new BigInteger(intent.getExtras().getString("num_jades"));
        tickets = intent.getIntExtra("tickets",0);
        incrementar = intent.getExtras().getInt("incrementar");
        costo = intent.getIntExtra("costo", 12);
        iconInt = intent.getIntExtra("icono",iconInt);

        //INSERTAR DATOS
        jades.setText(FormatoNum(num_jades));
        botonJades.setText(costo + " jades");
        botonTickets.setText("10 tickets");
        textvales.setText("" + tickets);


        botonTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultado = new Intent();
                if(tickets >=10){
                    iconInt = getIconint();
                    tickets -= 10;
                }
                resultado.putExtra(KEY_NAME,iconInt);
                resultado.putExtra(KEY_TICKETS,tickets);
                resultado.putExtra(KEY_JADES,num_jades.toString());
                setResult(RESULT_OK,resultado);
                finish();
            }
        });

        botonAutoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restar();
                cambiarview(v);
                Intent operacion = new Intent();
                operacion.putExtra(KEY_JADES,num_jades.toString());
                operacion.putExtra(KEY_INCREMENTAR,incrementar);
                operacion.putExtra(KEY_TICKETS,tickets);
                operacion.putExtra(KEY_COSTO,costo);
                operacion.putExtra(KEY_NAME,iconInt);
                setResult(RESULT_OK,operacion);
                finish();
            }
        });
    }

    public void cambiarview(View v){
        jades.setText(FormatoNum(num_jades));
        botonJades.setText(costo + " jades");
        textvales.setText("" + tickets);
    }


    public int getIconint() {
        int r =0;
        //cambiar icono
        iconos = new int[]{R.drawable.sampo2, R.drawable.asta, R.drawable.dandinero, R.drawable.tingyun,
                R.drawable.danheng2, R.drawable.topaz, R.drawable.jade, R.drawable.sietedemarzo,
                R.drawable.pompom, R.drawable.yanqing,R.drawable.sampojades,R.drawable.clara};
        Random random = new Random();
        int randomIndex = random.nextInt(iconos.length);
        r = iconos[randomIndex];
        return r;
    }


    public void restar() {
        if (num_jades.compareTo(BigInteger.valueOf(costo)) >= 0) {
            num_jades = num_jades.subtract(BigInteger.valueOf(costo));
            incrementar++;
            costo += 20;
            tickets++;
        }
    }


    public String FormatoNum(BigInteger num) {
        String r = "";
        if (num.compareTo(BigInteger.valueOf(1000)) < 0) {
            r = ("" + num.toString());
        } else if (num.compareTo(BigInteger.valueOf(1000_000)) > 0 && num.compareTo(BigInteger.valueOf(1000_000_000)) <= 0) {
            BigInteger mill = num.divide(BigInteger.valueOf(1000_000));
            r = (mill.toString() + "K");
        } else if (num.compareTo(BigInteger.valueOf(1000_000_000)) > 0 && num.compareTo(BigInteger.valueOf(1_000_000_000_000l)) <= 0) {
            BigInteger trill = num.divide(BigInteger.valueOf(1000_000_000));
            r = (trill.toString() + "M");
        } else {
            BigInteger trill = num.divide(BigInteger.valueOf(1000_000_000_000l));
            r = ("" + num.toString());
        }
        return r;
    }



    public void goBack(View v) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("num", num_jades);
        i.putExtra("incrementar", incrementar);
        finish();
    }
}
