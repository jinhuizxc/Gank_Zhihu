package com.example.jh.gank_zhihu.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.bean.daily.DailyTimeLine;
import com.example.jh.gank_zhihu.ui.adapter.DailyListAdapter;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.view.DailyFgView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jinhui  on 2017/4/12
 * 邮箱: 1004260403@qq.com
 */

public class DailyFgPresenter extends BasePresenter<DailyFgView> {

    private static final String TAG = "DailyFgPresenter";
    private Context context;
    private DailyFgView dailyFgView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private DailyTimeLine timeLine;
    private DailyListAdapter adapter;
    private int lastVisibleItem;
    private String has_more;
    private String next_pager;
    private boolean isLoadMore = false; // 是否加载过更多

    public DailyFgPresenter(Context context) {
//      这个super()的方法有也可以，没有也可以，构造方法里面的上下文
//        super();
        this.context = context;
    }

    public void getDailyTimeLine(String num) {
        dailyFgView = getView();
        if (dailyFgView != null) {
            mRecyclerView = dailyFgView.getRecyclerView();
            layoutManager = dailyFgView.getLayoutManager();

            dailyApi.getDailyTimeLine(num)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dailyTimeLine -> {
                        if (dailyTimeLine.getMeta().getMsg().equals("success")) {
                            disPlayDailyTimeLine(context, dailyTimeLine, mRecyclerView, dailyFgView);
                        }
                    }, this::loadError);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        dailyFgView.setDataRefresh(false);
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
    }

    private void disPlayDailyTimeLine(Context context, DailyTimeLine dailyTimeLine, RecyclerView mRecyclerView, DailyFgView dailyFgView) {
        if (dailyTimeLine.getResponse().getLast_key() != null) {
            next_pager = dailyTimeLine.getResponse().getLast_key();
        }
        has_more = dailyTimeLine.getResponse().getHas_more();
        // 是否加载更多
        if (isLoadMore) {
            if (dailyTimeLine.getResponse().getFeeds() == null) {
                adapter.updateLoadStatus(adapter.LOAD_NONE);
                dailyFgView.setDataRefresh(false);
                return;
            } else {
                timeLine.getResponse().getFeeds().addAll(dailyTimeLine.getResponse().getFeeds());
            }
            adapter.notifyDataSetChanged();
        } else {
            timeLine = dailyTimeLine;
            adapter = new DailyListAdapter(context, timeLine.getResponse());
            mRecyclerView.setAdapter(adapter);
        }
        dailyFgView.setDataRefresh(false);

    }

    /**
     * recyclerView Scroll listener , maybe in here is wrong ?
     * 这里是否有错？
     */
    public void scrollRecycleView() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = layoutManager
                            .findLastVisibleItemPosition();
                    Log.e(TAG, "lastVisibleItem =" + lastVisibleItem);
                    if (layoutManager.getItemCount() == 1) {
                        adapter.updateLoadStatus(adapter.LOAD_NONE);
                        return;
                    }
                    if (lastVisibleItem + 1 == layoutManager
                            .getItemCount()) {
                        adapter.updateLoadStatus(adapter.LOAD_PULL_TO);
                        if (has_more.equals("true")) {
                            isLoadMore = true;
                        }
                        adapter.updateLoadStatus(adapter.LOAD_MORE);
                        new Handler().postDelayed(() -> getDailyTimeLine(next_pager), 1000);
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
}
