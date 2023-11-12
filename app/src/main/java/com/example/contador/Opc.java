package com.example.contador;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Opc extends AppCompatActivity {
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        player = MediaPlayer.create(this,R.raw.song);
        player.setLooping(true);
        player.start();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionmenu, menu);
        return true;
    }

    //metodo para asignar funciones
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id == R.id.item1){
            Toast.makeText(this,"Opcion 1", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.item2){
            Toast.makeText(this,"Opcion 2", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item3) {
            Toast.makeText(this,"Opcion 3", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }




    //metodos para reproduccion musica
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
    //fin metodos para reproduccion musica


    public void goBack(View v) {
        Intent i = new Intent(this, PantallaInicio.class);
        startActivity(i);
        finish();
    }

}
