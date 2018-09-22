package com.example.tianxiang.diyihuangxiangmu.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.tianxiang.diyihuangxiangmu.data.remote.Channel;
import com.example.tianxiang.diyihuangxiangmu.entity.TabBt;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment.TabFragment;

import java.util.List;

public class InformationFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Channel> mChannelList;

    public InformationFragmentAdapter(FragmentManager fm, List<Channel> mChannelList) {
        super(fm);
        this.mChannelList = mChannelList;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        int itemPosition = super.getItemPosition(object);
        return itemPosition;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);

    }
    @Override
    public Fragment getItem(int position) {
        TabFragment tabFragment=new TabFragment();
        Bundle bundle=new Bundle();
        bundle.putString("key",mChannelList.get(position).getChannelId());
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public int getCount() {
        return mChannelList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mChannelList.get(position).getChannelName();
    }
}
