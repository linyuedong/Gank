package com.pax.linyd.gank.module.network;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by linyd on 2017/4/20.
 */
public interface GankApi {
    @GET("data/{type}/{number}/{page}")
    Observable<gank> getGankDatas(
            @Path("type") String type, @Path("number") int number, @Path("page") int page);
}
