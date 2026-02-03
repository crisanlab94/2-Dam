package com.example.proyecto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Locale;

public class PerfilActivity extends AppCompatActivity {

    private static final String TAG = "STUDY_MATCH_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Configurar Spinner "Tipo de Entidad"
        Spinner spinnerEntidad = findViewById(R.id.spinnerTipoEntidad);
        if (spinnerEntidad != null) {
            String[] opciones = {"Instituto", "Universidad", "Academia", "Otros"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEntidad.setAdapter(adapter);
        }

        // Selector de Fecha de Nacimiento
        EditText etFecha = findViewById(R.id.etFechaNacPerfil);
        if (etFecha != null) {
            etFecha.setFocusable(false);
            etFecha.setOnClickListener(v -> abrirCalendario(etFecha));
        }

        // Botón Volver
        View btnBack = findViewById(R.id.btnBackPerfil);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Botón de Ayuda
        View btnAyuda = findViewById(R.id.btnAyudaHeader);
        if (btnAyuda != null) {
            btnAyuda.setOnClickListener(v -> {
                Log.d(TAG, "Ayuda solicitada: contacto@studymatch.com");
                mostrarNotificacionPremium("📧 Soporte: contacto@studymatch.com");
            });
        }

        // Botón Actualizar
        View btnActualizar = findViewById(R.id.btnActualizarPerfil);
        if (btnActualizar != null) {
            btnActualizar.setOnClickListener(v -> {
                Log.d(TAG, "BOTÓN ACTUALIZAR: Simulación de guardado (sin cambios reales).");
            });
        }

        // Botón Eliminar
        View btnEliminar = findViewById(R.id.btnEliminarPerfil);
        if (btnEliminar != null) {
            btnEliminar.setOnClickListener(v -> {
                Log.d(TAG, "BOTÓN ELIMINAR: Simulación de borrado. ");
            });
        }

        configurarNavegacionInferior();


        EditText etClave = findViewById(R.id.etClavePerfil);
        if (etClave != null) {
            etClave.setText("Cristina31@");
        }
    }

    private void abrirCalendario(EditText et) {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            et.setText(String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, (month + 1), year));
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void configurarNavegacionInferior() {
        View btnCalendario = findViewById(R.id.btnNavCalendario);
        if (btnCalendario != null) {
            btnCalendario.setOnClickListener(view -> {
                PopupMenu popup = new PopupMenu(PerfilActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.menu_calendario, popup.getMenu());
                popup.show();
            });
        }

        View btnBiblio = findViewById(R.id.btnNavBiblioteca);
        if (btnBiblio != null) btnBiblio.setOnClickListener(v -> mostrarNotificacionPremium("📚 Biblioteca en desarrollo"));

        View btnMetodos = findViewById(R.id.btnNavMetodos);
        if (btnMetodos != null) btnMetodos.setOnClickListener(v -> mostrarNotificacionPremium("📚 Accediendo a métodos"));

        View btnPerfil = findViewById(R.id.btnNavPerfil);
        if (btnPerfil != null) btnPerfil.setOnClickListener(v -> mostrarNotificacionPremium("👤 Ya estás en tu perfil"));
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