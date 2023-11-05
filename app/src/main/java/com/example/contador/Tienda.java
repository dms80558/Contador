package com.example.contador;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



import org.w3c.dom.Text;

import java.util.Random;

public class Tienda extends AppCompatActivity {
    Button botonAutoClick;
    Button botonTickets;
    TextView textvales;
    TextView jades;
    int num_jades=0;
    int[] iconos;
    int vales = 100;
    int iconInt;
    //VARIABLE PARA SABER SI SE PULSO EL BOTON DE CAMBIO DE ICONO
    boolean yes = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        textvales = (TextView) findViewById(R.id.textvales);
        botonTickets = findViewById(R.id.bticono);

        botonAutoClick = (Button) findViewById(R.id.button3);
        botonTickets = (Button) findViewById(R.id.bticono);
        jades = (TextView) findViewById(R.id.cantidad_jades);


        //RECOGER DATOS
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        num_jades =  Integer.parseInt(intent.getExtras().getString("num_jades",""));
        String tickets = intent.getExtras().getString("num_tickets","");
        int precio = intent.getExtras().getInt("precio");


        //INSERTAR DATOS
        jades.setText(""+num_jades);
        botonAutoClick.setText(precio +" jades");
        botonTickets.setText(tickets + "tickets");
        botonTickets.setText(vales + " tickets");
        textvales.setText(""+vales);

    }


  public int getIconint(){
        int r = R.drawable.dandinero;


            //cambiar icono
            iconos= new int[]{R.drawable.sampo2, R.drawable.asta, R.drawable.dandinero,R.drawable.tingyun,
                    R.drawable.danheng2,R.drawable.topaz,R.drawable.jade};
            Random random = new Random();
            int randomIndex = random.nextInt(iconos.length);
            r = iconos[randomIndex];
            //cambiar variables
            vales = 0;
            yes = true;
            // textvales.setText(0);
        iconInt = r;
        return r;
    }



    public void goBack(View v) {
        Intent i = new Intent(this, MainActivity.class);
       i.putExtra("num", jades.getText().toString());
       i.putExtra("iconoint",iconInt);
            //setResult(RESULT_OK, i);
        finish();
        }
}
