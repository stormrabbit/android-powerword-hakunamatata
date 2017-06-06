package com.siegfrield.hakuna_matata.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.SurfaceHolder;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.siegfrield.hakuna_matata.utils.WindowManagerUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */

public class Preview extends FrameLayout implements SurfaceHolder.Callback, Camera.PictureCallback {
    SurfaceView mSurfaceView;
    SurfaceHolder mHolder;
    private Camera mCamera;
    private List<Camera.Size> mSupportedPreviewSizes;
    private ImageView mImageView;


    public Preview(Context context) {
        this(context, null, 0);
    }

    public Preview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Preview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        mSurfaceView = new SurfaceView(context);
        FrameLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mSurfaceView, layoutParams);

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mImageView = new ImageView(context);
        addView(mImageView,layoutParams);
        mImageView.setVisibility(GONE);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    public void setCamera(Camera camera) {
        if (mCamera == camera) { return; }

        stopPreviewAndFreeCamera();

        mCamera = camera;

        if (mCamera != null) {
            List<Camera.Size> localSizes = mCamera.getParameters().getSupportedPreviewSizes();
            mSupportedPreviewSizes = localSizes;
            requestLayout();

            try {
                mCamera.setPreviewDisplay(mHolder);
                this.setParameter();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Important: Call startPreview() to start updating the preview
            // surface. Preview must be started before you can take a picture.
            mCamera.startPreview();
        }
    }

    private void setParameter() {
        if(mCamera == null){
            return ;
        }

        Camera.Parameters parameters = mCamera.getParameters(); // 获取各项参数


        parameters.setPictureFormat(PixelFormat.JPEG); // 设置图片格式
        parameters.setJpegQuality(100); // 设置照片质量
//        //获得相机支持的照片尺寸,选择合适的尺寸
//        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
//        if(getContext() instanceof Activity){
//            Point point = WindowManagerUtil.getWindowSize((Activity) getContext());
//        }
//        int maxSize = Math.max(1080, 1080);
//        int length = sizes.size();
//        if (maxSize > 0) {
//            for (int i = 0; i < length; i++) {
//                if (maxSize <= Math.max(sizes.get(i).width, sizes.get(i).height)) {
//                    parameters.setPictureSize(sizes.get(i).width, sizes.get(i).height);
//                    break;
//                }
//            }
//        }
//        List<Camera.Size> ShowSizes = parameters.getSupportedPreviewSizes();
//        int showLength = ShowSizes.size();
//        if (maxSize > 0) {
//            for (int i = 0; i < showLength; i++) {
//                if (maxSize <= Math.max(ShowSizes.get(i).width, ShowSizes.get(i).height)) {
//                    parameters.setPreviewSize(ShowSizes.get(i).width, ShowSizes.get(i).height);
//                    break;
//                }
//            }
//        }
//
//        parameters.set("orientation","portrait");
        parameters.setPictureSize(1920,1080);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        parameters.set("rotation", 90);
        mCamera.setDisplayOrientation(90); // 这句比较关键
        mCamera.setParameters(parameters);
    }

    private void stopPreviewAndFreeCamera() {
        if (mCamera != null) {
            // Call stopPreview() to stop updating the preview surface.
            mCamera.stopPreview();

            // Important: Call release() to release the camera for use by other
            // applications. Applications should release the camera immediately
            // during onPause() and re-open() it during onResume()).
            mCamera.release();

            mCamera = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {



    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // Surface will be destroyed when we return, so stop the preview.
        if (mCamera != null) {
            // Call stopPreview() to stop updating the preview surface.
            mCamera.stopPreview();
        }
    }

    public void takePhoto() {
        if(mCamera!=null){
            mCamera.takePicture(null, null ,this);
        }
    }

    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            mImageView.setImageBitmap(bitmap);
            mImageView.setVisibility(VISIBLE);
            mSurfaceView.setVisibility(GONE);
        }catch (Exception ex){
            ex.printStackTrace();
        }



    }

}
