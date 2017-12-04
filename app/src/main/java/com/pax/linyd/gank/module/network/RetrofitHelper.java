package com.pax.linyd.gank.module.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by linyd on 2017/4/24.
 */
public class RetrofitHelper {
    public static final String BASE_GANK_URL = "http://gank.io/api/";

    public static GankApi getGankApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_GANK_URL)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GankApi gankApi = retrofit.create(GankApi.class);
        return gankApi;
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return okHttpClient;


    }
}
