package com.example.jh.gank_zhihu.ui.fragment;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.base.MVPBaseFragment;
import com.example.jh.gank_zhihu.ui.presenter.DailyFgPresenter;

/**
 * Created by jinhui  on 2017/4/12
 * 邮箱: 1004260403@qq.com
 */

public class DailyFragment extends MVPBaseFragment {


    // 默认是return null，
    // 这个方法返回的是p，所以要return的对象是子类p的引用
    // 传入一个空的构造函数即可。
    @Override
    protected BasePresenter createPresenter() {
        return new DailyFgPresenter();
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_daily;
    }
}
