package cn.base;

import java.lang.Thread.UncaughtExceptionHandler;

import cn.base.util.LogUtils;

/**
 * 全局异常捕获器
 * @author timpkins
 */
public class ExceptionHandler implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        LogUtils.e("EXCEPTION", "Thread's name is " + thread.getName(), throwable);
        BaseApplication.getInstance().onTerminate();
    }
}
