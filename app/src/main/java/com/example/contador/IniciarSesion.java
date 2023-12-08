package com.example.contador;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contador.userdata.MyDatabaseHelper;

public class IniciarSesion extends AppCompatActivity {
    EditText usuario, contraseña;
    Cursor fila;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inciarsesion);
        usuario = findViewById(R.id.user);
        contraseña = findViewById(R.id.password);
        DB = new DBHelper(this);
    }

    public void inicarSesion(View v) {
         /*Creamos un objeto de la clase DBHelper e
        instanciamos el constructor y damos el nonbre de
         la base de datos y la version*/

        //DBHelper admin = new DBHelper(this);

        /*Abrimos la base de datos como escritura*/
        SQLiteDatabase db = DB.getWritableDatabase();
        String userText = usuario.getText().toString();
        String passText = contraseña.getText().toString();
        /*inicializamos al cursor y llamamos al objeto de la base
        de datos para realizar un sentencia query where donde
         pasamos las dos variables nombre de usuario y password*/


        fila = db.rawQuery("select nombre_usuario,contraseña from datos where nombre_usuario='" +
                userText + "' and contraseña='" + passText + "'", null);
        /*Realizamos un try catch para captura de errores*/

        if (TextUtils.isEmpty(userText) || TextUtils.isEmpty(passText))
            Toast.makeText(IniciarSesion.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        else {
            Boolean checkuser = DB.checkUsername(userText);
            Boolean checkpass = DB.checkPassword(passText);
            Boolean checkuserNamePassword = DB.checkuserNamePassword(userText,passText);

            if (checkuserNamePassword==true) {
                Toast.makeText(IniciarSesion.this, "Se ha inciciado sesión", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("nombre_usuario",userText);
                startActivity(intent);
            } else {
                Toast.makeText(IniciarSesion.this, "No se ha podido iniciar sesión", Toast.LENGTH_SHORT).show();
            }


        }

    }
    public void goRegistro (View v){
        Intent i = new Intent(IniciarSesion.this, Registro.class);
        startActivity(i);
    }
}











