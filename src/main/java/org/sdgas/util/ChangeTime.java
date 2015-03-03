package org.sdgas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class ChangeTime {

    private static final SimpleDateFormat fullTimeFormat1 = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat dateFormat1 = new SimpleDateFormat(
            "yyyy-MM-dd");
    private static final SimpleDateFormat fullTimeFormat2 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat dateFormat3 = new SimpleDateFormat(
            "HHmmss");
    private static final SimpleDateFormat dateFormat4 = new SimpleDateFormat(
            "HH:mm:ss");

    public static String getCurrentDate() {
        return ChangeTime.formatWholeDate(new Date());
    }

    public static String formatDate(Date date) {
        return date == null ? "00000000000000" : fullTimeFormat1.format(date);
    }

    public static String formatDate() {
        return fullTimeFormat1.format(new Date());
    }

    public static String formatWholeDate(Date date) {
        return date == null ? "0000-00-00 00:00:00.0" : fullTimeFormat2.format(date);
    }

    public static String formatTime(Date date) {
        return date == null ? "" : dateFormat3.format(date);
    }

    public static String formatTime2(Date date) {

        return date == null ? "" : dateFormat4.format(date);
    }

    public static String formatWholeDate() {
        return fullTimeFormat2.format(new Date());
    }

    /**
     * 返回长日期格式，包含时间 用于对字符串的转换
     *
     * @param date String 型参数
     * @return
     */
    public static synchronized String formatDate(String date) {
        StringBuffer strbf = new StringBuffer();
        StringTokenizer st = new StringTokenizer(date.substring(0, 10), "-");
        while (st.hasMoreTokens()) {
            strbf.append(st.nextToken());
        }
        st = new StringTokenizer(date.substring(11, 19), ":");
        while (st.hasMoreTokens()) {
            strbf.append(st.nextToken());
        }
        return strbf.toString();
    }

    /**
     * 返回短日期格式，不包含时间 一般用于当前时间取值
     *
     * @param date Date 型参数
     * @return
     */
    public static synchronized String formatShortDate(Date date) {
        return date == null ? "00000000" : dateFormat.format(date);
    }

    /**
     * 返回当前系统日期短日期格式，不包含时间 用于取当前日期值<br>
     * 格式：20100512
     *
     * @return
     */
    public static String formatShortDate() {
        return dateFormat.format(new Date());
    }

    /**
     * 返回当前系统日期短日期格式，不包含时间 用于取当前日期值年只取后两位<br>
     * 格式：100512 假如当前日期为：2010-05-12
     *
     * @return
     */
    public static String formatShortYearDate() {
        return dateFormat.format(new Date());
    }

    /**
     * 返回短日期格式（带减号），不包含时间 一般用于当前时间取值
     *
     * @param date Date 型参数
     * @return
     */
    public static synchronized String formatRealDate(Date date) {
        return date == null ? "0000-00-00" : dateFormat1.format(date);
    }

    /**
     * 返回短日期格式，不包含时间 用于对字符串的转换
     *
     * @param date String 型参数
     * @return
     */
    public static synchronized String formatShortDate(String date) {
        StringBuffer strbf = new StringBuffer();
        StringTokenizer st = new StringTokenizer(date.substring(0, 10), "-");
        while (st.hasMoreTokens()) {
            strbf.append(st.nextToken());
        }
        return strbf.toString();
    }

    /**
     * dateString格式："YYYY-MM-dd"
     *
     * @param dateString
     * @return
     */
    public static Date parseShortDate(String dateString) {
        try {
            return dateString == null ? new Date() : fullTimeFormat2.parse(dateString + " 0:0:0");
        } catch (Exception e) {
            //it would be better if you can use a logger to log down the exception
            //e.printStackTrace();
            return new Date();
        }
    }

    public static Date parseStringToShortDate(String date) {
        try {
            return date == null ? new Date() : dateFormat1.parse(date);
        } catch (Exception e) {
            return new Date();
        }
    }

    /**
     * dateString格式："YYYY-MM-dd hh:mm:ss"
     *
     * @param dateString
     * @return
     */
    public static Date parseDate(String dateString) {
        try {
            return dateString == null ? new Date() : fullTimeFormat2.parse(dateString);
        } catch (Exception e) {
            //it would be better if you can use a logger to log down the exception
            //e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 返回上一个时间与现在时间的差
     *
     * @param last
     * @return
     */
    public static long cha(String last) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long day = 0L;
        Date date;
        try {
            date = myFormatter.parse(last);
            Date mydate = myFormatter.parse(ChangeTime.getCurrentDate());
            day = (mydate.getTime() - date.getTime()) / 1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    public static String getCurrentShortDate() {
        return ChangeTime.formatRealDate(new Date());
    }

}
