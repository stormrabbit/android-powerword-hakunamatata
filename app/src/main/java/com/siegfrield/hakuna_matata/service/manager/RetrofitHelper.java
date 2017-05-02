package com.siegfrield.hakuna_matata.service.manager;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 欧阳夏昱 on 2017/4/26.
 *
 * Restrofit 下载逻辑基础
 *
 * 通过 init() 获得一个 retrofit 对象
 */

public class RetrofitHelper {
    private Retrofit retrofit;
    private final String api = "" ;
    private RetrofitHelper (){
        init();
    }

    public static RetrofitHelper getInstance(){
        return HelperInstance.INSTANCE;
    }

    public Retrofit getRetrofit(){
        return this.retrofit;
    }

    private static class HelperInstance{
        private static RetrofitHelper INSTANCE = new RetrofitHelper();
    }
    private void init(){

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.weather.com.cn/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }
}
