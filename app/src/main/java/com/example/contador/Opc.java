package com.example.contador;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Opc extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
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

    public void irWeb(View v){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/guide/components/intents-filters?hl=es-419"));
        startActivity(i);
    }
}
