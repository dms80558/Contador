package com.example.contador;

import android.content.Intent;
import android.database.Cursor;
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

    public void inicarSesion (View v){
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
        fila = db.rawQuery("select nombre_usuario,contraseña from usuarios where username='"+
                userText+"' and contraseña='"+passText+"'",null);
        /*Realizamos un try catch para captura de errores*/




            if (TextUtils.isEmpty(userText) || TextUtils.isEmpty(passText))
                Toast.makeText(IniciarSesion.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuser = DB.checkUsername(userText);
                Boolean checkpass = DB.checkPassword(passText);



                if (checkuser == true&&checkpass == true) {
                    Toast.makeText(IniciarSesion.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(IniciarSesion.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }





            /*Condicional if preguntamos si cursor tiene algun dato*/
            /*if(fila.moveToFirst()){
                //capturamos los valores del cursos y lo almacenamos en variable
                String usua=fila.getString(1);
                String pass=fila.getString(2);
                //preguntamos si los datos ingresados son iguales
                if (userText.equals(usua)&&passText.equals(pass)){
                    //si son iguales entonces vamos a otra ventana
                    //Menu es una nueva actividad empty
                    Intent ven=new Intent(this, MainActivity.class);
                    //lanzamos la actividad
                    startActivity(ven);
                    //limpiamos las las cajas de texto
                    usuario.setText("");
                    contraseña.setText("");
                }
            }//si la primera condicion no cumple entonces que envie un mensaje toast
            else {
                Toast toast=Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_LONG);
                //mostramos el toast
                toast.show();
            }*/

        }

    }




    public void goRegistro(View v){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }




}
