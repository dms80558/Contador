package com.example.contador;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        confimar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Registro.this);
                myDB.addUser(user.getText().toString().trim(),password.getText().toString().trim());
            }
        });
    }
}
