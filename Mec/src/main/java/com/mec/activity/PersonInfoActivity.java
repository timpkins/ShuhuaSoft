package com.mec.activity;

import com.shuhuasoft.R;

/**
 * @author timpkins
 */
public class PersonInfoActivity extends MecTitleActivity {
    @Override
    protected int setContentLayout() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initTitle() {
        super.initTitle();
        setTitleContent(R.string.person_title);
    }
}
