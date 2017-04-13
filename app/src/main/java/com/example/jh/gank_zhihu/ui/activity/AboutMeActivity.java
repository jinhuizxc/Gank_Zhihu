package com.example.jh.gank_zhihu.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.widget.TextView;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.base.MVPBaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jinhui  on 2017/4/11
 * 邮箱: 1004260403@qq.com
 */

public class AboutMeActivity extends MVPBaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_github)
    TextView tvGithub;
    @Bind(R.id.tv_blog)
    TextView tvBlog;
    // 这个布局属性：
    public CollapsingToolbarLayout collapsingToolbarLayout;
    Intent intent;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about_me;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();

        tvGithub.setOnClickListener(this);
        tvBlog.setOnClickListener(this);
    }

    /**
     * 初始化ToolBar
     */
    private void initToolbar() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("很高兴你能看到这里");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_github:
//                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tvGithub.getText().toString()));
//                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
//                startActivity(intent);
                break;
            case R.id.tv_blog:
//                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tvBlog.getText().toString()));
//                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
//                startActivity(intent);
                break;
        }
    }
}
