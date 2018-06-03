package com.angehack.sharedparking.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

public class LogUtil {

    private static boolean isEnabled;
    private static final String LOG_TAG = "UUGAMING_LOG";

    public static void init(boolean isEnabled) {
        LogUtil.isEnabled = isEnabled;
        Logger.init(LOG_TAG).methodCount(2).hideThreadInfo();
    }

    public static void d(String message, Object... args) {
        if(LogUtil.isEnabled)
            Logger.d(message, args);
    }

    public static void e(String message, Object... args) {
        if(LogUtil.isEnabled)
            Logger.e(message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if(LogUtil.isEnabled)
            Logger.e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        if(LogUtil.isEnabled)
            Logger.i(message, args);
    }

    public static void v(String message, Object... args) {
        if(LogUtil.isEnabled)
            Logger.v(message, args);
    }

    public static void w(String message, Object... args) {
        if(LogUtil.isEnabled)
            Logger.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        if(LogUtil.isEnabled)
            Logger.wtf(message, args);
    }

    public static void json(String json) {
        if(LogUtil.isEnabled)
            Logger.json(json);
    }

    public static void xml(String xml) {
        if(LogUtil.isEnabled)
            Logger.xml(xml);
    }

    public static void Object(Object object) {
        if(!LogUtil.isEnabled)
            return;

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(object);

        json(json);
    }
}
