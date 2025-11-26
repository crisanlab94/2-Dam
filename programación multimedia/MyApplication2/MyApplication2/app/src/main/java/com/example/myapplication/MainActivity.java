package com.example.myapplication;

import static android.Manifest.permission.CALL_PHONE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CAMERA_PERMISSION = 1;

    //pedir permisos en onCreate.
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1);





        /*
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            // Si ya tiene permiso, abrir la cámara
            abrirCamara();
        } else {
            // Si no tiene permiso, solicitarlo
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        }

*/

        /*
        setContentView(R.layout.activity_actividad1);
        Intent ejemplo = new Intent(this, MainActivity2.class);
        startActivity(ejemplo);
*/

/*
   Intent intentWeb = new Intent(Intent.ACTION_VIEW);
    intentWeb.setData(Uri.parse("https://blogsaverroes.juntadeandalucia.es/iestorrredelosguzmanes/"));
    startActivity(intentWeb);
*/
/*
        Intent intentDial = new Intent(Intent.ACTION_CALL);
        intentDial.setData(Uri.parse("tel:123456789")); // Reemplaza con el número real
        startActivity(intentDial);
        */

        /*
        Intent ejemploFoto= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(ejemploFoto);
*/


    }

    //Ejecutamos
    /*
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Entrada", "Estoy en onStart2");

        Intent ejemploLlamar = new Intent(Intent.ACTION_CALL);
        ejemploLlamar.setData(Uri.parse("tel:123456789"));
        if(ContextCompat.checkSelfPermission(getApplicationContext(),CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
            startActivity(ejemploLlamar);
        }
        else{
            requestPermissions(new String[] {CALL_PHONE},1);
        }

          }
          */
    /*
          private void realizarLlamada(){
              Log.i("Entrada", "Realizando llamada...");
              Intent ejemploLlamar = new Intent(Intent.ACTION_CALL);
        ejemploLlamar.setData(Uri.parse());

          }*/

    @Override
    protected void onStart() {
        // Verificar si el permiso de cámara está concedido
        super.onStart();
        Intent ejemplillo = new Intent(this, MainActivity2.class);
        ejemplillo.putExtra("Surname", "García Méndez");
        startActivity(ejemplillo);
    }
    /*
    private void abrirCamara() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); // Acción para abrir la cámara
        startActivity(intent);  // Lanzar la actividad de la cámara
    }

*/


    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
    }
*/

    /*
    Ejecutamos
    @Override
    protected void onStart() {
        super.onStart();
    }

    Lo vemos
    @Override
    protected void onResume() {
        //creacion de intents, que pasa cuando está activa.
        super.onResume();
    }

Lo paramos y recuperamos rápido
    @Override
    //se para
    protected void onPause() {

        super.onPause();
    }


    Lo paramos y tendremos que hacer un restart o stop
    @Override
    protected void onStop() {
        //no visible para el usuario
        super.onStop();
    }

    @Override
    protected void onRestart() {
        //vuelve a llamar al primer plano, se sigue de un start.
        super.onRestart();
    }

Finaliza la aplicación.
    @Override
    protected void onDestroy() {
        //es destruida y eliminada.
        super.onDestroy();
    }
*/
    /*
    int permisionCheck = ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.Camera);
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int [] grantResults){
        switch (requestCode){
            case MY_PEMISSIONS_CAMERA:{
                if (grantResults.length>0 && grantResults[0])==PackageManager.PERMISSION_GRANTED){
                    //Código de permiso aceptado
                } else{
                    //Código de permiso denegado
                }
                return;
            }
        }
    }
*/
}