package com.example.tianxiang.diyihuangxiangmu.base;

import android.app.Activity;

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);

    Activity getActivity();
}
