package com.example.contador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class Tienda extends AppCompatActivity {
    Button botonAutoClick;
    Button botonTickets;
    TextView jades;
    int num_jades=0;
    int[] iconos;
    int tickets = 0;
    int iconInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

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
        jades.setText(num_jades);
        botonAutoClick.setText(precio +" jades");
        botonTickets.setText(tickets + "tickets");

    }


   public int getIconint(){
        int r=0;
        if(num_jades>10){
            //cambiar variables
            tickets -= 10 ;
            //cambiar icono
            iconos= new int[]{R.drawable.sampo2, R.drawable.asta, R.drawable.dandinero,R.drawable.tingyun,
                    R.drawable.danheng2,R.drawable.topaz,R.drawable.jade};
            Random random = new Random();
            int randomIndex = random.nextInt(iconos.length);
            r = iconos[randomIndex];
            //jadeic.setImageResource(iconos[randomIndex]);
        }
        return r;
    }


    public void goBack(View v) {
        iconInt = getIconint();
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("num", jades.getText().toString());
        i.putExtra("iconoint",iconInt);
            //setResult(RESULT_OK, i);
            finish();
        }
}
