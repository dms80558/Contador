package com.example.contador.practicas;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contador.R;

import java.util.List;

public class PersonajesAdapter extends RecyclerView.Adapter<PersonajesAdapter.ViewHolder>{
    List<Personajes> personajes;
    Context context;


    public PersonajesAdapter(Context context,List<Personajes> modelList){
        this.context = context;
        personajes = modelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.informacion, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(personajes.get(position));
    }

    @Override
    public int getItemCount() {
        return personajes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "MyViewHolder";
        private final TextView nombre;
        private final ImageView imagen;
        CardView cardView;

        ViewHolder(@NonNull View v){
            super(v);
            nombre = v.findViewById(R.id.nombreper);
            imagen =  v.findViewById(R.id.personimg);
            cardView = v.findViewById(R.id.card);


        }



        public void bind(Personajes personajes){
            nombre.setText(personajes.getNombre());
            imagen.setImageResource(personajes.getImagen());
        }
    }


}
