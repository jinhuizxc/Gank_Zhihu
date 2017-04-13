package com.example.jh.gank_zhihu.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.bean.zhihu.NewsTimeLine;
import com.example.jh.gank_zhihu.ui.adapter.ZhihuListAdapter;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.view.ZhihuView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jinhui  on 2017/4/13
 * 邮箱: 1004260403@qq.com
 */

public class ZhihuPresenter extends BasePresenter<ZhihuView>{

    private Context context;
    private NewsTimeLine timeLine;
    private ZhihuView zhihuView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private boolean isLoadMore = false; // 是否加载过更多
    private ZhihuListAdapter adapter;
    // scroll的监听的属性
    private int lastVisibleItem;

    public ZhihuPresenter(Context context) {
        this.context = context;
    }

    // 得到最新新闻
    public void getLatestNews() {
        zhihuView = getView();
        if(zhihuView != null){
            mRecyclerView = zhihuView.getRecyclerView();
            layoutManager = zhihuView.getLayoutManager();
            //view获取信息的方法实现,这个方法是api中的方法getLatestNews
            zhihuApi.getLatestNews()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(newsTimeLine ->{
                        // 显示知乎新闻列表
                        disPlayZhihuList(newsTimeLine, context, zhihuView, mRecyclerView);
                    });
        }
    }

    String time;
    private void disPlayZhihuList(NewsTimeLine newsTimeLine, Context context, ZhihuView zhihuView, RecyclerView mRecyclerView) {
        if (isLoadMore) {
            if (time == null) {
                adapter.updateLoadStatus(adapter.LOAD_NONE);
                zhihuView.setDataRefresh(false);
                return;
            }
            else {
                timeLine.getStories().addAll(newsTimeLine.getStories());
            }
            adapter.notifyDataSetChanged();
        } else {
            timeLine = newsTimeLine;
            adapter = new ZhihuListAdapter(context, timeLine);
            mRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        zhihuView.setDataRefresh(false);
        time = newsTimeLine.getDate();
    }

    /**
     * recyclerView Scroll listener , maybe in here is wrong ?
     */
    public void scrollRecycleView() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = layoutManager
                            .findLastVisibleItemPosition();
                    if (layoutManager.getItemCount() == 1) {
                        adapter.updateLoadStatus(adapter.LOAD_NONE);
                        return;
                    }
                    if (lastVisibleItem + 1 == layoutManager
                            .getItemCount()) {
                        adapter.updateLoadStatus(adapter.LOAD_PULL_TO);
                        isLoadMore = true;
                        adapter.updateLoadStatus(adapter.LOAD_MORE);
                        new Handler().postDelayed(() -> getBeforeNews(time), 1000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    // 得到之前的新闻
    private void getBeforeNews(String time) {
        zhihuView = getView();
        if (zhihuView != null) {
            mRecyclerView = zhihuView.getRecyclerView();
            layoutManager = zhihuView.getLayoutManager();

            zhihuApi.getBeforetNews(time)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(newsTimeLine -> {
                        disPlayZhihuList(newsTimeLine,context,zhihuView,mRecyclerView);
                    },this::loadError);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
    }
}
