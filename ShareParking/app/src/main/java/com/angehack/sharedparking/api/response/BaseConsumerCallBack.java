package com.angehack.sharedparking.api.response;

import android.content.Intent;
import android.text.TextUtils;

import com.angehack.sharedparking.MyApplication;


import io.reactivex.functions.Consumer;

/**
 * Created by xingle on 2018/1/23.
 */

public abstract class BaseConsumerCallBack<T> implements Consumer<T> {

    @Override
    public void accept(T t) throws Exception {

        if (!(t instanceof HttpResult)) {
//            onError(MyApplication.getInstance().getString(R.string.ret_error_data));
            return;
        }

        HttpResult result = (HttpResult) t;
        if (result.getStatus() == 1) {
            onSuccess((T) result);
        } else if (result.getStatus() == 2){

        } else {
            onError(result.getMessage());
        }
    }



    public abstract void onSuccess(T result);

    public abstract void onError(String message);
}
