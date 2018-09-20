package cn.bridge;

import android.app.Activity;

import java.io.IOException;

/**
 * 网络请求回调
 * @author timpkins
 */
public interface NetCallback {

    void onSuccess(Activity activity, String url, String result);

    void onFailure(Activity activity, String url);

    void onError(Activity activity, String url, IOException e);
}
