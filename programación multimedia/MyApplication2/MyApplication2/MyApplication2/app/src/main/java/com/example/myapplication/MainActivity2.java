package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad1);
        Bundle b = getIntent().getExtras();
        Log.i("Datos", b.getString("Surname"));

        TextView miTexto = findViewById(R.id.textView);
        miTexto.setTextColor(getResources().getColor(R.color.principal));


        miTexto.setText("Animaciooon");
        Animation miAnimacion = AnimationUtils.loadAnimation(this, R.anim.animacion);
        miAnimacion.setRepeatMode(Animation.RESTART);
        miAnimacion.setRepeatCount(20);
        miTexto.setAnimation(miAnimacion);




        /*
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fuenteRobotic.ttf");
        textView.setTypeface(typeface);
        */





    }
}
