package com.siegfrield.hakuna_matata.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    public static SimpleDateFormat sdfyyyy_MM_dd = new SimpleDateFormat(
            "yyyy-MM-dd");

    public static SimpleDateFormat sdfyyyy_MM_dd_HH_mm_ss = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat sdfyyyy_MM_dd_HH_mm = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm");
    public static SimpleDateFormat sdfyyyy_MM_dd_HH = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:00");

    public static SimpleDateFormat sdfHH_mm = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat sdfMM_dd = new SimpleDateFormat("MM月dd日");
    public static SimpleDateFormat sdf_mm_ss = new SimpleDateFormat("mm:ss");

    /**
     * return yyyy-MM-dd
     *
     * @return
     */
    public static String getDate_yyyy_MM_dd() {
        return sdfyyyy_MM_dd.format(new Date());
    }

    public static String getDate_HH_mm() {
        return sdfHH_mm.format(new Date());
    }

    public static String msToss(long ms) {
        sdf_mm_ss.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String ss = sdf_mm_ss.format(ms);
        return ss;
    }

    /**
     * return yyyy_MM_dd_HH_mm_ss
     *
     * @return
     */
    public static String getDate_yyyy_MM_dd_HH_mm() {
        return sdfyyyy_MM_dd_HH_mm.format(new Date());
    }


    /**
     * 由返回回来的毫秒数算出真正的时间
     *
     * @param s
     * @return
     */
    public static String getDateString(long s, SimpleDateFormat format) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(s * 1000));
        Date dt = c.getTime();
        String str = format.format(dt);
        return str;
    }


    public static String getHistoryDateString(long s, SimpleDateFormat format) {
        Date curDate = new Date(s);//获取当前时间
        String str = format.format(curDate);
        return str;
    }
}