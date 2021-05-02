package com.example.baseproject.iView.activity.common;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.baseproject.R;
import com.example.baseproject.base.BaseActivity;
import com.example.baseproject.base.baseMVP.BaseIView;
import com.example.baseproject.entity.WelcomeEntity;
import com.example.baseproject.iPresenter.WelcomePresenter;
import com.example.baseproject.router.RouterCenter;
import com.example.baseproject.router.RouterPath;
import com.example.baseproject.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

@Route(path = RouterPath.commonWelcome)
public class WelcomeActivity extends BaseActivity<WelcomePresenter, WelcomeActivity> implements BaseIView {

    private static boolean hasPermissions = true;
    private static boolean hasStartPermission = false;
    private static final int REQUEST_CODE = 1;
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    ImageView welcome_bg;
    TextView time_text;

    @Override
    public int getContentView() {
        return R.layout.activity_common_welcome;
    }

    @Override
    protected WelcomePresenter setPresenter() {
        return new WelcomePresenter();
    }

    @Override
    protected void initView() {
        welcome_bg = findViewById(R.id.welcome_bg);
        time_text = findViewById(R.id.time_text);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermissions();             //检查权限
        if (hasPermissions) {
            loadData();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //权限申请结果反馈
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        } else {
            loadData();
        }
    }

    @Override
    public void loadData() {
        iPresenter.doWelcome();
    }

    @Override
    public void success(Object obj) {
        WelcomeEntity welcomeEntity = (WelcomeEntity) obj;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(WelcomeActivity.this).load(welcomeEntity.getWelcomePic()).into(welcome_bg);
            }
        });

        timeJump();
    }

    @Override
    public void error(String msg) {
        ToastUtil.errorToast(this, msg);
    }

    private void checkPermissions() {
        // 缺少权限时, 进入权限配置页面
        for (String permission : PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED & !hasStartPermission) {
                PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
                hasStartPermission = true;
                hasPermissions = false;
            }
        }
    }

    int time = 3;

    private void timeJump() {
        time_text.setVisibility(View.VISIBLE);
        time_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 0;
            }
        });

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (time == 0) {
                    timer.cancel();
                    RouterCenter.toCommonHome();
                    finish();
                }
                time_text.setText(time + " s");
                time--;
            }
        };
        timer.schedule(task, 0, 1000);
    }

}
