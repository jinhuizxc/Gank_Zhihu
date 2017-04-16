package com.example.jh.gank_zhihu.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jinhui  on 2017/4/14
 * 邮箱: 1004260403@qq.com
 *
 * 日期工具类
 */

public class DateUtils {

    public static boolean isSameDate(Date date1, Date date2){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        Calendar selectDate = Calendar.getInstance();
        selectDate.setTime(date2);

        return calendar.get(Calendar.DAY_OF_YEAR)
                == selectDate.get(Calendar.DAY_OF_YEAR);
    }
}
