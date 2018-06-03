package com.angehack.sharedparking.constant;

import com.angehack.sharedparking.common.LogUtil;
import com.angehack.sharedparking.module.entity.ParkBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingle on 2018/6/2.
 */

public class AppConstant {
    public static List<ParkBean> listParks = new ArrayList<>();
    public static List<ParkBean> PendingParks = new ArrayList<>();

    public static void AddPendingParks(ParkBean bean){
        AppConstant.PendingParks.add(new ParkBean(bean.getId(), bean.getName(), bean.getAddress(),
                bean.getStartTime(), bean.getEndTime(), bean.getPrice(), 1));
        LogUtil.d("list", AppConstant.PendingParks.size(), AppConstant.PendingParks.get(0));
    }
}
