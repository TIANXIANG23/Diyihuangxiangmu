package com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment;

import com.example.tianxiang.diyihuangxiangmu.base.BasePresenter;
import com.example.tianxiang.diyihuangxiangmu.base.BaseView;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsData;
import com.example.tianxiang.diyihuangxiangmu.entity.NewList;
import com.example.tianxiang.diyihuangxiangmu.entity.NewListTwo;

public interface TabContract {
    public  interface View extends BaseView<TabContract.Presenter>{
        void sendNewList(NewsData news);

    }
    public  interface Presenter extends BasePresenter<TabContract.View>{
        void sendNewList(String  id);

    }
}
