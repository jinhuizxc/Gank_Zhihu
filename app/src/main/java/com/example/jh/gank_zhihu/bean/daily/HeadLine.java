package com.example.jh.gank_zhihu.bean.daily;

import java.io.Serializable;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 */

public class HeadLine implements Serializable {

    private String description;
    private String title;

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "HeadLine{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
