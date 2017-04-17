package com.example.jh.gank_zhihu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.MVPBaseActivity;
import com.example.jh.gank_zhihu.ui.presenter.GankWebPresenter;
import com.example.jh.gank_zhihu.ui.view.GankWebView;

import butterknife.Bind;

/**
 * Created by jinhui  on 2017/4/11
 * 邮箱: 1004260403@qq.com
 * java.lang.IllegalArgumentException: Receiver not registered:
 * android.widget.ZoomButtonsController$1@3bdd2de8 at android.app.LoadedApk.forgetReceiverDispatcher(LoadedApk.java:871)
 * at android.app.ContextImpl.unregisterReceiver(ContextImpl.java:2110)
 * at android.content.ContextWrapper.unregisterReceiver(ContextWrapper.java:529)
 * 解决：发现是webview的 ZoomButton，也就是那两个放大和缩小的按钮导致的。
 * 如果设置为让他们出现，并且可以自动隐藏，那么，由于他们的自动隐藏是一个渐变的过程，
 * 所以在逐渐消失的过程中如果调用了父容器的destroy方法，就会导致Leaked。 所以解决方案是，在destroy之前，先让他俩立马消失。
 * 我的解决办法是，在finish掉此activity时，把子view 全部remove掉。
 * 理论上说，只需要remove这个zoom view就可以，但是我没找到获取该view的办法，
 * 只好remove掉所有的子view。这样在activity destroy时就不会报 WindowLeaked的错误了。
 * @Override public void finish() {
 * ViewGroup view = (ViewGroup) getWindow().getDecorView();
 * view.removeAllViews();
 * super.finish();
 * }
 * 也有人说，可以直接设置webView.setVisiable(View.GONE)；
 * 在Activity的onDestroy里面加上这么一句：web.setVisibility(View.GONE);把WebView设置为GONE就可以了。
 *
 */

public class GankWebActivity extends MVPBaseActivity <GankWebView, GankWebPresenter> implements GankWebView {

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
