package com.example.jh.gank_zhihu.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jh.gank_zhihu.R;

import butterknife.ButterKnife;

/**
 * Created by jinhui  on 2017/4/11
 * 邮箱: 1004260403@qq.com
 */

public abstract class MVPBaseFragment <V,P extends BasePresenter<V>> extends Fragment{

    protected P mPresenter;
    private SwipeRefreshLayout mRefreshLayout;
    private boolean mIsRequestDataRefresh = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(createViewLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        // 初始化控件
        initView(rootView);
        // 设置刷新view
        if(isSetRefresh()) {
            setupSwipeRefresh(rootView);
        }
        return rootView;
    }

    private void setupSwipeRefresh(View rootView) {
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        if(mRefreshLayout != null){
            mRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1,
                    R.color.refresh_progress_2,R.color.refresh_progress_3);
            mRefreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
                    .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,getResources().getDisplayMetrics()));
            mRefreshLayout.setOnRefreshListener(this::requestDataRefresh);
        }
    }

    protected void requestDataRefresh() {
        mIsRequestDataRefresh = true;
    }

    private boolean isSetRefresh() {
        return true;
    }


    // 设置刷新的方法
    public void setRefresh(boolean requestDataRefresh) {
        if (mRefreshLayout == null) {
            return;
        }
        if (!requestDataRefresh) {
            mIsRequestDataRefresh = false;
            mRefreshLayout.postDelayed(() -> {
                if (mRefreshLayout != null) {
                    mRefreshLayout.setRefreshing(false);
                }
            },1000);
        } else {
            mRefreshLayout.setRefreshing(true);
        }
    }

    // 此方法改为protected才能被子类调用
    protected void initView(View rootView) {
    }

    // 抽象方法
    protected abstract P createPresenter();
    protected abstract int createViewLayoutId();


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

}
