package com.github.xch168.gank.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xch on 2017/3/26.
 */

public class DateUtil {

    public static String format(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd hh:mm");
        return dateFormat.format(date);
    }
}
