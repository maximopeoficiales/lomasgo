package com.maximoprog.lomasgo.ui.servicios;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.maximoprog.lomasgo.R;
import com.maximoprog.lomasgo.databinding.FragmentInfoBinding;
import com.maximoprog.lomasgo.databinding.FragmentServicesBinding;
import com.maximoprog.lomasgo.ui.info.InfoFragment;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ServiciosFragment extends Fragment {
    private static final String TAG = ServiciosFragment.class.getName();
    public final CompositeDisposable disposables = new CompositeDisposable();
    public Context context;
    FragmentServicesBinding binding;

    private ServiciosViewModel serviciosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        serviciosViewModel = new ViewModelProvider(this).get(ServiciosViewModel.class);
        binding = FragmentServicesBinding.inflate(inflater, container, false);

        context = container.getContext();
        return binding.getRoot();
    }
}