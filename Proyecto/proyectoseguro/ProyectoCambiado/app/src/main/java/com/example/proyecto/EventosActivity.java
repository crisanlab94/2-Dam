package com.example.proyecto;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Locale;

public class EventosActivity extends AppCompatActivity {

    private static final String TAG = "STUDY_MATCH_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        // Configurar el Spinner de tipos de examen
        Spinner spinner = findViewById(R.id.spTipoExamenEvento);
        if (spinner != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.opciones_eventos, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }

        // Botón Volver
        ImageView btnBack = findViewById(R.id.btnBackEventos);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        // Selectores de Fecha y Hora
        TextView tvFecha = findViewById(R.id.tvFechaEvento);
        if (tvFecha != null) tvFecha.setOnClickListener(v -> abrirCalendario(tvFecha));

        TextView tvHora = findViewById(R.id.tvHoraEvento);
        if (tvHora != null) tvHora.setOnClickListener(v -> abrirReloj(tvHora));

        // Botón GUARDAR
        Button btnGuardar = findViewById(R.id.btnGuardarEvento);
        if (btnGuardar != null) {
            btnGuardar.setOnClickListener(v -> {
                Log.d(TAG, "El usuario ha simulado que guarda un evento");
                mostrarNotificacionPremium("✨ El evento ha sido guardado correctamente ✨");

                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    Intent intent = new Intent(EventosActivity.this, InicioActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }, 3000);
            });
        }

        //Configuración de la Barra de Navegación Inferior
        configurarNavegacionInferior();
    }

    private void configurarNavegacionInferior() {
        // Calendario - PopupMenu
        View btnNavCalendario = findViewById(R.id.btnNavCalendario);
        if (btnNavCalendario != null) {
            btnNavCalendario.setOnClickListener(view -> {
                PopupMenu popup = new PopupMenu(EventosActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.menu_calendario, popup.getMenu());
                popup.setOnMenuItemClickListener(item -> {
                    int id = item.getItemId();
                    if (id == R.id.opcion_añadir_evento) {
                        mostrarNotificacionPremium("📝 Ya estás en la pantalla de añadir evento");
                        return true;
                    } else if (id == R.id.opcion_mis_eventos) {
                        Log.d(TAG, "Simulando Mis Eventos desde pantalla de añadir");
                        mostrarNotificacionPremium("✨ Mostrando tus eventos... ✨");
                        return true;
                    }
                    return false;
                });
                popup.show();
            });
        }

        // Biblioteca
        View btnBiblio = findViewById(R.id.btnNavBiblioteca);
        if (btnBiblio != null) {
            btnBiblio.setOnClickListener(v -> {
                Log.d(TAG, "Abriendo la pantalla de Biblioteca desde Eventos");
                Intent intent = new Intent(EventosActivity.this, BibliotecaActivity.class);
                startActivity(intent);
            });
        }

        //  Métodos
        View btnMetodos = findViewById(R.id.btnNavMetodos);
        if (btnMetodos != null) {
            btnMetodos.setOnClickListener(v -> {
                Log.d(TAG, "El estudiante ha simulado que va sus métodos");
                mostrarNotificacionPremium("📚 Accediendo a mis métodos");
            });
        }

        // Perfil
        View btnPerfil = findViewById(R.id.btnNavPerfil);
        if (btnPerfil != null) {
            btnPerfil.setOnClickListener(v -> {
                Log.d(TAG, "Abriendo el Perfil desde Eventos");
                Intent intent = new Intent(EventosActivity.this, PerfilActivity.class);
                startActivity(intent);
            });
        }
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

    private void abrirCalendario(TextView tv) {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            tv.setText(String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, (month + 1), year));
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void abrirReloj(TextView tv) {
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            tv.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }
}