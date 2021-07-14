package com.maximoprog.lomasgo;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.maximoprog.lomasgo.databinding.ActivityServiceDetalleBinding;
import com.maximoprog.lomasgo.enviroments.Credentials;
import com.maximoprog.lomasgo.models.Image;
import com.maximoprog.lomasgo.models.Service;
import com.maximoprog.lomasgo.utils.Alert;
import com.squareup.picasso.Picasso;

public class ServiceDetalleActivity extends AppCompatActivity {
    ActivityServiceDetalleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityServiceDetalleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        virificacion de bundle

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.get("service") != null) {
            Service service = bundle.getParcelable("service");
            Image serviceImage = service.getImage();
//          muestro un msg y seteo el texto en el input
            String urlImagen = "";
            if (serviceImage == null) {
                urlImagen = Credentials.URI_IMAGE_NOT_FOUND;
            } else {
                urlImagen = Credentials.URL_API + serviceImage.getUrl();
            }

            Picasso.with(this)
                    .load(urlImagen)
                    .fit().into(binding.imagenServiceCard);
            binding.titleNewCard.setText(service.getName());
            binding.descriptionNewCard.setText(service.getDescription());

            Alert.showMessageSuccess(this, "Se cargo correctamente el Servicio");

        } else {
            Alert.showMessageError(this, "El Servicio no existe");
        }
    }
}