package com.mec.activity;

import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.shuhuasoft.R;

import cn.base.activity.BaseTitleActivity;

/**
 * @author timpkins
 */
public abstract class MecTitleActivity extends BaseTitleActivity {
    protected TextView tvLeft, tvRight;


    @Override
    protected void initTitle() {
        tvLeft = findView(R.id.tvTitleLeft);
        tvRight = findView(R.id.tvTitleRight);

    }

    protected void setTitleContent(@StringRes int leftRes){
        tvLeft.setText(leftRes);
        tvLeft.setOnClickListener(view -> finish());
        tvRight.setVisibility(View.GONE);
    }

    protected void setTitleContent(@StringRes int leftRes, @StringRes int rightRes){
        tvLeft.setText(leftRes);
        tvLeft.setOnClickListener(view -> finish());
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(rightRes);
    }


}
