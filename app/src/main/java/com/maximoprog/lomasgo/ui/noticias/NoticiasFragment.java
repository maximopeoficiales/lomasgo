package com.maximoprog.lomasgo.ui.noticias;

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

import com.maximoprog.lomasgo.NoticiaDetalleActivity;
import com.maximoprog.lomasgo.api.services.NewService;
import com.maximoprog.lomasgo.databinding.FragmentNewsBinding;
import com.maximoprog.lomasgo.models.New;
import com.maximoprog.lomasgo.ui.adapters.NewAdapter;
import com.maximoprog.lomasgo.utils.Alert;
import com.maximoprog.lomasgo.utils.HandlerUtilitity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NoticiasFragment extends Fragment {
    private static final String TAG = NoticiasFragment.class.getName();
    public final CompositeDisposable disposables = new CompositeDisposable();
    public NewService newService = new NewService();
    public Context context;
    FragmentNewsBinding binding;
    private NoticiasViewModel noticiasViewModel;

    //    adaptador
    private RecyclerView recyclerView;
    private NewAdapter newAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        noticiasViewModel = new ViewModelProvider(this).get(NoticiasViewModel.class);
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        context = container.getContext();
//        instancia de adaptador
        newAdapter = new NewAdapter(context, new NewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(New noticia, int position) {
//                onClickItem(position);
                Intent intent = new Intent(
                        context, NoticiaDetalleActivity.class
                );
//                le pasa el item osea el objecto como parametro
                intent.putExtra("new", noticia);
//                inicia la actividad
                startActivity(intent);
            }
        });
        this.binding.newsRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.binding.newsRV.setLayoutManager(layoutManager);
        this.binding.newsRV.setAdapter(newAdapter);
        this.binding.newsRV.setItemAnimator(new DefaultItemAnimator());
//        se ejecutara a los 3500
        HandlerUtilitity.setTimeOut(new Runnable() {
            @Override
            public void run() {
                //        obtengo todas la noticias
                getNews();
            }
        }, 2500);

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        if (disposables != null) {
            disposables.clear();
        }
        super.onDestroy();
    }

    public void getNews() {
        newService.getNews().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.rxjava3.core.Observer<List<New>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<New> news) {
                        newAdapter.addNews(news);
                        Alert.showMessageSuccess(context, "Existen " + news.size() + " NOTICIAS");
                        binding.paperOpenLottie.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d(TAG, "Estoy en onERROR");
                        Alert.showMessageError(context, "Ocurrio un error" + e.getMessage() + " NOTICIAS");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onClickItem(int position) {
        Alert.showMessage(context, "Es el: " + position);
    }
}