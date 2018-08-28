package com.mec.activity;

import android.support.annotation.StringRes;
import android.widget.TextView;

import com.shuhuasoft.R;

import cn.base.activity.BaseTitleActivity;

/**
 * @author timpkins
 */
public abstract class MecTitleActivity extends BaseTitleActivity {
    protected TextView tvLeft, tvName;


    @Override
    protected void initTitle() {
        tvLeft = findView(R.id.tvTitleLeft);
        tvName = findView(R.id.tvTitleName);

    }

    protected void setTitleContent(@StringRes int leftRes){
        tvLeft.setOnClickListener(view -> finish());
        tvName.setText(leftRes);
    }
}
