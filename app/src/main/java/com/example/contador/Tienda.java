package com.example.contador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;

public class Tienda extends AppCompatActivity {
    Button botonAutoClick;
    Button botonTickets;
    TextView jades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        botonAutoClick = (Button) findViewById(R.id.button3);
        botonTickets = (Button) findViewById(R.id.bticono);
        jades = (TextView) findViewById(R.id.cantidad_jades);

        if(extras.isEmpty()){
            //completar en su dia
        }
        else{
            String num = extras.getString("num");
            int costo = extras.getInt("costo");
            int incremento = extras.getInt("incremento");
            int tickets = extras.getInt("tickets");

            BigInteger jades = new BigInteger(num);
            BigInteger precio = new BigInteger(String.valueOf(costo));



            botonAutoClick.setText(costo +"jades");
            botonTickets.setText(tickets + "tickets");
        }
    }





    public void volver(View v){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("num", jades.getText().toString());

        setResult(RESULT_OK,i);
        finish();
    }

}
