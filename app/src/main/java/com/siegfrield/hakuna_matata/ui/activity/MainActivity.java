package com.siegfrield.hakuna_matata.ui.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.google.gson.GsonBuilder;
import com.siegfrield.hakuna_matata.R;
import com.siegfrield.hakuna_matata.databinding.ActivityMainBinding;
import com.siegfrield.hakuna_matata.model.data.WeatherData;
import com.siegfrield.hakuna_matata.service.network.GetApi;
import com.siegfrield.hakuna_matata.service.network.RetrofitHelper;
import com.siegfrield.hakuna_matata.service.network.WeatherService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {
    ActivityMainBinding activityMainBinding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.tvHw.setText("fuck you, world!");


      Observable<WeatherData> observable =  GetApi.getWeatherApi().getWeatherData();

      observable.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<WeatherData>() {
                  @Override
                  public void onCompleted() {

                  }

                  @Override
                  public void onError(Throwable e) {

                  }

                  @Override
                  public void onNext(WeatherData weatherData) {
                      activityMainBinding.tvHw.setText(weatherData.toString());
                  }
              });
    }
}
