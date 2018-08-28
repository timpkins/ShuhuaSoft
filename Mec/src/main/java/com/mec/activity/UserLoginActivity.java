package com.mec.activity;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.shuhuasoft.R;

/**
 * @author timpkins
 */
public class UserLoginActivity extends MecTitleActivity implements OnClickListener {

    @Override
    protected int setContentLayout() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        setTitleContent(R.string.login_title);
    }

    @Override
    protected void initViews() {
        TextView tvRegist = findView(R.id.tvRegist);

        SpannableString ssRegist = new SpannableString(getResources().getString(R.string.toRegist));
        ssRegist.setSpan(new ForegroundColorSpan(Color.parseColor("#44B4FF")), 6, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegist.setText(ssRegist);
        tvRegist.setOnClickListener(this);
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
