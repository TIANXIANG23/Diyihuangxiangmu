package com.example.tianxiang.diyihuangxiangmu.loginfragment.register;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.base.MyObserver;
import com.example.tianxiang.diyihuangxiangmu.utils.Logger;
import com.example.tianxiang.diyihuangxiangmu.utils.SystemFacade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter implements RegisterContract.Presenter  {
    private static final String TAG = "RegisterPresenter";

    private static final int CAMERA_REQUEST_CODE = 0x100;
    private static final int GALLERY_REQUEST_CODE = 0x200;
    private Dialog dialog;
    private Button btn_take_photo;
    private Button btn_pick_photo;

    private Button btn_cancel;
    private RegisterContract.View mView;

    private String mImageFilePath;
    private TextView register_text;
    private StringBuffer id;
    private EditText register_etv_phone_number;
    private ImageView register_iv_btn_touxiang;

    @Override
    public void attachView(RegisterContract.View baseView) {
    mView=baseView;
    }

    @Override
    public void detachView() {
     mView=null;
    }


    @Override
    public void showPopupWindow() {
        //创建一个pop实例化对象
        final PopupWindow popupWindow = new PopupWindow();
       //设置pop的view视图
        View  inflate=View.inflate(mView.getActivity(),R.layout.pop_layout,null);
        popupWindow.setContentView(inflate);
       //给pop设置宽和高
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置pop外面点击消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());


        popupWindow.showAtLocation(mView.getActivity().findViewById(R.id.resgister_iv_btn_wancheng),Gravity.CENTER,0,0);
        //初始化控件
        register_text = inflate.findViewById(R.id.register_text);
        Button register_shi=inflate.findViewById(R.id.register_shi);
        Button register_xiu=inflate.findViewById(R.id.register_xiu);
        register_etv_phone_number = mView.getActivity().findViewById(R.id.register_etv_phone_number);
        showrandomNumber();
        register_shi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            register_etv_phone_number.setText("user"+id);
            popupWindow.dismiss();
            }
        });
        register_xiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            register_etv_phone_number.setText("");
            popupWindow.dismiss();
            }
        });
    }
    //设置随机数
    private void showrandomNumber() {
        id = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            char s = 0;
            int j=random.nextInt(3) % 4;
            switch (j) {
                case 0:
                    //随机生成数字
                    s = (char) (random.nextInt(57) % (57 - 48 + 1) + 48);
                    break;
                case 1:
                    //随机生成大写字母
                    s = (char) (random.nextInt(90) % (90 - 65 + 1) + 65);
                    break;
                case 2:
                    //随机生成小写字母
                    s = (char) (random.nextInt(122) % (122 - 97 + 1) + 97);
                    break;
            }
            id.append(s);
        }
        register_text.setText("user"+id);
    }

    @Override
    public void showDialog() {
        dialog = new Dialog(mView.getActivity(), R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(mView.getActivity()).inflate(R.layout.popup, null);
        //初始化控件
        btn_take_photo = (Button) inflate.findViewById(R.id.btn_take_photo);
        btn_pick_photo = (Button) inflate.findViewById(R.id.btn_pick_photo);
        btn_cancel = (Button) inflate.findViewById(R.id.btn_cancel);

        btn_pick_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhotoFromGallery();
            }
        });
        btn_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhotoFromCamera();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
