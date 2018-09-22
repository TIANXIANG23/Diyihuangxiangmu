package com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo;


import com.example.tianxiang.diyihuangxiangmu.base.BasePresenter;
import com.example.tianxiang.diyihuangxiangmu.base.BaseView;
import com.example.tianxiang.diyihuangxiangmu.data.remote.Channel;
import com.example.tianxiang.diyihuangxiangmu.entity.NewList;
import com.example.tianxiang.diyihuangxiangmu.entity.TabBt;

import java.util.List;

public interface InformationtwoContract {
    public static final String PARAMS_CHANNER_ID = "channelId";

    public interface View extends BaseView<InformationtwoContract.Presenter>{
        void getTabarCode(List<Channel> channels);
    }
    public  interface Presenter extends BasePresenter<InformationtwoContract.View>{
        void getTabarCode();
    }
}
