package com.example.proyecto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NuevoEstudianteActivity extends AppCompatActivity {

    private EditText etNombre, etEmail, etFecha, etMovil, etClave, etClaveConfirm, etNombreEntidad, etMunicipioEntidad;
    private Spinner spEntidad, spCurso, spModalidad;
    private LinearLayout layoutModalidad, contenedorCheckboxes;
    private Button btnAgregar, btnVolver;
    private ImageView ojoClave, ojoClaveConfirm;
    private boolean esClaveVisible = false;
    private boolean esClaveConfirmVisible = false;

    private Map<String, Map<String, Object>> datosMaster = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_estudiante);

        inicializarDatosAsignaturas();
        vincularComponentes();
        configurarSpinnersIniciales();
        configurarEventos();

        // Botón siempre activo (sin validaciones)
        btnAgregar.setEnabled(true);
        btnAgregar.setAlpha(1.0f);

        // AL PULSAR CREAR CUENTA -> IR A LOGIN
        btnAgregar.setOnClickListener(v -> {
            Log.d("STUDY_MATCH", "Registro simulado. Redirigiendo a Login...");
            Intent intent = new Intent(NuevoEstudianteActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Cerramos esta pantalla para que no pueda volver atrás al registro
        });

        btnVolver.setOnClickListener(v -> finish());
    }

    private void vincularComponentes() {
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etFecha = findViewById(R.id.etFecha);
        etMovil = findViewById(R.id.etMovil);
        etNombreEntidad = findViewById(R.id.etNombreEntidad);
        etMunicipioEntidad = findViewById(R.id.etMunicipioEntidad);
        etClave = findViewById(R.id.etClave);
        etClaveConfirm = findViewById(R.id.etClaveConfirm);
        spEntidad = findViewById(R.id.spEntidad);
        spCurso = findViewById(R.id.spCurso);
        spModalidad = findViewById(R.id.spModalidad);
        layoutModalidad = findViewById(R.id.layoutModalidad);
        contenedorCheckboxes = findViewById(R.id.contenedorCheckboxes);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnVolver = findViewById(R.id.btnVolver);
        ojoClave = findViewById(R.id.ojoClave);
        ojoClaveConfirm = findViewById(R.id.ojoClaveConfirm);
    }

    private void configurarSpinnersIniciales() {
        String[] entidades = {"Selecciona...", "Colegio", "Instituto"};
        spEntidad.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, entidades));

        String[] mods = {"Ciencias y Tecnología", "Humanidades y Ciencias Sociales", "Artes"};
        spModalidad.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mods));
    }

    private void configurarEventos() {
        etFecha.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, y, m, d) -> etFecha.setText(d + "/" + (m + 1) + "/" + y),
                    c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        spEntidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> p, View v, int pos, long id) {
                String ent = spEntidad.getSelectedItem().toString();
                layoutModalidad.setVisibility(ent.equals("Instituto") ? View.VISIBLE : View.GONE);
                actualizarCursos(ent);
            }
            @Override public void onNothingSelected(AdapterView<?> p) {}
        });

        AdapterView.OnItemSelectedListener refreshAsig = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> p, View v, int pos, long id) { generarCheckboxes(); }
            @Override public void onNothingSelected(AdapterView<?> p) {}
        };
        spCurso.setOnItemSelectedListener(refreshAsig);
        spModalidad.setOnItemSelectedListener(refreshAsig);

        ojoClave.setOnClickListener(v -> {
            esClaveVisible = !esClaveVisible;
            togglePassword(etClave, ojoClave, esClaveVisible);
        });
        ojoClaveConfirm.setOnClickListener(v -> {
            esClaveConfirmVisible = !esClaveConfirmVisible;
            togglePassword(etClaveConfirm, ojoClaveConfirm, esClaveConfirmVisible);
        });
    }

    private void togglePassword(EditText et, ImageView img, boolean visible) {
        et.setInputType(visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        img.setImageResource(visible ? R.drawable.eye_visible : R.drawable.eye);
        et.setSelection(et.getText().length());
    }

    private void actualizarCursos(String entidad) {
        List<String> lista = new ArrayList<>();
        lista.add("Selecciona curso...");
        if (entidad.equals("Colegio")) {
            lista.addAll(Arrays.asList("5º Primaria", "6º Primaria"));
        } else if (entidad.equals("Instituto")) {
            lista.addAll(Arrays.asList("1º ESO", "2º ESO", "3º ESO", "4º ESO", "1º Bachillerato", "2º Bachillerato"));
        }
        spCurso.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lista));
    }

    private void generarCheckboxes() {
        contenedorCheckboxes.removeAllViews();
        String ent = spEntidad.getSelectedItem().toString();
        if (spCurso.getSelectedItem() == null) return;
        String cur = spCurso.getSelectedItem().toString();
        if (cur.equals("Selecciona curso...") || ent.contains("Selecciona")) return;

        List<String> asigsFinales = new ArrayList<>();
        Object cursoData = datosMaster.get(ent).get(cur);

        if (cursoData instanceof List) {
            asigsFinales.addAll((List<String>) cursoData);
        } else if (cursoData instanceof Map) {
            Map<String, List<String>> bach = (Map<String, List<String>>) cursoData;
            asigsFinales.addAll(bach.get("Comunes"));

            String modText = spModalidad.getSelectedItem().toString();
            String claveMod = "Artes";
            if (modText.contains("Ciencias")) claveMod = "Ciencias";
            if (modText.contains("Humanidades")) claveMod = "Sociales";

            if (bach.containsKey(claveMod)) asigsFinales.addAll(bach.get(claveMod));
        }

        for (String a : asigsFinales) {
            CheckBox cb = new CheckBox(this);
            cb.setText(a);
            cb.setChecked(true);
            contenedorCheckboxes.addView(cb);
        }
    }

    private void inicializarDatosAsignaturas() {
        // COLEGIO
        Map<String, Object> col = new HashMap<>();
        col.put("5º Primaria", Arrays.asList("Lengua Castellana", "Matemáticas", "Ciencias Naturaleza", "Ciencias Sociales", "Inglés", "Educación Física", "Religión", "Valores"));
        col.put("6º Primaria", Arrays.asList("Lengua Castellana", "Matemáticas", "Ciencias Naturaleza", "Ciencias Sociales", "Inglés", "Educación Física", "Religión", "Valores Cívicos"));
        datosMaster.put("Colegio", col);

        // INSTITUTO
        Map<String, Object> ins = new HashMap<>();
        ins.put("1º ESO", Arrays.asList("Lengua", "Matemáticas", "Inglés", "Biología", "Geografía", "Educación Física", "Música", "Plástica"));
        ins.put("2º ESO", Arrays.asList("Lengua", "Matemáticas", "Inglés", "Física y Química", "Geografía", "Educación Física", "Tecnología"));
        ins.put("3º ESO", Arrays.asList("Lengua", "Matemáticas", "Inglés", "Biología", "Física y Química", "Geografía", "Educación Física"));
        ins.put("4º ESO", Arrays.asList("Lengua", "Inglés", "Geografía", "Matemáticas", "Educación Física", "Biología", "Física", "Economía", "Latín"));

        // 1º BACHILLERATO
        Map<String, List<String>> b1 = new HashMap<>();
        b1.put("Comunes", Arrays.asList("Lengua I", "Inglés I", "Filosofía", "Educación Física"));
        b1.put("Ciencias", Arrays.asList("Matemáticas I", "Biología y Geología", "Física y Química", "Dibujo Técnico I"));
        b1.put("Sociales", Arrays.asList("Matemáticas Aplicadas I", "Economía", "Historia Contemporánea", "Latín I"));
        b1.put("Artes", Arrays.asList("Dibujo Artístico I", "Cultura Audiovisual", "Volumen", "Análisis Musical I"));
        ins.put("1º Bachillerato", b1);

        // 2º BACHILLERATO
        Map<String, List<String>> b2 = new HashMap<>();
        b2.put("Comunes", Arrays.asList("Lengua II", "Inglés II", "Historia de España", "Filosofía II"));
        b2.put("Ciencias", Arrays.asList("Matemáticas II", "Biología", "Física", "Química"));
        b2.put("Sociales", Arrays.asList("Matemáticas Aplicadas II", "Economía de Empresa", "Geografía", "Latín II"));
        b2.put("Artes", Arrays.asList("Dibujo Artístico II", "Diseño", "Cultura Audiovisual II", "Artes Escénicas"));
        ins.put("2º Bachillerato", b2);

        datosMaster.put("Instituto", ins);
    }
}