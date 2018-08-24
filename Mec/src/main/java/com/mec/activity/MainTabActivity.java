package com.mec.activity;

import android.os.Bundle;

import com.mec.fragment.MineTabFragment;
import com.mec.fragment.WebTabFragment;
import com.shuhuasoft.R;

import java.util.ArrayList;
import java.util.List;

import cn.base.activity.BaseTabActivity;
import cn.base.fragment.BaseTabFragment;
import cn.base.view.BottomNavigationItem;

/**
 * @author timpkins
 */
public class MainTabActivity extends BaseTabActivity {
    private static final String TAG = MainTabActivity.class.getSimpleName();
    private List<BaseTabFragment> tabFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<BottomNavigationItem> items = new ArrayList<>();
        items.add(new BottomNavigationItem(R.mipmap.tab_home_active, "首页").setInactiveIconResource(R.mipmap.tab_home_inactive));
        items.add(new BottomNavigationItem(R.mipmap.tab_category_active, "分类").setInactiveIconResource(R.mipmap.tab_category_inactive));
        items.add(new BottomNavigationItem(R.mipmap.tab_follow_active, "关注").setInactiveIconResource(R.mipmap.tab_follow_inactive));
        items.add(new BottomNavigationItem(R.mipmap.tab_found_active, "发现").setInactiveIconResource(R.mipmap.tab_found_inactive));
        items.add(new BottomNavigationItem(R.mipmap.tab_mine_active, "我的").setInactiveIconResource(R.mipmap.tab_mine_inactive));
        tabFragments = new ArrayList<>();
        tabFragments.add(WebTabFragment.newInstance("http://mec.shuhuasoft.com/"));
        tabFragments.add(WebTabFragment.newInstance("http://www.mi.com"));
        tabFragments.add(WebTabFragment.newInstance("http://www.jd.com"));
        tabFragments.add(WebTabFragment.newInstance("http://www.taobao.com"));
        tabFragments.add(MineTabFragment.newInstance("我的"));
        setItems(items, tabFragments);
        refresh();
    }

    @Override
    public void onTabSelected(int position) {
//        LogUtils.e(TAG, "onTabSelected :: tab" + position);
        switchFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {
//        LogUtils.e(TAG, "onTabUnselected :: tab" + position);
    }

    @Override
    public void onTabReselected(int position) {
//        LogUtils.e(TAG, "onTabReselected :: tab" + position);
//        setItemZoom(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
