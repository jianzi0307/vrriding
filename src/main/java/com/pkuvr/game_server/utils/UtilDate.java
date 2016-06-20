package com.pkuvr.game_server.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * java.sql.Timestamp & java.util.Date Utility Function
 *
 * @author
 */
public class UtilDate {
    public static final int DAY_SECONDS = 24 * 60 * 60;
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String ORDER_DATE_PATTERN = "yy-MM-dd";
    public final static String TIME_PATTERN = "HH:mm:ss";
    public final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.fff";
    public final static String SHOW_DATETIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final Timestamp NOTAVAIABLE = UtilDate.str2Date("2037-12-31", DATE_PATTERN);
    public final static String DATE_PATTERN_INT = "yyyyMMdd";
    private static FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss"); // 指定年月日,多线程安全

    // /////////////////////////////////////////////////////////
    // java.sql.Timestamp Functions
    // /////////////////////////////////////////////////////////

    /**
     * 得到两个时间的差值,以秒记
     *
     * @param bigDate
     * @param smallDate
     * @return
     */
    public static long secondDistance(Date bigDate, Date smallDate) {
        if (bigDate == null || smallDate == null)
            return 0;

        return (bigDate.getTime() - smallDate.getTime()) / 1000;
    }

    public static long secondDistance(Date smallDate) {
        return secondDistance(new Date(), smallDate);
    }


    /**
     * 将天数转换成秒
     *
     * @return
     */
    public static long formatTwoDays(int days) {
        return days * 24 * 3600;
    }

    /**
     * 以当前时间为基准, 向前(后)推移若干秒
     *
     * @param second
     * @return
     */
    public static Date moveSecond(long second) {
        return moveSecond(nowTimestamp(), second);
    }

    /**
     * 以给定时间为基准, 向前(后)推移若干秒
     *
     * @param baseDttm
     * @param second
     * @return
     */
    public static Date moveSecond(Date baseDttm, long second) {
        if (baseDttm == null)
            baseDttm = nowDateTime();
        long time = baseDttm.getTime();
        time += second * 1000;

        return new Date(time);
    }

    /**
     * Return a Timestamp for right now
     *
     * @return Timestamp for right now
     */
    public static Timestamp nowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date nowDateTime() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * Timestamp to Str, 只要时间部分HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getTime(Timestamp dttm) {
        if (dttm == null)
            return null;
        return getDatetime(dttm).substring(11);
    }

    /**
     * Timestamp to Str, 只要日期部分yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getDate(Date dttm) {
        if (dttm == null)
            return null;

        SimpleDateFormat lFormatTimestamp = new SimpleDateFormat(DATE_PATTERN);
        return lFormatTimestamp.format(dttm);
    }

    public static int getDateInt(Date dttm) {
        if (dttm == null)
            return 0;

        SimpleDateFormat lFormatTimestamp = new SimpleDateFormat(DATE_PATTERN_INT);
        String now = lFormatTimestamp.format(dttm);
        int rtn = 0;
        if (StringUtils.isNumeric(now))
            rtn = Integer.valueOf(now);
        return rtn;
    }

    /**
     * 当前时间是否在某个时间段内
     *
     * @param startTime HH:MM:SS
     * @param endTime   HH:MM:SS
     * @return
     */
    public static boolean betweenTime(String startTime, String endTime) {
        String sTime = getDate(nowTimestamp()) + " " + startTime;
        String eTime = getDate(nowTimestamp()) + " " + endTime;

        Timestamp tsStart = str2Date(sTime, DATETIME_PATTERN);
        Timestamp tsEnd = str2Date(eTime, DATETIME_PATTERN);
        Timestamp now = nowTimestamp();

        return (now.compareTo(tsStart) >= 0 && now.compareTo(tsEnd) <= 0);
    }

    public static boolean betweenTime(Timestamp startTime, Timestamp endTime) {
        long nowTime = System.currentTimeMillis();
        return (nowTime >= startTime.getTime() && nowTime <= endTime.getTime());
    }

    /**
     * Timestamp to Str, yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String getDatetime(Timestamp dttm) {
        if (dttm == null)
            return null;

        SimpleDateFormat lFormatTimestamp = new SimpleDateFormat(
                DATETIME_PATTERN);
        return lFormatTimestamp.format(dttm);
    }

    /**
     * string to timstamp, with "yyyy-MM-dd HH:mm:ss.fffffffff"
     *
     * @param asDate
     * @return
     */
    public static Timestamp str2Date(String asDate) {
        return str2Date(asDate, DATETIME_PATTERN);
    }

