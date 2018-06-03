package com.angehack.sharedparking.module;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.angehack.sharedparking.R;
import com.angehack.sharedparking.api.response.BaseConsumerCallBack;
import com.angehack.sharedparking.api.response.ErrorCallBack;
import com.angehack.sharedparking.api.response.HttpResult;
import com.angehack.sharedparking.module.adapter.PendingAdapter;
import com.angehack.sharedparking.module.common.BaseActivity;
import com.angehack.sharedparking.module.entity.UserBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xingle on 2018/6/2.
 */

public class PendingActivity extends BaseActivity {

    @BindView(R.id.list_pending) ListView listPending;
    @BindView(R.id.sp_refresh) SwipeRefreshLayout refresh;
    private PendingAdapter adapter;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_pending);
        ButterKnife.bind(this);
    }

    @Override
    protected boolean showTitleBar() {
        return true;
    }

    @Override
    protected void loadTopBarRes() {
        setTopTitle("预约列表");
    }

    @Override
    protected void processLogic() {
        refresh.setRefreshing(true);
        adapter = new PendingAdapter(baseContext,service);
        listPending.setAdapter(adapter);
    }





}
