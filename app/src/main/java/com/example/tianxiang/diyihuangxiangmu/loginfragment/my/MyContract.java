package com.example.tianxiang.diyihuangxiangmu.loginfragment.my;

import com.example.tianxiang.diyihuangxiangmu.base.BasePresenter;
import com.example.tianxiang.diyihuangxiangmu.base.BaseView;

public interface MyContract {
    public interface View extends BaseView<MyContract.Presenter>{

    }
    public interface Presenter extends BasePresenter<MyContract.View>{

    }
}
