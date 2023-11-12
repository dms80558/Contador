package com.example.contador;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
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
                if (result.getData() != null && result.getData().getIntExtra(Tienda.KEY_NAME, R.drawable.jade) != 0) {
                    jadeic.setImageResource(result.getData().getIntExtra(Tienda.KEY_NAME, R.drawable.jade));
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

    //Button icono;

    //variables a pasar
    BigInteger jades = new BigInteger("0");

    int incrementar = 1;
    int costo = 120;
    int tickets = 0;

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
        //NO TE CARRULA LA IMAGEN
        jadeic = (ImageView) findViewById(R.id.jade);

        contador.setText("" + jades);
        contador.setText("" + jades);


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
        //int n = Integer.parseInt(contador.getText().toString()) +1;
        jades = jades.add(BigInteger.valueOf(incrementar));
        //num += incrementar;
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