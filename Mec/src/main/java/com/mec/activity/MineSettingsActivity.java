package com.mec.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.shuhuasoft.R;

/**
 * 设置
 * @author timpkins
 */
public class MineSettingsActivity extends MecTitleActivity implements OnClickListener{

    @Override
    protected int setContentLayout() {
        return R.layout.activity_mine_settings;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        setTitleContent(R.string.settings_title);
    }

    @Override
    protected void initViews() {
        TextView tvCache = findView(R.id.tvCache);
        TextView tvFeedback = findView(R.id.tvFeedback);
        TextView tvAbout = findView(R.id.tvAbout);
        TextView tvLogout = findView(R.id.tvLogout);

        tvCache.setOnClickListener(this);
        tvFeedback.setOnClickListener(this);
        tvAbout.setOnClickListener(this);
        tvLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this,((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.tvCache:
                break;
            case R.id.tvFeedback:
                break;
            case R.id.tvAbout:
                break;
            case R.id.tvLogout:
                break;
        }
    }
}
