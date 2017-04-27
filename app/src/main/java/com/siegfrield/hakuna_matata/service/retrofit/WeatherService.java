package com.siegfrield.hakuna_matata.service.retrofit;

import com.siegfrield.hakuna_matata.model.data.WeatherData;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by 欧阳夏昱 on 2017/4/26.
 */

public interface WeatherService {

    @GET("data/sk/101010100.html")
    Observable<WeatherData> getWeatherData();
}
