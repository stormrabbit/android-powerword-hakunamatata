package com.siegfrield.hakuna_matata.service.network;

import com.siegfrield.hakuna_matata.model.data.ResultData;
import com.siegfrield.hakuna_matata.model.data.ReturnType;
import com.siegfrield.hakuna_matata.model.data.WeatherData;
import com.siegfrield.hakuna_matata.service.manager.RetrofitHelper;
import com.siegfrield.hakuna_matata.service.retrofit.WeatherService;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 欧阳夏昱 on 2017/4/27.
 */

public class WeatherApi {
  public WeatherService service = null;

  private WeatherApi (){
        init();
  }

    private void init() {
      this.service = RetrofitHelper.getInstance().getRetrofit().create(WeatherService.class);
    }

    public static WeatherApi getApi(){
    return WeatherApiInstance.INSTANCE;
  }

  private static class WeatherApiInstance{
      private static WeatherApi INSTANCE = new WeatherApi();
  }



  public void getWeatherDemo(final int reqCode , final OnHttp onHttp){
      Observable<WeatherData> observable =  service.getWeatherData();
      final ResultData<WeatherData> result = new ResultData<>();
      observable.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<WeatherData>() {
                  @Override
                  public void onCompleted() {

                  }

                  @Override
                  public void onError(Throwable e) {
                      onHttp.onHttpResult(reqCode, ReturnType.Fail, null);
                  }

                  @Override
                  public void onNext(WeatherData weatherData) {
                      result.set(weatherData);
                      onHttp.onHttpResult(reqCode, ReturnType.Success, result);
                  }
              });
  }
}
