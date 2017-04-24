package com.siegfrield.hakuna_matata.ui.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public abstract class BaseActivity<T extends ViewDataBinding> extends Activity {
    T mBinding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(this.getLayoutId(), null, false);
        mBinding = DataBindingUtil.bind(rootView);
        super.setContentView(rootView);

        initView();
    }

    protected abstract int getLayoutId() ;

    protected abstract void initView();
}
