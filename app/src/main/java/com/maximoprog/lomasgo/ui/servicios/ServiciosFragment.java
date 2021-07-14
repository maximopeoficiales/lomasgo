package com.maximoprog.lomasgo.ui.servicios;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maximoprog.lomasgo.ServiceDetalleActivity;
import com.maximoprog.lomasgo.api.services.ServiceService;
import com.maximoprog.lomasgo.databinding.FragmentServicesBinding;
import com.maximoprog.lomasgo.models.Service;
import com.maximoprog.lomasgo.ui.adapters.ServiceAdapter;
import com.maximoprog.lomasgo.utils.Alert;
import com.maximoprog.lomasgo.utils.HandlerUtilitity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ServiciosFragment extends Fragment {
    private static final String TAG = ServiciosFragment.class.getName();
    public final CompositeDisposable disposables = new CompositeDisposable();
    public Context context;
    FragmentServicesBinding binding;
    private ServiciosViewModel serviciosViewModel;
    //    adaptador
    private RecyclerView recyclerView;
    private ServiceAdapter newAdapter;
    //    servicio
    private ServiceService serviceService = new ServiceService();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        serviciosViewModel = new ViewModelProvider(this).get(ServiciosViewModel.class);
        binding = FragmentServicesBinding.inflate(inflater, container, false);
        context = container.getContext();
        chargingAdapter();
        return binding.getRoot();

    }

    public void chargingAdapter() {
        newAdapter = new ServiceAdapter(context, new ServiceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Service service, int position) {
//                onClickItem(position);
                Intent intent = new Intent(
                        context, ServiceDetalleActivity.class
                );
//                le pasa el item osea el objecto como parametro
                intent.putExtra("service", service);
//                inicia la actividad
                startActivity(intent);
            }
        });
        this.binding.servicesRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.binding.servicesRV.setLayoutManager(layoutManager);
        this.binding.servicesRV.setAdapter(newAdapter);
        this.binding.servicesRV.setItemAnimator(new DefaultItemAnimator());
//        se ejecutara a los 3500
        HandlerUtilitity.setTimeOut(new Runnable() {
            @Override
            public void run() {
                //        obtengo todas la noticias
                getServices();
            }
        }, 2500);
    }


    public void getServices() {
        serviceService.getServices()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Service>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Service> services) {
                        newAdapter.addServices(services);
                        Alert.showMessageSuccess(context, "Existen " + services.size() + " Servicios");
                        binding.servicesOpenLottie.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d(TAG, "Estoy en onERROR");
                        Alert.showMessageError(context, "Ocurrio un error" + e.getMessage());
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


}