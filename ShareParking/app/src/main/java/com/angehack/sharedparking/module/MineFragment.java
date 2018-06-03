package com.angehack.sharedparking.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.angehack.sharedparking.common.LogUtil;
import com.angehack.sharedparking.common.PreferenceUtil;
import com.angehack.sharedparking.common.ToastUtil;
import com.angehack.sharedparking.constant.AppConstant;
import com.angehack.sharedparking.module.common.BaseFragment;
import com.angehack.sharedparking.R;
import com.angehack.sharedparking.module.entity.ParkBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xingle on 2018/6/2.
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.btn_pending)
    AppCompatButton btnPending;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        logic();
        return view;
    }

    private void logic() {
        if (PreferenceUtil.getInstance().getPendingStatus() == 0) {
            rlContent.setVisibility(View.GONE);
            tvContent.setVisibility(View.VISIBLE);
        } else {
            setData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        logic();
    }

    private void setData() {
        rlContent.setVisibility(View.VISIBLE);
        tvContent.setVisibility(View.GONE);
        LogUtil.d("parks", AppConstant.PendingParks.size());
        final ParkBean bean = AppConstant.PendingParks.get(0);
        tvName.setText(bean.getName() + "   " + bean.getStartTime() + "——" + bean.getEndTime() + "  " + bean.getPrice() + "元");
        tvAddress.setText(bean.getAddress());

        btnPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferenceUtil.getInstance().getPendingStatus() == 1) {
                    ToastUtil.showToast("地锁已经解锁，请停靠你的爱车吧！");
                    PreferenceUtil.getInstance().setPendingStatus(2);
                    btnPending.setText("使用中");
                } else if (PreferenceUtil.getInstance().getPendingStatus() == 2) {
                    ToastUtil.showToast("欢迎使用" + bean.getName() + "的共享车位");
                    PreferenceUtil.getInstance().setPendingStatus(3);
                    btnPending.setText("空闲");
                    btnPending.setEnabled(false);
                }
            }
        });
    }
}
