package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private TextView miTexto;
    private Button botonCambiar;
    private Button botonVolver;
    private EditText numeroEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad4);
        Bundle b = getIntent().getExtras();
        Log.i("Datos", b.getString("Surname"));


        numeroEditText = findViewById(R.id.numeroEditText);

        // Enfoca el EditText y abre el teclado numérico automáticamente
        numeroEditText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
/*
        miTexto = findViewById(R.id.textView);
        botonCambiar = findViewById(R.id.botonCambiar);
        botonVolver = findViewById(R.id.botonVolver);


        botonCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miTexto.setTextColor(getResources().getColor(R.color.principal));
            }
        });


        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miTexto.setTextColor(getResources().getColor(R.color.rosa));
            }
        });


        miTexto.setTextColor(getResources().getColor(R.color.rosa));  // color inicial

        Animation miAnimacion = AnimationUtils.loadAnimation(this, R.anim.animacion4);
        miAnimacion.setRepeatMode(Animation.RESTART);
        miAnimacion.setRepeatCount(20);
        miTexto.startAnimation(miAnimacion);

    }



    /*

    public void cambiarColor(View view) {
        TextView miTexto = findViewById(R.id.textView);
        miTexto.setTextColor(getResources().getColor(R.color.principal));
    }


    public void volverColor(View view) {
        miTexto.setTextColor(getResources().getColor(R.color.rosa));
    }
    */

    /*

    Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fuenteRobotic.ttf");
    textView.setTypeface(typeface);
    */





