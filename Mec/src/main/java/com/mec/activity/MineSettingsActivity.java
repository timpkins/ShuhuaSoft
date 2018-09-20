package com.mec.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.mec.Constants.NetUrl;
import com.mec.Constants.Preference;
import com.mec.NetHttpCallback;
import com.shuhuasoft.R;

import cn.base.util.LogUtils;
import cn.bridge.NetParams;
import cn.bridge.NetRequester;
import cn.bridge.RequestOption;

/**
 * 设置
 * @author timpkins
 */
public class MineSettingsActivity extends MecTitleActivity implements OnClickListener {

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
        switch (v.getId()) {
            case R.id.tvCache:
                Toast.makeText(this, ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvFeedback:
                Toast.makeText(this, ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvAbout:
                Toast.makeText(this, ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvLogout:
                logout();
                break;
        }
    }

    private void logout() {
        NetParams params = new NetParams();
        params.put("token", application().getPreferencesHelper().getData(Preference.TOKE, ""));
        RequestOption option = new RequestOption();
        option.setMediaType(RequestOption.MEDIA_FORM);
        new NetRequester(this, option).post(NetUrl.LOGOUT, params, new NetHttpCallback() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e("TAG", result);
                application().getPreferencesHelper().remove(Preference.TOKE); // 清除Token
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
