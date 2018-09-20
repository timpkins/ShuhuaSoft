package com.mec.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mec.activity.MineInfoActivity;
import com.mec.activity.MineSafeActivity;
import com.mec.activity.MineSettingsActivity;
import com.mec.activity.UserLoginActivity;
import com.shuhuasoft.R;

import cn.base.fragment.BaseTabFragment;
import cn.base.util.LogUtils;

/**
 * 我的
 * @author timpkins
 */
public class MineTabFragment extends BaseTabFragment implements OnClickListener {
    private TextView tvBalance, tvName;

    public static MineTabFragment newInstance(String webUrl) {
        Bundle args = new Bundle();
        args.putString(KEY_MESSAGE, webUrl);
        MineTabFragment fragment = new MineTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvOrder = view.findViewById(R.id.tvOrder);
        TextView tvBill = view.findViewById(R.id.tvBill);
        TextView tvPersion = view.findViewById(R.id.tvPersion);
        TextView tvSafe = view.findViewById(R.id.tvSafe);
        TextView tvSettings = view.findViewById(R.id.tvSettings);
        tvName = view.findViewById(R.id.tvUserName);
        tvBalance = view.findViewById(R.id.tvUserBalance);

        tvOrder.setOnClickListener(this);
        tvBill.setOnClickListener(this);
        tvPersion.setOnClickListener(this);
        tvSafe.setOnClickListener(this);
        tvSettings.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tvBalance.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        Activity activity = getActivity();
        if (activity != null) {
            switch (v.getId()) {
                case R.id.tvOrder: // TODO 我的订单
//                    activity.startActivity(new Intent(getActivity(), MineInfoActivity.class));
                    break;
                case R.id.tvBill: // TODO 账单明细
//                    activity.startActivity(new Intent(getActivity(), MineInfoActivity.class));
                    break;
                case R.id.tvPersion: // 个人资料
                    activity.startActivity(new Intent(getActivity(), MineInfoActivity.class));
                    break;
                case R.id.tvSafe: // 安全管理
                    activity.startActivity(new Intent(getActivity(), MineSafeActivity.class));
                    break;
                case R.id.tvSettings: // 设置
                    activity.startActivityForResult(new Intent(getActivity(), MineSettingsActivity.class), 0x02);
                    break;
                case R.id.tvUserName:
                    activity.startActivityForResult(new Intent(getActivity(), UserLoginActivity.class), 0x01);
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("TAG", "requestCode = " + requestCode);
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == 0x01){
                tvName.setText("用户已登录");
            }else if (requestCode == 0x02){
                tvName.setText(R.string.mine_login);
            }
        }

    }
}
