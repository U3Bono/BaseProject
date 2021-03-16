package com.example.baseproject.iView.activity.user;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baseproject.R;
import com.example.baseproject.base.BaseActivity;
import com.example.baseproject.base.baseMVP.BaseIView;
import com.example.baseproject.entity.UserDetailEntity;
import com.example.baseproject.iPresenter.UserDetailPresenter;
import com.example.baseproject.router.RouterPath;
import com.example.baseproject.utils.GlideUtil;
import com.example.baseproject.utils.ToastUtil;

@Route(path = RouterPath.userDetail)
public class UserDetailActivity extends BaseActivity<UserDetailPresenter, UserDetailActivity> implements BaseIView {

    @Autowired()
    int user_id;

    ImageView image_head;
    TextView text_name;
    TextView text_phone;

    @Override
    public int getContentView() {
        return R.layout.activity_user_detail;
    }

    @Override
    protected UserDetailPresenter setPresenter() {
        return new UserDetailPresenter();
    }

    @Override
    protected void initView() {
        image_head = findViewById(R.id.image_head);
        text_name = findViewById(R.id.text_name);
        text_phone = findViewById(R.id.text_phone);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Override
    public void loadData() {
        iPresenter.getDetail(user_id);
    }

    @Override
    public void success(Object obj) {
        UserDetailEntity userDetailEntity = (UserDetailEntity) obj;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GlideUtil.userIc(UserDetailActivity.this, userDetailEntity.getUser_ic(), image_head);
                text_name.setText(userDetailEntity.getUser_account());
                text_phone.setText(userDetailEntity.getUser_phone());
            }
        });
    }

    @Override
    public void error(String msg) {
        ToastUtil.errorToast(this, msg);
    }
}
