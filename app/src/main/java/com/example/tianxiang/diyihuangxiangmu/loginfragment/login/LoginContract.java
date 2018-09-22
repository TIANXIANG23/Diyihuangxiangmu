package com.example.tianxiang.diyihuangxiangmu.loginfragment.login;

import com.example.tianxiang.diyihuangxiangmu.base.BasePresenter;
import com.example.tianxiang.diyihuangxiangmu.base.BaseView;

public interface LoginContract {

    public interface View extends BaseView<LoginContract.Presenter>{
        void verificationCodeSuccess();
        void verificationCodeFail();

        void loginSuccess();

        void loginFail(String msg);
    }
    public interface Presenter extends BasePresenter<LoginContract.View>{
        void getVerificationCode(String phoneNumber);

        void login(String phoneNumber,String code);
    }
}
