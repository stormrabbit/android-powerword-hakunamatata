package com.siegfrield.hakuna_matata.utils;

import android.app.Activity;
import android.graphics.Point;

/**
 * Created by Administrator on 2017/6/5.
 */

public class WindowManagerUtil {

    public static Point getWindowSize(Activity activity){
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }
}
