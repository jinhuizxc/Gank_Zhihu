package com.example.jh.gank_zhihu.ui.view;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jinhui  on 2017/4/13
 * 邮箱: 1004260403@qq.com
 */

public interface ZhihuWebView {
    WebView getWebView();

    ImageView getWebImg();

    TextView getImgTitle();

    TextView getImgSource();
}
