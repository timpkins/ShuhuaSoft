package com.mec;

import android.app.Activity;

import com.google.gson.Gson;
import com.mec.bean.NetResponse;

import java.io.IOException;

import cn.base.util.LogUtils;
import cn.bridge.NetCallback;

/**
 * @author timpkins
 */
public abstract class NetHttpCallback implements NetCallback {

    @Override
    public void onSuccess(Activity activity, String url, String result) {
        // 请求成功，判断数据  code = 200为有效数据，其他为异常数据
        NetResponse response = new Gson().fromJson(result, NetResponse.class);
        if (response.getCode() == 200){
            onSuccess(result);
        }else{
            // 进行错误处理
            LogUtils.e("请求返回", "code != 200" + "     real code = " + response.getCode());
        }
    }

    @Override
    public void onFailure(Activity activity, String url) {
        LogUtils.e("请求返回", "请求失败 = " + url);
    }

    @Override
    public void onError(Activity activity, String url, IOException e) {
        LogUtils.e("请求返回", "请求异常 = " + url, e);
    }

    public abstract void onSuccess(String result);
}
