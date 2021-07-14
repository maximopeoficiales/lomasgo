package com.maximoprog.lomasgo.api.services;

import com.maximoprog.lomasgo.api.RetrofitInstance;
import com.maximoprog.lomasgo.dto.repository.IServiceRepository;
import com.maximoprog.lomasgo.models.Service;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class ServiceService {
    private IServiceRepository repository;

    public ServiceService() {
        repository = RetrofitInstance.getInstance().createRepository(IServiceRepository.class);
    }

    public Observable<List<Service>> getServices() {
        return repository.getServices();
    }

}
