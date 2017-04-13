package com.example.jh.gank_zhihu.ui.presenter;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.bean.zhihu.News;
import com.example.jh.gank_zhihu.ui.activity.ZhihuWebActivity;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.view.ZhihuWebView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jinhui  on 2017/4/13
 * 邮箱: 1004260403@qq.com
 */

public class ZhihuWebPresenter extends BasePresenter<ZhihuWebView>{

    private Context context;
    public ZhihuWebPresenter(Context context) {
//        super();
        this.context = context;
    }

    public void getDetailNews(String id) {
        zhihuApi.getDetailNews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> {
                   setWebView(news);
                }, this::loadError);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
    }

    // 设置webview
    ImageView webImg;
    TextView imgTitle;
    TextView imgSource;
    private void setWebView(News news) {
        WebView webView = getView().getWebView();
        webImg = getView().getWebImg();
        imgTitle = getView().getImgTitle();
        imgSource = getView().getImgSource();
        // webView的相关配置
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        String head = "<head>\n" +
                "\t<link rel=\"stylesheet\" href=\""+news.getCss()[0]+"\"/>\n" +
                "</head>";
        String img = "<div class=\"headline\">";
        String html =head + news.getBody().replace(img," ");
        webView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);
        // Glide加载图片资源
        Glide.with(context).load(news.getImage()).centerCrop().into(webImg);
        imgTitle.setText(news.getTitle());
        imgSource.setText(news.getImage_source());
    }


    public void destoryImg(){
        if(webImg != null){
            Glide.clear(webImg);
        }
    }
}