    /**
     * string to timestamp, with given pattern
     *
     * @param asDate
     * @param asPattern
     * @return
     */
    public static Timestamp str2Date(String asDate, String asPattern) {
        Timestamp lStamp = null;

        if (asDate == null || asDate.trim().length() == 0)
            return null;

        if (asPattern == null || asPattern.trim().length() == 0) {
            try {
                lStamp = Timestamp.valueOf(asDate);
            } catch (Exception e) {
                return null;
            }
        } else {
            try {
                SimpleDateFormat lFormat = new SimpleDateFormat(asPattern);
                lStamp = new Timestamp(lFormat.parse(asDate).getTime());
            } catch (Exception e) {
                return null;
            }
        }

        return lStamp;
    }

    // /////////////////////////////////////////////////////////
    // java.util.Date Functions
    // /////////////////////////////////////////////////////////
    public static String date2Text(Date date) {
        return date2Text(date, DATE_PATTERN);
    }

    public static String datetime2Text(Date datetime) {
        return date2Text(datetime, DATETIME_PATTERN);
    }

    public static String datetime2ShowText(Date datetime) {
        return date2Text(datetime, DATE_PATTERN);
    }

    public static String date2Text(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String text = "";
        try {
            text = formatter.format(date);
        } catch (Exception e) {
        }
        return text;
    }

    public static Date text2Date(String text) {
        return text2Date(text, DATETIME_PATTERN);
    }

    public static Date datetime2Text(String text) {
        return text2Date(text, DATETIME_PATTERN);
    }

    public static Date text2Date(String text, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = formatter.parse(text);
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * 将600 秒 转为10:00
     *
     * @param time
     * @return
     */
    public static String formatIntTime(int time) {
        if (time < 60)
            return "00:00:" + StringUtils.leftPad("" + time, 2, '0');
        else if (time >= 60 && time < 3600) {
            String mm = "" + time / 60;
            String ss = "" + time % 60;
            return "00:" + StringUtils.leftPad(mm, 2, '0') + ":"
                    + StringUtils.leftPad(ss, 2, '0');
        } else {
            String hh = "" + time / 3600;
            int ms = time % 3600;
            String mm = "" + ms / 60;
            String ss = "" + ms % 60;
            return StringUtils.leftPad(hh, 2, '0') + ":"
                    + StringUtils.leftPad(mm, 2, '0') + ":"
                    + StringUtils.leftPad(ss, 2, '0');
        }
    }

    public static String formatlongTimeToMinutes(long time) {
        if (time < 60)
            return "00:" + StringUtils.leftPad("" + time, 2, '0');
        else {
            String mm = "" + time / 60;
            String ss = "" + time % 60;
            return StringUtils.leftPad(mm, 2, '0') + ":"
                    + StringUtils.leftPad(ss, 2, '0');
        }
    }

    /**
     * 当前时间是否在每天指定的时间后
     *
     * @param time HH:mm:ss
     */
    public static boolean dailyAfterTime(String time) {
        Timestamp now = UtilDate.nowTimestamp();
        String timeStr = UtilDate.getDate(now) + " " + time + ".000";
        Timestamp pointTime = UtilDate.str2Date(timeStr);
        if (UtilDate.secondDistance(pointTime, now) > 0)
            return true;
        return false;
    }


    /**
     * 当前时间是否在每天指定的时间前
     *
     * @param time HH:mm:ss
     */
    public static boolean dailyBeforeTime(String time) {
        Timestamp now = UtilDate.nowTimestamp();
        String timeStr = UtilDate.getDate(now) + " " + time + ".000";
        Timestamp pointTime = UtilDate.str2Date(timeStr);
        if (UtilDate.secondDistance(pointTime, now) < 0)
            return true;
        return false;
    }


    /**
     * 获取当前时间一个月前的日期点
     *
     * @param
     * @return
     */
    public static String getLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        if (Calendar.MONTH == 0)
            calendar.add(Calendar.YEAR, -1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        String dateStr = String.valueOf(year) + String.valueOf(month) + String.valueOf(date) + "00:00:00";
        return dateStr;
    }


    public static int betweenTwoDate(Date lastDate, Date nowDate) {
        String lastDateStr = UtilDate.getDate(lastDate);
        String nowDataStr = UtilDate.getDate(nowDate);
        lastDateStr = lastDateStr + " 00:00:00";
        nowDataStr = nowDataStr + " 00:00:00";
        Date newLastDate = UtilDate.text2Date(lastDateStr, DATETIME_PATTERN);
        Date newNowDate = UtilDate.text2Date(nowDataStr, DATETIME_PATTERN);
        if (newNowDate.getTime() - newLastDate.getTime() > (24 * 3600 * 1000)) {
            return 2;
        } else if (newNowDate.getTime() - newLastDate.getTime() == (24 * 3600 * 1000)) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * 获取日期
     *
     * @return
     */
    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    public static void main(String[] args) {
//		System.out.print(UtilDate.formatlongTimeToMinutes(new Long(352)));
    }

    public static String getDateAsString(Date d) {
        return fastDateFormat.format(d);
    }

    /**
     * 获取每月的天数
     *
     * @return
     */
    public static int getDayOfMonth() {
        Calendar aCalendar = Calendar.getInstance();
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

}
