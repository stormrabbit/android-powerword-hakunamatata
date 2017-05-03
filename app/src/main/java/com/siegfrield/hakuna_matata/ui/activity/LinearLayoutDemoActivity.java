package com.siegfrield.hakuna_matata.ui.activity;

import android.os.Bundle;
import android.app.Activity;

import com.siegfrield.hakuna_matata.R;
import com.siegfrield.hakuna_matata.databinding.ActivityLinearLayoutDemoBinding;
import com.siegfrield.hakuna_matata.utils.LinearLayoutUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LinearLayoutDemoActivity extends BaseActivity<ActivityLinearLayoutDemoBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_linear_layout_demo;
    }

    @Override
    protected void initView() {
        String[] rwby = {"Ruby Rose", "Yang Xiaolong", "Blake Belladonna", "White Schnee"
        , "Jaune Arc", "Lie Ren", "Nora Valkyrie", "Pyrrha Nikos"};
        List<String> names = Arrays.asList(rwby);

//        mBinding.llMain.post(new Runnable() {
//            @Override
//            public void run() {
//                int width = mBinding.llMain.getWidth();
//            }
//        });
        mBinding.llMain.measureTest();
//             mBinding.llMain.setTags(names);
            // mBinding.llMain.measureTest();
   //     LinearLayoutUtils.addNames(mBinding.llMain, names, this, 0);

        mBinding.btnTest.setOnClickListener(v -> {
            System.out.println(mBinding.llMain.getMeasuredWidth());
        });
    }

}
