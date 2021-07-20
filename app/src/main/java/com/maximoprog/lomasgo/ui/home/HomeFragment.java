package com.maximoprog.lomasgo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.maximoprog.lomasgo.ServiceDetalleActivity;
import com.maximoprog.lomasgo.api.services.NewService;
import com.maximoprog.lomasgo.api.services.ServiceService;
import com.maximoprog.lomasgo.databinding.FragmentHomeBinding;
import com.maximoprog.lomasgo.models.New;
import com.maximoprog.lomasgo.models.Service;
import com.maximoprog.lomasgo.ui.adapters.CardServiceAdapter;
import com.maximoprog.lomasgo.ui.adapters.SliderNoticiaAdapter;
import com.maximoprog.lomasgo.utils.Alert;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getName();
    public final CompositeDisposable disposables = new CompositeDisposable();
    public final CompositeDisposable disposables2 = new CompositeDisposable();
    public Context context;
    FragmentHomeBinding binding;
    //    adaptadores
    SliderNoticiaAdapter sliderNoticiaAdapter;
    CardServiceAdapter cardServiceAdapter;
    private HomeViewModel homeViewModel;
    //    servicios
    private NewService newService = new NewService();
    private ServiceService newServicio = new ServiceService();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        context = container.getContext();

        sliderNoticiaAdapter = new SliderNoticiaAdapter(context);


        binding.imageSliderNoticias.setSliderAdapter(sliderNoticiaAdapter);
        binding.imageSliderNoticias.setIndicatorAnimation(IndicatorAnimationType.WORM);
        binding.imageSliderNoticias.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        binding.imageSliderNoticias.startAutoCycle();
//        otro adaptador
        cardServiceAdapter = new CardServiceAdapter(context, new CardServiceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Service service, int position) {
                Intent intent = new Intent(
                        context, ServiceDetalleActivity.class
                );
                intent.putExtra("service", service);
                startActivity(intent);
            }
        });

        this.binding.rvCardService.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        this.binding.rvCardService.setLayoutManager(layoutManager);
        this.binding.rvCardService.setAdapter(cardServiceAdapter);
        this.binding.rvCardService.setItemAnimator(new DefaultItemAnimator());

//        HandlerUtilitity.setTimeOut(new Runnable() {
//            @Override
//            public void run() {
//                //        obtengo todas la noticias
//            }
//        }, Credentials.TIME_OUT);
        getNews();
        getServices();

        return binding.getRoot();


    }

    public void getNews() {
        newService.getNewsTop(5, "created_at:DESC").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.rxjava3.core.Observer<List<New>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<New> news) {
                        sliderNoticiaAdapter.addNews(news);

//                        Alert.showMessageSuccess(context, "Existen " + news.size() + " NOTICIAS");
//                        cargarImagesSliders(news);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Alert.showMessageError(context, "Ocurrio un error" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getServices() {
        newServicio.getServicesTop(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Service>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Service> services) {
                        cardServiceAdapter.addServices(services);
                        Alert.showMessageSuccess(context, "Existen " + services.size() + " Servicios");
//                        binding.servicesOpenLottie.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//                        Log.d(TAG, "Estoy en onERROR");
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
        if (disposables2 != null) {
            disposables2.clear();
        }
        super.onDestroy();
    }
}