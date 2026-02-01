package com.example.proyecto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Locale;

public class TestEstudioActivity extends AppCompatActivity {

    private static final String TAG = "STUDY_MATCH_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_estudio);


        configurarSpinners();

        // Botón Mostrar
        Button btnMostrar = findViewById(R.id.btnMostrarMetodo);
        btnMostrar.setOnClickListener(v -> {
            Log.d(TAG, "El estudiante ha simulado su metodo de estudio");
            startActivity(new Intent(this, ResultadoTestActivity.class));
        });

        // 3. Botón volver
        findViewById(R.id.btnBackTest).setOnClickListener(v -> finish());

        // --- FUNCIONALIDAD BARRA DE NAVEGACIÓN INFERIOR ---

        // Calendario (Con Popup)
        View btnNavCalendario = findViewById(R.id.btnNavCalendario);
        btnNavCalendario.setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(TestEstudioActivity.this, view);
            popup.getMenuInflater().inflate(R.menu.menu_calendario, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.opcion_añadir_evento) {
                    Log.d(TAG, "Navegando a Añadir Evento desde Test");
                    startActivity(new Intent(TestEstudioActivity.this, EventosActivity.class));
                    return true;
                } else if (id == R.id.opcion_mis_eventos) {
                    Log.d(TAG, "Simulando Mis Eventos desde Test");
                    mostrarNotificacionPremium("✨ Mostrando tus eventos... ✨");
                    return true;
                }
                return false;
            });
            popup.show();
        });

        // Biblioteca
        findViewById(R.id.btnNavBiblioteca).setOnClickListener(v -> {
            Log.d(TAG, "Accediendo a Biblioteca desde la pantalla de Test");
            Intent intent = new Intent(TestEstudioActivity.this, BibliotecaActivity.class);
            startActivity(intent);
        });

        // Métodos
        findViewById(R.id.btnNavMetodos).setOnClickListener(v -> {
            Log.d("STUDY_MATCH_DEBUG", "El estudiante ha simulado que va sus métodos");
            mostrarNotificacionPremium("📚 Accediendo a mis métodos");
        });

        // Perfil
        findViewById(R.id.btnNavPerfil).setOnClickListener(v -> {
            Log.d(TAG, "Abriendo el Perfil desde el Test");
            Intent intent = new Intent(TestEstudioActivity.this, PerfilActivity.class);
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

    private void configurarSpinners() {
        Spinner spAsignatura = findViewById(R.id.spAsignaturaTest);
        String[] materias = {"Matemáticas", "Física", "Historia", "Programación", "Inglés", "Biología"};
        ArrayAdapter<String> adapterAsig = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materias);
        adapterAsig.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spAsignatura != null) spAsignatura.setAdapter(adapterAsig);

        Spinner spTipo = findViewById(R.id.spTipoExamenTest);
        String[] tipos = {"Teórico: Datos y memoria", "Ciencias: Problemas y fórmulas", "Análisis: Redacción y comentario", "Idiomas: Gramática y expresión", "Práctico / Creativo"};
        ArrayAdapter<String> adapterTipo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spTipo != null) spTipo.setAdapter(adapterTipo);
    }
}