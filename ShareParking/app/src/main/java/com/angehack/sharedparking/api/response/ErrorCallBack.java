package com.angehack.sharedparking.api.response;


import org.greenrobot.eventbus.EventBus;

import io.reactivex.functions.Consumer;

/**
 * Created by xingle on 2018/1/30.
 */

public class ErrorCallBack implements Consumer<Throwable> {

    @Override
    public void accept(Throwable throwable) throws Exception {
    }
}
