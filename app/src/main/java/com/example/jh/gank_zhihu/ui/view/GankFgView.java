package com.example.jh.gank_zhihu.ui.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jinhui  on 2017/4/13
 * 邮箱: 1004260403@qq.com
 */

public interface GankFgView {

    void setDataRefresh(Boolean refresh);
    GridLayoutManager getLayoutManager();
    RecyclerView getRecyclerView();
}
