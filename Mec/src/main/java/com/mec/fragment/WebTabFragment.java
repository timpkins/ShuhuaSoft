package com.mec.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shuhuasoft.R;

import cn.base.fragment.BaseTabFragment;
import cn.base.util.LogUtils;

/**
 * 加载网页的Fragment
 * @author timpkins
 */
public class WebTabFragment extends BaseTabFragment {
    private static final String TAG = WebTabFragment.class.getSimpleName();
    private WebView wvShop;

    public static WebTabFragment newInstance(String webUrl) {
        Bundle args = new Bundle();
        args.putString(KEY_MESSAGE, webUrl);
        WebTabFragment fragment = new WebTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWebView();
    }

    private void initWebView() {
        wvShop = getView().findViewById(R.id.wvContent);
        WebSettings settings = wvShop.getSettings();
        settings.setJavaScriptEnabled(true);
        wvShop.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wvShop.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        LogUtils.e(TAG, "WEB_URL = " + getArguments().getString(KEY_MESSAGE));
        wvShop.loadUrl(getArguments().getString(KEY_MESSAGE));
    }

    public void onBackPressed() {
        wvShop.goBack();
    }

}
