package com.siegfrield.hakuna_matata.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;


import com.siegfrield.hakuna_matata.service.manager.MyApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class SharedPreTools {

    //单键值存储----------------------------  
    public static String readShare(String sharename, String key) {
        SharedPreferences user = MyApplication.getInstance().getSharedPreferences(sharename, 0);
        return user.getString(key, "");
    }

    public static void writeShare(String sharename, String key, String value) {
        SharedPreferences user = MyApplication.getInstance().getSharedPreferences(sharename, 0);
        Editor editor = user.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void removeShare(String sharename, String key) {
        SharedPreferences user = MyApplication.getInstance().getSharedPreferences(sharename, 0);
        Editor editor = user.edit();
        editor.remove(key);
        editor.commit();
    }


    //多键值存储  
    public static void writegroup(String sharename, String key, String value) {
        SharedPreferences call = MyApplication.getInstance().getSharedPreferences(sharename, 0);
        String group = call.getString(key, "");
        if (group.indexOf(value) < 0) {
            String newgroup = group + "," + key;
            Editor editor = call.edit();
            editor.putString(key, newgroup);
            editor.commit();
        }
    }

    public static void removegroup(String sharename, String key) {
        SharedPreferences call = MyApplication.getInstance().getSharedPreferences(sharename, 0);
        String group = call.getString(key, "");
        String newgroup = group.replace("," + key, "");
        Editor editor = call.edit();
        editor.putString(key, newgroup);
        editor.commit();
    }




    /**
     * 删除单条对象数据
     *
     * @param per_name
     * @param key
     */
    public static void removeObjectPre(String per_name, String key) {
        SharedPreferences loginUserInfo = MyApplication.getInstance().
                getSharedPreferences(per_name, Context.MODE_APPEND);
        Editor preEd = null;
        if (loginUserInfo != null) {
            preEd = loginUserInfo.edit();
        }
        preEd.remove(key);
        preEd.commit();
    }

    //当前时间为id  
    public static long getTimeId() {
        Date curDate = new Date(System.currentTimeMillis());
        long time = curDate.getTime();
        return time;
    }

    //---------------------------  

} 