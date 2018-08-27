package com.mec.activity;

import com.shuhuasoft.R;

/**
 * 安全管理
 * @author timpkins
 */
public class MineSafeActivity extends MecTitleActivity {
    @Override
    protected int setContentLayout() {
        return R.layout.activity_mine_safe;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initTitle() {
        super.initTitle();
        setTitleContent(R.string.safe_title);
    }
}
