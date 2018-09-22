package com.example.tianxiang.diyihuangxiangmu.data.remote;

import com.example.tianxiang.diyihuangxiangmu.entity.NewList;
import com.example.tianxiang.diyihuangxiangmu.entity.NewListTwo;
import com.example.tianxiang.diyihuangxiangmu.entity.TabBt;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.Map;

import io.reactivex.Observer;

public interface UserDataSource {
    void login(RxFragment fragment, Map<String, String> params, Observer<User> observer);

    void sendVerificationCode(RxFragment fragment, Map<String, String> params, Observer<Object> observer);

}
