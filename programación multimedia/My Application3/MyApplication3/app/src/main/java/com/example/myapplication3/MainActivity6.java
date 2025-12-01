package com.example.myapplication3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        EditText entradaNombre = findViewById(R.id.entradaNombre);
        Spinner spinner = findViewById(R.id.spinnerColores);
        Button btnEnviar = findViewById(R.id.btnEnviar);

        String[] colores = {"Rojo", "Verde", "Azul"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, colores);
        spinner.setAdapter(adapter);

        btnEnviar.setOnClickListener(v -> {
            String nombre = entradaNombre.getText().toString();
            String color = spinner.getSelectedItem().toString();
            Toast.makeText(this, "Hola " + nombre + ", tu color favorito es " + color,
                    Toast.LENGTH_LONG).show();
        });
    }
}
