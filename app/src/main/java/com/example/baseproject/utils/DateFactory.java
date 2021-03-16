package com.example.baseproject.utils;

/**
 * 描述：时间格式化工具
 */

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFactory {

    private static DateFactory util;

    public static DateFactory getInstance() {
        if (util == null) {
            util = new DateFactory();
        }
        return util;

    }

    private DateFactory() {
        super();
    }

    public static SimpleDateFormat date_Formater_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat date_Formater_2 = new SimpleDateFormat("yyyy-MM-dd");

    public static String toDateFormter1(Long time) {
        Date date = new Date(time);
        return date_Formater_1.format(date);
    }

    public static String toDateFormter2(Long time) {
        Date date = new Date(time);
        return date_Formater_2.format(date);
    }

    public static Date getDate(String dateStr) {
        Date date = null;
        if (TextUtils.isEmpty(dateStr)) {
            return date;
        }
        try {
            date = date_Formater_1.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return date;

    }

    public String getDateString_1(Date date) {
        if (date == null) {
            date = new Date();
        }
        String str = date_Formater_1.format(date);
        return str;

    }

    public String getDateString_2(Date date) {
        if (date == null) {
            date = new Date();
        }
        String str = date_Formater_2.format(date);
        return str;

    }

    public static String getDateString_1(String time) {
        Date date = getDate(time);
        String str = date_Formater_1.format(date);
        return str;

    }

    public static String getDateString_2(String time) {
        Date date = getDate(time);
        String str = date_Formater_2.format(date);
        return str;

    }

    /**
     * 将日期变成常见中文格式
     */
    public static String getRencentTime(String date) {
        Date time = getDate(date);
        if (time == null) {
            return "一年前";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        String curDate = date_Formater_2.format(cal.getTime());
        String paramDate = date_Formater_2.format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "1天前";
        } else if (days > 2 && days < 365) {
            ftime = days + "天前";
        } else if (days > 365) {
            ftime = "一年前";
        } else {
            ftime = date_Formater_2.format(time);
        }
        return ftime;
    }

    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return getRencentTime(dateFormat.format(new Date(timeInMillis)));
    }

    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, date_Formater_1);
    }

    /**
     * 时间戳转换成时间格式
     */
    public static String longToDate(long timeInMillis) {
        Date date = new Date(60 * 60 * 8 * 1000l + timeInMillis);
        String str = date_Formater_1.format(date);
        return str;
    }


    /**
     * 日期时间格式转换
     */
    public String stringDateToStringData(String typeFrom, String typeTo,
                                         String value) {
        String re = value;
        SimpleDateFormat sdfFrom = new SimpleDateFormat(typeFrom);
        SimpleDateFormat sdfTo = new SimpleDateFormat(typeTo);

        try {
            re = sdfTo.format(sdfFrom.parse(re));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re;
    }

    /**
     * 得到这个月有多少天
     */
    public int getMonthLastDay(int year, int month) {
        if (month == 0) {
            return 0;
        }
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 得到年份
     */
    public String getCurrentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) + "";
    }

    /**
     * 得到月份
     */
    public String getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return (c.get(Calendar.MONTH) + 1) + "";
    }

    /**
     * 获得当天的日期
     */
    public String getCurrDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH) + "";
    }

    /**
     * 得到几天/周/月/年后的日期，整数往后推,负数往前移动
     */
    public String getDayByDate(Calendar calendar, int calendarType, int next) {

        calendar.add(calendarType, next);
        Date date = calendar.getTime();
        String dateString = date_Formater_1.format(date);
        return dateString;

    }

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    public static Long getLongTime() {
        return System.currentTimeMillis();
    }

    /**
     * 秒转化为天小时分秒字符串
     */
    public static String formatSeconds(long seconds) {
        String timeStr = seconds + "秒";
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            timeStr = min + "分" + second + "秒";
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                timeStr = hour + "小时" + min + "分" + second + "秒";
                if (hour > 24) {
                    hour = ((seconds / 60) / 60) % 24;
                    long day = (((seconds / 60) / 60) / 24);
                    timeStr = day + "天" + hour + "小时" + min + "分" + second + "秒";
                }
            }
        }
        return timeStr;
    }

    /**
     * 秒转化为天小时分秒字符串
     */
    public static String formatTime(long seconds) {
        String timeStr = "00:00:" + ((seconds < 10) ? "0" + seconds : seconds);
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            timeStr = "00:" + ((min < 10) ? "0" + min : min) + ":" + ((second < 10) ? "0" + second : second);
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                timeStr = ((hour < 10) ? "0" + hour : hour) + ":" + ((min < 10) ? "0" + min : min) + ":" + ((second < 10) ? "0" + second : second);
            }
        }
        return timeStr;
    }

    /**
     * 秒转化为天小时分秒字符串
     */
    public static String formatTime2(long seconds) {
        String timeStr = "00:" + ((seconds < 10) ? "0" + seconds : seconds);
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            timeStr = ((min < 10) ? "0" + min : min) + ":" + ((second < 10) ? "0" + second : second);
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                timeStr = ((hour < 10) ? "0" + hour : hour) + ":" + ((min < 10) ? "0" + min : min) + ":" + ((second < 10) ? "0" + second : second);
            }
        }
        return timeStr;
    }

    /**
     * 时间比较
     */
    public static int compareTime(String startTime, String finishTime) {
        int i = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//年-月-日 时-分
        try {
            Date date1 = dateFormat.parse(startTime);//开始时间
            Date date2 = dateFormat.parse(finishTime);//结束时间
            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime() < date1.getTime()) {
                i = 1;
            } else if (date2.getTime() == date1.getTime()) {
                i = 2;
            } else if (date2.getTime() > date1.getTime()) {
                //正常情况下的逻辑操作.
                i = 3;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static class TimeInfo {
        private long startTime;
        private long endTime;

        public TimeInfo() {
        }

        public long getStartTime() {
            return this.startTime;
        }

        public void setStartTime(long var1) {
            this.startTime = var1;
        }

        public long getEndTime() {
            return this.endTime;
        }

        public void setEndTime(long var1) {
            this.endTime = var1;
        }
    }

}
