package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "STUDY_MATCH_DEBUG";
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Vincular componentes del XML
        Button btnEntrar = findViewById(R.id.btnEntrar);
        Button btnVolver = findViewById(R.id.btnVolverLogin);
        EditText etClave = findViewById(R.id.etLoginClave);
        ImageView ojoLogin = findViewById(R.id.ojoLogin);

        // Acción del botón Entrar
        btnEntrar.setOnClickListener(v -> {

            Log.d(TAG, "El usuario ha simulado su inicio de sesión");


            Intent intent = new Intent(LoginActivity.this, InicioActivity.class);
            startActivity(intent);


            finish();
        });


        ojoLogin.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;

            if (isPasswordVisible) {

                etClave.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                //Icono ojo abierto
                ojoLogin.setImageResource(R.drawable.eye_visible);
            } else {

                etClave.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                //Icono ojo cerrado
                ojoLogin.setImageResource(R.drawable.eye);
            }


            etClave.setSelection(etClave.getText().length());
        });


        btnVolver.setOnClickListener(v -> finish());
    }
}