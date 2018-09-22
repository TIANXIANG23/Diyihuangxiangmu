package com.example.tianxiang.diyihuangxiangmu.loginfragment.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.base.BaseFragment;

public class CircleFragment extends BaseFragment implements CircleContract.View {

    public CircleContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_circle,container,false);
        return view;
    }

    @Override
    public void setPresenter(CircleContract.Presenter presenter) {
        mPresenter=presenter;
        presenter.attachView(this);
    }
}
