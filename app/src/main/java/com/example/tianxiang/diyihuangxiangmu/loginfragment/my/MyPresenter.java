package com.example.tianxiang.diyihuangxiangmu.loginfragment.my;

public class MyPresenter implements MyContract.Presenter {
    public MyContract.View mView;
    @Override
    public void attachView(MyContract.View baseView) {
        mView=baseView;
    }

    @Override
    public void detachView() {
        mView=null;
    }
}
