package com.example.jh.gank_zhihu.ui.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jinhui  on 2017/4/13
 * 邮箱: 1004260403@qq.com
 * <p>
 * 关于Boolean与boolean区别：
 * boolean是基本数据类型
 * Boolean是它的封装类，和其他类一样，有属性有方法，可以new，例如：
 * Boolean flag = new Boolean("true");  &#47;&#47; boolean 则不可以！
 * Boolean 是boolean 的实例化对象类，和Integer对应int一样
 * 自jdk1.5.0以上版本后，Boolean在"赋值"和判断上和boolean一样，
 * 即是你： boolean b1 = true ; 或者 Boolean b2 = true ; 都可以。
 * 唯一只能使用Boolean上的就是从列表或者哈希表获取值时。
 * 比如 boolean t = false;
 * Map map = new HashMap();
 * map.put("t", t);
 * 那么获取值时只能用
 * Boolean t1 = (Boolean) map.get(t); &#47;&#47;前面只能用Boolean强制转换，不能使用boolean.
 */

public interface ZhihuView {
    RecyclerView getRecyclerView();

    LinearLayoutManager getLayoutManager();

    //   void setDataRefresh(boolean b);
    void setDataRefresh(Boolean refresh);
}