/*        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);*/
        params.width = WindowManager.LayoutParams.MATCH_PARENT;//如果不设置,可能部分机型出现左右有空隙,也就是产生margin的感觉
        //设置窗口高度为包裹内容
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.dimAmount = 0.5f;//设置对话框的透明程度背景(非布局的透明度)
        dialogWindow.setAttributes(params);
        dialog.show();//显示对话框
    }

    @Override
    public void takePhotoFromCamera() {
        dialog.cancel();
        checkIfNeedRequestPermission();
    }

    @Override
    public void getPhotoFromGallery() {
        dialog.cancel();
        //调用相册
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        ((Fragment) mView).startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }



    @Override
    public void saveBitmap(Bitmap bitmap) {
        Observable.just(bitmap).flatMap(new Function<Bitmap, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Bitmap bitmap) throws Exception {
                File cacheDir = SystemFacade.getExternalCacheDir(mView.getActivity());
                if (cacheDir == null) {
                    return Observable.error(new FileNotFoundException( " 手机内部存储不可用"));
                }
                File avatar = new File(cacheDir, System.currentTimeMillis() + "circle_head.jpeg");
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(avatar);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    return Observable.just(avatar.getAbsolutePath());
                } catch (Exception e){
                    return Observable.error(e);
                }finally{
                    if(outputStream != null){
                        outputStream.close();
                    }
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<String>(){
                    @Override
                    public void onNext(String s) {
                        mView.onSaveBitmapSuccess(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onSaveBitmapFail(e.getMessage());
                    }
                });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Logger.d("%s request permission call back,requestCode = %s", TAG, requestCode);

        if (Logger.DEBUG_D) {
            for (int i = 0; i < permissions.length; i++) {
                Logger.d("      %s %s = %s", TAG, permissions[i], grantResults[i]);
            }
        }

        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(mView.getActivity(), Manifest.permission.CAMERA)) {
                        showRequestPermissionAlertDialog(true);
                    } else {
                        showRequestPermissionAlertDialog(false);
                    }

                }
            }
        }
    }
    private void checkIfNeedRequestPermission() {
        if (!SystemFacade.hasPermission(mView.getActivity(), Manifest.permission.CAMERA)) {
            Logger.d("%s App  has no camera permission ,so need to request permission ", TAG);
            requestPermission();
        } else {
            Logger.d("%s has camera permission ", TAG);
            takePhoto();
        }
    }

    private void requestPermission() {
        ((Fragment) mView).requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
    }

    private void showRequestPermissionAlertDialog(final boolean flag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mView.getActivity());

        builder.setTitle(mView.getActivity().getString(R.string.login_register_request_permission_dialog_title));
        if(flag){
            builder.setMessage(mView.getActivity().getString(R.string.login_register_request_permission_dialog_description));
        }else{
            builder.setMessage(mView.getActivity().getString(R.string.login_register_request_permission_dialog_description_2));
        }

        builder.setPositiveButton(R.string.login_register_request_permission_dialog_request_permission, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if(flag){
                    requestPermission(); // 再次请求用户授权
                }else{
                    startSystemPermissionActivity(); // 打开app 详细设置界面
                }
            }

            private void startSystemPermissionActivity() {
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.fromParts("package", mView.getActivity().getPackageName(), null));
                mView.getActivity().startActivity(intent);

            }
        });

        builder.setNegativeButton(R.string.login_register_request_permission_dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void takePhoto() {
        File cacheDir = SystemFacade.getExternalCacheDir(mView.getActivity());
        if (cacheDir == null) {
            mView.onTakePhotoFail("手机存储空间有异常不能进行拍照");
            return;
        }


        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File head_pic = new File(cacheDir, System.currentTimeMillis() + "_head.jpeg");
        Uri photoUri;

        mImageFilePath = head_pic.getAbsolutePath();
        if (SystemFacade.hasN()) {
            photoUri = FileProvider.getUriForFile(mView.getActivity(), mView.getActivity().getPackageName() + ".fileProvider", head_pic);
        } else {
            photoUri = Uri.fromFile(head_pic);
        }

        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

        ((Fragment) mView).startActivityForResult(takePhotoIntent, CAMERA_REQUEST_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                File image = new File(mImageFilePath);
                if(image.exists()){
                    mView.onTakePhotoSuccess(mImageFilePath);
                }else{
                    mView.onTakePhotoFail("未知异常");
                }

            }
            break;
            case GALLERY_REQUEST_CODE:{
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                Cursor c = mView.getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePathColumns[0]);
                String imagePath = c.getString(columnIndex);
               showView(imagePath);
                c.close();
            }
            break;
        }
    }
    public void showView(String imagePath) {
        register_iv_btn_touxiang = mView.getActivity().findViewById(R.id.register_iv_btn_touxiang);
        Glide.with(mView.getActivity()).load(imagePath).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(register_iv_btn_touxiang);
        Bitmap bm = BitmapFactory.decodeFile(imagePath);
        register_iv_btn_touxiang.setImageBitmap(bm);
        dialog.cancel();
     }
}
