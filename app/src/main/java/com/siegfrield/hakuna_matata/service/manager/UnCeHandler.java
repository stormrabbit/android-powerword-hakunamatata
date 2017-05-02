package com.siegfrield.hakuna_matata.service.manager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.util.Log;


import com.siegfrield.hakuna_matata.utils.Logger;

import java.lang.Thread.UncaughtExceptionHandler;


public class UnCeHandler implements UncaughtExceptionHandler {

    private UncaughtExceptionHandler mDefaultHandler;
    public static final String TAG = "CatchExcep";
    MyApplication application;

    public UnCeHandler(MyApplication application) {
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.application = application;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Logger.wirteException(application.getApplicationContext(), ex);
        ex.printStackTrace();
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            setCrashCnt(getCrashCnt()+1);

        }
    }

    private void setCrashCnt(int num) {
        SharedPreferences sharedPreferences = application.getSharedPreferences("CrashCount", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("cnt", num);
        editor.commit();
    }

    private int getCrashCnt() {
        SharedPreferences sharedPreferences = application.getSharedPreferences("CrashCount", Activity.MODE_PRIVATE);
        return sharedPreferences.getInt("cnt",0);
    }
    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 使用Toast来显示异常信息
        Logger.v("ERROR", ex.toString());
        return true;
    }

}


