package com.example.tianxiang.diyihuangxiangmu.loginfragment.register;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.base.BaseFragment;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.information.InformationActivity;
import com.example.tianxiang.diyihuangxiangmu.utils.EditImageView;

public class RegisterFragment extends BaseFragment implements RegisterContract.View, View.OnClickListener {
    private RegisterContract.Presenter mPresenter;
    private ImageButton register_iv_btn_fan;
    private ImageButton register_iv_btn_close;
    private ImageView register_iv_btn_touxiang;
    private TextView register_iv_btn_tiaoguo;

    private Group mGroupEditPhoto;
    private Group mGroupRegister;

    private FrameLayout mEditImageViewLayout;

    private Button mEditOk;
    private Button mEditCancle;
    private EditImageView mEditImageView;
    private Button register_iv_btn_wancheng;
    private EditText register_etv_phone_number;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        initView(view);
        return view;

    }

    private void initView(View view) {
        register_iv_btn_fan = (ImageButton) view.findViewById(R.id.register_iv_btn_fan);
        register_iv_btn_close = (ImageButton) view.findViewById(R.id.register_iv_btn_close);
        register_iv_btn_touxiang = (ImageView) view.findViewById(R.id.register_iv_btn_touxiang);
        register_iv_btn_tiaoguo = (TextView) view.findViewById(R.id.register_iv_btn_tiaoguo);
        register_iv_btn_wancheng = view.findViewById(R.id.resgister_iv_btn_wancheng);
        register_etv_phone_number=view.findViewById(R.id.register_etv_phone_number);
        register_etv_phone_number.setInputType(InputType.TYPE_NULL);
        mGroupEditPhoto = view.findViewById(R.id.group);
        mGroupRegister = view.findViewById(R.id.group2);


        mEditImageViewLayout = view.findViewById(R.id.login_register_edite_pic);
        mGroupRegister.setVisibility(View.VISIBLE);
        mGroupEditPhoto.setVisibility(View.GONE);

        mEditOk = view.findViewById(R.id.login_register_btn_edite_pic_ok);
        mEditCancle = view.findViewById(R.id.login_register_btn_edite_pic_cancle);

        register_iv_btn_fan.setOnClickListener(this);

        register_iv_btn_close.setOnClickListener(this);
        register_iv_btn_touxiang.setOnClickListener(this);
        register_iv_btn_tiaoguo.setOnClickListener(this);
        register_iv_btn_wancheng.setOnClickListener(this);
        register_etv_phone_number.setOnClickListener(this);
        mEditOk.setOnClickListener(this);
        mEditCancle.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //注册页面返回键
            case R.id.register_iv_btn_fan:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
                //注册页面×键
            case R.id.register_iv_btn_close:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
                //注册页面上传头像
            case R.id.register_iv_btn_touxiang:
                mPresenter.showDialog();
                Toast.makeText(getActivity(),"成功了",Toast.LENGTH_SHORT).show();
                break;
                //注册页面点击输入框弹出pop
            case R.id.register_etv_phone_number:
                mPresenter.showPopupWindow();
                break;
                //注册页面跳过
            case R.id.register_iv_btn_tiaoguo:
                Intent intent=new Intent(getContext(), InformationActivity.class);
                getActivity().startActivity(intent);
                break;
                //拍照剪切里的确定键
            case R.id.login_register_btn_edite_pic_ok:
                mPresenter.saveBitmap(mEditImageView.getCircleBitmap());
                break;
                //拍照剪切里的取消键
            case R.id.login_register_btn_edite_pic_cancle:
                mGroupEditPhoto.setVisibility(View.INVISIBLE);
                mGroupRegister.setVisibility(View.VISIBLE);
                mGroupEditPhoto.requestLayout();

                break;
                //注册里的完成按钮
            case R.id.resgister_iv_btn_wancheng:
                Toast.makeText(getActivity(),"成功了",Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getContext(), InformationActivity.class);
               getActivity().startActivity(intent1);
                break;
        }
    }



//头像获取成功的回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onTakePhotoSuccess(String filePath) {
        mGroupEditPhoto.setVisibility(View.VISIBLE);
        mGroupRegister.setVisibility(View.INVISIBLE);
        mEditImageView = new EditImageView(getActivity(), filePath);
        mEditImageViewLayout.addView(mEditImageView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onTakePhotoFail(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveBitmapSuccess(String filePath) {
        mGroupEditPhoto.setVisibility(View.INVISIBLE);
        mGroupRegister.setVisibility(View.VISIBLE);
        Glide.with(getActivity()).load(filePath).into(register_iv_btn_touxiang);
    }

    @Override
    public void onSaveBitmapFail(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }





    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }
}
