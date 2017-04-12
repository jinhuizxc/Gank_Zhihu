package com.example.jh.gank_zhihu.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.jh.gank_zhihu.ui.base.MVPBaseFragment;

import java.util.List;

/**
 * Created by jinhui  on 2017/4/12
 * 邮箱: 1004260403@qq.com
 */

public class ViewPagerFgAdapter extends FragmentPagerAdapter {

    private String tag;
    private List<MVPBaseFragment> fragmentList;

    // 构造方法
    public ViewPagerFgAdapter(FragmentManager fm, List<MVPBaseFragment> fragmentList, String tag) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tag = tag;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (fragmentList != null) {
            return fragmentList.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(tag.equals("main_view_pager")){
            switch (position) {
                case 0:
                    return "知乎";
                case 1:
                    return "干货";
                case 2:
                    return "满足你的好奇心";
            }
        }
        return null;
    }

    // 这个方法并没有在本demo中用到，只要知道有这个方法就可以啦
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}
