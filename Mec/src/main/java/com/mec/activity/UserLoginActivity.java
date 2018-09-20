package com.mec.activity;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mec.Constants.NetUrl;
import com.mec.Constants.Preference;
import com.mec.NetHttpCallback;
import com.mec.bean.Login;
import com.mec.view.InputEditText;
import com.shuhuasoft.R;

import cn.base.util.LogUtils;
import cn.base.util.ToastUtils;
import cn.bridge.NetParams;
import cn.bridge.NetRequester;
import cn.bridge.RequestOption;

/**
 * @author timpkins
 */
public class UserLoginActivity extends MecTitleActivity implements OnClickListener {
    private static final String TAG = UserLoginActivity.class.getSimpleName();
    private InputEditText ietAccount, ietPasswd;

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
        TextView tvLogin = findView(R.id.tvLogin);
        ietAccount = findView(R.id.ietAccount);
        ietPasswd = findView(R.id.ietPasswd);

        SpannableString ssRegist = new SpannableString(getResources().getString(R.string.toRegist));
        ssRegist.setSpan(new ForegroundColorSpan(Color.parseColor("#44B4FF")), 6, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegist.setText(ssRegist);
        tvRegist.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        getWindow().setStatusBarColor(getResources().getColor(R.color.other_statusbar));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTitleLeft:
                finish();
                break;
            case R.id.tvRegist:
                startActivity(UserRegistActivity.class);
                break;
            case R.id.tvLogin:
                login();
                break;
        }
    }

    private void login() {
        String passwd = ietPasswd.getText().toString();
        String account = ietAccount.getText().toString();
        if (TextUtils.isEmpty(account)) {
            ToastUtils.toastShort("帐号不能为空");
        } else if (TextUtils.isEmpty(passwd)) {
            ToastUtils.toastShort("密码不能为空");
        } else {
            // j_username=admin&j_password=admin&submit=Login
            NetParams params = new NetParams();
            params.put("j_username", account);
            params.put("j_password", passwd);
            params.put("submit", "Login");
            RequestOption option = new RequestOption();
            option.setMediaType(RequestOption.MEDIA_FORM);
            new NetRequester(this, option).post(NetUrl.LOGIN, params, new NetHttpCallback() {
                @Override
                public void onSuccess(String result) {
                    // {"code":"200","msg":"ktest123 登入成功","token":"104F5F5654EF8AD1CDAAC7602287178C"}
                    LogUtils.e(TAG, "请求成功 " + result);
                    Login login = new Gson().fromJson(result, Login.class);
                    if (login != null && !TextUtils.isEmpty(login.getToken())) {
                        application().getPreferencesHelper().putData(Preference.TOKE, login.getToken());
                        ToastUtils.toastShort("登录成功");
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            });
        }
    }
}
