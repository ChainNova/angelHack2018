package com.angehack.sharedparking.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.angehack.sharedparking.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TopTitleView extends FrameLayout {
	@BindView(R.id.img_left) ImageView imgLeft;
	@BindView(R.id.tv_title)
    TextView tvTitle;
	@BindView(R.id.img_right_sub)
    ImageView imgRightSub;
	@BindView(R.id.tv_right_sub)
    TextView tvRightSub;


	public TopTitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		ButterKnife.bind(this);
	}

	public void setTitle(String title){
		if (tvTitle != null) {
			tvTitle.setText(title);
		}
	}

	public void setRightDraw(int imgId, OnClickListener listener){
		if (imgRightSub != null){
			imgRightSub.setBackgroundResource(imgId);
			imgRightSub.setVisibility(View.VISIBLE);
			setView(imgRightSub, 0, listener);
		}
	}

	public void setTvRightSub(String title, int color, OnClickListener listener){
		if (tvRightSub != null){
			tvRightSub.setText(title);
			tvRightSub.setTextColor(color);
			setView(tvRightSub, 0 ,listener);
		}
	}

	private void setView(View view, int backgroudResId, OnClickListener listener) {
		if (view != null) {
			view.setVisibility(View.VISIBLE);
			if (backgroudResId != 0) view.setBackgroundResource(backgroudResId);
			if (listener != null) view.setOnClickListener(listener);
		}
	}

	public void setOnBackClickLitener(OnClickListener onClickListener) {
		imgLeft.setOnClickListener(onClickListener);
	}


}
