package com.maximoprog.lomasgo.dto.repository;

import com.maximoprog.lomasgo.enviroments.Credentials;
import com.maximoprog.lomasgo.models.Service;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IServiceRepository {
    @GET(Credentials.URI_SERVICES)
    Observable<List<Service>> getServices();

    @GET(Credentials.URI_SERVICES)
    Observable<List<Service>> getServicesTop(@Query("_limit") Integer quantity);

    @GET(Credentials.URI_SERVICES + "/{idService}")
    Observable<Service> getServicesById(@Path("idService") int idService);

}
