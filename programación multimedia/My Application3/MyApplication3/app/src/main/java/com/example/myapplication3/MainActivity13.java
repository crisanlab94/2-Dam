package com.example.myapplication3;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity13 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);

        TextView textoCentral = findViewById(R.id.textoCentral);
        EditText entradaUsuario = findViewById(R.id.entradaUsuario);
        Spinner spinner = findViewById(R.id.spinnerOpciones);
        ProgressBar progreso = findViewById(R.id.progresoDemo);
        Button btnAccion = findViewById(R.id.btnAccion);

        // Spinner con opciones
        String[] opciones = {"Opción A", "Opción B", "Opción C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapter);

        // Acción al pulsar el botón
        btnAccion.setOnClickListener(v -> {
            // 1. Mostrar un Toast con el nombre
            String nombre = entradaUsuario.getText().toString();
            Toast.makeText(this, "Hola " + nombre, Toast.LENGTH_SHORT).show();

            // 2. Incrementar la barra de progreso
            int valor = progreso.getProgress();
            progreso.setProgress((valor + 10) % 100);

            // 3. Cambiar el texto central según el spinner
            String opcion = spinner.getSelectedItem().toString();
            textoCentral.setText("Elegiste: " + opcion);

            // 4. Animar el texto con clip
            Animation animClip = AnimationUtils.loadAnimation(this, R.anim.anim_bounce);
            textoCentral.startAnimation(animClip);
        });
    }
}
