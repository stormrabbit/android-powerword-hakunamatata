package com.siegfrield.hakuna_matata.service.manager;

import android.hardware.Camera;
import android.util.Log;

import com.siegfrield.hakuna_matata.R;
import com.siegfrield.hakuna_matata.ui.custom.Preview;

/**
 * Created by Administrator on 2017/6/2.
 */

public class CameraOperationHelper {
    public Camera mCamera;
    private Preview mPreview;
    public static final int FRONT_CAMERA = Camera.CameraInfo.CAMERA_FACING_FRONT;
    public static final int BACK_CAMERA = Camera.CameraInfo.CAMERA_FACING_BACK;
    private int current_camera = -1;
    private CameraOperationHelper(){

    }

    public static CameraOperationHelper getHelper(){
        return CameraOperationHelperInstance.INSTANCE ;
    }

    private static class CameraOperationHelperInstance{
        private static CameraOperationHelper INSTANCE = new CameraOperationHelper();
    }

    public boolean safeCameraOpen(){
       return this.safeCameraOpen(BACK_CAMERA);
    }
    public boolean safeCameraOpen(int id) {
        boolean qOpened = false;

        try {
            releaseCameraAndPreview();
            mCamera = Camera.open(id);
            qOpened = (mCamera != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return qOpened;
    }

    public void setPreview(Preview mPreview){
        this.mPreview = mPreview;
    }

    public void releaseCameraAndPreview() {
        mPreview.setCamera(null);
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    public void takePhoto(){
        if(mPreview != null){
            mPreview.takePhoto();
        }
    }


}
