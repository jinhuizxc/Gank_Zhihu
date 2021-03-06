package com.example.jh.gank_zhihu.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.bean.daily.DailyTimeLine;
import com.example.jh.gank_zhihu.ui.adapter.DailyFeedAdapter;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.view.DailyFeedView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jinhui  on 2017/4/16
 * 邮箱: 1004260403@qq.com
 */

public class DailyFeedPresenter extends BasePresenter<DailyFeedView> {

    private Context context;
    private DailyFeedView dailyFeedView;
    private RecyclerView mRecyclerView;
    private DailyTimeLine timeLine;
    private DailyFeedAdapter adapter;
    private GridLayoutManager layoutManager;
    private int lastVisibleItem;
    private String has_more;
    private String next_pager;
    private String d_id;
    private boolean isLoadMore = false; // 是否加载过更多

    public DailyFeedPresenter(Context context) {
        this.context = context;
    }

    public void getDailyFeedDetail(String id,String num){
        d_id = id;
        dailyFeedView = getView();
        if(dailyFeedView !=null){
            mRecyclerView = dailyFeedView.getRecyclerView();
            layoutManager = dailyFeedView.getLayoutManager();
            dailyApi.getDailyFeedDetail(id,num)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dailyTimeLine -> {
                        disPlayDailyTimeLine(context,dailyTimeLine,mRecyclerView,dailyFeedView);
                    },this::loadError);
        }
    }

    private void disPlayDailyTimeLine(Context context, DailyTimeLine dailyTimeLine, RecyclerView mRecyclerView, DailyFeedView dailyFeedView) {
        if(dailyTimeLine.getResponse().getLast_key()!=null){
            next_pager = dailyTimeLine.getResponse().getLast_key();
            has_more = dailyTimeLine.getResponse().getHas_more();
        }
        if (isLoadMore) {
            if (dailyTimeLine.getResponse().getOptions() == null) {
                dailyFeedView.setDataRefresh(false);
                return;
            }
            else {
                timeLine.getResponse().getOptions().addAll(dailyTimeLine.getResponse().getOptions());
            }
            adapter.notifyDataSetChanged();
        } else {
            timeLine = dailyTimeLine;
            adapter = new DailyFeedAdapter(context, timeLine.getResponse().getOptions());
            mRecyclerView.setAdapter(adapter);
        }
        dailyFeedView.setDataRefresh(false);

    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        dailyFeedView.setDataRefresh(false);
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
    }

    /**
     * recyclerView Scroll listener , maybe in here is wrong ?
     * 这里是否有错误？
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
                        return;
                    }
                    if (lastVisibleItem + 1 == layoutManager
                            .getItemCount()) {
                        if(has_more.equals("true")) {
                            isLoadMore = true;
                            dailyFeedView.setDataRefresh(true);
                            new Handler().postDelayed(() -> getDailyFeedDetail(d_id,next_pager), 1000);
                        }
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
