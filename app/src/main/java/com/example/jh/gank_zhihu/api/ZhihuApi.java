package com.example.jh.gank_zhihu.api;

import com.example.jh.gank_zhihu.bean.zhihu.News;
import com.example.jh.gank_zhihu.bean.zhihu.NewsTimeLine;
import com.example.jh.gank_zhihu.bean.zhihu.SplashImage;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 *
 * 知乎api
 */

public interface ZhihuApi {

    @GET("start-image/1080*1920")
    Observable<SplashImage> getSplashImage();

    @GET("news/latest")
    Observable<NewsTimeLine> getLatestNews();

    @GET("news/before/{time}")
    Observable<NewsTimeLine> getBeforetNews(@Path("time") String time);

    @GET("news/{id}")
    Observable<News> getDetailNews(@Path("id") String id);
}
