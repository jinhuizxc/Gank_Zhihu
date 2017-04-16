package com.example.jh.gank_zhihu.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.bean.gank.Gank;
import com.example.jh.gank_zhihu.ui.activity.GankActivity;
import com.example.jh.gank_zhihu.ui.activity.PictureActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jinhui  on 2017/4/14
 * 邮箱: 1004260403@qq.com
 */

public class GankListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Gank> list;

    public GankListAdapter(Context context, List<Gank> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_gank_meizi, parent, false);
        return new GankMeizhiViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GankMeizhiViewHolder) {
            GankMeizhiViewHolder gankMeizhiViewHolder = (GankMeizhiViewHolder) holder;
            // 适配布局控件对象
            gankMeizhiViewHolder.bindItem(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GankMeizhiViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_meizhi)
        CardView card_meizhi;
        @Bind(R.id.iv_meizhi)
        ImageView iv_meizhi;
        @Bind(R.id.tv_meizhi_title)
        TextView tv_meizhi_title;

        public GankMeizhiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(Gank meizhi) {
            tv_meizhi_title.setText(meizhi.getDesc());
            Glide.with(context).load(meizhi.getUrl()).centerCrop().into(iv_meizhi);
            // 图片点击事件
            iv_meizhi.setOnClickListener(v -> {
                Intent intent = PictureActivity.newIntent(context, meizhi.getUrl(), meizhi.getDesc());
                // 这里是要做什么呢？转场动画
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity) context, iv_meizhi, PictureActivity.TRANSIT_PIC);
                // Android 5.0+
//                try {
//                    ActivityCompat.startActivity((Activity) context,intent,optionsCompat.toBundle());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    context.startActivity(intent);
//                }
                ActivityCompat.startActivity(context, intent, optionsCompat.toBundle());
                context.startActivity(intent);
            });
            // cardview点击事件
            card_meizhi.setOnClickListener(v -> {
                Intent intent = GankActivity.newIntent(context, meizhi.getPublishedAt().getTime());
                context.startActivity(intent);
            });
        }
    }
}
