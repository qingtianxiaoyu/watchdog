# android日志库 对log4j的二次封装
对java日志库log4j的二次封装，目前只是先提供了一个基础的log打印类
使用方法

            LogConfigurator logConfigurator = new LogConfigurator();
            logConfigurator.setRootLevel(Level.DEBUG);
            logConfigurator.setLevel(context.getPackageName(), Level.ERROR);
            logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
            logConfigurator.setMaxFileSize(1024 * 1024 * 5);
            logConfigurator.setImmediateFlush(true);
            File cacheFile = Environment.isExternalStorageEmulated() ? context.getExternalCacheDir() : context.getCacheDir();
            logConfigurator.setFileName(cacheFile.getPath() + File.separator + "logs" + File.separator + "log");
            logConfigurator.setUseLogCatAppender(true);
            logConfigurator.configure();

            Logger.TAG = LogConfiguration.class.getPackage().getName();

            CustomUncaughtExceptionHandler customUncaughtExceptionHandler = new CustomUncaughtExceptionHandler();
            customUncaughtExceptionHandler.registerExceptionHandler();

## 注意
因为这个日志库有具有保存log日志文件的，android6.0以上的对本地存储卡的访问权限加强了，所以需要对文件路径进行权限申请，最简单的方法就是对文件读写权限进行申请。因为运行时权限的申请，需要在Activity中，所以可以在项目启动的第一个Activity中对该权限进行检查，不过这不是十分保险的方法，除此之外还有另外一个方法，就是设置私有权限的文件目录，[http://blog.csdn.net/u013243573/article/details/54426063](http://blog.csdn.net/u013243573/article/details/54426063)
