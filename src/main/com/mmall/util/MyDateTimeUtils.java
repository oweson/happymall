package com.mmall.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/8/28 0028 10:11
 */
public class MyDateTimeUtils {
    public static final String FORMAT = "yyyy-mm-dd HH:mm:ss";

    public static String dateToString(Date d) {
        if (d == null) {
            return StringUtils.EMPTY;
        }
        DateTime date = new DateTime(d);
        return date.toString(FORMAT);
    }

    public static void main(String[] args) {
        String s = dateToString(new Date());
        System.out.println(s);
    }

}
