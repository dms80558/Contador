package com.example.contador;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Registro extends AppCompatActivity {


    EditText user, password,conf_password;
    Button confimar;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        conf_password = findViewById(R.id.confim_password);
        confimar = findViewById(R.id.registarse);
        DB = new DBHelper(this);


    }


    public void RegistrarDataUser(View v){
        DBHelper admin=new DBHelper(this);
        SQLiteDatabase db = admin.getWritableDatabase();
        String userText=user.getText().toString();
        String passText =password.getText().toString();
        String confipassText = conf_password.getText().toString();

        Cursor cursor = db.rawQuery("select nombre_usuario from datos where nombre_usuario=?", new String[]{userText});

        if(cursor.getCount()==0&&passText.equals(confipassText)) {
            String pts = "0";
            ContentValues values = new ContentValues();
            values.put("nombre_usuario",userText);
            values.put("contraseña", passText);
            values.put("puntos",pts);
            db.insert("datos",null,values);
            db.close();
            Toast ToastMens= Toast.makeText(this,"Usuario registrado",Toast.LENGTH_SHORT);
            ToastMens.show();
            Intent intent=new Intent(this, MainActivity.class);
            intent.putExtra("nombre_usuario",userText);
            startActivity(intent);
        } else{
            if(cursor.getCount()>0){
                Toast toast=Toast.makeText(this,"Es nombre de usuario ya existe",Toast.LENGTH_LONG);
                toast.show();
                user.setText("");
                password.setText("");
                conf_password.setText("");

            }
            else {
                Toast toast = Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_LONG);
                toast.show();
            }
        }


    }


}
