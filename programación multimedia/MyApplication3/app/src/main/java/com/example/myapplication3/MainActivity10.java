package com.example.myapplication3;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity10 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        EditText entradaNombre = findViewById(R.id.entradaNombre);
        Spinner spinner = findViewById(R.id.spinnerColores);
        CheckBox checkNoticias = findViewById(R.id.checkNoticias);
        RadioGroup grupoGenero = findViewById(R.id.grupoGenero);
        SeekBar seekEdad = findViewById(R.id.seekEdad);
        Button btnAnimar = findViewById(R.id.btnAnimar);
        TextView textoTitulo = findViewById(R.id.textoTitulo);

        // Spinner con opciones
        String[] colores = {"Rojo", "Verde", "Azul"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, colores);
        spinner.setAdapter(adapter);

        // CheckBox
        checkNoticias.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(this, isChecked ? "Noticias activadas" : "Noticias desactivadas",
                    Toast.LENGTH_SHORT).show();
        });

        // RadioGroup
        grupoGenero.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioHombre) {
                Toast.makeText(this, "Seleccionaste Hombre", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radioMujer) {
                Toast.makeText(this, "Seleccionaste Mujer", Toast.LENGTH_SHORT).show();
            }
        });

        // SeekBar
        seekEdad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity10.this, "Edad: " + progress, Toast.LENGTH_SHORT).show();
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Botón con animación
        btnAnimar.setOnClickListener(v -> {
            Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
            textoTitulo.startAnimation(animRotate);
        });
    }
}
