package com.example.jh.gank_zhihu.api;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 */

public class ApiFactory {

    protected static final Object monitor = new Object();
    //定义3个api接口的单例模式
    public static ZhihuApi zhihuApiSingleton = null;
    public  static GankApi gankApiSingleton = null;
    public static DailyApi dailyApiSingleton = null;

    // return 单例模式     知乎api
    public static ZhihuApi getZhihuApiSingleton(){
        synchronized (monitor){
            if (zhihuApiSingleton == null){
                zhihuApiSingleton = new ApiRetrofit().getZhihuApiService();
            }
            return zhihuApiSingleton;
        }
    }

    // GankApi
    public static GankApi getGankApiSingleton() {
        synchronized (monitor) {
            if (gankApiSingleton == null) {
                gankApiSingleton = new ApiRetrofit().getGankApiService();
            }
            return gankApiSingleton;
        }
    }

    // DailyApi
    public static DailyApi getDailyApiSingleton() {
        synchronized (monitor) {
            if (dailyApiSingleton == null) {
                dailyApiSingleton = new ApiRetrofit().getDailyApiService();
            }
            return dailyApiSingleton;
        }
    }


}
