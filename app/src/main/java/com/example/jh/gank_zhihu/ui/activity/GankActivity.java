package com.example.jh.gank_zhihu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.base.MVPBaseActivity;
import com.example.jh.gank_zhihu.ui.presenter.GankPresenter;
import com.example.jh.gank_zhihu.ui.view.GankView;

import java.util.Calendar;

import butterknife.Bind;

/**
 * Created by jinhui  on 2017/4/11
 * 邮箱: 1004260403@qq.com
 * <p>
 * Gank 干货详情界面内容
 *
 */

public class GankActivity extends MVPBaseActivity<GankView, GankPresenter> implements GankView {

    private static final String TAG = "GankActivity";
    @Bind(R.id.gank_list)
    RecyclerView gank_list;

    private static final String DATE = "date";
    private LinearLayoutManager layoutManager;
    private int year;
    private int month;
    private int day;

    @Override
    protected GankPresenter createPresenter() {
        return new GankPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_gank;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutManager = new LinearLayoutManager(this);
        gank_list.setLayoutManager(layoutManager);
        setTitle("Gank の 今日特供");
        parseIntent();
        setDataRefresh(true);
        mPresenter.getGankList(year, month, day);
    }

    private void parseIntent() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getIntent().getLongExtra(DATE, 0));
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }


    public static Intent newIntent(Context context, long date) {
        Intent intent = new Intent(context, GankActivity.class);
        intent.putExtra(GankActivity.DATE, date);
        return intent;
    }


    @Override
    public Boolean isSetRefresh() {
        Log.e(TAG, "isSetRefresh方法被执行");
        return true;
    }

    @Override
    public boolean canBack() {
        Log.e(TAG, "canBack方法被执行");
        return true;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return gank_list;
    }

    @Override
    public void setDataRefresh(boolean refresh) {
        setRefresh(refresh);
    }

    // 这个方法是在父类下拉刷新的方法中执行。
    // 所有的方法都是父类方法先于子类方法执行，包括构造方法
    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        Log.e(TAG, "requestDataRefresh方法被执行");
        setDataRefresh(true);
        mPresenter.getGankList(year, month, day);
    }
}
