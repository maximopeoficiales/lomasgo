package com.maximoprog.lomasgo.ui.home;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.maximoprog.lomasgo.api.services.NewService;
import com.maximoprog.lomasgo.databinding.FragmentHomeBinding;
import com.maximoprog.lomasgo.models.New;
import com.maximoprog.lomasgo.ui.adapters.SliderNoticiaAdapter;
import com.maximoprog.lomasgo.utils.Alert;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getName();
    public final CompositeDisposable disposables = new CompositeDisposable();
    public Context context;
    FragmentHomeBinding binding;
    SliderNoticiaAdapter sliderNoticiaAdapter;
    private NewService newService = new NewService();
    private HomeViewModel homeViewModel;

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

//        HandlerUtilitity.setTimeOut(new Runnable() {
//            @Override
//            public void run() {
//                //        obtengo todas la noticias
//            }
//        }, Credentials.TIME_OUT);
        getNews();

        return binding.getRoot();


    }

    public void getNews() {
        newService.getNewsTop(5).subscribeOn(Schedulers.io())
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


    @Override
    public void onDestroy() {
        if (disposables != null) {
            disposables.clear();
        }
        super.onDestroy();
    }
}