package com.example.myapplication3;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Aquí cargas el layout activity_main2.xml
        setContentView(R.layout.activity_main2);

        // Referencias a los controles definidos en activity_main2.xml
        ImageView imagen = findViewById(R.id.imagen);
        Button btnAnimar = findViewById(R.id.btnAnimar);

        // Al pulsar el botón, se aplica la animación a la imagen
        btnAnimar.setOnClickListener(v -> {
            Animation animScale = AnimationUtils.loadAnimation(this, R.anim.animation_scale);
            imagen.startAnimation(animScale);
        });
    }
}
