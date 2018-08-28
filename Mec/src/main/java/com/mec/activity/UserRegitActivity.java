package com.mec.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

import com.mec.view.InputEditText;
import com.shuhuasoft.R;

/**
 * @author timpkins
 */
public class UserRegitActivity extends MecTitleActivity implements OnClickListener, OnFocusChangeListener {

    @Override
    protected int setContentLayout() {
        return R.layout.activity_user_regist;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        setTitleContent(R.string.regist_title);
    }

    @Override
    protected void initViews() {
        TextView tvRegist = findView(R.id.tvRegist);
        InputEditText ietAccount = findView(R.id.ietAccount);
        ietAccount.setOnFocusChangeListener(this);

        tvRegist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTitleLeft:
                finish();
                break;
            case R.id.tvRegist:
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus){
            ((InputEditText)v).inputError();
        }
    }
}
