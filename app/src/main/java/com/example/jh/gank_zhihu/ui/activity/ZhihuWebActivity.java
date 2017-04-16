package com.example.jh.gank_zhihu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.MVPBaseActivity;
import com.example.jh.gank_zhihu.ui.presenter.ZhihuWebPresenter;
import com.example.jh.gank_zhihu.ui.view.ZhihuWebView;

import butterknife.Bind;

/**
 * Created by jinhui  on 2017/4/11
 * 邮箱: 1004260403@qq.com
 */

public class ZhihuWebActivity extends MVPBaseActivity <ZhihuWebView, ZhihuWebPresenter> implements ZhihuWebView{

    private static final String ID = "id";
    private String id;

    @Bind(R.id.web_view)
    WebView web_view;
    @Bind(R.id.iv_web_img)
    ImageView iv_web_img;
    @Bind(R.id.tv_img_title)
    TextView tv_img_title;
    @Bind(R.id.tv_img_source)
    TextView tv_img_source;

    @Override
    protected ZhihuWebPresenter createPresenter() {
        return new ZhihuWebPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_web_view;
    }

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, ZhihuWebActivity.class);
        intent.putExtra(ZhihuWebActivity.ID, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent();
        mPresenter.getDetailNews(id);
    }

    private void parseIntent() {
        id = getIntent().getStringExtra(ID);
    }

    @Override
    public WebView getWebView() {
        return web_view;
    }

    @Override
    public ImageView getWebImg() {
        return iv_web_img;
    }

    @Override
    public TextView getImgTitle() {
        return tv_img_title;
    }

    @Override
    public TextView getImgSource() {
        return tv_img_source;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destoryImg();
    }
}
