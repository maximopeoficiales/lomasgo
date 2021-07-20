package com.maximoprog.lomasgo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.maximoprog.lomasgo.databinding.ActivityMainBinding;
import com.maximoprog.lomasgo.enviroments.Credentials;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        fuerza que no se vea la barra superior
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//           setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

//    asignacion de animaciones
        binding.esloganTextView.setAnimation(animation2);
        binding.titleLogoTextView.setAnimation(animation2);
        binding.logoImageView.setAnimation(animation);
//    temporizador para cargar la siguiente actividad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, Credentials.TIME_OUT_SPLASH);
    }
}