package com.angehack.sharedparking.module;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.angehack.sharedparking.api.response.BaseConsumerCallBack;
import com.angehack.sharedparking.api.response.ErrorCallBack;
import com.angehack.sharedparking.api.response.HttpResult;
import com.angehack.sharedparking.module.common.BaseActivity;
import com.angehack.sharedparking.R;
import com.angehack.sharedparking.module.entity.UserBean;

import java.util.ArrayList;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private final int SDK_PERMISSION_REQUEST = 127;
    private String permissionInfo;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private MineFragment myFragment;
    private HomePageFragment homepageFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setSelect(0);
                    return true;
                case R.id.navigation_mine:
                    setSelect(1);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        setSelect(0);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        getPersimmions();
    }

    @Override
    protected void loadTopBarRes() {

    }

    @Override
    protected void processLogic() {
        service.getSMSCode("18500208914")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumerCallBack<HttpResult<UserBean>>() {
                    @Override
                    public void onSuccess(HttpResult<UserBean> result) {
                        dismissLoading();
                    }

                    @Override
                    public void onError(String message) {
                        dismissLoading();
//                        ToastUtil.showToast(message);
                    }
                }, new ErrorCallBack());
    }

    private void setSelect(int i) {
        transaction = fragmentManager.beginTransaction();
        hideFragment();
        switch (i) {
            case 0:
                if (homepageFragment == null) {
                    homepageFragment = new HomePageFragment();
                    transaction.add(R.id.content, homepageFragment);
                } else {
                    transaction.show(homepageFragment);
                }
                break;
            case 1:
                if (myFragment == null) {
                    myFragment = new MineFragment();
                    transaction.add(R.id.content, myFragment);
                } else {
                    transaction.show(myFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment() {
        if (homepageFragment != null && homepageFragment.isAdded())
            if (myFragment != null && myFragment.isAdded())
                transaction.hide(myFragment);
    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            /*
			 * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

}
