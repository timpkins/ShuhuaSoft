package cn.base;

import android.app.Application;

/**
 * Application基类
 * @author timpkins
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        System.exit(0);
    }
}
