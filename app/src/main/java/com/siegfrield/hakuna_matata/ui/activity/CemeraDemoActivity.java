package com.siegfrield.hakuna_matata.ui.activity;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.siegfrield.hakuna_matata.R;
import com.siegfrield.hakuna_matata.databinding.ActivityCemeraDemoBinding;
import com.siegfrield.hakuna_matata.databinding.ActivityHostBinding;
import com.siegfrield.hakuna_matata.service.manager.CameraOperationHelper;

public class CemeraDemoActivity extends BaseActivity<ActivityCemeraDemoBinding> implements View.OnClickListener {
    CameraOperationHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = CameraOperationHelper.getHelper();
        helper.setPreview(mBinding.prvCamera);

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
        Camera camera ;
        if(helper.safeCameraOpen()){
            try{
                mBinding.prvCamera.setCamera(helper.mCamera);
                helper.takePhoto();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
