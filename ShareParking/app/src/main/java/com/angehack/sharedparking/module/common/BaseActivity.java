package com.angehack.sharedparking.module.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.angehack.sharedparking.R;
import com.angehack.sharedparking.api.DaggerDeps;
import com.angehack.sharedparking.api.Deps;
import com.angehack.sharedparking.api.NetworkModule;
import com.angehack.sharedparking.api.NetworkServiceInter;
import com.angehack.sharedparking.common.ActivityUtil;
import com.angehack.sharedparking.common.LoadingDialog;
import com.angehack.sharedparking.common.TopTitleView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import javax.inject.Inject;


/**
 * Created by xingle on 2017/12/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ViewGroup rootLayout;
    protected BaseActivity baseContext;
    protected TopTitleView topTitleView;
    protected LoadingDialog loadingDialog;
    @Inject
    public NetworkServiceInter service;
    private Deps deps;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        baseContext = BaseActivity.this;
        loadingDialog = new LoadingDialog(baseContext);
        initView();
    }

    private void initView() {
        File cacheFile = new File(baseContext.getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
        deps.inject(this);
        loadViewLayout();
        loadTopBarRes();
        processLogic();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if (showTitleBar()) {
            rootLayout = (ViewGroup) View.inflate(this, R.layout.activity_base, null);
            View child = View.inflate(this, layoutResID, null);
            if (rootLayout == null) {
                super.setContentView(layoutResID);
                return;
            }
            topTitleView = rootLayout.findViewById(R.id.view_title);
            rootLayout.addView(child, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            topTitleView.setOnBackClickLitener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    onBack();
                }
            });
            super.setContentView(rootLayout);
        } else {
            super.setContentView(layoutResID);
        }
    }

    protected void setTopTitle(String title) {
        if (topTitleView != null) {
            topTitleView.setTitle(title);
        }
    }

    protected void setImgRight(String title,int imgRight, View.OnClickListener listener){
        if (topTitleView != null) {
            topTitleView.setTitle(title);
            topTitleView.setRightDraw(imgRight,listener);
        }
    }

    protected void onBack() {
        ActivityUtil.finishActivity(baseContext);
    }


    protected abstract void loadViewLayout();

    protected abstract void loadTopBarRes();

    protected abstract void processLogic();

    protected boolean showTitleBar() {
        return false;
    }


    public void showLoading() {
        loadingDialog.show();
    }

    public void dismissLoading() {
        if (loadingDialog != null)
            loadingDialog.dismiss();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
