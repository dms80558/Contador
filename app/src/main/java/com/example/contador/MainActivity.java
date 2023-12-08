package com.example.contador;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> actLaucher;

    private ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK) {
                userText = result.getData().getStringExtra("nombre_usuario");
                if (result.getData() != null && result.getData().getIntExtra(Tienda.KEY_NAME, R.drawable.jade) != 0) {
                    jadeic.setImageResource(result.getData().getIntExtra(Tienda.KEY_NAME, R.drawable.jade));
                    iconInt = result.getData().getIntExtra(Tienda.KEY_NAME, R.drawable.jade);
                    jades = new BigInteger(result.getData().getStringExtra(Tienda.KEY_JADES));
                    //jades = BigInteger.valueOf(result.getData().getIntExtra(Tienda.KEY_JADES, jades.intValue()));

                }
                if (result.getData() != null && result.getData().getStringExtra(Tienda.KEY_JADES) != null) {
                    //if (jades.intValue() == result.getData().getIntExtra(Tienda.KEY_JADES, jades.intValue())) {
                    jades = new BigInteger(result.getData().getStringExtra(Tienda.KEY_JADES));
                    //}
                    contador.setText("" + result.getData().getIntExtra(Tienda.KEY_JADES, jades.intValue()));
                    jades = new BigInteger(result.getData().getStringExtra(Tienda.KEY_JADES));

                }
                if (result.getData() != null && result.getData().getIntExtra(Tienda.KEY_INCREMENTAR, 1) != 1) {
                    incrementar = result.getData().getIntExtra(Tienda.KEY_INCREMENTAR, jades.intValue());
                    if (incrementar == 2) {
                        crearHilos();
                    }
                }

                if (result.getData() != null && result.getData().getIntExtra(Tienda.KEY_TICKETS, 0) >= 0) {
                    tickets = result.getData().getIntExtra(Tienda.KEY_TICKETS, 0);
                }
                if (result.getData() != null && result.getData().getIntExtra(Tienda.KEY_COSTO, 12) != 12) {
                    costo = result.getData().getIntExtra(Tienda.KEY_COSTO, 12);
                }
            }
        }
    });


    MediaPlayer player;
    TextView contador;
    Button boton;
    Button reset;
    ImageView save;

    String userText;

    TextView pruebauser;

    //variables a pasar
    BigInteger jades = new BigInteger("0");

    int incrementar = 1;
    int costo = 120;
    int tickets = 0;
    int autoclick = 0;

    //variables iconos
    ImageView jadeic;
    int iconInt;
    int[] iconos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contador = (TextView) findViewById(R.id.textocontador);
        //botonAutoClick = (Button) findViewById(R.id.button3);
        reset = (Button) findViewById(R.id.reset);
        save = findViewById(R.id.save);
        jadeic = (ImageView) findViewById(R.id.jade);
        pruebauser = findViewById(R.id.usuariopr);



        //RECORGER NOMBRE USUARIO
        Intent us = getIntent();
        userText = us.getStringExtra("nombre_usuario");
        pruebauser.setText("Nombre de usuario:"+userText);


        insertcontador(contador);


        //GUARDAR SCORE
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] split = pruebauser.getText().toString().split(":");
                userText = split[1];
                DBHelper db = new DBHelper(v.getContext());
                db.saveDatos(userText,jades.toString(),costo,incrementar,iconInt,autoclick);
            }
        });


        //MUSICA
        player = MediaPlayer.create(this, R.raw.song);
        player.setLooping(true);
        player.start();

        //Listener para el metodo precuacion
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                precaucion();
            }
        });

        actLaucher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String newjades = data.getStringExtra("num");
                            contador.setText(newjades);
                        }
                    }
                });

    }


    public void sumar(View v) {
        ScaleAnimation fade_in = new ScaleAnimation(0.7f, 1.2f, 0.7f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        fade_in.setDuration(100);
        jadeic.startAnimation(fade_in);
        jades = jades.add(BigInteger.valueOf(incrementar));
        contador.setText(FormatoNum(jades));
    }

    public String FormatoNum(BigInteger num) {
        String r = "";
        if (num.compareTo(BigInteger.valueOf(1000)) < 0) {
            r = ("" + num.toString());
        } else if (num.compareTo(BigInteger.valueOf(1000)) > 0 && num.compareTo(BigInteger.valueOf(1000_000)) <= 0) {
            BigInteger mil = num.divide(BigInteger.valueOf(1000));
            r = (mil.toString() + "MIL");
        } else if (num.compareTo(BigInteger.valueOf(1000_000)) > 0 && num.compareTo(BigInteger.valueOf(1000_000_000)) <= 0) {
            BigInteger mill = num.divide(BigInteger.valueOf(1000_000));
            r = (mill.toString() + "K");
        } else if (num.compareTo(BigInteger.valueOf(1000_000_000)) > 0 && num.compareTo(BigInteger.valueOf(1_000_000_000_000l)) <= 0) {
            BigInteger trill = num.divide(BigInteger.valueOf(1000_000_000));
            r = (trill.toString() + "M");
        } else {
            BigInteger trill = num.divide(BigInteger.valueOf(1000_000_000_000l));
            r = (trill.toString() + " ∞");
        }
        return r;
    }

    public void restar(View v) {

        if (jades.compareTo(BigInteger.valueOf(costo)) >= 0) {
            jades = jades.subtract(BigInteger.valueOf(costo));
            incrementar++;
            contador.setText("" + jades);
            costo += 20;
            boton.setText(costo + " jades");
            crearHilos();
        }
    }


    private void precaucion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Deseas resetear el contador?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                jades = BigInteger.ZERO;
                contador.setText(jades.toString());
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


    public void crearHilos() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        autoclick = 1;

        executor.execute(() -> {
            //Background work here
            while (true) {
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
                });
            }
        });
    }

    public void insertcontador(View v){
        DBHelper dbHelper = new DBHelper(v.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select puntos,costo,incremento,icono,autoclick from datos where nombre_usuario=?", new String[]{userText});
        if(cursor.moveToFirst() && cursor.getCount() >= 1){
            int puntosIndex = cursor.getColumnIndex("puntos");
            int costoIndex = cursor.getColumnIndex("costo");
            int incrementoIndex = cursor.getColumnIndex("incremento");
            int iconoIndex = cursor.getColumnIndex("icono");
            int autoclikIndex = cursor.getColumnIndex("autoclick");
            if(cursor.getInt(incrementoIndex)!=0){
                costo = cursor.getInt(costoIndex);
                incrementar = cursor.getInt(incrementoIndex);
                iconInt = cursor.getInt(iconoIndex);
                autoclick = cursor.getInt(autoclikIndex);
                jadeic.setImageResource(iconInt);
            }
            String pts = cursor.getString(puntosIndex);
            Log.d("MiApp", "puntos " + pts);

            //insertar contador y autoclicker
            jades = new BigInteger(pts);
            contador.setText(pts);
            if(autoclick == 1){
                crearHilos();
            }
        }else{
            contador.setText("0");
        }
        }


    public void play(View v) {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.song);
            player.setLooping(true);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    public void stop(View v) {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }


    public void goShop(View v) {

        Intent i = new Intent(this, Tienda.class);
        //set data
        String num_jades = jades.toString();
        i.putExtra("num_jades", jades.toString());
        i.putExtra("tickets", tickets);
        i.putExtra("costo", costo);
        i.putExtra("incrementar", incrementar);
        i.putExtra("icono", iconInt);
        startForResult.launch(i);

    }

    public void goBack(View v) {
        Intent i = new Intent(this, PantallaInicio.class);
        startActivity(i);
        finish();
    }



}