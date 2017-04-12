package com.example.jh.gank_zhihu.bean.daily;

import java.io.Serializable;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 */

public class Category implements Serializable {

    private String image_lab;
    private String title;

    public String getImage_lab() {
        return image_lab;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "image_lab='" + image_lab + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
