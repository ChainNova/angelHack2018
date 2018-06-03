package com.angehack.sharedparking.util;


/**
 * Created by xingle on 2017/12/28.
 */

public class CommonUtil {
    /**
     * 上次点击的时间
     */
    private static long lastClickTime;

    public static boolean isFastDoubleClick(int delta) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        return 0 < timeD && timeD < delta;
    }
}
