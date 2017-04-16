package com.example.jh.gank_zhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.base.MVPBaseFragment;
import com.example.jh.gank_zhihu.ui.presenter.DailyFgPresenter;
import com.example.jh.gank_zhihu.ui.view.DailyFgView;

import butterknife.Bind;

/**
 * Created by jinhui  on 2017/4/12
 * 邮箱: 1004260403@qq.com
 *
 * 谨记：关于在写setRefresh()刷新这个方法的时候的注意点：
 * 1.开始写刷新方法时，默认一直在加载，不会取消
 *
 */

public class DailyFragment extends MVPBaseFragment <DailyFgView, DailyFgPresenter> implements DailyFgView{


    // 默认是return null，
    // 这个方法返回的是p，所以要return的对象是子类p的引用
    // 传入一个空的构造函数即可。

    @Bind(R.id.content_list)
    RecyclerView content_list;

    private LinearLayoutManager mLayoutManager;

    @Override
    protected DailyFgPresenter createPresenter() {
        return new DailyFgPresenter(getContext());   // 对于Activity用this，Fragment获取上下文 getContext()
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_daily;
    }

    // 初始化View,在父类oncreate()方法里面已经执行
    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mLayoutManager = new LinearLayoutManager(getContext());
        content_list.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDataRefresh(true);
        mPresenter.getDailyTimeLine("0");
        mPresenter.scrollRecycleView();
    }

    // 不加这个方法无法取消刷新
    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getDailyTimeLine("0");
    }

    @Override
    public void setDataRefresh(Boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return content_list;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }
}
