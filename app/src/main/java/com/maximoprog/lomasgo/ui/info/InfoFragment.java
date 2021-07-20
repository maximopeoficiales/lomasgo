package com.maximoprog.lomasgo.ui.info;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.maximoprog.lomasgo.api.services.InfoService;
import com.maximoprog.lomasgo.databinding.FragmentInfoBinding;
import com.maximoprog.lomasgo.enviroments.Credentials;
import com.maximoprog.lomasgo.models.ImageSite;
import com.maximoprog.lomasgo.models.Info;
import com.maximoprog.lomasgo.utils.Alert;
import com.maximoprog.lomasgo.utils.GoogleMaps;
import com.maximoprog.lomasgo.utils.HandlerUtilitity;
import com.squareup.picasso.Picasso;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class InfoFragment extends Fragment {
    private static final String TAG = InfoFragment.class.getName();
    public final CompositeDisposable disposables = new CompositeDisposable();
    public Context context;
    FragmentInfoBinding binding;
    InfoService infoService = new InfoService();
    Info minfo;
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    private FusedLocationProviderClient mfusedFusedLocationProviderClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_info, container, false);
        binding = FragmentInfoBinding.inflate(inflater, container, false);
//        oculto el infoCard
        binding.infoCard.setVisibility(View.GONE);
        context = container.getContext();
        HandlerUtilitity.setTimeOut(new Runnable() {
            @Override
            public void run() {
                //        obtengo todas la noticias
                getInfo();
            }
        }, Credentials.TIME_OUT);
        binding.irBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLatLongActual();
            }
        });
        return binding.getRoot();
    }

    public void getInfo() {
        infoService.getInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Info>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Info info) {
                        minfo = info;
                        Alert.showMessageSuccess(context, "Cargado Satisficatorio");
                        binding.infoOpenLottie.setVisibility(View.GONE);
                        ImageSite imageSite = info.getImageSite();
                        String urlImagen = "";
                        if (imageSite == null) {
                            urlImagen = Credentials.URI_IMAGE_NOT_FOUND;
                        } else {
                            urlImagen = Credentials.URL_API + imageSite.getUrl();
                        }

                        Picasso.with(context)
                                .load(urlImagen)
                                .fit().into(binding.imagenInfoCard);
                        binding.titleInfoCard.setText(info.getNameSite());
                        binding.historyInfoCard.setText(info.getHistoryContent());
                        binding.infoCard.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "Estoy en onERROR");
                        Alert.showMessageError(context, "Ocurrio un error" + e.getMessage() + " NOTICIAS");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroy() {
        if (disposables != null) {
            disposables.clear();
        }
        super.onDestroy();
    }

    private void getLatLongActual() {
        mfusedFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            return;
        }
        mfusedFusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
//                    Alert.showMessageSuccess(context, "LAT: " + location.getLatitude() + " Long: " + location.getLongitude());
                    Intent intent = GoogleMaps.cargarRuta(location.getLatitude(), location.getLongitude(), Double.parseDouble(minfo.getLatitude()), Double.parseDouble(minfo.getLongitude()));
                    startActivity(intent);
                } else {
                    Double lat = Double.parseDouble(minfo.getLatitude());
                    Double log = Double.parseDouble(minfo.getLongitude());
                    Intent intent = GoogleMaps.cargarRuta(Credentials.LAT_HOME, Credentials.LONG_HOME, lat, log);

//                    Alert.showMessageError(context, "No cargo las coordenadas, reinicie su Ubicacion por Favor");
                    startActivity(intent);
                }
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@androidx.annotation.NonNull Exception e) {
                Alert.showMessageError(context, e.getMessage());
            }
        });
    }
}