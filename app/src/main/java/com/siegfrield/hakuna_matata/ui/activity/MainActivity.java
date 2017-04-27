package com.siegfrield.hakuna_matata.ui.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.siegfrield.hakuna_matata.R;
import com.siegfrield.hakuna_matata.databinding.ActivityMainBinding;
import com.siegfrield.hakuna_matata.model.data.ResultData;
import com.siegfrield.hakuna_matata.model.data.ReturnType;
import com.siegfrield.hakuna_matata.model.data.WeatherData;
import com.siegfrield.hakuna_matata.service.network.WeatherApi;
import com.siegfrield.hakuna_matata.service.network.OnHttp;

public class MainActivity extends Activity implements OnHttp<WeatherData>{
    ActivityMainBinding activityMainBinding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.tvHw.setText("fuck you, world!");

        WeatherApi.getApi().getWeatherDemo(123,this);
//      Observable<WeatherData> observable =  GetApi.getWeatherApi().getWeatherData();
//
//      observable.subscribeOn(Schedulers.io())
//              .observeOn(AndroidSchedulers.mainThread())
//              .subscribe(new Observer<WeatherData>() {
//                  @Override
//                  public void onCompleted() {
//
//                  }
//
//                  @Override
//                  public void onError(Throwable e) {
//
//                  }
//
//                  @Override
//                  public void onNext(WeatherData weatherData) {
//                      activityMainBinding.tvHw.setText(weatherData.toString());
//                  }
//              });
    }

    @Override
    public void onHttpResult(int requestCode, ReturnType type, ResultData<WeatherData> resultData) {
            if(type == ReturnType.Success){
                // WeatherData data = (WeatherData) resultData.getT();
                WeatherData data = resultData.get();
                activityMainBinding.tvHw.setText(data.toString());
            }
    }
}
