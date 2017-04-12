package com.example.jh.gank_zhihu.ui.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.jh.gank_zhihu.BuildConfig;
import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.base.MVPBaseActivity;
import com.example.jh.gank_zhihu.widget.SplashView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jinhui  on 2017/4/11
 * 邮箱: 1004260403@qq.com
 */

public class SplashActivity extends MVPBaseActivity {

    private static final String TAG = "SplashActivity";
    private Handler mHandler = new Handler();

    @Bind(R.id.splash_view)
    SplashView splashView;
    @Bind(R.id.tv_splash_info)
    TextView tvSplashInfo;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onStart() {
        super.onStart();
        AssetManager mgr = getAssets();//得到AssetManager
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/rm_albion.ttf");//根据路径得到Typeface
        tvSplashInfo.setTypeface(tf);//设置字体
        // 加载动画
        startLoadingData();
    }

    private void startLoadingData() {
        // finish "loading data" in a random time between 1 and 3 seconds
        Random random = new Random();
        mHandler.postDelayed(this::onLoadingDataEnded, 1000 + random.nextInt(2000));
    }

    private void onLoadingDataEnded() {
        // start the splash animation
        splashView.splashAndDisappear(new SplashView.ISplashListener() {
            @Override
            public void onStart() {
                // log the animation start event
                if(BuildConfig.DEBUG){
                    Log.d(TAG, "splash started");
                }
            }

            @Override
            public void onUpdate(float completionFraction) {
                // log animation update events
                if(BuildConfig.DEBUG){
                    Log.d(TAG, "splash at " + String.format("%.2f", (completionFraction * 100)) + "%");
                }
            }

            @Override
            public void onEnd() {
                // log the animation end event
                if(BuildConfig.DEBUG){
                    Log.d(TAG, "splash ended");
                }
                // free the view so that it turns into garbage
                splashView = null;
                goToMain();
            }
        });
    }

    private void goToMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    // 通常加载动画的方法在跳转之前执行，这里将方法体提取出来写啦
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,0);
    }
}
