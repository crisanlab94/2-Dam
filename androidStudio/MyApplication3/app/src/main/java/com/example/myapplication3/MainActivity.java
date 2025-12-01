package com.example.myapplication3;

// IMPORTS de todos los widgets que usamos
import android.os.Bundle;
import android.widget.TextView;      // Para mostrar texto
import android.widget.EditText;     // Para cajas de texto
import android.widget.Button;       // Para botones
import android.widget.Spinner;      // Para listas desplegables
import android.widget.ArrayAdapter; // Para llenar el Spinner con datos
import android.widget.CheckBox;     // Para casillas de verificación
import android.widget.RadioGroup;   // Para agrupar RadioButtons
import android.widget.RadioButton;  // Para botones de selección única
import android.widget.Toast;        // Para mensajes emergentes

import androidx.appcompat.app.AppCompatActivity; // Clase base de actividades

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enlazamos esta clase con el layout XML
        setContentView(R.layout.activity_main);

        // TextView: muestra texto fijo
        TextView miTexto = findViewById(R.id.texto);
        miTexto.setText("Texto cambiado desde Java");

        // EditText: caja de texto
        EditText entrada = findViewById(R.id.miEditText);

        // Button: al pulsarlo muestra lo que escribiste en el EditText
        Button btn = findViewById(R.id.miBoton);
        btn.setOnClickListener(v -> {
            String valor = entrada.getText().toString();
            miTexto.setText("Has escrito: " + valor);
            Toast.makeText(this, "Texto mostrado", Toast.LENGTH_SHORT).show();
        });

        // Spinner: lista desplegable
        Spinner spinner = findViewById(R.id.miSpinner);
        String[] opciones = {"Opción 1", "Opción 2", "Opción 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapter);

        // CheckBox: casilla de verificación
        CheckBox check = findViewById(R.id.miCheckBox);
        check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "CheckBox marcado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "CheckBox desmarcado", Toast.LENGTH_SHORT).show();
            }
        });

        // RadioGroup: conjunto de RadioButtons
        RadioGroup grupo = findViewById(R.id.miGrupo);
        grupo.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio1) {
                Toast.makeText(this, "Seleccionaste Radio 1", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radio2) {
                Toast.makeText(this, "Seleccionaste Radio 2", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
