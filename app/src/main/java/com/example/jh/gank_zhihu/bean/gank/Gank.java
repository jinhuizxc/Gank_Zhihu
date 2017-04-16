package com.example.jh.gank_zhihu.bean.gank;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 */

public class Gank implements Serializable {

    private String url;
    private String type;
    // 设置title的描述
    public  String desc;
    private String who;
    private boolean used;
    private Date createdAt;
    private Date updatedAt;
    private Date publishedAt;

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getWho() {
        return who;
    }

    public boolean isUsed() {
        return used;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    @Override
    public String toString() {
        return "Gank{" +
                "url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                ", who='" + who + '\'' +
                ", used=" + used +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", publishedAt=" + publishedAt +
                '}';
    }
}

