package com.li.example;


import com.li.watchdog.Logger;


/**
 * 自定义异常类
 */

public class CustomUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler oldExceptionHandler;

    public void registerExceptionHandler() {
        oldExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger.e(e.getMessage());
        if (oldExceptionHandler != null) {
            oldExceptionHandler.uncaughtException(t, e);
        }
    }

}
