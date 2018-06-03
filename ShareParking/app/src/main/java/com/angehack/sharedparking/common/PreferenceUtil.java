package com.angehack.sharedparking.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.angehack.sharedparking.MyApplication;

/**
 * Created by xingle on 2017/12/26.
 */

public class PreferenceUtil {

    public static final String PREFERENCE_NAME = "block_chain_pre";

    private static PreferenceUtil mPreferenceUtils = null;
    private static volatile SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor editor;

    private PreferenceUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public static PreferenceUtil getInstance() {
        if (mPreferenceUtils == null) {
            mPreferenceUtils = new PreferenceUtil(MyApplication.getInstance());
        }
        return mPreferenceUtils;
    }


    public void setPendingStatus(int status){
        editor.putInt("pending",status).apply();
    }

    public int getPendingStatus(){
        return mSharedPreferences.getInt("pending", 0);
    }

}
