package com.example.contador;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contador.practicas.MainActivity2;

public class Info extends AppCompatActivity {
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        player = MediaPlayer.create(this,R.raw.song);
        player.setLooping(true);
        player.start();
    }



    public void volver(View v){
        Intent i = new Intent(this, PantallaInicio.class);
        finish();
    }

    public void goRecycle(View v){
       Intent p = new Intent(this, com.example.contador.practicas.MainActivity2.class );
       startActivity(p);
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
}
