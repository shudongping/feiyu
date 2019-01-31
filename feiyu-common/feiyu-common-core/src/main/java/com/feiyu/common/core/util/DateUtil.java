package com.feiyu.common.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理类
 */
public class DateUtil {

    private DateUtil() {
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 使用默认的SimpleDataFormat格式化处理时间对象
     * pattern =  “yyyy-MM-dd”
     *
     * @param date
     * @return
     */
    public static String dateFormat(Date date) {
        return sdf.format(date);
    }


    /**
     * 自定义格式处理时间对象
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


    public static Date strFormat(String dataStr) throws ParseException {
        return sdf.parse(dataStr);
    }

    public static Date strFormat(String dataStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dataStr);
    }


}
