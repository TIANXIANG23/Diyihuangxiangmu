package com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment;

import android.util.Log;

import com.example.tianxiang.diyihuangxiangmu.base.MyObserver;
import com.example.tianxiang.diyihuangxiangmu.data.remote.Channel;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsData;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsDataSource;
import com.example.tianxiang.diyihuangxiangmu.data.remote.UserDataSource;
import com.example.tianxiang.diyihuangxiangmu.entity.NewList;
import com.example.tianxiang.diyihuangxiangmu.entity.NewListTwo;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.InformationtwoContract;
import com.example.tianxiang.diyihuangxiangmu.utils.Logger;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class TabPresenter implements TabContract.Presenter {
    private TabContract.View mView;

    private NewsDataSource mUserDataRepository;

    public TabPresenter(NewsDataSource UserDataRepository){
        mUserDataRepository=UserDataRepository;
    }
    @Override
    public void attachView(TabContract.View baseView) {
        mView=baseView;
    }

    @Override
    public void detachView() {
        mView=null;
    }

    @Override
    public void sendNewList(String id) {
        Map<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("userId", "d56ea66e7ee741f498ca51242c8c6394");
        objectObjectHashMap.put(InformationtwoContract.PARAMS_CHANNER_ID, id);
        objectObjectHashMap.put("cursor", "0");
        mUserDataRepository.getNewsByChannel(objectObjectHashMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose((mView instanceof RxAppCompatActivity) ? (((RxAppCompatActivity) mView).<NewsData>bindUntilEvent(ActivityEvent.DESTROY)) : (((RxFragment) mView).<NewsData>bindUntilEvent(FragmentEvent.DETACH)))
                .subscribe(new MyObserver<NewsData>() {
                    @Override
                    public void onNext(NewsData data) {
                        mView.sendNewList(data);
                        Log.e("TAG", "onNext: "+data.toString() );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("========111",e.getMessage() );
                    }
                });

    }
}
