package com.example.tianxiang.diyihuangxiangmu.loginfragment.circle;

public class CirclePresenter  implements CircleContract.Presenter{
    public CircleContract.View mView;
    @Override
    public void attachView(CircleContract.View baseView) {
        mView=baseView;
    }

    @Override
    public void detachView() {
        mView=null;
    }
}
