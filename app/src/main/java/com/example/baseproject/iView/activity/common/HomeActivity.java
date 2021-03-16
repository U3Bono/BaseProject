package com.example.baseproject.iView.activity.common;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baseproject.R;
import com.example.baseproject.base.BaseActivity;
import com.example.baseproject.base.baseMVP.BaseIView;
import com.example.baseproject.entity.LoginEntity;
import com.example.baseproject.iPresenter.HomePresenter;
import com.example.baseproject.router.RouterCenter;
import com.example.baseproject.router.RouterPath;
import com.example.baseproject.utils.GlobalVar;
import com.example.baseproject.utils.PreferencesUtil;
import com.example.baseproject.utils.ToastUtil;
import com.example.baseproject.view.X5WebView;

import java.util.Timer;
import java.util.TimerTask;

@Route(path = RouterPath.commonHome)
public class HomeActivity extends BaseActivity<HomePresenter, HomeActivity> implements BaseIView {

    private LoginEntity loginEntity;
    private int time = 0;

    @Override
    public int getContentView() {
        return R.layout.activity_common_home;
    }

    @Override
    protected HomePresenter setPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (time == 0) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        time = 0;
                    }
                };
                timer.schedule(task, 2000);
                time++;
                ToastUtil.noticeToast(HomeActivity.this, getResources().getString(R.string.back_toast));
                return false;
            } else {
                finish();
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void loadData() {
        getUser();
    }

    @Override
    public void success(Object obj) {

    }

    @Override
    public void error(String msg) {
        ToastUtil.errorToast(this, msg);
    }

    private void getUser() {
        loginEntity = (LoginEntity) PreferencesUtil.getBean(HomeActivity.this, GlobalVar.USER_KEY);
        if (loginEntity == null) {
            RouterCenter.toUserLogin();
        } else {
            RouterCenter.toUserDetail(loginEntity.getUser_id());
        }
    }

}
