package com.mec.activity;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.mec.Constants.NetUrl;
import com.mec.NetHttpCallback;
import com.mec.util.ValidateUtils;
import com.mec.view.InputEditText;
import com.shuhuasoft.R;

import cn.base.util.ToastUtils;
import cn.bridge.NetParams;
import cn.bridge.NetRequester;
import cn.bridge.RequestOption;

/**
 * @author timpkins
 */
public class UserRegistActivity extends MecTitleActivity implements OnClickListener {
    private InputEditText ietAccount, ietPasswd, ietPasswdConfirm, ietEmail, ietName, ietCode, ietPhone;
    private TextView tvCode;
    private static final int MAX_TIME = 60;
    private int lastSecond = MAX_TIME;
    private Handler handler;

    private Runnable run = new Runnable() {
        @Override
        public void run() {
            tvCode.setText(getString(R.string.user_obtainCode_hint, lastSecond));
            if (lastSecond == 0) {
                tvCode.setEnabled(true);
                tvCode.setText(R.string.user_obtainCode);
                handler.removeCallbacks(run);
            } else {
                lastSecond--;
                handler.postDelayed(run, 1000L);
            }
        }
    };

    @Override
    protected int setContentLayout() {
        return R.layout.activity_user_regist;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        setTitleContent(R.string.regist_title);
        getWindow().setStatusBarColor(getResources().getColor(R.color.other_statusbar));
    }

    @Override
    protected void initViews() {
        TextView tvRegist = findView(R.id.tvRegist);
        TextView tvSubmit = findView(R.id.tvSubmit);
        ietAccount = findView(R.id.ietAccount);
        ietPasswd = findView(R.id.ietPasswd);
        ietPasswdConfirm = findView(R.id.ietPasswdConfirm);
        ietEmail = findView(R.id.ietEmail);
        ietName = findView(R.id.ietName);
        ietCode = findView(R.id.ietCode);
        ietPhone = findView(R.id.ietPhone);
        tvCode = findView(R.id.tvCode);

        tvRegist.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        tvCode.setOnClickListener(this);
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
            case R.id.tvSubmit:
                regist();
                break;
            case R.id.tvCode:
                obtainCode(v);
                break;
        }
    }

    private void regist() {
//            “username”: “abc”,
//     “password” : “123456”,
//     “email” : “”,
//      “phone” : “13512345678”,
//      “truename” : “张三”
        String account = ietAccount.getText().toString();
        String passwd = ietPasswd.getText().toString();
        String repasswd = ietPasswdConfirm.getText().toString();
        String email = ietEmail.getText().toString();
        String name = ietName.getText().toString();
        String phone = ietPhone.getText().toString();
        String code = ietCode.getText().toString();

        ietAccount.inputNormal();
        ietPasswd.inputNormal();
        ietPasswdConfirm.inputNormal();
        ietName.inputNormal();
        ietEmail.inputNormal();
        ietPhone.inputNormal();
        ietCode.inputNormal();

        if (TextUtils.isEmpty(account)) {
            ToastUtils.toastShort("帐号不能为空");
            ietAccount.inputError();
        } else if (!ValidateUtils.isAccount(account)) {
            ToastUtils.toastShort("请输入有效的帐号");
            ietAccount.inputError();
        } else if (TextUtils.isEmpty(passwd)) {
            ToastUtils.toastShort("密码不能为空");
            ietPasswd.inputError();
        } else if (!ValidateUtils.isAccount(passwd)) {
            ToastUtils.toastShort("请输入有效的密码");
            ietPasswd.inputError();
        } else if (TextUtils.isEmpty(repasswd)) {
            ToastUtils.toastShort("请再次输入密码");
            ietPasswdConfirm.inputError();
        } else if (!repasswd.equals(passwd)) {
            ToastUtils.toastShort("两次密码输入不一致");
            ietPasswd.inputError();
            ietPasswdConfirm.inputError();
        } else if (!TextUtils.isEmpty(email) && !ValidateUtils.isEmail(email)) {
            ToastUtils.toastShort("请输入有效邮箱");
            ietEmail.inputError();
        } else if (TextUtils.isEmpty(name)) {
            ToastUtils.toastShort("真实姓名不能为空");
            ietName.inputError();
        } else if (TextUtils.isEmpty(phone)) {
            ToastUtils.toastShort("手机号码不能为空");
            ietPhone.inputError();
        } else if (TextUtils.isEmpty(code)) {
            ToastUtils.toastShort("验证码不能为空");
            ietCode.inputError();
        } else {
            NetParams params = new NetParams();
            params.put("username", account);
            params.put("password", passwd);
            if (!TextUtils.isEmpty(email)) {
                params.put("email", email);
            }
            params.put("phone", phone);
            params.put("truename", name);
            RequestOption option = new RequestOption();
            option.setMediaType(RequestOption.MEDIA_FORM);
            new NetRequester(this, option).post(NetUrl.REGISTER, params, new NetHttpCallback() {
                @Override
                public void onSuccess(String result) {

                }
            });
        }
    }

    private void obtainCode(View v) {
        ietPhone.inputNormal();
        String phone = ietPhone.getText().toString();
        if (!ValidateUtils.isPhone(phone)) {
            ietPhone.inputError();
            ToastUtils.toastShort("请填入正确的手机号");
        } else if (tvCode.isEnabled()) {  // 允许获取验证码
            lastSecond = MAX_TIME;
            handler = new Handler();
            handler.post(run);
            tvCode.setEnabled(false);
            // TODO: 2018-09-20 发起获取验证码请求
        }
    }
}
