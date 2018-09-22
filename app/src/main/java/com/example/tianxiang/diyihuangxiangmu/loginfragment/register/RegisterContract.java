package com.example.tianxiang.diyihuangxiangmu.loginfragment.register;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.tianxiang.diyihuangxiangmu.base.BasePresenter;
import com.example.tianxiang.diyihuangxiangmu.base.BaseView;

public interface RegisterContract {
    public interface View extends BaseView<RegisterContract.Presenter>{

        void onTakePhotoSuccess(String filePath);//拍照后大图的 filepath

        void onTakePhotoFail(String msg);//拍照保存图片到指定路径失败

        void onSaveBitmapSuccess(String filePath);//保存剪切后的原图filepath

        void onSaveBitmapFail(String msg);//保存剪切图片失败

    }
    public interface Presenter extends BasePresenter<RegisterContract.View>{


        void showPopupWindow();//实现pop对话框

        void showDialog();//实现dialog从底部显示

        void takePhotoFromCamera();//通过相机拍照

        void getPhotoFromGallery();//通过相册获取照片

        void saveBitmap(Bitmap bitmap);//保存剪切后的圆形bimap

        void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
        void onActivityResult(int requestCode, int resultCode, Intent data);

    }
}
