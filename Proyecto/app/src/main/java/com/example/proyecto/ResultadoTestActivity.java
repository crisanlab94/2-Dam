package com.example.proyecto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadoTestActivity extends AppCompatActivity {

    private static final String TAG = "STUDY_MATCH_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_test);

        // Botón volver
        ImageView btnBack = findViewById(R.id.btnBackResultado);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        // Lógica del Botón GUARDAR PLAN
        Button btnGuardar = findViewById(R.id.btnGuardarPlan);
        btnGuardar.setOnClickListener(v -> {
            // LOG PARA CONSOLA
            Log.d(TAG, "El estudiante ha simulado guardar su metodo de estudio");


            mostrarNotificacionPremium("✨ ¡Plan guardado con éxito! ✨");

            // ESPERAR 3 SEGUNDOS Y REDIRIGIR A INICIO
            new Handler(Looper.getMainLooper()).postDelayed(() -> {

                Intent intent = new Intent(ResultadoTestActivity.this, InicioActivity.class);


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                //3 segundos de espera
            }, 3000);
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