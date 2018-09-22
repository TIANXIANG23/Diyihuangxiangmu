package com.example.tianxiang.diyihuangxiangmu.loginfragment.dilogin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.base.BaseFragment;

public class LoginDiFragment extends BaseFragment implements LoginDiContract.View, View.OnClickListener {
    public LoginDiContract.Presenter mPresenter;
    private TextView register_di_qu;
    private ImageView register_di_touxiang;
    private TextView register_di_text;
    private Button register_di_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dilogin, container, false);
        initView(view);
        return view;
    }

    @Override
    public void setPresenter(LoginDiContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    private void initView(View view) {
        register_di_qu = (TextView) view.findViewById(R.id.register_di_qu);
        register_di_touxiang = (ImageView) view.findViewById(R.id.register_di_touxiang);
        register_di_text = (TextView) view.findViewById(R.id.register_di_text);
        register_di_btn = (Button) view.findViewById(R.id.register_di_btn);

        register_di_btn.setOnClickListener(this);
        register_di_qu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_di_btn:

                break;
            case R.id.register_di_qu:
                    getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }
}
