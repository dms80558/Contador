package com.example.contador;

import android.content.Intent;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaInicio extends AppCompatActivity {
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallainico);
        player = MediaPlayer.create(this,R.raw.song);
        player.setLooping(true);
        player.start();
    }
    public void play(View v){
       if(player == null){
           player = MediaPlayer.create(this,R.raw.song);
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
    public void stop(View v){
        stopPlayer();
    }
    private void stopPlayer() {
        if(player != null){
            player.release();
            player = null;
            Toast.makeText(this, "Ns", Toast.LENGTH_SHORT);
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        stopPlayer();
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
        Intent i = new Intent(this, IniciarSesion.class);
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
