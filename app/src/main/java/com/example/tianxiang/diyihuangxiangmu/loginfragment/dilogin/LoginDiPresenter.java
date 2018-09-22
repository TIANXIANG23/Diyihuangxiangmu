package com.example.tianxiang.diyihuangxiangmu.loginfragment.dilogin;

public class LoginDiPresenter implements LoginDiContract.Presenter {

    public LoginDiContract.View mView;
    @Override
    public void attachView(LoginDiContract.View baseView) {
        mView=baseView;
    }

    @Override
    public void detachView() {
        mView=null;
    }
}
