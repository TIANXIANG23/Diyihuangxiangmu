package com.example.tianxiang.diyihuangxiangmu.loginfragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.base.BaseActivity;
import com.example.tianxiang.diyihuangxiangmu.data.remote.UserDataRepository;

public class LoginActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginPresenter loginPresenter = new LoginPresenter(UserDataRepository.getInstance());
        addFragment(LoginFragment.class,loginPresenter,R.id.login_container,null,null);

}
}
