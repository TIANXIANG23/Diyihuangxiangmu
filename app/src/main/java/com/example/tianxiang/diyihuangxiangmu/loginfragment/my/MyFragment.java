package com.example.tianxiang.diyihuangxiangmu.loginfragment.my;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.base.BaseFragment;

public class MyFragment extends BaseFragment implements MyContract.View {

    public MyContract.Presenter mPresneter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,container,false);
        return view;
    }

    @Override
    public void setPresenter(MyContract.Presenter presenter) {
        mPresneter=presenter;
        presenter.attachView(this);
    }
}
