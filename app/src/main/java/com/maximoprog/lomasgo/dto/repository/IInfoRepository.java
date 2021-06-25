package com.maximoprog.lomasgo.dto.repository;

import com.maximoprog.lomasgo.enviroments.Credentials;
import com.maximoprog.lomasgo.models.Info;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface IInfoRepository {

    @GET(Credentials.URI_INFO)
    Observable<Info> getInfo();

}
