package com.li.example;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import com.li.watchdog.Logger;

import org.apache.log4j.Level;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by liweifa on 2016/12/23.
 */

public class LogConfiguration {

    public static boolean configuration(Context context) {
        //因为5.0以上版本对存储卡的读写权限需要申请 所以在此会对权限进行再次判断 防止异常
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            File cacheFile = Environment.isExternalStorageEmulated() ? context.getExternalCacheDir() : context.getCacheDir();
            LogConfigurator logConfigurator = new LogConfigurator();
            logConfigurator.setRootLevel(Level.DEBUG);
            logConfigurator.setLevel(context.getPackageName(), Level.ERROR);
            logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
            logConfigurator.setMaxFileSize(1024 * 1024 * 5);
            logConfigurator.setImmediateFlush(true);
            logConfigurator.setFileName(cacheFile.getPath() + File.separator + "logs" + File.separator + "log");
            logConfigurator.setUseLogCatAppender(true);
            logConfigurator.configure();

            Logger.TAG = LogConfiguration.class.getPackage().getName();

            CustomUncaughtExceptionHandler customUncaughtExceptionHandler = new CustomUncaughtExceptionHandler();
            customUncaughtExceptionHandler.registerExceptionHandler();
            return true;
        }
        return false;

    }


}
