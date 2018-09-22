package com.example.tianxiang.diyihuangxiangmu.loginfragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.base.BaseFragment;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.dilogin.LoginDiFragment;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.dilogin.LoginDiPresenter;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.register.RegisterFragment;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.register.RegisterPresenter;
import com.gyf.barlibrary.ImmersionBar;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class LoginFragment extends BaseFragment implements LoginContract.View {
    private LoginContract.Presenter mPresenter;
    private TextView mTvSendCode;
    private EditText mEtvPhoneNumber;
    private EditText mEtvVerificationCode;
    private CheckBox mCbLisence;
    private RegisterPresenter registerPresenter;
    private Button mBtnLoggin;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login ,container,false );

        mTvSendCode = view.findViewById(R.id.login_btn_send_verification_code);

        mEtvPhoneNumber = view.findViewById(R.id.login_etv_phone_number);
        mEtvVerificationCode = view.findViewById(R.id.login_etv_verification_code);

        mCbLisence = view.findViewById(R.id.login_cb_license);


        mBtnLoggin = view.findViewById(R.id.login_btn_login);

        imageView = view.findViewById(R.id.imageView2);

        //initData();
        mCbLisence.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mBtnLoggin.setEnabled(isChecked);
            }
        });
        mTvSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumer = mEtvPhoneNumber.getText().toString();
                //todo 需要手机号校验
                mPresenter.getVerificationCode(phoneNumer);

            }
        });

        mBtnLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = mEtvPhoneNumber.getText().toString();
                String code = mEtvVerificationCode.getText().toString();
                registerPresenter = new RegisterPresenter();
                baseActivity.addFragment(RegisterFragment.class,registerPresenter,R.id.login_container,null,null);
//                mPresenter.login(phoneNumber,code);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareConfig config = new UMShareConfig();
                config.isNeedAuthOnGetUserInfo(true);
                UMShareAPI.get(getContext()).setShareConfig(config);
                UMShareAPI.get(getContext()).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, authListener);

            }
        });
        return view;

    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
                    * @param platform 平台名称
                    */
            @Override
            public void onStart(SHARE_MEDIA platform) {

            }

            /**
             * @desc 授权成功的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param data 用户资料返回
             */
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                Toast.makeText(getContext(), "成功了", Toast.LENGTH_SHORT).show();
                LoginDiPresenter loginDiPresenter=new LoginDiPresenter();
                baseActivity.addFragment(LoginDiFragment.class,loginDiPresenter,R.id.login_container,null,null);
            /*String name = data.get("name");
            String iconurl = data.get("iconurl");*/



        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(getContext(), "失败：" + t.getMessage(),Toast.LENGTH_SHORT).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getContext(), "取消了", Toast.LENGTH_SHORT).show();

        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void verificationCodeSuccess() {
    mTvSendCode.setText(R.string.login_verification_code_send_ok);
    }

    @Override
    public void verificationCodeFail() {
        mTvSendCode.setText(R.string.login_verification_code_send_fail);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT);
        /*getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_right_in,
                        R.anim.slide_left_out,
                        R.anim.slide_left_in,
                        R.anim.slide_right_out
                ).replace(R.id.login_container,registerFragment)
                .addToBackStack(null)
                .commit();*/
        registerPresenter = new RegisterPresenter();
        baseActivity.addFragment(RegisterFragment.class,registerPresenter,R.id.login_container,null,null);
    }

    @Override
    public void loginFail(String msg) {
        Toast.makeText(getContext(), "登录失败" +msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

        mPresenter = presenter;

        mPresenter.attachView(this);
    }
}
