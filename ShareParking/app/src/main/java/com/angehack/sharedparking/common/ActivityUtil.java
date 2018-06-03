package com.angehack.sharedparking.common;

import android.app.Activity;
import android.content.Intent;

import com.angehack.sharedparking.R;


/**
 * Created by xingle on 2017/12/22.
 */

public class ActivityUtil {

    public static void startActivity(Activity activity, Intent intent){
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_in_right, R.anim.push_out_left);
    }

    public static void startActivityNomal(Activity activity, Intent intent){
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_in_right, R.anim.push_out_left);
    }

    public static void startActivityClearData(Activity activity, Intent intent){
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_in_right, R.anim.push_out_left);
    }

    public static void finishActivity(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.push_in_left, R.anim.push_out_right);
    }
}
