package com.yongjinbao.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mashenwei on 16/1/20.
 * 常用的工具类
 */
public class DateUtils {

    public static String DATETYPE_YMD = "yyyy-MM-dd";

    public static String DATETYPE_YMDHM = "yyyy-MM-dd HH:mm";

    public static String DATETYPE_MD = "MM月dd日";

    public static String DATETYPE_MDHM = "MM月dd日 HH:mm";

    public static String DATETYPE_CHAT = "yyyyMMddHHmm";

    public static String DATETYPE_H5_MODIFY = "yyyyMMdd";

    public static String DATETYPE_DAY = "dd";

    public static String DATETYPE_MONTH = "MM";

    public static String DATETYPE_HOUR = "HH";

    public static String DATETYPE_YEAR = "yyyy";

    public static String DATETYPE_MIN = "mm";

    public static String DATETYPE_SMART = "yyyy-MM-dd-HH-mm";


    public static String dateToString(Date date, String dateType){
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
        return dateFormat.format(date);
    }

    public static Boolean compareDate(Date date1, Date date2, String dateType){
        return dateToString(date1,dateType).equals(dateToString(date2, dateType));
    }

    public static String formatNullString(String nullString){
        return nullString.isEmpty()?"":nullString;
    }

    public static String getSmartDateString(Date date){
        //获取date与当前时间now的比较值
        SimpleDateFormat commonFormat = new SimpleDateFormat(DATETYPE_MDHM);
        String commonDateString = commonFormat.format(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATETYPE_SMART);
        String dateString = dateFormat.format(date);
        String[] dateArray = dateString.split("-");
        String nowString = dateFormat.format(new Date());
        String[] nowArray = nowString.split("-");
        //判断年
        if (dateArray[0].equals(nowArray[0])){
            //判断月
            if (dateArray[1].equals(nowArray[1])){
                //判断天
                if (dateArray[2].equals(nowArray[2])){
                    //判断小时
                    if (dateArray[3].equals(nowArray[3])){
                        //判断分钟
                        if (dateArray[4].equals(nowArray[4])){
                            return "刚刚";
                        }else {
                            int sub = parseInt(nowArray[4])-parseInt(dateArray[4]);
                            if (sub>0){
                                return sub+"分钟前";
                            }else {
                                return "刚刚";
                            }
                        }
                    }else {
                        int sub = parseInt(nowArray[3])-parseInt(dateArray[3]);
                        return sub+"小时前";
                    }
                }else {
                    int sub = parseInt(nowArray[2])-parseInt(dateArray[2]);
                    return sub+"天前";
                }
            }
        }
        return commonDateString;
    }

    private static int parseInt(String m){
        return Integer.parseInt(m);
    }


    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String dateToStr(Date date) {
        if (date!=null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String str = format.format(date);
            return str;
        }
        return "";
    }
    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String dateToStrWithTime(Date date) {
        if (date!=null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = format.format(date);
            return str;
        }
        return "";
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date strToDate(String str) {

        if(StringUtils.isNotEmpty(str)){
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
        return  null;
    }

    /**
     * 字符串转换成日期
     * @param dateString
     * @param dateFormate
     * @return
     */
    public final static Date parseDate(String dateString,String dateFormate) {
        SimpleDateFormat sd=new SimpleDateFormat(dateFormate);
        Date date=null;
        try {
            date=sd.parse(dateString);
        } catch (Exception ex) {
            System.out.print("Pase the Date(" + dateString + ") occur errors:"
                    + ex.getMessage());

        }
        return date;
    }


    /**
     * 比较两个日期相差的天数
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daysOfTwoV1(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysOfTwo(Date bdate,Date smdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 日期转换成格式化日期
     * @param date
     * @return str
     */
    public static Date dateToFormatDate(Date date) {
        return date!=null? strToDate(dateToStr(date)):null;
    }

    /**
     * 两个日期的时间差:1个月3天
     * @param smallDate
     * @param bigDate
     * @return
     */
    public static String getMonthAndDaysBetweenDate(String smallDate, String bigDate){
        Map<String, Integer>  map=new HashMap();
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        try {
            d1 = sd.parse(smallDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d2=null;
        try {
            d2 = sd.parse(bigDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int months=0;//相差月份
        int days=0;
        int y1=d1.getYear();
        int y2=d2.getYear();
        int dm1=d2.getMonth();//起始日期月份
        int dm2=d2.getMonth();//结束日期月份
        int dd1=d1.getDate(); //起始日期天
        int dd2=d2.getDate(); //结束日期天
//        if(d1.getTime()<d2.getTime()){
            months=d2.getMonth()-d1.getMonth()+(y2-y1)*12;
            if(dd2<dd1){
                months=months-1;
            }
//            System.out.println(getFormatDateTime(addMonthsToDate(parseDate(date1, "yyyy-MM-dd"),months),"yyyy-MM-dd"));
            try {
                days=daysOfTwo(parseDate(bigDate, "yyyy-MM-dd"),addMonthsToDate(parseDate(smallDate, "yyyy-MM-dd"),months));
                map.put("months", months);
                map.put("days", days);
            }catch (ParseException e){

            }
//        }
        return map.get("months")+"个月"+map.get("days")+"天";
    }

    private static Date addMonthsToDate(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }



    public static void main(String[] args){
//        System.out.print(getSmartDateString(new Date()));
        System.out.print(getMonthAndDaysBetweenDate("2016-08-28 ", "2016-09-21"));
        ;

    }


}
