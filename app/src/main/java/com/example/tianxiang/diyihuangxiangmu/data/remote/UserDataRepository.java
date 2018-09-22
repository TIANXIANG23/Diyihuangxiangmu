package com.example.tianxiang.diyihuangxiangmu.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.tianxiang.diyihuangxiangmu.entity.NewList;
import com.example.tianxiang.diyihuangxiangmu.entity.NewListTwo;
import com.example.tianxiang.diyihuangxiangmu.entity.TabBt;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.trello.rxlifecycle2.internal.Preconditions;

import java.util.Map;

import io.reactivex.Observer;

public class UserDataRepository implements UserDataSource{

    private final UserDataSource mRemoteDataSource;

    @Nullable
    private static UserDataRepository INSTANCE = null;

    private UserDataRepository(@NonNull UserDataSource remoteDataSource){
        mRemoteDataSource = Preconditions.checkNotNull(remoteDataSource, "Login remote data source is not allowed null");
    }
    public static UserDataRepository getInstance(){
        if(INSTANCE == null){
            synchronized (UserDataRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new UserDataRepository(new UserRemoteDataSource());
                }
            }
        }
        return INSTANCE;
    }
    @Override
    public void login(RxFragment fragment, Map<String, String> params, Observer<User> observer) {

        mRemoteDataSource.login(fragment, params,observer);
    }

    @Override
    public void sendVerificationCode(RxFragment fragment, Map<String, String> params, Observer<Object> observer) {

        mRemoteDataSource.sendVerificationCode(fragment, params, observer);
    }


}
