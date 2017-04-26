package com.siegfrield.hakuna_matata.service.network;

/**
 * Created by 欧阳夏昱 on 2017/4/26.
 */

public class GetApi {
    public static WeatherService getWeatherApi(){
         WeatherService service = RetrofitHelper.getInstance().getRetrofit().create(WeatherService.class);
         return service;
    }
}
