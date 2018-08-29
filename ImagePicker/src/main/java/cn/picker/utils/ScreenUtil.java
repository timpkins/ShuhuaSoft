package cn.picker.utils;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * 屏幕工具类--获取手机屏幕信息
 * @author zihao
 */
public class ScreenUtil {


    /**
     * 获取屏幕的高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }
}