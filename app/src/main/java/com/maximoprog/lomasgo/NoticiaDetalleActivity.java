package com.maximoprog.lomasgo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.maximoprog.lomasgo.databinding.ActivityNoticiaDetalleBinding;
import com.maximoprog.lomasgo.enviroments.Credentials;
import com.maximoprog.lomasgo.models.Image;
import com.maximoprog.lomasgo.models.New;
import com.maximoprog.lomasgo.utils.Alert;
import com.squareup.picasso.Picasso;

public class NoticiaDetalleActivity extends AppCompatActivity {
    ActivityNoticiaDetalleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_noticia_detalle);

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_new_details);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityNoticiaDetalleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.get("new") != null) {
            New noticia = bundle.getParcelable("new");
            Image imageNoticia = noticia.getImage();
//          muestro un msg y seteo el texto en el input
            String urlImagen = "";
            if (imageNoticia == null) {
                urlImagen = Credentials.URI_IMAGE_NOT_FOUND;
            } else {
                urlImagen = Credentials.URL_API + imageNoticia.getUrl();
            }

            Picasso.with(this)
                    .load(urlImagen)
                    .fit().into(binding.imagenNewCard);
            binding.titleNewCard.setText(noticia.getTitle());
            binding.sumaryNewCard.setText(noticia.getSummary());
            binding.descriptionNewCard.setText(noticia.getContent());
            Alert.showMessageSuccess(this, "Se cargo correctamente la noticia");

        } else {
            Alert.showMessageError(this, "La noticia es Nula");
        }
    }
}