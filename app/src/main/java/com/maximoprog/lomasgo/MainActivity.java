package com.maximoprog.lomasgo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        fuerza que no se vea la barra superior
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView esloganTextView = findViewById(R.id.esloganTextView);
        TextView titleLogoTextView = findViewById(R.id.titleLogoTextView);
        ImageView logoImageView = findViewById(R.id.logoImageView);
//    asignacion de animaciones
        esloganTextView.setAnimation(animation2);
        titleLogoTextView.setAnimation(animation2);
        logoImageView.setAnimation(animation);
//    temporizador para cargar la siguiente actividad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}