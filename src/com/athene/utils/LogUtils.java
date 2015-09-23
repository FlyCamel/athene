package com.athene.utils;

/**
 * Created by bing on 6/18/15.
 */
public class LogUtils {
    public static final String MODE_D = "DEBUG";
    public static final String MODE_V = "VERBOSE";
    public static final String MODE_W = "WARN";
    public static final String MODE_E = "ERROR";
    public static final String MODE_I = "INFO";

    private static final boolean F = true;
    private static final boolean E = true;
    private static final boolean W = false;
    private static final boolean I = true;
    private static final boolean D = true;
    private static final boolean V = false;

    /**
     * Show current invoking stacks for debug.
     *
     * @param tag
     *      Used to identify the source of log message
     * @param msg
     *      The message you would like logged
     *
     * Usage:
     *      LogUtils.showStackInfo("BingTag", "DiyFragment::initLayout");
     */
    public static void stackInfo(String tag, String msg) {
        android.util.Log.d(tag, msg, new Throwable());
    }

    /**
     * Save current message into file
     *
     * @param tag
     * @param msg
     */
    public synchronized static void f(String tag, String msg) {
    }

    public synchronized static void f(String mode, String tag, String msg) {

    }

    public static void d(String tag, String msg) {
        if (D) {
            if (F) {
                f(tag, msg);
            } else {
                android.util.Log.d(tag, msg);
            }
        }
    }

    public static void w(String tag, String msg) {
        if (W) {
            if (F) {
                f(tag, msg);
            } else {
                android.util.Log.w(tag, msg);
            }
        }
    }

    public static void v(String tag, String msg) {
        if (V) {
            if (F) {
                f(tag, msg);
            } else {
                android.util.Log.v(tag, msg);
            }
        }
    }

    public static void e(String tag, String msg) {
        if (E) {
            if (F) {
                f(tag, msg);
            } else {
                android.util.Log.e(tag, msg, new Throwable());
            }
        }
    }

    public static void i(String tag, String msg) {
        if (I) {
            if (F) {
                f(tag, msg);
            } else {
                android.util.Log.i(tag, msg);
            }
        }
    }
}
