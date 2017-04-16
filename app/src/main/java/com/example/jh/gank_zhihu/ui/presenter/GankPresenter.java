package com.example.jh.gank_zhihu.ui.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.bean.gank.Gank;
import com.example.jh.gank_zhihu.ui.activity.GankActivity;
import com.example.jh.gank_zhihu.ui.adapter.GankActivityAdapter;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.view.GankView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jinhui  on 2017/4/14
 * 邮箱: 1004260403@qq.com
 */

public class GankPresenter extends BasePresenter<GankView> {

    private Context context;
    private GankView gankView;
    private RecyclerView recyclerView;
    GankActivityAdapter adapter;

    public GankPresenter(Context context) {
//        super();
        this.context = context;
    }

    public void getGankList(int year, int month, int day) {
        gankView = getView();
        if (gankView != null) {
            recyclerView = gankView.getRecyclerView();

            gankApi.getGankData(year, month, day)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(gankData -> {
                        displayGankList(context, gankData.results.getAllResults(), gankView, recyclerView);
                    }, this::loadError);
        }
    }

    private void displayGankList(Context context, List<Gank> gankList, GankView gankView, RecyclerView recyclerView) {
        adapter = new GankActivityAdapter(context, gankList);
        recyclerView.setAdapter(adapter);
        gankView.setDataRefresh(false);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.gank_load_error, Toast.LENGTH_SHORT).show();
    }
}
