package com.example.baseproject.iView.activity.user;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baseproject.R;
import com.example.baseproject.base.BaseActivity;
import com.example.baseproject.base.baseMVP.BaseIView;
import com.example.baseproject.entity.LoginEntity;
import com.example.baseproject.iPresenter.LoginPresenter;
import com.example.baseproject.router.RouterPath;
import com.example.baseproject.utils.GlobalVar;
import com.example.baseproject.utils.PreferencesUtil;
import com.example.baseproject.utils.ToastUtil;

@Route(path = RouterPath.userLogin)
public class LoginActivity extends BaseActivity<LoginPresenter, LoginActivity> implements BaseIView {

    private LoginEntity loginEntity;
    private int opType = 1; //1:登录 2：注册

    EditText text_phone;
    EditText text_password;
    TextView button_login;
    TextView button_sign_in;

    @Override
    public int getContentView() {
        return R.layout.activity_user_login;
    }

    @Override
    protected LoginPresenter setPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        text_phone = findViewById(R.id.text_phone);
        text_password = findViewById(R.id.text_password);
        button_login = findViewById(R.id.button_login);
        button_sign_in = findViewById(R.id.button_sign_in);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opType = 1;
                loadData();
            }
        });
        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opType = 2;
                loadData();
            }
        });
    }

    @Override
    public void loadData() {
        String phone = text_phone.getText().toString();
        String password = text_password.getText().toString();
        loginEntity = new LoginEntity();
        loginEntity.setUser_phone(phone);
        loginEntity.setUser_password(password);
        iPresenter.doLogin(loginEntity, opType);
    }

    @Override
    public void success(Object obj) {
        loginEntity.setUser_id((int) obj);
        PreferencesUtil.putBean(LoginActivity.this, GlobalVar.USER_KEY, loginEntity);//保存用户信息
        finish();
    }

    @Override
    public void error(String msg) {
        ToastUtil.errorToast(this, msg);
    }
}
