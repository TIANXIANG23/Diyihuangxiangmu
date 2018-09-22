package com.example.tianxiang.diyihuangxiangmu.base;

public interface BasePresenter<T extends BaseView> {
   void attachView(T baseView);

   void detachView();
}
