package com.example.jh.gank_zhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.MVPBaseFragment;
import com.example.jh.gank_zhihu.ui.presenter.GankFgPresenter;
import com.example.jh.gank_zhihu.ui.view.GankFgView;

import butterknife.Bind;

/**
 * Created by jinhui  on 2017/4/12
 * 邮箱: 1004260403@qq.com
 */

public class GankFragment extends MVPBaseFragment<GankFgView, GankFgPresenter> implements GankFgView {

    private static final String TAG = "GankFragment";
    private GridLayoutManager gridLayoutManager;

    @Bind(R.id.content_list)
    RecyclerView content_list;

    @Override
    protected GankFgPresenter createPresenter() {
        return new GankFgPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView(View rootView) {
//        super.initView(rootView);
        gridLayoutManager = new GridLayoutManager(getContext(), 2); // 2代表gardview横向2个item
        content_list.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 设置加载进度条
        setDataRefresh(true);
        // 获取数据
        mPresenter.getGankData();
        mPresenter.scrollRecycleView();
    }

    // 这个方法什么时候会被执行？
    @Override
    protected void requestDataRefresh() {
        super.requestDataRefresh();
        Log.e(TAG, "requestDataRefresh 子类方法被执行");
        setDataRefresh(true);
        mPresenter.getGankData();
    }

    @Override
    public void setDataRefresh(Boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public GridLayoutManager getLayoutManager() {
        return gridLayoutManager;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return content_list;
    }
}
