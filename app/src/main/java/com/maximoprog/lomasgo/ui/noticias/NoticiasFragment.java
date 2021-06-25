package com.maximoprog.lomasgo.ui.noticias;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.maximoprog.lomasgo.R;
import com.maximoprog.lomasgo.api.services.NewService;
import com.maximoprog.lomasgo.models.New;

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
    private NoticiasViewModel noticiasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        noticiasViewModel =
                new ViewModelProvider(this).get(NoticiasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        context = container.getContext();
        final TextView textView = root.findViewById(R.id.text_notifications);
        noticiasViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
//        obtengo todas la noticias
        getNews();
        return root;
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
                        Log.d(TAG, "Estoy en onNext");
                        for (New noticia : news) {
                            Log.d(TAG, noticia.toString());
                        }
                        Toast.makeText(context, "Existen " + news.size() + " NOTICIAS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d(TAG, "Estoy en onERROR");
                        Toast.makeText(context, "Existen " + e.getMessage() + " NOTICIAS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}