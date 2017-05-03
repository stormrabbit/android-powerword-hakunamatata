package com.siegfrield.hakuna_matata.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreTools {
    private static SharedPreferences sharedPreferences;
    //存储
    public static void saveString(Context context,String key,String value){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putString(key, value).commit();
    }
    //读取
    public static String getString(Context context,String key,String defValue){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, defValue);
    }
    //存储
    public static void saveInt(Context context,String key,int value){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putInt(key, value).commit();
    }
    //读取
    public static int getInt(Context context,String key,int defValue){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getInt(key, defValue);
    }

    public static boolean getBoolean(Context context,String key,boolean defValue){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void saveBoolean(Context context,String key,boolean defValue){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putBoolean(key, defValue).commit();
    }
}
