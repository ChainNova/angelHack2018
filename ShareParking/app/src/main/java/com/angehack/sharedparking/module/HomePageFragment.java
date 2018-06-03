package com.angehack.sharedparking.module;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Poi;
import com.angehack.sharedparking.common.ActivityUtil;
import com.angehack.sharedparking.common.LogUtil;
import com.angehack.sharedparking.module.common.BaseFragment;
import com.angehack.sharedparking.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xingle on 2018/6/2.
 */

public class HomePageFragment extends BaseFragment implements AMap.OnPOIClickListener, AMap.OnMarkerClickListener {


    @BindView(R.id.map)
    MapView mapView;
    private AMap aMap;
    @BindView(R.id.et_content)
    EditText etContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        aMap.setTrafficEnabled(true);
        setOnClickPOI();
        setMark();
        return view;
    }

    private void setOnClickPOI() {
        aMap.setOnPOIClickListener(this);
        aMap.setOnMarkerClickListener(this);
    }

    private void setMark() {
        //绘制marker
        ArrayList<MarkerOptions> markerOptions = new ArrayList<>();
        markerOptions.add(new MarkerOptions().title("Jim share park  8:00——10:00  4¥").position(new LatLng(39.986919, 116.353369)).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.g_verify)))
                .draggable(true));
        markerOptions.add(new MarkerOptions().title("Amery  9:00——14:00 10¥").position(new LatLng(39.919413, 116.466392)).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.g_verify)))
                .draggable(true));
        markerOptions.add(new MarkerOptions().title("老高的车位  12:00——16:00 8¥").position(new LatLng(39.917249, 116.46902)).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.g_verify)))
                .draggable(true));
        markerOptions.add(new MarkerOptions().title("girl  8:00——15:00 15¥").position(new LatLng(39.916838, 116.473612)).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.g_verify)))
                .draggable(true));
        markerOptions.add(new MarkerOptions().title("One day  8:00——10:00  4¥").position(new LatLng(39.921462, 116.459911)).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.g_verify)))
                .draggable(true));
        markerOptions.add(new MarkerOptions().title("A  8:00——10:00 4¥").position(new LatLng(39.921709, 116.458881)).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.g_verify)))
                .draggable(true));
        markerOptions.add(new MarkerOptions().title("Hello  8:00——10:00 4¥").position(new LatLng(39.924252, 116.460062)).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.g_verify)))
                .draggable(true));
        markerOptions.add(new MarkerOptions().title("Jim share park  8:00——10:00 4¥").position(new LatLng(39.924474, 116.460995)).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.g_verify)))
                .draggable(true));
        markerOptions.add(new MarkerOptions().title("Jim share park  8:00——10:00 4¥").position(new LatLng(39.921462, 116.460512)).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.g_verify)))
                .draggable(true));

        ArrayList<Marker> markers = aMap.addMarkers(markerOptions, true);


        for (Marker maker : markers) {
            /*Animation animation = new RotateAnimation(maker.getRotateAngle(),maker.getRotateAngle()+180);
            long duration = 1000L;
            animation.setDuration(duration);
            animation.setInterpolator(new LinearInterpolator());

            maker.setAnimation(animation);
            maker.startAnimation();*/
            maker.setClickable(true);

        }

    }


    @OnClick(R.id.img_search)
    void onClickListener() {
        if (!TextUtils.isEmpty(etContent.getText().toString())){
            ActivityUtil.startActivity(getActivity(), new Intent(getActivity(), PendingActivity.class));
        }
    }

    @Override
    public void onPOIClick(Poi poi) {
        /*aMap.clear();
        Log.i("MY", poi.getPoiId() + poi.getName());
        MarkerOptions markOptiopns = new MarkerOptions();
        markOptiopns.position(poi.getCoordinate());
        TextView textView = new TextView(getActivity());
        textView.setText("到" + poi.getName() + "去");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundResource(R.mipmap.g_verify);
        markOptiopns.icon(BitmapDescriptorFactory.fromView(textView));
        aMap.addMarker(markOptiopns);*/
//        ToastUtil.showToast(poi.getName());
        LogUtil.d("poi", poi.getName());
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // 构造导航参数
       /* NaviPara naviPara = new NaviPara();
        // 设置终点位置
        naviPara.setTargetPoint(marker.getPosition());
        // 设置导航策略，这里是避免拥堵
        naviPara.setNaviStyle(AMapUtils.DRIVING_AVOID_CONGESTION);
        try {
            // 调起高德地图导航
            AMapUtils.openAMapNavi(naviPara, getActivity());
        } catch (com.amap.api.maps.AMapException e) {
            // 如果没安装会进入异常，调起下载页面
            AMapUtils.getLatestAMapApp(getActivity());
        }
        aMap.clear();*/
//        ToastUtil.showToast(marker.getTitle());
        LogUtil.d("marker", marker.toString());
        String title = marker.getTitle();

        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }


    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
