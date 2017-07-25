package com.github.xch168.gank.helper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XuCanHui on 2017/7/12.
 */

public class RetrofitHelper {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {

        synchronized (Retrofit.class) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://gank.io/api/")
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return retrofit;
    }

    public static <T> T getService(Class<T> service) {

        return getRetrofit().create(service);
    }
}
