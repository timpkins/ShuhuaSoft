package com.mec.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuhuasoft.R;

import cn.base.fragment.BaseTabFragment;

/**
 * 我的
 * @author timpkins
 */
public class MineTabFragment extends BaseTabFragment {

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
//        TextView tv = view.findViewById(R.id.tv);
//        tv.setOnClickListener(v -> getActivity().startActivity(new Intent(getActivity(), PersonInfoActivity.class)));
    }
}
