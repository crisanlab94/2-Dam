package com.example.proyecto;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TutorActivity extends AppCompatActivity {

    private Spinner spinnerAsignatura;
    private RecyclerView rvChat;
    private EditText etMensaje, etTema;
    private ImageButton btnEnviar, btnBack;
    private Button btnIniciarGuia;
    private ChatAdapter adapter;
    private List<MensajeChat> listaMensajes;
    private String temaActual = ""; // Variable para que la IA recuerde el tema escrito

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        // 1. Inicializar vistas
        spinnerAsignatura = findViewById(R.id.spinnerAsignatura);
        rvChat = findViewById(R.id.rvChat);
        etMensaje = findViewById(R.id.etMensaje);
        etTema = findViewById(R.id.etTema);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnBack = findViewById(R.id.btnBack);
        btnIniciarGuia = findViewById(R.id.btnIniciarGuia);

        // 2. Rellenar asignaturas específicas
        String[] asignaturas = {"Biologia", "CMC", "Lengua", "Inglés", "Filosofia"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, asignaturas);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAsignatura.setAdapter(spinnerAdapter);

        // 3. Configurar Chat
        listaMensajes = new ArrayList<>();
        adapter = new ChatAdapter(listaMensajes);
        rvChat.setLayoutManager(new LinearLayoutManager(this));
        rvChat.setAdapter(adapter);

        // 4. Lógica del Botón "Iniciar Guía"
        btnIniciarGuia.setOnClickListener(v -> {
            // Guardamos el tema que el usuario ha escrito antes de iniciar
            temaActual = etTema.getText().toString();
            String asignatura = spinnerAsignatura.getSelectedItem().toString();

            // Lógica para abrir el chat con la ayuda de guía paso a paso automáticamente
            iniciarGuiaAutomática(temaActual, asignatura);
        });

        // 5. Botón Enviar (Sin validaciones por diseño [cite: 2026-01-25])
        btnEnviar.setOnClickListener(v -> {
            String texto = etMensaje.getText().toString();
            enviarMensajeUsuario(texto);
            etMensaje.setText("");

            new Handler().postDelayed(() -> responderIA(texto), 1000);
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void iniciarGuiaAutomática(String tema, String asignatura) {
        String mensajeIA;

        if (tema.isEmpty()) {
            mensajeIA = "¡Hola, Cristina! Veo que quieres empezar a estudiar " + asignatura + ". ¿Qué tema específico te gustaría que desglosáramos hoy?";
        } else {
            // Aquí la IA sabe qué tema has escrito y pregunta por el material subido
            mensajeIA = "¡Perfecto! Vamos a iniciar la guía paso a paso sobre '" + tema + "' en " + asignatura + ". " +
                    "¿Te gustaría que utilizara el material que tienes subido en tu biblioteca para personalizar la explicación?";
        }

        listaMensajes.add(new MensajeChat(mensajeIA, MensajeChat.TIPO_IA));
        adapter.notifyDataSetChanged();
        rvChat.scrollToPosition(listaMensajes.size() - 1);
    }

    private void enviarMensajeUsuario(String mensaje) {
        listaMensajes.add(new MensajeChat(mensaje, MensajeChat.TIPO_USUARIO));
        adapter.notifyItemInserted(listaMensajes.size() - 1);
        rvChat.scrollToPosition(listaMensajes.size() - 1);
    }

    private void responderIA(String mensajeUsuario) {
        String respuesta;
        String asignatura = spinnerAsignatura.getSelectedItem().toString();
        String msgLow = mensajeUsuario.toLowerCase();

        // --- FILTRO PEDAGÓGICO REFORZADO ---
        boolean esEducativo = msgLow.contains("explicame") || msgLow.contains("que es") ||
                msgLow.contains("tema") || msgLow.contains("duda") ||
                msgLow.contains("ejercicio") || msgLow.contains("hola") ||
                msgLow.contains("ayuda") || msgLow.contains("si") ||
                msgLow.contains("no") || msgLow.isEmpty();

        if (!esEducativo) {
            respuesta = "Lo siento, como tu tutor pedagógico de " + asignatura + " solo puedo ayudarte con el estudio. Vamos a centrarnos en '" + (temaActual.isEmpty() ? "la materia" : temaActual) + "'.";
        } else if (msgLow.contains("si") || msgLow.contains("material")) {
            respuesta = "Excelente. Estoy conectando con tu biblioteca para extraer los puntos clave de '" + temaActual + "'. ¿Quieres empezar por el resumen o por un esquema de conceptos?";
        } else if (msgLow.contains("hola") || mensajeUsuario.isEmpty()) {
            respuesta = "¡Hola, Cristina! Soy tu tutor. ¿Lista para avanzar con " + asignatura + "?";
        } else {
            respuesta = "Entiendo. Sobre '" + (temaActual.isEmpty() ? mensajeUsuario : temaActual) + "', lo primero que debemos tener claro es la base. ¿Te lo explico paso a paso?";
        }

        listaMensajes.add(new MensajeChat(respuesta, MensajeChat.TIPO_IA));
        adapter.notifyItemInserted(listaMensajes.size() - 1);
        rvChat.scrollToPosition(listaMensajes.size() - 1);
    }
}