package com.example.jh.gank_zhihu.bean.gank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 */

public class Meizhi implements Serializable {

    private boolean error;
    private List<Gank> results;

    public boolean isError() {
        return error;
    }

    public List<Gank> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Meizhi{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}

