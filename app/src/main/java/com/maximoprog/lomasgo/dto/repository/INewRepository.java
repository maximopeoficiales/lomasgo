package com.maximoprog.lomasgo.dto.repository;

import com.maximoprog.lomasgo.enviroments.Credentials;
import com.maximoprog.lomasgo.models.New;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface INewRepository {

    @GET(Credentials.URI_NEWS)
    Observable<List<New>> getNews();

    @GET(Credentials.URI_NEWS + "/{idNew}")
    Observable<New> getNewById(@Path("idNew") int idNew);

}
