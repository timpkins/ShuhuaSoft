package com.mec.activity;

import android.Manifest.permission;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mec.Constants.NetUrl;
import com.mec.NetHttpCallback;
import com.mec.bean.Menu;
import com.mec.bean.Menu.Tab;
import com.mec.fragment.MineTabFragment;
import com.mec.fragment.WebTabFragment;
import com.shuhuasoft.R;

import java.util.ArrayList;
import java.util.List;

import cn.base.activity.BaseTabActivity;
import cn.base.fragment.BaseTabFragment;
import cn.base.permission.PermissionHelper;
import cn.base.permission.PermissionInterface;
import cn.base.util.LogUtils;
import cn.base.view.BottomNavigationItem;
import cn.bridge.NetRequester;

/**
 * @author timpkins
 */
public class MainTabActivity extends BaseTabActivity implements PermissionInterface {
    private static final String TAG = MainTabActivity.class.getSimpleName();
    private List<BaseTabFragment> tabFragments;
    private PermissionHelper mPermissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPermissionHelper = new PermissionHelper(this, this);
        mPermissionHelper.requestPermissions();

//        initView();
        initNetRequest();
    }

    private void initView() {
        List<BottomNavigationItem> items = new ArrayList<>();
        Drawable mine = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.mipmap.tab_mine_active));
        items.add(new BottomNavigationItem(R.mipmap.tab_home_active, "首页").setInactiveIconResource(R.mipmap.tab_home_inactive));
        items.add(new BottomNavigationItem(R.mipmap.tab_category_active, "分类").setInactiveIconResource(R.mipmap.tab_category_inactive));
        items.add(new BottomNavigationItem(R.mipmap.tab_follow_active, "关注").setInactiveIconResource(R.mipmap.tab_follow_inactive));
        items.add(new BottomNavigationItem(R.mipmap.tab_found_active, "发现").setInactiveIconResource(R.mipmap.tab_found_inactive));
        items.add(new BottomNavigationItem(mine, "我的").setInactiveIconResource(R.mipmap.tab_mine_inactive));
        tabFragments = new ArrayList<>();
        tabFragments.add(WebTabFragment.newInstance("http://mec.shuhuasoft.com/"));
        tabFragments.add(WebTabFragment.newInstance("http://www.mi.com"));
        tabFragments.add(WebTabFragment.newInstance("http://www.jd.com"));
        tabFragments.add(WebTabFragment.newInstance("http://www.taobao.com"));
        tabFragments.add(MineTabFragment.newInstance("我的"));
        setItems(items, tabFragments);
        refresh();
    }

    private void initNetRequest() {
        new NetRequester(this).get(NetUrl.MENU, new NetHttpCallback() {
            @Override
            public void onSuccess(String result) {
                Menu menu = new Gson().fromJson(result, Menu.class);
                new Thread(() -> {
                    tabFragments = new ArrayList<>();
                    List<BottomNavigationItem> items = new ArrayList<>();
                    if (menu != null && menu.getMenu() != null && !menu.getMenu().isEmpty()) {
                        for (Tab tab : menu.getMenu()) {
                            try {
                                String activeUrl = tab.getMenuicon().split(",")[0];
                                String inActiveUrl = tab.getMenuicon().split(",")[1];
                                Drawable active = Glide.with(MainTabActivity.this).asDrawable().load(activeUrl).submit().get();
                                Drawable inActive = new BitmapDrawable(Glide.with(MainTabActivity.this).asBitmap().load(inActiveUrl).submit().get());
                                items.add(new BottomNavigationItem(inActive, tab.getMenuname()).setInactiveIcon(active).setInActiveColor
                                        (R.color.tabColor).setInActiveColor(R.color.colorPrimary));
                                tabFragments.add(WebTabFragment.newInstance(tab.getMenuurl()));
//                                LogUtils.e(TAG, "添加TAB = " + tab.getMenuname());
//                                LogUtils.e(TAG, "drawable = " + active + "    " + inActive);
                            } catch (Exception e) {
                                LogUtils.e("TAG", "", e);
                            }
                        }
                    }
                    items.add(new BottomNavigationItem(R.mipmap.tab_mine_active, "我的").setInactiveIconResource(R.mipmap.tab_mine_inactive).setInActiveColor
                            (R.color.tabColor).setInActiveColor(R.color.colorPrimary));
                    tabFragments.add(MineTabFragment.newInstance("我的"));
                    LogUtils.e(TAG, "item = " + items.size());
                    MainTabActivity.this.runOnUiThread(() -> {
                        LogUtils.e(TAG, "添加TAB = " + tabFragments.size() + "    当前线程 = " + Thread.currentThread().getName());
                        for (int i = 0; i < items.size();  i++){
                            LogUtils.e(TAG, items.get(i).toString()+"\n");
                        }

                        setItems(items, tabFragments);
                        refresh();
                    });
                }).start();
            }
        });
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

    @Override
    public int getPermissionsRequestCode() {
        return 0x000A;
    }

    @Override
    public String[] getPermissions() {
        return new String[]{
                permission.READ_EXTERNAL_STORAGE
        };
    }

    @Override
    public void requestPermissionsSuccess() {
//        initView();
    }

    @Override
    public void requestPermissionsFail() {
        LogUtils.e(TAG, "===授权失败===");
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)) {
            //权限请求结果，并已经处理了该回调
            for (String permission : permissions) {
                LogUtils.e(TAG, "permission = " + permission + grantResults[0]);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tabFragments.get(tabFragments.size() - 1).onActivityResult(requestCode, resultCode, data); // 将数据转发到Fragment中
    }
}
