package com.example.jh.gank_zhihu.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.bean.gank.Gank;
import com.example.jh.gank_zhihu.bean.gank.Meizhi;
import com.example.jh.gank_zhihu.bean.gank.Video;
import com.example.jh.gank_zhihu.ui.adapter.GankListAdapter;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.view.GankFgView;
import com.example.jh.gank_zhihu.utils.DateUtils;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jinhui  on 2017/4/12
 * 邮箱: 1004260403@qq.com
 */

public class GankFgPresenter extends BasePresenter<GankFgView>{

    private static final String TAG = "GankFgPresenter";
    private Context context;
    private GankFgView gankFgView;
    private RecyclerView mRecyclerView;
    private GridLayoutManager layoutManager;
    // data + 适配器
    private List<Gank> list;
    private GankListAdapter adapter;
    // 当前页
    private int page = 1;

    private int lastVisibleItem;
    private boolean isLoadMore = false; // 是否加载过更多


    public GankFgPresenter(Context context) {
//        super();
        this.context = context;
    }

    public void getGankData() {
        gankFgView = getView();
        if(gankFgView != null){
            mRecyclerView = gankFgView.getRecyclerView();
            layoutManager = gankFgView.getLayoutManager();
            // 这里会有是否加载更多的判断
            if(isLoadMore){
                Log.e(TAG, "isLoadMore =" + isLoadMore);
                page = page + 1;
            }
            Observable.zip(gankApi.getMeizhiData(page),
                    gankApi.getVideoData(page),this::createDesc)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(meizhi ->{
                        /**
                         * 这个方法需要传的参数：
                         *
                         * 1.context 用于适配器的上下文对象
                         * 2.meizhi.getResults(),数据
                         * 3.gankView，执行不刷新方法。
                         * 4.mRecyclerView 用于绑定设配器
                         */
                        displayMeizi(context, meizhi.getResults(), gankFgView, mRecyclerView);
                    },this::loadError);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        gankFgView.setDataRefresh(false);
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
    }

    public void scrollRecycleView() {
         // 列表滑动监听
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    Log.e(TAG, "lastVisibleItem =" + lastVisibleItem);
                    if(layoutManager.getItemCount() == 1){
                        return;
                    }
                    // getItemCount
                    if (lastVisibleItem + 1 == layoutManager.getItemCount()) {
                        Log.e(TAG, "layoutManager.getItemCount() =" + layoutManager.getItemCount());
                        gankFgView.setDataRefresh(true);
                        isLoadMore = true;
                        new Handler().postDelayed(() -> getGankData(), 1000);
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
    // 用于gridView列表资源的显示
    private void displayMeizi(Context context, List<Gank> meiZhiList, GankFgView gankFgView, RecyclerView mRecyclerView) {
        // 待添加加载更多
        if(isLoadMore){
            if(meiZhiList == null){
                gankFgView.setDataRefresh(false);
                return;
            }else{
                list.addAll(meiZhiList);
                adapter.notifyDataSetChanged();
            }
        } else{
            list = meiZhiList;
//        list.addAll(meiZhiList);
            adapter = new GankListAdapter(context, list);
            mRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        // 设置停止刷新的方法
        gankFgView.setDataRefresh(false);
    }



    /**
     * MeiZhi = list , gankmeizhi = 福利
     *
     * @param meizhi list
     * @param video  list
     * @return
     */
    private Meizhi createDesc(Meizhi meizhi, Video video) {
        for (Gank gankmeizzhi:meizhi.getResults()) {
            gankmeizzhi.desc = gankmeizzhi.desc +" " +
                    getVideoDesc(gankmeizzhi.getPublishedAt(), video.getResults());
        }
        return meizhi;
    }

    //匹配同一天的福利描述和视频描述
    private String getVideoDesc(Date publishedAt, List<Gank> results) {
        String videoDesc = "";
        for (int i = 0; i < results.size() ; i++) {
            Gank video = results.get(i);
            if(video.getPublishedAt() == null)
                video.setPublishedAt(video.getCreatedAt());
            if(DateUtils.isSameDate(publishedAt, video.getPublishedAt())){
                videoDesc = video.getDesc();
                break;
            }
        }
        return videoDesc;
    }
}
