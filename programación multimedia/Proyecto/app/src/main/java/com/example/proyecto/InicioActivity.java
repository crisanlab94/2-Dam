package com.example.proyecto;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        TextView tvSaludo = findViewById(R.id.tvSaludo);
        tvSaludo.setText("¡Hola, Cristina!");

        ProgressBar progressBar = findViewById(R.id.pbProgreso);
        progressBar.setProgress(70);
    }
}