package com.example.contador;

import static com.example.contador.R.drawable.sampo2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    TextView contador;
    Button boton;
    Button reset;

    Button icono;
    BigInteger num =new BigInteger("0") ;

    int incrementar = 1;
    int costo = 100;

    //ImageView tienda;

    ImageView jade;
    ImageView jadens ;
    int[] iconos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador  = (TextView) findViewById(R.id.textocontador);
        //boton = (Button) findViewById(R.id.button3);
        reset = (Button) findViewById(R.id.reset);
        //ns
        iconos= new int[]{R.drawable.sampo2, R.drawable.asta, R.drawable.dandinero};
        jade= (ImageView)findViewById(R.id.jade);
       ;


        contador.setText(""+num);


        //crearHilos();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                precaucion();
            }
        });

        }

    private void precaucion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Deseas resetear el contador?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                num = BigInteger.ZERO;
                contador.setText(num.toString());
                contador.setTextColor(Color.WHITE);
    }

    });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss(); //No hará nada
        }
    });
    AlertDialog dialog = builder.create();
        dialog.show();
}


    public void irtienda(View v){
        Intent tienda = new Intent(this, Tienda.class);
        //Pasar "datos"
      /*
      *
      */

      Bundle extras = new Bundle();
      extras.putString("data", num.toString());
      extras.putInt("incrementar",incrementar);
      extras.putInt("costo",costo);

        tienda.putExtras(extras);
        startActivity(tienda);

  }
    public void volver(View v) {
        Intent i = new Intent(this, MainActivity.class);
        finish();
    }



        public void sumar(View v){
        ScaleAnimation fade_in = new ScaleAnimation(0.7f, 1.2f, 0.7f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        fade_in.setDuration(100);
        jade.startAnimation(fade_in);
        //int n = Integer.parseInt(contador.getText().toString()) +1;
        num = num.add(BigInteger.valueOf(incrementar));
        //num += incrementar;
        contador.setText(FormatoNum(num));



    }

    public String FormatoNum(BigInteger num){
        String r="";
        if(num.compareTo(BigInteger.valueOf(1000))<0) {
            r = (""+num.toString());
        } else if(num.compareTo(BigInteger.valueOf(1000))>0&&num.compareTo(BigInteger.valueOf(1000_000))<=0){
            BigInteger mil = num.divide(BigInteger.valueOf(1000));
            r = (mil.toString()+"MIL");
        }else if (num.compareTo(BigInteger.valueOf(1000_000))>0&&num.compareTo(BigInteger.valueOf(1000_000_000))<=0) {
            BigInteger mill = num.divide(BigInteger.valueOf(1000_000));
            r = (mill.toString()+"K");
        }
        else if (num.compareTo(BigInteger.valueOf(1000_000_000))>0&&num.compareTo(BigInteger.valueOf(1_000_000_000_000l))<=0) {
            BigInteger trill = num.divide(BigInteger.valueOf(1000_000_000));
            r = (trill.toString()+"M");}
        else{
            BigInteger trill = num.divide(BigInteger.valueOf(1000_000_000_000l));
            r = (trill.toString()+" ∞");
        }
        return r;
    }

    public void restar(View v){

        if(num.compareTo(BigInteger.valueOf(costo))>=0){
            num = num.subtract(BigInteger.valueOf(costo));
            incrementar++;
            contador.setText(""+num);
            costo += 20;
            boton.setText(costo+" jades");
            crearHilos();
        }
    }



    public void reset(View v){
        num = BigInteger.valueOf(0);
        contador.setText(""+num);
    }



    public void crearHilos(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            //Background work here
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ScaleAnimation fade_in = new ScaleAnimation(0.7f, 1.2f, 0.7f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                fade_in.setDuration(100);
                jade.startAnimation(fade_in);
                num = num.add(BigInteger.valueOf(incrementar));

                handler.post(() -> {
                //UI Thread work here
                contador.setText(FormatoNum(num));
            });}
        });
    }

public void cambiarIcono(){


        jade.setImageResource(R.drawable.sampo2);

      }



}