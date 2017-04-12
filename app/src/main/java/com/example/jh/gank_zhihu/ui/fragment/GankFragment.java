package com.example.jh.gank_zhihu.ui.fragment;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.base.MVPBaseFragment;
import com.example.jh.gank_zhihu.ui.presenter.GankFgPresenter;

/**
 * Created by jinhui  on 2017/4/12
 * 邮箱: 1004260403@qq.com
 */

public class GankFragment extends MVPBaseFragment {
    @Override
    protected BasePresenter createPresenter() {
        return new GankFgPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_gank;
    }
}
