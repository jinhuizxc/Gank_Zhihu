package com.example.jh.gank_zhihu.ui.base;

import com.example.jh.gank_zhihu.api.ApiFactory;
import com.example.jh.gank_zhihu.api.DailyApi;
import com.example.jh.gank_zhihu.api.GankApi;
import com.example.jh.gank_zhihu.api.ZhihuApi;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by jinhui  on 2017/4/10
 * 邮箱: 1004260403@qq.com
 * <p>
 * 在本类中封装了zhihuApi、gankApi、dailyApi
 */

public class BasePresenter<V> {

    // 这里在基类的presenter里面对api进行封装，直接调用
    public static final ZhihuApi zhihuApi = ApiFactory.getZhihuApiSingleton();
    public static final GankApi gankApi = ApiFactory.getGankApiSingleton();
    public static final DailyApi dailyApi = ApiFactory.getDailyApiSingleton();

    // 软引用
    protected Reference<V> mViewRef;

    protected V getView() {
        return mViewRef.get();
    }

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
