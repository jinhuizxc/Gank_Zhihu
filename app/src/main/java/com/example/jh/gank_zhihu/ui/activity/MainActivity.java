package com.example.jh.gank_zhihu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jh.gank_zhihu.R;
import com.example.jh.gank_zhihu.ui.adapter.ViewPagerFgAdapter;
import com.example.jh.gank_zhihu.ui.base.BasePresenter;
import com.example.jh.gank_zhihu.ui.base.MVPBaseActivity;
import com.example.jh.gank_zhihu.ui.base.MVPBaseFragment;
import com.example.jh.gank_zhihu.ui.fragment.DailyFragment;
import com.example.jh.gank_zhihu.ui.fragment.GankFragment;
import com.example.jh.gank_zhihu.ui.fragment.ZhihuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 * Created by jinhui  on 2017/4/11
 * 邮箱: 1004260403@qq.com
 *
 * 使用jdk1.8 ,mvp、retrofit2、rxJava
 * 忙里偷闲开始着手写一个新闻类项目，也是一个学习的过程。
 * 2017-4-16 今天完成预期目标，
 * 但是没有加入Google Material Desgin,准备进行下一步设计！
 */
public class MainActivity extends MVPBaseActivity {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.content_viewPager)
    ViewPager contentViewPager;

    private List<MVPBaseFragment> fragmentList;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabView();
    }

    //初始化Tab滑动
    private void initTabView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new ZhihuFragment());
        fragmentList.add(new GankFragment());
        fragmentList.add(new DailyFragment());
        contentViewPager.setOffscreenPageLimit(3);//设置至少3个fragment，防止重复创建和销毁，造成内存溢出
        contentViewPager.setAdapter(new ViewPagerFgAdapter(getSupportFragmentManager(),fragmentList,"main_view_pager"));//给ViewPager设置适配器
        tabLayout.setupWithViewPager(contentViewPager);//将TabLayout和ViewPager关联起来
    }

    // 这个方法解析menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.today_github){
            String github_trending = "https://github.com/trending";
            // 界面跳转
            startActivity(GankWebActivity.newIntent(this, github_trending));
            return true;
        }else if(item.getItemId() == R.id.about_me){
            startActivity(new Intent(this,AboutMeActivity.class));
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}
