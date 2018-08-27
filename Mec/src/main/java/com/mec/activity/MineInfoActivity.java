package com.mec.activity;

import com.shuhuasoft.R;

/**
 * @author timpkins
 */
public class MineInfoActivity extends MecTitleActivity {
    @Override
    protected int setContentLayout() {
        return R.layout.activity_mine_info;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        setTitleContent(R.string.info_title);
    }

    @Override
    protected void initViews() {
        // TODO: 2018-08-27 选择头像
    }

}
