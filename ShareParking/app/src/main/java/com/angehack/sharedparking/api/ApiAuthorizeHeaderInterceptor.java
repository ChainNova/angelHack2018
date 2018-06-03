package com.angehack.sharedparking.api;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiAuthorizeHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();


        /*if(TextUtils.isEmpty(PreferenceUtil.getInstance().getAccessToken())) {
            return chain.proceed(request);
        }

        Request authorised = request.newBuilder()
                .header("a", PreferenceUtil.getInstance().getAccessToken())         //access_token
                .build();

        LogUtil.d("token :%s",PreferenceUtil.getInstance().getAccessToken());*/

//        return chain.proceed(authorised);
        return null;
    }
}
