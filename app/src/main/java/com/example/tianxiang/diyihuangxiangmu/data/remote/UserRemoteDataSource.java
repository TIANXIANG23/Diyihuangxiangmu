package com.example.tianxiang.diyihuangxiangmu.data.remote;

import android.system.Os;

import com.example.tianxiang.diyihuangxiangmu.data.remote.retrofit.DataRetrofit;
import com.example.tianxiang.diyihuangxiangmu.data.remote.retrofit.FirstgaService;
import com.example.tianxiang.diyihuangxiangmu.entity.NewList;
import com.example.tianxiang.diyihuangxiangmu.entity.NewListTwo;
import com.example.tianxiang.diyihuangxiangmu.entity.TabBt;
import com.example.tianxiang.diyihuangxiangmu.exception.ServerException;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class UserRemoteDataSource implements UserDataSource {
    private FirstgaService service;


    public UserRemoteDataSource() {
        service = DataRetrofit.getRetrofitService();

    }


    @Override
    public void login(RxFragment fragment, Map<String, String> params, Observer<User> observer) {

        Observable<HttpResult<User>> observable = service.login(params);

        observable.flatMap(new Function<HttpResult<User>, ObservableSource<User>>() {
            @Override
            public ObservableSource<User> apply(HttpResult<User> userHttpResult) throws Exception {

                if (userHttpResult.getCode() == 0) {
                    return Observable.just(userHttpResult.getData());
                }
                return Observable.error(new ServerException(userHttpResult.getCode(),userHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.<User>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(observer);
    }

    @Override
    public void sendVerificationCode(RxFragment fragment, Map<String, String> params, Observer<Object> observer) {
        Observable<HttpResult<Object>> observable = service.sendVerificationCode(params);

        observable.flatMap(new Function<HttpResult<Object>, ObservableSource<Object>>() {
            @Override
            public ObservableSource<Object> apply(HttpResult<Object> userHttpResult) throws Exception {

                if (userHttpResult.getCode() == 0) {
                    return Observable.just(new Object());
                }
                return Observable.error(new ServerException(userHttpResult.getCode(),userHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.<Object>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(observer);

    }

}
