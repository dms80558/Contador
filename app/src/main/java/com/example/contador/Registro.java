package com.example.contador;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {


    EditText user, password,conf_password;
    Button confimar;
    DBHelper DB;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inciarsesion);

        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        conf_password = findViewById(R.id.confim_password);
        confimar = findViewById(R.id.registarse);
        DB = new DBHelper(this);
    }
}
