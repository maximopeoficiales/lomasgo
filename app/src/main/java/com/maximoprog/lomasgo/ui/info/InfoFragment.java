package com.maximoprog.lomasgo.ui.info;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maximoprog.lomasgo.R;
import com.maximoprog.lomasgo.api.services.NewService;
import com.maximoprog.lomasgo.databinding.FragmentInfoBinding;
import com.maximoprog.lomasgo.databinding.FragmentNewsBinding;
import com.maximoprog.lomasgo.ui.noticias.NoticiasFragment;

import io.reactivex.rxjava3.disposables.CompositeDisposable;


public class InfoFragment extends Fragment {
    private static final String TAG = InfoFragment.class.getName();
    public final CompositeDisposable disposables = new CompositeDisposable();
    public Context context;
    FragmentInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_info, container, false);
        binding = FragmentInfoBinding.inflate(inflater, container, false);

        context = container.getContext();
        return binding.getRoot();
    }
}