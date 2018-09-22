package com.example.tianxiang.diyihuangxiangmu.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.tianxiang.diyihuangxiangmu.R;

import java.util.List;

public abstract class   BaseActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //支持fragment的管理
        mFragmentManager = getSupportFragmentManager();
    }

    public<T extends BaseFragment,E extends BasePresenter> void addFragment(@NonNull Class<T> tClass,E presenter,int containerId,String tag,Bundle args){
       if (TextUtils.isEmpty(tag)){
           tag=tClass.getName();
       }
       //fragment开启一个事物
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment addedFragment = mFragmentManager.findFragmentByTag(tag);
         BaseFragment targetFragment=null;
        if (addedFragment==null){
            try {
                targetFragment = tClass.newInstance();
                targetFragment.setArguments(args);
                if (targetFragment instanceof BaseView){
                    ((BaseView) targetFragment).setPresenter(presenter);
                }
                addFragmentAnimation(fragmentTransaction, targetFragment);
                fragmentTransaction.add(containerId,targetFragment,tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            targetFragment=(BaseFragment) addedFragment;
            if (targetFragment.isHidden()){
                addFragmentAnimation(fragmentTransaction, targetFragment);
            }
            fragmentTransaction.show(targetFragment);
        }

        if (targetFragment !=null){
            addFragmentAnimation(fragmentTransaction,targetFragment);
            hidePreFragment(fragmentTransaction,  targetFragment);
            if (targetFragment.isNeedAddToBackStack()){
                fragmentTransaction.addToBackStack(null);
            }
            fragmentTransaction.commit();
        }
    }

    private void hidePreFragment(FragmentTransaction fragmentTransaction, BaseFragment baseFragment){
       if (baseFragment.isNeedHidePreFragment()){
           List<Fragment> fragments = mFragmentManager.getFragments();

           for (Fragment fragment:fragments){
               if (fragment!=baseFragment){
                   fragmentTransaction.hide(fragment);
               }
           }

       }
    }

    private  void addFragmentAnimation(FragmentTransaction fragmentTransaction, BaseFragment baseFragment){
        fragmentTransaction.setCustomAnimations(baseFragment.getEnterAnimId(), baseFragment.getPreExistAnimId() ,baseFragment.getPopPreEnterAnimId(),baseFragment.getPopExistAnimId());
    }
}
