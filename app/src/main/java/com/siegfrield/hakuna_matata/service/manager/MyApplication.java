package com.siegfrield.hakuna_matata.service.manager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;


/**
 * @author
 * @ClassName: MyApplication
 * @Description: TODO(应用配置，处理全局操作)
 * @date 2015年9月1日 上午10:03:52
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    private static Context mContext;
    private Resources mLocalResources = null;
    public static int mLocalVersion;
    public static int mRemoteVersion;
    public static String LOCALPATH;
    private SharedPreferences mSp;
    private Editor mEditor;
    private static final String TAG = "MyApplication";

    public Context getContext() {
        return mContext;
    }

    public static MyApplication getInstance() {
        MyApplication localMyApplication = instance;
        return localMyApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
        initErrorHandler();

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), 0);
            mLocalVersion = info.versionCode;
            mSp = getSharedPreferences("install_info", Context.MODE_PRIVATE);
            int version = mSp.getInt("version_code", -1);
            Log.d(TAG, "version: " + version);
            this.mEditor = this.mSp.edit();
            boolean isInstalled = mSp.getBoolean("is_installed", false);

            mEditor.putInt("version_code", mLocalVersion);
            mEditor.commit();
            mRemoteVersion = mLocalVersion;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }


    }

    private static void enabledStrictMode() {
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
//                .detectAll() //
//                .penaltyLog() //
//                .penaltyDeath() //
//                .build());
    }

    public Resources getLocalResources() {
        try {
            if (this.mLocalResources == null) {
                this.mLocalResources = getContext().getResources();
            } else {
                Resources localResources = this.mLocalResources;
                return localResources;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mLocalResources;
    }

    public String getLocalPath() {

        if (null == LOCALPATH) {
            LOCALPATH = mContext.getFilesDir().getAbsolutePath()
                    + File.separator + "apps";
        }
        return LOCALPATH;

    }

    /**
     * 获取application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public String getAppMetaData() {
        ApplicationInfo appInfo;
        try {
            appInfo = getPackageManager().getApplicationInfo(getPackageName(),
                    PackageManager.GET_META_DATA);
            String msg = appInfo.metaData.getString("Source");
            Log.d(TAG, "appInfo: " + msg);
            return msg;
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public void initErrorHandler() {
        // 设置该CrashHandler为程序的默认处理器
        UnCeHandler catchExcep = new UnCeHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
    }


}