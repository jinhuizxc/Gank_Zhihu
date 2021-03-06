package com.example.jh.gank_zhihu.bean.daily;

import java.io.Serializable;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 */

public class Post implements Serializable {

    private String appview;
    private String title;
    private String id;
    private String description;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public String getAppview() {
        return appview;
    }

    public void setAppview(String appview) {
        this.appview = appview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "appview='" + appview + '\'' +
                ", title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
