package com.hundsun.ts2dingding.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by huangzx19274 on 2016/11/30.
 * 时间工具类
 */
public class TimeUtil {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("SYS");
    private static final String TIME_FORMAT_SET = "yyyyMMddHHmmss";
    private static final String DATE_FORMAT_SET = "yyyy-MM-dd";

    private static final String TS_FORMAT_SET = "yyyy-MM-dd'T'HH:mm:ss";

    /** 时间转时间戳 */
    public static String time2Stamp(String time){
        if(time == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(TS_FORMAT_SET);
        try {
            return String.valueOf(sdf.parse(time).getTime()/1000);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /** 获取当前时间戳 */
    public static String getNowTimeStamp(){
        Date date = new Date();
        return String.valueOf(date.getTime()/1000);
    }

    /** 获取当前日期 */
    public static String getNowDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SET);
        return sdf.format(date);
    }

    /** 获取偏移后日期 */
    public static String getDateChange(String date, int calendarType, int size){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SET);
        Calendar rightNow = Calendar.getInstance();
        try {
            rightNow.setTime(sdf.parse(date));
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        rightNow.add(calendarType, size);
        return sdf.format(rightNow.getTime());
    }

    /** 获取偏移后时间戳 */
    public static String getNowStampChange(int calendarType, int size){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(calendarType, size);
        return String.valueOf(rightNow.getTime().getTime()/1000);
    }

    /** 获得当前时间 */
    public static String getNowTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_SET);
        return sdf.format(date);
    }

    /** 转时间为字符串 */
    private static String transforTimeToString(Date tempTime){
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_SET);
        return sdf.format(tempTime);
    }

    /** 转时间为Date */
    private static Date transforTimeToDate(String tempTime){
        SimpleDateFormat sdf= new SimpleDateFormat(TIME_FORMAT_SET);
        Date date = null;

        try {
            date = sdf.parse(tempTime);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }

        return date;
    }

    /**
     * 改变时间 如 20161201115959 等于 changeTime("20161202000000", Calendar.SECOND, -1)
     * @param tempTime 要进行改变的时间
     * @param calendarType 利用Calendar类的常量设置改变的单位
     * @param size 改变的幅度
     * @return 改变后的时间
     * */
    public static String changeTime(String tempTime, int calendarType, int size){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(transforTimeToDate(tempTime));
        rightNow.add(calendarType, size);

        return transforTimeToString(rightNow.getTime());
    }

    /** 判断时间大小 */
    public static boolean judgeTimeLarger(String time1, String time2){
        long t1 = transforTimeToDate(time1).getTime();
        long t2 = transforTimeToDate(time2).getTime();

        return t1 > t2;
    }

    /** 判断时间小大 */
    public static boolean judgeTimeLess(String time1, String time2){
        long t1 = transforTimeToDate(time1).getTime();
        long t2 = transforTimeToDate(time2).getTime();

        return t1 < t2;
    }

    /** 时间相减 */
    public static long subtractInMin(String time1, String time2){
        long t1 = transforTimeToDate(time1).getTime();
        long t2 = transforTimeToDate(time2).getTime();

        long diff = t1 - t2;
        diff = (diff >= 0)?diff : (-diff);
        return diff/ 60000;
    }

    /** 获取13位时间戳 */
    public static long getTimeStamp() {
        return System.currentTimeMillis();
    }
}
