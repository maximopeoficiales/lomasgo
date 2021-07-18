package com.maximoprog.lomasgo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.maximoprog.lomasgo.models.Service;
import com.maximoprog.lomasgo.utils.Alert;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Context context;
    Service service;
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    //    maps
    private GoogleMap mMap;
    private FusedLocationProviderClient mfusedFusedLocationProviderClient;
    //    btns
    private Button type1Button;
    private Button type2Button;
    private Location mlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        context = this;
        getServiceExtra();
        cargarListeners();

    }

    private void getLatLongActual(GoogleMap googleMap) {
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
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            return;
        }
        mfusedFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mlocation = location;
                    Alert.showMessageSuccess(context, "LAT: " + location.getLatitude() + " Long: " + location.getLongitude());
                    Log.e("Latitud:", location.getLatitude() + " longitude: " + location.getLongitude());
                    cargarMarcadores(googleMap);
                }
            }

        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Alert.showMessageError(context, e.getMessage());

            }
        });
    }

    private void getServiceExtra() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.get("service") != null) {
            service = bundle.getParcelable("service");

//            Alert.showMessageSuccess(this, "Se cargo correctamente el Mapa");
        } else {
            Alert.showMessageError(this, "El Servicio no existe");
        }

    }

    private void cargarListeners() {
        type1Button = (Button) findViewById(R.id.sateliteBtn);
        type2Button = (Button) findViewById(R.id.hibridoBtn);
        type1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
        type2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//        obtengo los datos del extra
        mMap = googleMap;
        getLatLongActual(googleMap);
    }

    public void cargarMarcadores(GoogleMap googleMap) {

        // Add a marker in Sydney and move the camera
//        LatLng ubicacionServicio = new LatLng(Double.parseDouble(service.getLatitude()), Double.parseDouble(service.getLongitude()));
        LatLng ubicacionServicio = new LatLng(mlocation.getLatitude(),mlocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(
                ubicacionServicio).title(service.getName())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacionServicio));
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

// Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(ubicacionServicio)      // Sets the center of the map to Mountain View
                .zoom(15)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}