package com.example.contador;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> actLaucher;

    TextView contador;
    Button boton;
    Button reset;

    Button icono;

    //variables a pasar
    BigInteger jades =new BigInteger("0") ;

    int incrementar = 1;
    int costo = 100;
    int tickets = 0;

  //variables iconos
    ImageView jadeic;

    int[] iconos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador  = (TextView) findViewById(R.id.textocontador);
        //botonAutoClick = (Button) findViewById(R.id.button3);
        reset = (Button) findViewById(R.id.reset);
        jadeic = (ImageView)findViewById(R.id.jade);
        contador.setText(""+ jades);

        //Listener para el metodo precuacion
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                precaucion();
            }
        });

        actLaucher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data != null){
                            String newjades = data.getStringExtra("num");
                            contador.setText(newjades);
                        }
                    }
                });



        }


    //IR TIENDA + PASAR DATOS
    public void irtienda(View v){
        Intent tienda = new Intent(this, Tienda.class);
        //Pasar "datos"
      /*
      *
      */

      Bundle extras = new Bundle();
      extras.putString("jades", jades.toString());
      extras.putInt("incrementar",incrementar);
      extras.putInt("costo",costo);
      extras.putInt("tickets", tickets);

        tienda.putExtras(extras);
        actLaucher.launch(tienda);


  }
    public void volver(View v) {
        Intent i = new Intent(this, MainActivity.class);
        finish();
    }



        public void sumar(View v){
        ScaleAnimation fade_in = new ScaleAnimation(0.7f, 1.2f, 0.7f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        fade_in.setDuration(100);
        jadeic.startAnimation(fade_in);
        //int n = Integer.parseInt(contador.getText().toString()) +1;
        jades = jades.add(BigInteger.valueOf(incrementar));
        //num += incrementar;
        contador.setText(FormatoNum(jades));
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

        if(jades.compareTo(BigInteger.valueOf(costo))>=0){
            jades = jades.subtract(BigInteger.valueOf(costo));
            incrementar++;
            contador.setText(""+ jades);
            costo += 20;
            tickets++;
            boton.setText(costo+" jades");
            crearHilos();
        }
    }



    public void reset(View v){
        jades = BigInteger.valueOf(0);
        contador.setText(""+ jades);
    }
    private void precaucion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Deseas resetear el contador?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                jades = BigInteger.ZERO;
                contador.setText(jades.toString());
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
                jadeic.startAnimation(fade_in);
                jades = jades.add(BigInteger.valueOf(incrementar));

                handler.post(() -> {
                //UI Thread work here
                contador.setText(FormatoNum(jades));
            });}
        });
    }


    public void cambiarIcono(View v){
        if(tickets>(tickets*2)){
            //cambiar variables
            tickets = tickets - (tickets*2);
            //cambiar icono
            iconos= new int[]{R.drawable.sampo2, R.drawable.asta, R.drawable.dandinero,R.drawable.tingyun,
                    R.drawable.danheng2,R.drawable.topaz,R.drawable.jade};
            Random random = new Random();
            int randomIndex = random.nextInt(iconos.length);
            jadeic.setImageResource(iconos[randomIndex]);
        }




      }



}