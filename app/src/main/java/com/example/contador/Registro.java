package com.example.contador;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contador.userdata.MyDatabaseHelper;

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
        /*creamos un objeto de la clase DBHelper
         * inicializamos el constructor
         * nombramos la base de datos
         * version de la base de datos*/
        MyDatabaseHelper admin=new MyDatabaseHelper(this);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db = admin.getWritableDatabase();
        /*creamos dos variables string
         * inicializamos y convertimos*/
        String userText=user.getText().toString();
        String passText =password.getText().toString();
        String confipassText = conf_password.getText().toString();

        if(passText.equals(confipassText)) {
            /*Creamos un objeto contentvalues y instanciamos*/
            ContentValues values = new ContentValues();
            /*capturamos valores*/
            values.put("nombre_usuario",userText);
            values.put("contraseña", passText);
            /*llamamos al insert damos el nombre de la base de datos
             * y los valores*/
            db.insert("usuarios",null,values);
            /*cerramos la base de datos*/
            db.close();
            /*Lanzamos una notificacion toast*/
            Toast ToastMens= Toast.makeText(this,"Usuario registrado",Toast.LENGTH_SHORT);
            /*mostramos el toast*/
            ToastMens.show();
            /*lanzamos la actividad*/
            Intent intent=new Intent(this, MainActivity.class);
            /*iniciamos la actividad*/
            startActivity(intent);
        } else{
            Toast toast=Toast.makeText(this,"La contraseña no coincide",Toast.LENGTH_LONG);
            toast.show();
        }


    }


}
