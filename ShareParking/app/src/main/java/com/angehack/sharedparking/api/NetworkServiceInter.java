package com.angehack.sharedparking.api;


import com.angehack.sharedparking.api.response.HttpResult;
import com.angehack.sharedparking.module.entity.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkServiceInter {

   /* @FormUrlEncoded
    @POST("api/user/request_sms_code")
    Observable<HttpResult<UserBean>> getSMSCode(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("api/user/login")
    Observable<HttpResult<UserBean>> login(@Field("mobile") String combile, @Field("verify_code") String verify_code);


    @GET("api/user/info")
    Observable<HttpResult<UserBean>> getUserInfo();*/
   @GET("rent/lockingPart1")
   Observable<HttpResult> getPendingPark();

   @FormUrlEncoded
   @POST("api/user/request_sms_code")
   Observable<HttpResult<UserBean>> getSMSCode(@Field("mobile") String mobile);

}
