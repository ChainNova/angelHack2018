package com.angehack.sharedparking.common;

import android.widget.Toast;

import com.angehack.sharedparking.MyApplication;


public class ToastUtil {
    public static void showToast(String message, Object... args) {
        if(args != null) {
            message = String.format(message, args);
        }

        Toast.makeText(MyApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }
    public static void showToast(int resId) {
        Toast.makeText(MyApplication.getInstance(), MyApplication.getInstance().getResources().getString(resId), Toast.LENGTH_SHORT).show();
    }
    public static void showLongToast(String message, Object... args) {
        if(args != null) {
            message = String.format(message, args);
        }

        Toast.makeText(MyApplication.getInstance(), message, Toast.LENGTH_LONG).show();
    }
}
