package com.example.jh.gank_zhihu;

import android.app.Application;
import android.content.Context;

/**
 * Created by jinhui  on 2017/4/11
 * 邮箱: 1004260403@qq.com
 */

public class MyApp extends Application {

    private static final String DB_NAME = "weibo.db";
    // 上下文对象
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
