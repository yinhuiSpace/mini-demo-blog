package com.mito.common.utils;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateTimeClient {

    public static final String SIMPLE_FORMAT="yyyy-MM-dd HH:mm:ss";

    /**
    * 时间戳转字符串
    * */
    public static String toStr(Timestamp timestamp,String timeFormat){
        //1.时间戳转本地时间
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        //2.本地时间转字符串
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);

        return formatter.format(localDateTime);
    }
}
