package com.zjzcn.util;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @className:DateUtil.java
 * @classDescription:时间处理类
 * @author:zhangjz
 * @createTime:Apr 3, 2008
 */
public class DateUtils
{
    private static SimpleDateFormat dateFormat = new SimpleDateFormat();
    
    public static String formatTime(long date)
    {
        return format(date, "HH:mm:ss");
    }
    public static String formatData(long date)
    {
        return format(date, "yyyy-MM-dd"); 
    }
    public static String formatDataTime(long date)
    {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    public static String formatDataTime(String date)
    {
        if(StringUtils.isBlank(date))
        {
            return date;
        }
        
        Date d = stringToDate(date, "yyyy-MM-dd HH:mm:ss");
        return dateToString(d, "yyyyMMddHHmmss");
    }
    
    public static String formatData(String date)
    {
        if(StringUtils.isBlank(date))
        {
            return date;
        }
        
        Date d = stringToDate(date, "yyyy-MM-dd");
        return dateToString(d, "yyyyMMdd");
    }
    
    public static String getCurrentDateTime()
    {
        long curr = System.currentTimeMillis();
        return formatDataTime(curr); 
    }
    
    public static String format(long date, String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat (format);       
        Date curDate = new Date(date);//获取当前时间       
        return formatter.format(curDate); 
    }
    
    public static long toLongTime(int year, int month, int day, int hour, int minute, int second)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day, hour, minute, second);
        long time = cal.getTimeInMillis();
        return time; 
    }
    /**
     * 将时间转换成数据
     * 
     * @param date
     *            需要转换的时间
     * @param format
     *            转换得格式 例如"yyyy-MM-dd hh:mm:ss"
     * @return String
     */
    public static String dateToString(Timestamp date, String format)
    {
        // 附加时间格式
        dateFormat.applyPattern(format);
        // 将时间转换成字符串
        return dateFormat.format(date);
    }
    
    /**
     * 将时间转换成数据
     * 
     * @param date
     *            需要转换的时间
     * @param format
     *            转换得格式 例如"yyyy-MM-dd hh:mm:ss"
     * @return String
     */
    public static String dateToString(Date date, String format)
    {
        // 附加时间格式
        dateFormat.applyPattern(format);
        // 将时间转换成字符串
        return dateFormat.format(date);
    }
    
    /**
     * 将时间转换成时间
     * 
     * @param date
     *            需要转换的时间
     * @param format
     *            转换得格式 例如"yyyy-MM-dd hh:mm:ss"
     * @return Date
     */
    public static Date formatDate(Date date, String format)
    {
        // 附加时间格式
        dateFormat.applyPattern(format);
        // 将时间转换成字符串
        try
        {
            return dateFormat.parse(dateToString(date, format));
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 将字符串转换成时间
     * 
     * @param dateString
     *            需要转换的时间字符串
     * @param format
     *            转换得格式 例如"yyyy-MM-dd hh:mm:ss"
     * @return Date
     */
    public static Date stringToDate(String dateString, String format)
    {
        if(format==null)
        {
            format="yyyy-MM-dd HH:mm:ss";
        }
        // 附加时间格式
        dateFormat.applyPattern(format);
        // 将时间转换成字符串
        try
        {
            return dateFormat.parse(dateString);
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 比较两个时间的差值(以秒为单位)
     * 
     * @param date1
     *            时间1
     * @param date2
     *            时间2
     * @return long
     */
    public static long dateDiff(Date date1, Date date2)
    {
        return date2.getTime() / 1000 - date1.getTime() / 1000; // 用立即数，减少乘法计算的开销
    }



    /**
     * 作用：获取当前时间 (Date 参数：null, 格式 yyyy-MM-dd HH:mm:ss)
     */
    public static String getCurrentTime(String format)
    {
        format = (format==null ? "yyyy-MM-dd HH:mm:ss" : format);
        // 获取当前日期
        String strTime = new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
        return strTime;
    }
    
    
    public static void main(String[] args)
    {
        String strTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        
        System.out.println(strTimestamp);
        System.out.println(DateUtils.stringToDate(strTimestamp, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(getCurrentTime(null));
    }
}
