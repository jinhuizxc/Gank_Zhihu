package com.example.jh.gank_zhihu.bean.zhihu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 */

public class NewsTimeLine implements Serializable {

    private String date;
    private List<Stories> stories;
    private List<TopStories> top_stories;

    public String getDate() {
        return date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public List<TopStories> getTop_stories() {
        return top_stories;
    }

    @Override
    public String toString() {
        return "NewsTimeLine{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
