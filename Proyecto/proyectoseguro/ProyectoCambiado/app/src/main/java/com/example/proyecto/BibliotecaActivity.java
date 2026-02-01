package com.example.proyecto;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class BibliotecaActivity extends AppCompatActivity {

    private static final String TAG = "BIBLIOTECA_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca);

        // Volver atrás
        ImageView btnBack = findViewById(R.id.btnBackBiblioteca);
        btnBack.setOnClickListener(v -> finish());

        // Botón subir
        findViewById(R.id.btnSubirArchivo).setOnClickListener(v -> {
            Log.d(TAG, "Simulación: Subiendo archivo a la biblioteca...");
        });

    }
}