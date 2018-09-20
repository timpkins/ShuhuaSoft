package cn.base.util;

import android.support.annotation.StringRes;
import android.widget.Toast;

import cn.base.BaseApplication;

/**
 * Toast显示，需在AndroidManifest.xml中注册{@link BaseApplication}的子类
 * @author timpkins
 */
public final class ToastUtils {

    /**
     * 短时间显示Toast
     * @param msg Toast显示的信息
     */
    public static void toastShort(String msg){
        toastShow(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     * @param resId Toast显示的信息
     */
    public static void toastShort(@StringRes int resId){
        Toast.makeText(BaseApplication.getInstance(), resId, Toast.LENGTH_SHORT).show();

    }

    /**
     * 长时间显示Toast
     * @param msg Toast显示的信息
     */
    public static void toastLong(String msg){
        toastShow(msg, Toast.LENGTH_LONG);
    }

    private static void toastShow(String msg, int duration){
        Toast.makeText(BaseApplication.getInstance(), msg, duration).show();
    }
}
