package com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo;

import com.example.tianxiang.diyihuangxiangmu.base.BaseFragment;
import com.example.tianxiang.diyihuangxiangmu.data.remote.Channel;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsDataSource;
import com.example.tianxiang.diyihuangxiangmu.data.remote.UserDataSource;
import com.example.tianxiang.diyihuangxiangmu.entity.TabBt;
import com.example.tianxiang.diyihuangxiangmu.utils.Logger;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InformationtwoPesenter implements InformationtwoContract.Presenter {
    public InformationtwoContract.View mView;

    private NewsDataSource mUserDataRepository;

    public InformationtwoPesenter(NewsDataSource UserDataRepository){
        mUserDataRepository=UserDataRepository;
    }
    @Override
    public void attachView(InformationtwoContract.View baseView) {
        mView=baseView;
    }

    @Override
    public void detachView() {
        mView=null;
    }

    @Override
    public void getTabarCode() {
        mUserDataRepository.loadChannels((BaseFragment) mView, new Observer<List<Channel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Channel> channels) {
                mView.getTabarCode(channels);
            }

            @Override
            public void onError(Throwable e) {
                Logger.e("====="+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
