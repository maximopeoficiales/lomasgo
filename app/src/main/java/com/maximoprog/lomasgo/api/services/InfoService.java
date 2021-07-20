package com.maximoprog.lomasgo.api.services;

import com.maximoprog.lomasgo.api.RetrofitInstance;
import com.maximoprog.lomasgo.dto.repository.IInfoRepository;
import com.maximoprog.lomasgo.models.Info;
import com.maximoprog.lomasgo.models.New;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class InfoService {
    private IInfoRepository repository;

    public InfoService() {
        repository = RetrofitInstance.getInstance().createRepository(IInfoRepository.class);
    }

    public Observable<Info> getInfo() {
        return repository.getInfo();
    }
}
