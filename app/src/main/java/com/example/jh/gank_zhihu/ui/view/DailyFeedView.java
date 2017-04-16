package com.example.jh.gank_zhihu.ui.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jinhui  on 2017/4/16
 * 邮箱: 1004260403@qq.com
 */

public interface DailyFeedView {
    void setDataRefresh(Boolean refresh);
    RecyclerView getRecyclerView();
    GridLayoutManager getLayoutManager();
}
