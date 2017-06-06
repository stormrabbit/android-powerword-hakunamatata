package com.siegfrield.hakuna_matata.ui.activity;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.siegfrield.hakuna_matata.R;
import com.siegfrield.hakuna_matata.databinding.ActivityCemeraDemoBinding;
import com.siegfrield.hakuna_matata.databinding.ActivityHostBinding;
import com.siegfrield.hakuna_matata.service.manager.CameraOperationHelper;

import java.util.Timer;
import java.util.TimerTask;

public class CemeraDemoActivity extends BaseActivity<ActivityCemeraDemoBinding> implements View.OnClickListener {
    CameraOperationHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = CameraOperationHelper.getHelper();
        helper.setPreview(mBinding.prvCamera);
//        if(helper.safeCameraOpen()){
//            try{
//                mBinding.prvCamera.setCamera(helper.mCamera);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
    }

    @Override
    protected void onResume() {

        super.onResume();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                startCamera();
            }
        };

        Timer timer = new Timer();
        TimerTask tt= new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(runnable1);
            }
        };

        timer.schedule(tt,3000);

    }

    @Override
    protected void onDestroy() {

        helper.releaseCameraAndPreview();
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cemera_demo;
    }

    @Override
    protected void initView() {
        mBinding.btnCameraTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        helper.takePhoto();
    }

    private void startCamera(){
        if(helper.safeCameraOpen()){
            try{
                mBinding.prvCamera.setCamera(helper.mCamera);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
