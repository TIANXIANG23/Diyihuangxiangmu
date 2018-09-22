package com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.adapter.InformationFragmentAdapter;
import com.example.tianxiang.diyihuangxiangmu.base.BaseFragment;
import com.example.tianxiang.diyihuangxiangmu.data.remote.Channel;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsDataRepository2;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsLocalDataSource2;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsRemoteDataSource2;
import com.example.tianxiang.diyihuangxiangmu.entity.TabBt;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

public class InformationtwoFragment extends BaseFragment implements InformationtwoContract.View {
    public InformationtwoContract.Presenter mPresener;
    private TabLayout tabLayout;
    private ViewPager informationtwo_viewpager;
    public List<String> list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infrormationtwo, container, false);
        initView(view);
        InformationtwoPesenter informationtwoPesenter=new InformationtwoPesenter(NewsDataRepository2.getInstance(NewsRemoteDataSource2.getInstance(),NewsLocalDataSource2.getInstance(getActivity())));
        setPresenter(informationtwoPesenter);
        mPresener.getTabarCode();
        return view;
    }

    @Override
    public void setPresenter(InformationtwoContract.Presenter presenter) {
        mPresener = presenter;
        presenter.attachView(this);
    }

    private void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        informationtwo_viewpager = (ViewPager) view.findViewById(R.id.informationtwo_viewpager);
    }

    @Override
    public void getTabarCode(List<Channel> channels) {
        InformationFragmentAdapter informationFragmentAdapter=new InformationFragmentAdapter(getChildFragmentManager(), channels);
        tabLayout.setupWithViewPager(informationtwo_viewpager);
        informationtwo_viewpager.setAdapter(informationFragmentAdapter);
    }

}
