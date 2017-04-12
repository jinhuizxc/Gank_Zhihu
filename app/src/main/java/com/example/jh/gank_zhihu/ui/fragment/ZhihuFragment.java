package com.example.jh.gank_zhihu.ui.fragment;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.base.MVPBaseFragment;
import com.example.jh.gank_zhihu.ui.presenter.ZhihuFgPresenter;

/**
 * Created by jinhui  on 2017/4/12
 * 邮箱: 1004260403@qq.com
 */

public class ZhihuFragment extends MVPBaseFragment {

    @Override
    protected ZhihuFgPresenter createPresenter() {
        return new ZhihuFgPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_zhihu;
    }
}
