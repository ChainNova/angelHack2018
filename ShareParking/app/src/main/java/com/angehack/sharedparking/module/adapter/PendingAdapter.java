package com.angehack.sharedparking.module.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.angehack.sharedparking.R;
import com.angehack.sharedparking.api.NetworkServiceInter;
import com.angehack.sharedparking.api.response.BaseConsumerCallBack;
import com.angehack.sharedparking.api.response.ErrorCallBack;
import com.angehack.sharedparking.api.response.HttpResult;
import com.angehack.sharedparking.common.LogUtil;
import com.angehack.sharedparking.common.PreferenceUtil;
import com.angehack.sharedparking.common.ToastUtil;
import com.angehack.sharedparking.common.ViewHolder;
import com.angehack.sharedparking.constant.AppConstant;
import com.angehack.sharedparking.module.common.BaseActivity;
import com.angehack.sharedparking.module.entity.ParkBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xingle on 2018/6/2.
 */

public class PendingAdapter extends BaseAdapter {


    private List<ParkBean> listParks;
    private Context context;
    private NetworkServiceInter service;

    public PendingAdapter(BaseActivity baseContext, NetworkServiceInter service) {
        this.listParks = AppConstant.listParks;
        this.context = baseContext;
        this.service = service;
    }

    @Override
    public int getCount() {
        return listParks.size() == 0 ? 0 : listParks.size();
    }

    @Override
    public Object getItem(int position) {
        return listParks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pending_item, null);
        }

        final ParkBean bean = listParks.get(position);
        TextView tvName = ViewHolder.get(convertView, R.id.tv_name);
        TextView tvAddress = ViewHolder.get(convertView, R.id.tv_address);
        AppCompatButton btnPending = ViewHolder.get(convertView, R.id.btn_pending);


        tvName.setText(bean.getName() + "   " + bean.getStartTime() + "——" + bean.getEndTime() + "  " + bean.getPrice() + "元");
        tvAddress.setText(bean.getAddress());

        btnPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
                if (PreferenceUtil.getInstance().getPendingStatus() == 1 || PreferenceUtil.getInstance().getPendingStatus() == 2) {
                    ToastUtil.showLongToast("您已有预约成功，不能二次预约");
                    return;
                } else {
                    notifyDataSetChanged();
                    ToastUtil.showToast("预约成功！");
                    PreferenceUtil.getInstance().setPendingStatus(1);
                    AppConstant.listParks.remove(position);
                }
            }
        });

        return convertView;
    }


    private void request() {
//        context.showLoading();
        service.getPendingPark()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumerCallBack<HttpResult>() {
                    @Override
                    public void onSuccess(HttpResult result) {

                    }

                    @Override
                    public void onError(String message) {
//                        dismissLoading();
                        ToastUtil.showToast(message);
                    }
                }, new ErrorCallBack());
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }
}
