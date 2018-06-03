package com.angehack.sharedparking;

import android.app.Application;

import com.angehack.sharedparking.common.LogUtil;
import com.angehack.sharedparking.constant.AppConstant;
import com.angehack.sharedparking.module.entity.ParkBean;

/**
 * Created by xingle on 2018/6/2.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LogUtil.init(true);
        setData();
        initSDK();
    }

    private void initSDK() {
    }

    private void setData() {
        AppConstant.listParks.add(new ParkBean(1, "Jim share park", "东大桥路1号院", "8:00", "10:00", 4.0f, 0));
        AppConstant.listParks.add(new ParkBean(2, "Amery", "东大桥路10号院", "11:00", "18:00", 14.0f, 0));
        AppConstant.listParks.add(new ParkBean(3, "老高的车位", "东大桥路9号院", "10:00", "14:00", 8.0f, 0));
        AppConstant.listParks.add(new ParkBean(4, "girl", "东大桥路6号院", "9:00", "11:00", 4.0f, 0));
        AppConstant.listParks.add(new ParkBean(5, "One day", "东大桥路6号院", "9:00", "11:00", 4.0f, 0));
        AppConstant.listParks.add(new ParkBean(6, "F", "东大桥路6号院", "9:00", "11:00", 4.0f, 0));
        LogUtil.d("list size:" + AppConstant.listParks.size());

        AppConstant.PendingParks.add(new ParkBean(1, "Jim share park", "东大桥路1号院", "8:00", "10:00", 4.0f, 1));
        LogUtil.d("list size:" + AppConstant.PendingParks.size());
    }


}
