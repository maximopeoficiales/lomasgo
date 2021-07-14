package com.maximoprog.lomasgo.ui.info;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.maximoprog.lomasgo.api.services.InfoService;
import com.maximoprog.lomasgo.databinding.FragmentInfoBinding;
import com.maximoprog.lomasgo.enviroments.Credentials;
import com.maximoprog.lomasgo.models.ImageSite;
import com.maximoprog.lomasgo.models.Info;
import com.maximoprog.lomasgo.utils.Alert;
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
        }, 2000);
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
}