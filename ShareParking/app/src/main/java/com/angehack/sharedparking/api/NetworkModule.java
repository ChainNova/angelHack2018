package com.angehack.sharedparking.api;


import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by adolphcui
 * Date：2017/12/25
 * Description：主要实现什么功能
 */
@Module
public class NetworkModule {

    File cacheFile;

    public NetworkModule(File cacheFile) {
        this.cacheFile = cacheFile;
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*if (AppConstants.DEBUG_MODE) {
        }*/
            client.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        client.addInterceptor(new ApiAuthorizeHeaderInterceptor());
        client.cache(cache);
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                Request build = setRequestHeader(request);
                Response response = chain.proceed(build);
                response.cacheResponse();
                return response;
            }
        });
        client.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        return client.build();
    }

    private Request setRequestHeader(Request request) {
//        int versionCode = SystemUtil.getPackageInfo(BTCNewsApplication.getInstance().getApplicationContext()).versionCode;
//        String channel = SystemUtil.getAppMetaData(BTCNewsApplication.getInstance(), "UMENG_CHANNEL");

        return request.newBuilder()
                .header("Content-Type", "application/json")
                .removeHeader("Pragma")
                .header("l", Locale.getDefault().getLanguage())                    //language
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
//                .baseUrl(BuildConfig.BASEURL)
                .baseUrl("https://www.cointool.vip/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkServiceInter providesNetworkService(Retrofit retrofit) {
        return retrofit.create(NetworkServiceInter.class);
    }

}
