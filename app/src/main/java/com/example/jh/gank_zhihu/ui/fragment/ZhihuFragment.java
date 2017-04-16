package com.example.jh.gank_zhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.MVPBaseFragment;
import com.example.jh.gank_zhihu.ui.presenter.ZhihuFgPresenter;
import com.example.jh.gank_zhihu.ui.view.ZhihuFgView;

import butterknife.Bind;

/**
 * Created by jinhui  on 2017/4/12
 * 邮箱: 1004260403@qq.com
 */

public class ZhihuFragment extends MVPBaseFragment<ZhihuFgView, ZhihuFgPresenter> implements ZhihuFgView {

    private LinearLayoutManager mLayoutManager;
    @Bind(R.id.content_list)
    RecyclerView contentList;

    @Override
    protected ZhihuFgPresenter createPresenter() {
        return new ZhihuFgPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initView(View rootView) {
        mLayoutManager = new LinearLayoutManager(getContext());
        contentList.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 父类方法
        setRefresh(true);
        mPresenter.getLatestNews();
        mPresenter.scrollRecycleView();
    }

    // 不加这个方法无法取消刷新
    @Override
    protected void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getLatestNews();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return contentList;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    @Override
    public void setDataRefresh(Boolean refresh) {
        setRefresh(refresh);
    }
}
