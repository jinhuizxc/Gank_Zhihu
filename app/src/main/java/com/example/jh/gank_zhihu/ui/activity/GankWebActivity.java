package com.example.jh.gank_zhihu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.base.MVPBaseActivity;
import com.example.jh.gank_zhihu.ui.presenter.GankWebPresenter;
import com.example.jh.gank_zhihu.ui.view.IGankWebView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jinhui  on 2017/4/11
 * 邮箱: 1004260403@qq.com
 *
 */

public class GankWebActivity extends MVPBaseActivity <IGankWebView, GankWebPresenter> implements IGankWebView{

    private static final String GANK_URL = "gank_url";
    private static final String TAG = "GankWebActivity";
    @Bind(R.id.pb_progress)
    ProgressBar pbProgress;
    @Bind(R.id.url_web)
    WebView urlWeb;

    private String gank_url;
    // 当我将这个activity通过泛型继承GankWebPresenter下面这个方法会报错，
    // 这个时候可以这么调整，将BasePresenter父类的换成子类的GankWebPresenter，
    // 为什么要这么做？因为子类就是子类要有自己的东西
    @Override
    protected GankWebPresenter createPresenter() {
        return new GankWebPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_gank_web;
    }

    // 要写一个有return对象的方法，必须此前需要这个方法调用
    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, GankWebActivity.class);
        intent.putExtra(GankWebActivity.GANK_URL, url);
        return intent;
    }

    // 通过快速注解得到的ButterKnife的控件id会自动生成下面这个方法，
    // 但是父类中有了就不需要这么写啦
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 得到Intent传递的数据
        parseIntent();
        mPresenter.setWebView(gank_url);
    }
    /**
     * 得到Intent传递的数据
     */
    private void parseIntent() {
        gank_url = getIntent().getStringExtra(GANK_URL);
        Log.e(TAG, "gank_url = " + gank_url);
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    public ProgressBar getProgressBar() {
        return pbProgress;
    }

    @Override
    public WebView getWebView() {
        return urlWeb;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        urlWeb.destroy();
    }
}
