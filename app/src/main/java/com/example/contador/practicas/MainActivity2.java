package com.example.contador.practicas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contador.Info;
import com.example.contador.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    Context context;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView rv = (RecyclerView) findViewById(R.id.itemlist);

        List<Personajes> p = new ArrayList<Personajes>();
        p.add(new Personajes("Dan Heng", R.drawable.danheng2));
        p.add(new Personajes("Sampo", R.drawable.sampo2));
        p.add(new Personajes("Asta", R.drawable.asta));
        p.add(new Personajes("Pom-pom", R.drawable.pompom));
        p.add(new Personajes("Tingyun", R.drawable.tingyun));
        p.add(new Personajes("Yanquing", R.drawable.yanqing));
        p.add(new Personajes("Siete de marzo", R.drawable.sietedemarzo));
        p.add(new Personajes("Topaz", R.drawable.topaz));

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new PersonajesAdapter(context,p));
        t =  (TextView) findViewById(R.id.moreInfo);

    }


    public boolean onContextItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case 101:
                Snackbar.make(findViewById(R.id.rootid),"Opcion ns selecionada",Snackbar.LENGTH_LONG).show();
                return true;
            case 102:
                Snackbar.make(findViewById(R.id.rootid),"Eliminado",Snackbar.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void moreInfo(View v){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hsr.hoyoverse.com/es-es/character?worldIndex=6"));
        startActivity(i);
    }

    public void goBack(View v){
        Intent back = new Intent(this, Info.class);
        finish();
    }

}
