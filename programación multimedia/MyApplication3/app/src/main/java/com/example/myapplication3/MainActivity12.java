package com.example.myapplication3;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity12 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        TextView textoTitulo = findViewById(R.id.textoTitulo);
        ImageView imagenDemo = findViewById(R.id.imagenDemo);
        EditText entradaNombre = findViewById(R.id.entradaNombre);
        CheckBox checkActivar = findViewById(R.id.checkActivar);
        SeekBar seekControl = findViewById(R.id.seekControl);
        Button btnAccion = findViewById(R.id.btnAccion);

        // Acción al pulsar el botón
        btnAccion.setOnClickListener(v -> {
            // 1. Mostrar un Toast con el nombre
            String nombre = entradaNombre.getText().toString();
            Toast.makeText(this, "Hola " + nombre, Toast.LENGTH_SHORT).show();

            // 2. Cambiar color del título si el CheckBox está marcado
            if (checkActivar.isChecked()) {
                textoTitulo.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            } else {
                textoTitulo.setTextColor(getResources().getColor(R.color.textoGrid));
            }

            // 3. Mostrar valor del SeekBar en el título
            int valor = seekControl.getProgress();
            textoTitulo.setText("Valor SeekBar: " + valor);

            // 4. Animar la imagen con alpha+rotate
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_rotate);
            imagenDemo.startAnimation(anim);
        });
    }
}
