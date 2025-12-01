package com.example.myapplication3;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity11 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        TextView textoArribaIzquierda = findViewById(R.id.textoArribaIzquierda);
        EditText entradaCentro = findViewById(R.id.entradaCentro);
        Spinner spinner = findViewById(R.id.spinnerAbajoIzquierda);
        SeekBar seekBar = findViewById(R.id.seekAbajoDerecha);
        Button btnAnimar = findViewById(R.id.btnCentroAbajo);

        // Spinner con opciones
        String[] opciones = {"Rojo", "Verde", "Azul"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapter);

        // SeekBar listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textoArribaIzquierda.setText("Valor SeekBar: " + progress);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Botón con animación
        btnAnimar.setOnClickListener(v -> {
            Animation animScale = AnimationUtils.loadAnimation(this, R.anim.animation_translate);
            textoArribaIzquierda.startAnimation(animScale);
        });
    }
}
