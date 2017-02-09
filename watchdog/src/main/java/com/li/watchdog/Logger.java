package com.li.watchdog;



import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




public class Logger {
    public static String TAG = "";

    /**
     * Get The Current Function Name
     *
     * @return
     */
    private static String getFunctionName(String message) {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return message;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(Logger.class.getName())) {
                continue;
            }

            return "[ " + Thread.currentThread().getName() + " : "
                    + st.getFileName() + " : " + st.getLineNumber() + " "
                    + st.getMethodName() + " ]" + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + "-" + message;
        }
        return message;
    }


    public static void v(String message) {
        LoggerFactory.getLogger(TAG).trace(getFunctionName(message));

    }


    public static void d(String message) {
        LoggerFactory.getLogger(TAG).debug(getFunctionName(message));

    }

    public static void i(String message) {
        LoggerFactory.getLogger(TAG).info(getFunctionName(message));
    }


    public static void w(String message) {
        LoggerFactory.getLogger(TAG).warn(getFunctionName(message));

    }

    public static void e(String message) {
        LoggerFactory.getLogger(TAG).error(getFunctionName(message));

    }


    private long memoryUsage;
    private long memoryUsageRate;

    /**
     * get use size
     */
    private void getUseSize() {
        Runtime runtime = Runtime.getRuntime();
        long totalSize = runtime.maxMemory() >> 10;
        this.memoryUsage = (runtime.totalMemory() - runtime.freeMemory()) >> 10;
        this.memoryUsageRate = this.memoryUsage * 100 / totalSize;
    }

    /**
     * get CPU rate
     *
     * @return
     */
    private int getProcessCpuRate() {

        StringBuilder tv = new StringBuilder();
        int rate = 0;

        try {
            String Result;
            Process p;
            p = Runtime.getRuntime().exec("top -n 1");

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((Result = br.readLine()) != null) {
                if (Result.trim().length() < 1) {
                    continue;
                } else {
                    String[] CPUUsr = Result.split("%");
                    tv.append("USER:" + CPUUsr[0] + "\n");
                    String[] CPUUsage = CPUUsr[0].split("User");
                    String[] SYSUsage = CPUUsr[1].split("System");
                    tv.append("CPU:" + CPUUsage[1].trim() + " length:" + CPUUsage[1].trim().length() + "\n");
                    tv.append("SYS:" + SYSUsage[1].trim() + " length:" + SYSUsage[1].trim().length() + "\n");

                    rate = Integer.parseInt(CPUUsage[1].trim()) + Integer.parseInt(SYSUsage[1].trim());
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rate;
    }

}
