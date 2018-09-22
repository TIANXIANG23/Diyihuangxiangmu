package com.example.tianxiang.diyihuangxiangmu.base;


import android.content.Context;

import com.example.tianxiang.diyihuangxiangmu.R;
import com.trello.rxlifecycle2.components.support.RxFragment;

public class BaseFragment extends RxFragment {
    protected BaseActivity baseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivity= (BaseActivity) context;
    }

    protected boolean isNeedAddToBackStack(){
        return true;
    }

    protected boolean isNeedHidePreFragment(){
        return true;
    }
    //enter 指定向栈中放入新的Fragment时的动画
    protected int getEnterAnimId(){
        return R.anim.actionsheet_fragment_in;
       // return R.anim.slide_right_in;
    }
    //exit 指定向栈中弹出当前栈顶的Fragment时的动画
    protected int getPreExistAnimId(){
        return R.anim.actionsheet_fragment_out;
        //return R.anim.slide_left_out;
    }
    protected int getPopPreEnterAnimId(){
         return R.anim.actionsheet_fragment_into;
       // return R.anim.slide_left_in;
    }
    //popEnter 指定由于当前栈顶Fragment弹出而显示底层的Fragment时的动画
    protected int getPopExistAnimId(){
        return R.anim.actionsheet_fragment_outto;
       // return R.anim.slide_right_out;
    }
    //popExit 指定当前栈顶的Fragment被弹出时的动画

}
