package com.example.tianxiang.diyihuangxiangmu.loginfragment.information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.adapter.FragmentAdapter;
import com.example.tianxiang.diyihuangxiangmu.base.BaseActivity;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.circle.CircleFragment;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.InformationtwoFragment;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.my.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends BaseActivity {
    private ViewPager mPager;
    private RadioGroup mGroup;
    private RadioButton rbChat, rbContacts, rbDiscovery;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_information);
        initView();
        initData();
    }
    private void initView() {
        mPager = (ViewPager) findViewById(R.id.viewPager);
        mGroup = (RadioGroup) findViewById(R.id.radiogroup);
        rbChat = (RadioButton) findViewById(R.id.rb_chat);
        rbContacts = (RadioButton) findViewById(R.id.rb_contacts);
        rbDiscovery = (RadioButton) findViewById(R.id.rb_discovery);
        //RadioGroup选中状态改变监听
        mGroup.setOnCheckedChangeListener(new InformationActivity.myCheckChangeListener());

    }


    private void initData() {
        InformationtwoFragment informationtwoFragment=new InformationtwoFragment();
        CircleFragment circleFragment=new CircleFragment();
        MyFragment myFragment=new MyFragment();
        fragments = new ArrayList<>();
        fragments.add(informationtwoFragment);
        fragments.add(circleFragment);
        fragments.add(myFragment);

        FragmentAdapter fragmentAdapter=new FragmentAdapter(this.getSupportFragmentManager(),fragments);
        mPager.setAdapter(fragmentAdapter);
        mPager.setCurrentItem(0);
        //ViewPager页面切换监听
        mPager.setOnPageChangeListener(new InformationActivity.myOnPageChangeListener());

    }


    private void initView(View view) {
        mPager = (ViewPager) view.findViewById(R.id.viewPager);
        mGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        rbChat = (RadioButton) view.findViewById(R.id.rb_chat);
        rbContacts = (RadioButton) view.findViewById(R.id.rb_contacts);
        rbDiscovery = (RadioButton) view.findViewById(R.id.rb_discovery);
        //RadioGroup选中状态改变监听
        mGroup.setOnCheckedChangeListener(new InformationActivity.myCheckChangeListener());

    }


    private class myCheckChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_chat:
                    //ViewPager显示第一个Fragment且关闭页面切换动画效果
                    mPager.setCurrentItem(0, false);
                    break;
                case R.id.rb_contacts:
                    mPager.setCurrentItem(1, false);
                    break;
                case R.id.rb_discovery:
                    mPager.setCurrentItem(2, false);
                    break;
            }
        }
    }

    /**
     * ViewPager切换Fragment,RadioGroup做相应变化
     */
    private class myOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mGroup.check(R.id.rb_chat);
                    break;
                case 1:
                    mGroup.check(R.id.rb_contacts);
                    break;
                case 2:
                    mGroup.check(R.id.rb_discovery);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    }
}
