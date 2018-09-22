package com.example.tianxiang.diyihuangxiangmu.loginfragment.login;

import com.example.tianxiang.diyihuangxiangmu.data.remote.User;
import com.example.tianxiang.diyihuangxiangmu.data.remote.UserDataSource;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View mView;

    private UserDataSource mUserDataRepository;

    public  LoginPresenter(UserDataSource UserDataRepository){
        mUserDataRepository=UserDataRepository;
    }

    public void getVerificationCode(String phoneNumber){
        Map<String,String> params=new HashMap<>();
        params.put("phone",phoneNumber);
        params.put("smsType","0");

        mUserDataRepository.sendVerificationCode((RxFragment) mView, params, new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                mView.verificationCodeSuccess();
            }

            @Override
            public void onError(Throwable e) {
                mView.verificationCodeFail();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void login(String phoneNumber,String code){
        Map<String,String> params=new HashMap<>();
        params.put("phone",phoneNumber);
        params.put("verificationCode", code);
        params.put("platform", "1");
        params.put("appVersion", "1.0.0");
        params.put("deviceId", "1454564545");
        mUserDataRepository.login((RxFragment) mView, params, new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(User user) {
                mView.loginSuccess();
            }

            @Override
            public void onError(Throwable e) {
                mView.loginFail(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void attachView(LoginContract.View baseView) {
        mView=baseView;
    }

    @Override
    public void detachView() {
        mView=null;
    }
}
