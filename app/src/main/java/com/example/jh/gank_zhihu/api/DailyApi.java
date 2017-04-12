package com.example.jh.gank_zhihu.api;

import com.example.jh.gank_zhihu.bean.daily.DailyTimeLine;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 */

public interface DailyApi {

    @GET("homes/index/{num}.json")
    Observable<DailyTimeLine> getDailyTimeLine(@Path("num") String num);

    @GET("options/index/{id}/{num}.json")
    Observable<DailyTimeLine> getDailyFeedDetail(@Path("id") String id, @Path("num") String num);

}
