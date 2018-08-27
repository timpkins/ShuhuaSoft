package com.mec.activity;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.shuhuasoft.R;

import cn.base.activity.BaseActivity;

/**
 * @author timpkins
 */
public class UserLoginActivity extends BaseActivity implements OnClickListener {

    @Override
    protected int setContentLayout() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initViews() {
        TextView tvRegist = findView(R.id.tvRegist);
        TextView tvTitleLeft = findView(R.id.tvTitleLeft);
        TextView tvTitleRight = findView(R.id.tvTitleRight);
        SpannableString ssRegist = new SpannableString(getResources().getString(R.string.toRegist));
        ssRegist.setSpan(new ForegroundColorSpan(Color.parseColor("#44B4FF")), 6, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegist.setText(ssRegist);
        tvRegist.setOnClickListener(this);
        tvTitleLeft.setOnClickListener(this);
        tvTitleRight.setText("登陆");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTitleLeft:
                finish();
                break;
            case R.id.tvRegist:
                startActivity(UserRegitActivity.class);
                break;
        }
    }
}
