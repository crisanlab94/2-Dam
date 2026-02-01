package com.example.proyecto;
import android.os.Bundle;
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

    // Vistas
    private EditText etTema, etMensaje;
    private Spinner spinnerAsignatura;
    private Button btnIniciarGuia;
    private ImageButton btnEnviar, btnBack;
    private RecyclerView rvChat;

    // Componentes del Chat
    private List<MensajeChat> listaMensajes;
    private ChatAdapter adapter;

    // Mensaje de restricción para temas no educativos
    private final String RESPUESTA_RESTRICTIVA = "He sido creado para ayudarte en tu educación, no en otros temas. Por favor, céntrate en estudiar.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        // 1. Inicializar vistas
        vincularVistas();

        // 2. Configurar el sistema de chat
        configurarChat();

        // 3. Listeners
        configurarListeners();
    }

    private void vincularVistas() {
        etTema = findViewById(R.id.etTema);
        etMensaje = findViewById(R.id.etMensaje);
        spinnerAsignatura = findViewById(R.id.spinnerAsignatura);
        btnIniciarGuia = findViewById(R.id.btnIniciarGuia);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnBack = findViewById(R.id.btnBack);
        rvChat = findViewById(R.id.rvChat);
    }

    private void configurarChat() {
        listaMensajes = new ArrayList<>();
        adapter = new ChatAdapter(listaMensajes);
        rvChat.setLayoutManager(new LinearLayoutManager(this));
        rvChat.setAdapter(adapter);
    }

    private void configurarListeners() {
        // Botón Volver
        btnBack.setOnClickListener(v -> finish());

        // Botón Iniciar Guía
        btnIniciarGuia.setOnClickListener(v -> {
            String tema = etTema.getText().toString();
            iniciarFlujoIA(tema);
        });

        // Botón Enviar Mensaje
        btnEnviar.setOnClickListener(v -> {
            String texto = etMensaje.getText().toString();
            procesarMensajeUsuario(texto);
        });
    }

    private void iniciarFlujoIA(String tema) {
        String saludoIA = "¡Hola! Vamos a trabajar el tema: " + (tema.isEmpty() ? "tu estudio" : tema) +
                ". ¿Te gustaría usar algún material que hayas subido previamente o empezamos de cero?";
        agregarMensajeALista(saludoIA, MensajeChat.TIPO_IA);
    }

    private void procesarMensajeUsuario(String texto) {
        if (texto.trim().isEmpty()) return;

        // Añadir mensaje del usuario
        agregarMensajeALista(texto, MensajeChat.TIPO_USUARIO);
        etMensaje.setText("");

        // Lógica de respuesta de la IA con filtro educativo
        if (esEducativo(texto)) {
            // Aquí llamamos a la API real. Por ahora, simulamos respuesta.

            agregarMensajeALista("Entiendo tu duda. Sigamos con el paso a paso del método de estudio...", MensajeChat.TIPO_IA);
        } else {
            // Restricción estricta si no es educativo
            agregarMensajeALista(RESPUESTA_RESTRICTIVA, MensajeChat.TIPO_IA);
        }
    }

    private boolean esEducativo(String texto) {

        String t = texto.toLowerCase();
        return t.contains("estudiar") || t.contains("examen") || t.contains("tema") ||
                t.contains("si") || t.contains("no") || t.contains("aprender") ||
                t.contains("duda") || t.contains("explicamelo") || t.contains("material");
    }

    private void agregarMensajeALista(String texto, int tipo) {
        listaMensajes.add(new MensajeChat(texto, tipo));


        adapter.notifyItemInserted(listaMensajes.size() - 1);
        rvChat.scrollToPosition(listaMensajes.size() - 1);
    }
}