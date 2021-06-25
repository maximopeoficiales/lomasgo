package com.maximoprog.lomasgo.api.services;

import com.maximoprog.lomasgo.api.RetrofitInstance;
import com.maximoprog.lomasgo.dto.repository.INewRepository;
import com.maximoprog.lomasgo.models.New;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class NewService {

    private INewRepository repository;

    public NewService() {
        repository = RetrofitInstance.getInstance().createRepository(INewRepository.class);
    }

    public Observable<List<New>> getNews() {
        return repository.getNews();
    }

    public Observable<New> getNewById(int idNew) {
        return repository.getNewById(idNew);
    }
}
