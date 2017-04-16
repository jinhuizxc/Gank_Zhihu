package com.example.jh.gank_zhihu.ui.presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.view.GankWebView;

/**
 * Created by jinhui  on 2017/4/12
 * 邮箱: 1004260403@qq.com
 */

public class GankWebPresenter extends BasePresenter<GankWebView>{

    private static final String TAG = "GankWebPresenter";
    // 这里传入的是Activity的参数，通常都会传上下文，
    // 这里有特别用意。
    private Activity activity;
    public GankWebPresenter(Activity activity) {
        this.activity = activity;
    }


    public void setWebView(String url) {
        GankWebView urlView = getView();
        ProgressBar progressBar = urlView.getProgressBar();
        WebView webView = urlView.getWebView();
        // 导入import android.webkit.WebSettings;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);// 支持JS
        settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        settings.setUseWideViewPort(true);// 支持双击放大缩小
        webView.setWebViewClient(new WebViewClient(){
            // 下面这2个方法要自己手动添加，并不会自动重载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                System.out.println("网页开始加载");
                progressBar.setVisibility(View.VISIBLE);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("网页加载结束");
                progressBar.setVisibility(View.GONE);
            }
            /**
             * 所有跳转的链接都在此方法中回调,但是少了这个方法程序依旧可以执行，
             * 还有并不会输出 System.out.println("loadUrl");这个log，说明这个方法没有执行到。
             * 有时间可以再看一下以前的webView的笔记。
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                System.out.println("loadUrl");
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                System.out.println("newProgress =" + newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                System.out.println("网页title："+title);
                activity.setTitle(title);
                // 从GankWebActivity到到GankWebActivity的跳转的title在webView方法中实现
                // 设置activity.setTitle(title);
            }
        });
        webView.loadUrl(url);
    }
}
