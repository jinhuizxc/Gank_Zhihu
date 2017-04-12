package com.example.jh.gank_zhihu.bean.daily;

import java.io.Serializable;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 */

public class DailyTimeLine implements Serializable {

    private Meta meta;
    private Response response;

    public Meta getMeta() {
        return meta;
    }
    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "DailyTimeLine{" +
                "meta=" + meta +
                ", response=" + response +
                '}';
    }
}
