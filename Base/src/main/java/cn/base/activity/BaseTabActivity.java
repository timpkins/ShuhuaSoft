package cn.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import cn.base.R;
import cn.base.fragment.BaseTabFragment;
import cn.base.view.BottomNavigationBar;
import cn.base.view.BottomNavigationBar.BackgroundStyle;
import cn.base.view.BottomNavigationBar.Mode;
import cn.base.view.BottomNavigationBar.OnTabSelectedListener;
import cn.base.view.BottomNavigationItem;
import cn.base.view.ShapeBadgeItem;
import cn.base.view.TextBadgeItem;

/**
 * 带底部导航栏的Activity
 * @author timpkins
 */
public abstract class BaseTabActivity extends AppCompatActivity implements OnTabSelectedListener {
    private static final String TAG = BaseTabActivity.class.getSimpleName();
    private BottomNavigationBar bottomNavigationBar;
    @Mode private int bottomMode = BottomNavigationBar.MODE_FIXED;
    @BackgroundStyle private int bottomBackgroundStyle = BottomNavigationBar.BACKGROUND_STYLE_STATIC;
    private boolean hideBottom = false;
    private boolean hideBadge = true;
    private List<BottomNavigationItem> items;
    private int lastSelectedPosition = 0;
    private boolean isItemZoom = false;
    private List<BaseTabFragment> tabFragments;
    @Nullable TextBadgeItem numberBadgeItem;
    private FragmentTransaction transaction;
    private Fragment mFragment;//当前显示的Fragment

    @Nullable
    ShapeBadgeItem shapeBadgeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_tab);
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this);
    }

    //========配置BottomNavigationBar=========
    public void setBottomMode(@Mode int bottomMode) {
        this.bottomMode = bottomMode;
    }

    public void setBottomBackgroundStyle(@BackgroundStyle int bottomBackgroundStyle) {
        this.bottomBackgroundStyle = bottomBackgroundStyle;
    }

    public void setHideBottom(boolean hideBottom) {
        this.hideBottom = hideBottom;
    }

    public void setHideBadge(boolean hideBadge) {
        this.hideBadge = hideBadge;
    }

    public void setItems(List<BottomNavigationItem> items, List<BaseTabFragment> tabFragments) {
        if (items.size() != tabFragments.size()) {
            throw new RuntimeException("item's size is no match Fragment's size");
        }
        this.items = items;
        this.tabFragments = tabFragments;
    }

    public void setItemZoom(boolean itemZoom) {
        isItemZoom = itemZoom;
    }

    public void setBadgeHide(boolean isHide) {
        if (numberBadgeItem != null) {
            if (isHide) {
                numberBadgeItem.hide();
            } else {
                numberBadgeItem.show();
            }
        }
        if (shapeBadgeItem != null) {
            if (isHide) {
                shapeBadgeItem.show();
            } else {
                shapeBadgeItem.hide();
            }
        }
    }
    //========配置BottomNavigationBar=========

    public void refresh() {
//        bottomNavigationBar.clearAll();
        bottomNavigationBar.setMode(bottomMode);
        bottomNavigationBar.setBackgroundStyle(bottomBackgroundStyle);
        bottomNavigationBar.toggle(hideBottom);
        setBadgeHide(hideBadge);
        if (items != null && !items.isEmpty()) {
            for (BottomNavigationItem item : items) {
                bottomNavigationBar.addItem(item);
            }
            bottomNavigationBar.setFirstSelectedPosition(lastSelectedPosition > items.size() ? items.size() : lastSelectedPosition);
            bottomNavigationBar.setItemZoom(isItemZoom);
            bottomNavigationBar.initialise();
        }
        bottomNavigationBar.show();
        initFragment();
        switchFragment(0);
    }

    private void initFragment() {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.home_activity_frag_container, tabFragments.get(0)).commit();
        mFragment = tabFragments.get(0);
    }

    protected void switchFragment(int postion) {
        Fragment fragment = tabFragments.get(postion);
        if ( mFragment == null || mFragment != fragment) {
            if (!fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.home_activity_frag_container, fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }

    public int getLastSelectedPosition() {
        return lastSelectedPosition;
    }

    public void setLastSelectedPosition(int lastSelectedPosition) {
        this.lastSelectedPosition = lastSelectedPosition;
    }
}
