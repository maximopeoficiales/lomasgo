package com.maximoprog.lomasgo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.maximoprog.lomasgo.databinding.ActivityServiceDetalleBinding;
import com.maximoprog.lomasgo.enviroments.Credentials;
import com.maximoprog.lomasgo.models.Image;
import com.maximoprog.lomasgo.models.Service;
import com.maximoprog.lomasgo.utils.Alert;
import com.squareup.picasso.Picasso;

public class ServiceDetalleActivity extends AppCompatActivity {
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS ;
    ActivityServiceDetalleBinding binding;
    Context context;
    Service service;
    private FusedLocationProviderClient mfusedFusedLocationProviderClient;
    private Location mlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityServiceDetalleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        context = this;
        setContentView(view);

//        virificacion de bundle

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.get("service") != null) {
             service = bundle.getParcelable("service");
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

        binding.irServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLatLongActual();
            }
        });


    }
    private void getLatLongActual() {
        mfusedFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(ServiceDetalleActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            return;
        }
        mfusedFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mlocation = location;
                    Alert.showMessageSuccess(context, "LAT: " + location.getLatitude() + " Long: " + location.getLongitude());
                    Log.e("Latitud:", location.getLatitude() + " longitude: " + location.getLongitude());

                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr="+location.getLatitude()+","+location.getLongitude()+"&daddr="+service.getLatitude()+","+service.getLongitude()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER );
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);

                }else {
                    Alert.showMessageError(context, "No cargo las coordenadas");
                }
            }

        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Alert.showMessageError(context, e.getMessage());

            }
        });
    }
}