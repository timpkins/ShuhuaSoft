package com.mec.activity;

import android.os.Bundle;

import com.shuhuasoft.R;

import java.util.ArrayList;
import java.util.List;

import cn.base.activity.BaseTabActivity;
import cn.base.util.LogUtils;
import cn.base.view.BottomNavigationItem;

/**
 * @author timpkins
 */
public class MainTabActivity extends BaseTabActivity {
    private static final String TAG = MainTabActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<BottomNavigationItem> items = new ArrayList<>();
        items.add(new BottomNavigationItem(R.mipmap.ic_home_white_36dp, "首页").setActiveColorResource(R.color.bottomAction)
                .setInActiveColorResource(R.color.bottomInAction));
        items.add(new BottomNavigationItem(R.mipmap.ic_module_white_36dp, "分类").setActiveColorResource(R.color.bottomAction)
                .setInActiveColorResource(R.color.bottomInAction));
        items.add(new BottomNavigationItem(R.mipmap.ic_explore_white_36dp, "发现").setActiveColorResource(R.color.bottomAction)
                .setInActiveColorResource(R.color.bottomInAction));
        items.add(new BottomNavigationItem(R.mipmap.ic_person_white_36dp, "我的").setActiveColorResource(R.color.bottomAction)
                .setInActiveColorResource(R.color.bottomInAction));
        setItems(items);
        refresh();
    }

    @Override
    public void onTabSelected(int position) {
        LogUtils.e(TAG, "onTabSelected :: tab" + position);
    }

    @Override
    public void onTabUnselected(int position) {
        LogUtils.e(TAG, "onTabUnselected :: tab" + position);
    }

    @Override
    public void onTabReselected(int position) {
        LogUtils.e(TAG, "onTabReselected :: tab" + position);
        setItemZoom(true);
    }
}
