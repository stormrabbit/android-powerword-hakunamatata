package com.siegfrield.hakuna_matata.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Logger {
    private static final String TAG = "ods";
    private static final int LEVEL = 6;

    public static void v(String tag, String msg) {
        if ( LEVEL >= Log.VERBOSE) {
            Log.v(TAG + ":" + tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL >= Log.DEBUG) {
            Log.d(TAG + ":" + tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable thr) {
        if ( LEVEL >= Log.DEBUG) {
            Log.d(TAG + ":" + tag, msg, thr);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL >= Log.ERROR) {
            Log.e(TAG + ":" + tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL >= Log.INFO) {
            Log.i(TAG + ":" + tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable thr) {
        if (LEVEL >= Log.INFO) {
            Log.i(TAG + ":" + tag, msg, thr);
        }
    }

    public static void wirteException(Context cxt, Throwable ex) {
        writeLogToFile(cxt, ex.toString());
        StackTraceElement[] traceArray = ex.getStackTrace();
        if (null != traceArray) {
            for (StackTraceElement trace : traceArray) {
                String errInfo = trace.getClassName() + " " + trace.getMethodName()
                        + " " + trace.getLineNumber();
                Log.e("Exception", errInfo);
                writeLogToFile(cxt, errInfo);
            }
        }

    }

    public static void writeLogToFile(Context cxt, String str) {
        if (null != cxt) {
            OutputStream out = null;
            try {
                String dir = null;
                String state = android.os.Environment.getExternalStorageState();
                if (android.os.Environment.MEDIA_MOUNTED.equals(state)) {
                    dir = cxt.getExternalFilesDir(null).getAbsolutePath();
                } else {
                    dir = cxt.getFilesDir().getAbsolutePath();
                }
                File fdir = new File(dir);
                if (!fdir.exists()) {
                    fdir.mkdir();
                }
                File file = new File(dir, "logServer.txt");

                if (!file.exists()) {
                    file.createNewFile();
                }
                out = new FileOutputStream(file, true);
                out.write((str + "\n").getBytes());
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
