package com.example.proyecto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        TextView tvSaludo = findViewById(R.id.tvSaludoInicio);
        tvSaludo.setText("¡Hola, Cristina!");

        ProgressBar progressBar = findViewById(R.id.pbProgreso);
        progressBar.setProgress(70);

        LinearLayout btnRealizarTest = findViewById(R.id.btnRealizarTest);
        btnRealizarTest.setOnClickListener(v -> {
            Intent intent = new Intent(InicioActivity.this, TestEstudioActivity.class);
            startActivity(intent);
        });

        View calendarCard = findViewById(R.id.calendarCard);
        calendarCard.setOnClickListener(v -> {
            Intent intent = new Intent(InicioActivity.this, EventosActivity.class);
            startActivity(intent);
        });

        //  Menú desplegable
        View btnNavCalendario = findViewById(R.id.btnNavCalendario);
        btnNavCalendario.setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(InicioActivity.this, view);
            popup.getMenuInflater().inflate(R.menu.menu_calendario, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.opcion_añadir_evento) {
                    Log.d("STUDY_MATCH_DEBUG", "El usuario entra para añadir un nuevo evento");
                    startActivity(new Intent(InicioActivity.this, EventosActivity.class));
                    return true;
                } else if (id == R.id.opcion_mis_eventos) {
                    Log.d("STUDY_MATCH_DEBUG", "El usuario ha simulado que entra en sus eventos");
                    mostrarNotificacionPremium("✨ Mostrando tus eventos... ✨");
                    return true;
                }
                return false;
            });
            popup.show();
        });

        // Biblioteca
        findViewById(R.id.btnNavBiblioteca).setOnClickListener(v -> {
            Log.d("STUDY_MATCH_DEBUG", "Abriendo la pantalla de Biblioteca");
            Intent intent = new Intent(InicioActivity.this, BibliotecaActivity.class);
            startActivity(intent);
        });

        // Métodos
        findViewById(R.id.btnNavMetodos).setOnClickListener(v -> {
            Log.d("STUDY_MATCH_DEBUG", "El estudiante ha simulado que va sus métodos");
            mostrarNotificacionPremium("📚 Accediendo a mis métodos");
        });

        // Perfil
        findViewById(R.id.btnNavPerfil).setOnClickListener(v -> {
            Log.d("STUDY_MATCH_DEBUG", "Abriendo la pantalla de Perfil");
            Intent intent = new Intent(InicioActivity.this, PerfilActivity.class);
            startActivity(intent);
        });
    }

    private void mostrarNotificacionPremium(String mensaje) {
        TextView tv = new TextView(this);
        tv.setText(mensaje);
        tv.setTextSize(20);
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setTypeface(null, android.graphics.Typeface.BOLD);
        tv.setBackgroundResource(R.drawable.custom_toast_bg);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(tv);
        toast.setGravity(Gravity.TOP, 0, 150);
        toast.show();
    }
}