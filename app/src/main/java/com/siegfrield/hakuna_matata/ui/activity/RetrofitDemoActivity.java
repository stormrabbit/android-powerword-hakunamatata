package com.siegfrield.hakuna_matata.ui.activity;

import android.os.Bundle;

import com.siegfrield.hakuna_matata.R;
import com.siegfrield.hakuna_matata.databinding.ActivityRetroFitBinding;
import com.siegfrield.hakuna_matata.model.data.ResultData;
import com.siegfrield.hakuna_matata.model.data.ReturnType;
import com.siegfrield.hakuna_matata.model.data.WeatherData;
import com.siegfrield.hakuna_matata.service.network.WeatherApi;
import com.siegfrield.hakuna_matata.service.network.OnHttp;

public class RetrofitDemoActivity extends BaseActivity<ActivityRetroFitBinding> implements OnHttp<WeatherData>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);



        WeatherApi.getApi().getWeatherDemo(123,this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retro_fit;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onHttpResult(int requestCode, ReturnType type, ResultData<WeatherData> resultData) {
            if(type == ReturnType.Success){
                WeatherData data = resultData.get();
                mBinding.setWeathData(data.getWeatherinfo());
                mBinding.tvSum.setText(data.toString());
            }
    }
}
