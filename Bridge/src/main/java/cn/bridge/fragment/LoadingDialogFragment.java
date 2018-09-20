package cn.bridge.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import cn.bridge.R;

/**
 * 数据加载对话框
 * @author timpkins
 */
public class LoadingDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loading_dialog, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            LayoutParams windowParams = window.getAttributes();
            windowParams.height = LayoutParams.MATCH_PARENT;
            windowParams.width = LayoutParams.MATCH_PARENT;
            windowParams.dimAmount = 0.0f;
            window.setAttributes(windowParams);
        }
    }
}
