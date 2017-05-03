package com.siegfrield.hakuna_matata.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import android.text.TextUtils;

import com.siegfrield.hakuna_matata.model.data.CustomDate;

import java.text.ParseException;

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

    public static String[] weekName = { "周日", "周一", "周二", "周三", "周四", "周五","周六" };

    public static int getMonthDays(int year, int month) {
        if (month > 12) {
            month = 1;
            year += 1;
        } else if (month < 1) {
            month = 12;
            year -= 1;
        }
        int[] arr = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int days = 0;

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            arr[1] = 29; // 闰年2月29天
        }

        try {
            days = arr[month - 1];
        } catch (Exception e) {
            e.getStackTrace();
        }

        return days;
    }

    @SuppressLint("WrongConstant")
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    @SuppressLint("WrongConstant")
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    @SuppressLint("WrongConstant")
    public static int getCurrentMonthDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    @SuppressLint("WrongConstant")
    public static int getWeekDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    @SuppressLint("WrongConstant")
    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    @SuppressLint("WrongConstant")
    public static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
    @SuppressLint("WrongConstant")
    public static CustomDate getNextSunday() {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 7 - getWeekDay()+1);
        CustomDate date = new CustomDate(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
        return date;
    }

    @SuppressLint("WrongConstant")
    public static int[] getWeekSunday(int year, int month, int day, int pervious) {
        int[] time = new int[3];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.add(Calendar.DAY_OF_MONTH, pervious);
        time[0] = c.get(Calendar.YEAR);
        time[1] = c.get(Calendar.MONTH )+1;
        time[2] = c.get(Calendar.DAY_OF_MONTH);
        return time;

    }

    @SuppressLint("WrongConstant")
    public static int getWeekDayFromDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateFromString(year, month));
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return week_index;
    }

    @SuppressLint("SimpleDateFormat")
    public static Date getDateFromString(int year, int month) {
        String dateString = year + "-" + (month > 9 ? month : ("0" + month))
                + "-01";
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public static boolean isToday(CustomDate date){
        return(date.year == DateUtil.getYear() &&
                date.month == DateUtil.getMonth()
                && date.day == DateUtil.getCurrentMonthDay());
    }

    public static boolean isCurrentMonth(CustomDate date){
        return(date.year == DateUtil.getYear() &&
                date.month == DateUtil.getMonth());
    }
    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort(long longDate) {

        String formats =  "yyyy.MM";
        Long timestamp = longDate*1000;
        String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));
        return date;
    }

    public static String getStringDateLong(long longDate) {
        String formats =  "yyyy-MM-DD HH:MM";
        Long timestamp = longDate*1000;
        String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));
        return date;
    }

    public static String TimeStamp2Date(String timestampString, String formats){
        if(TextUtils.isEmpty(formats)){
            formats =  "yyyy-MM-dd HH:mm:ss";
        }
        Long timestamp = Long.parseLong(timestampString)*1000;
        String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));
        return date;
    }

}