package com.example.ud3_ejemplo7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String CLAVE = "CONTADOR"; // Nombre de la clave.
    private int cont = 0; // Valor de la clave.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont++;

                TextView textview = findViewById(R.id.textViewContador);

                textview.setText("Contador: " + Integer.toString(cont));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Guardamos el estado usando el par clave/valor
        // Usamos putInt ya que cont es entero.
        outState.putInt(CLAVE, cont);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Recuperamos el valor en función de su clave.
        // Usamos getInt ya que cont es entero.
        cont = savedInstanceState.getInt(CLAVE);
    }
}
