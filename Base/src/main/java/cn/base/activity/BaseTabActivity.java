package cn.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private BottomNavigationBar bottomNavigationBar;
    @Mode private int bottomMode = BottomNavigationBar.MODE_FIXED;
    @BackgroundStyle private int bottomBackgroundStyle = BottomNavigationBar.BACKGROUND_STYLE_STATIC;
    private boolean hideBottom = false;
    private boolean hideBadge = true;
    private List<BottomNavigationItem> items;
    private int lastSelectedPosition = 0;
    private boolean isItemZoom = false;
    BaseTabFragment fragment1;
    BaseTabFragment fragment2;
    BaseTabFragment fragment3;
    BaseTabFragment fragment4;
    BaseTabFragment fragment5;
    BaseTabFragment fragment6;

    @Nullable TextBadgeItem numberBadgeItem;

    @Nullable
    ShapeBadgeItem shapeBadgeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        fragment1 = BaseTabFragment.newInstance(getString(R.string.para1));
        fragment2 = BaseTabFragment.newInstance(getString(R.string.para2));
        fragment3 = BaseTabFragment.newInstance(getString(R.string.para3));
        fragment4 = BaseTabFragment.newInstance(getString(R.string.para4));
        fragment5 = BaseTabFragment.newInstance(getString(R.string.para5));
        fragment6 = BaseTabFragment.newInstance(getString(R.string.para6));
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

    public void setItems(List<BottomNavigationItem> items) {
        this.items = items;
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
        bottomNavigationBar.clearAll();
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
    }

    public int getLastSelectedPosition() {
        return lastSelectedPosition;
    }

    public void setLastSelectedPosition(int lastSelectedPosition) {
        this.lastSelectedPosition = lastSelectedPosition;
    }
}
