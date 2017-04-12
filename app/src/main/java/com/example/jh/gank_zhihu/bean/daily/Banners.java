package com.example.jh.gank_zhihu.bean.daily;

import java.io.Serializable;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 *
 * 这个类暂时没用到，通过url看看HTML给出的结果可以知道返回什么信息，待测试。
 */

public class Banners implements Serializable {

    private String image;
    private int type;
    private Post post;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Banners{" +
                "image='" + image + '\'' +
                ", type=" + type +
                ", post=" + post +
                '}';
    }
}
