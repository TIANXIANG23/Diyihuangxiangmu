package com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.adapter.CorrelationAdapter;
import com.example.tianxiang.diyihuangxiangmu.adapter.HotAdapter;
import com.example.tianxiang.diyihuangxiangmu.base.BaseActivity;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsRemoteDataSource2;
import com.example.tianxiang.diyihuangxiangmu.entity.Comment;
import com.example.tianxiang.diyihuangxiangmu.entity.CommentList;
import com.example.tianxiang.diyihuangxiangmu.entity.Data;
import com.example.tianxiang.diyihuangxiangmu.entity.NewsDetails;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class WebActivity extends BaseActivity implements WebContract.View, View.OnClickListener {
    public WebContract.Pesenter mPesenter;
    private ImageButton web_iv_btn_fan;
    private ImageButton web_iv_btn_dian;
    private ImageView web_chaxun;
    private TextView web_title;
    private TextView web_biao;
    private TextView web_shi;
    private ImageButton web_iv_btn_shou;
    private ImageView web_fen;
    private ImageView web_ping;
    private ImageView web_cang;
    private WebView web_webview;
    private RecyclerView web_recycler;
    private RecyclerView web_recycler1;
    private String userId="d56ea66e7ee741f498ca51242c8c6394";
    private PopupWindow popupWindow;
    private TextView et_discuss;
    private TextView tv_confirm;
    private TextView tv_close;
    private HotAdapter hotAdapter;
    private List<Comment> list=new ArrayList<>();
    private List<Comment> list1=new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_web);
        initView();
        WebPesenter webPesenter = new WebPesenter(NewsRemoteDataSource2.getInstance());
        setPresenter(webPesenter);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String newsId = bundle.getString("newsId");
        String name = bundle.getString("name");
        String shi = bundle.getString("shi");
        web_title.setText(name);
        web_biao.setText("新闻来源 俄罗斯旅游网");
        web_shi.setText(shi);
        Log.e("====------111", "onCreate: " + newsId);
        mPesenter.setRequest(newsId);
        mPesenter.setcorrelation(newsId);
        mPesenter.setCommentList(userId);
    }


    @Override
    public void webRequest(NewsDetails newsDetails) {

        String html = "<html><header>" + "</header>" + newsDetails.getContent() + "</body><ml>";
        web_webview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        /*WebSettings mSettings = web_webview.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
*/
        getNewContent(html);
    }

    @Override
    public void setcorrelation(List<Data> data) {
        CorrelationAdapter correlationAdapter = new CorrelationAdapter(this, data);
        web_recycler.setAdapter(correlationAdapter);

    }

    @Override
    public void setCommentList(List<Comment> commentList) {
    list1.addAll(commentList);
        for (int i = 0; i <10 ; i++) {
            list.add(commentList.get(i));
        }
        hotAdapter = new HotAdapter(this,list);
        web_recycler1.setAdapter(hotAdapter);
    }

    @Override
    public void setupdateTopic() {
        mPesenter.setCommentList(userId);

    }


    private String getNewContent(String htmltext) {

        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }

        Log.d("VACK", doc.toString());
        return doc.toString();
    }
    @Override
    public void setPresenter(WebContract.Pesenter presenter) {
        mPesenter = presenter;
        presenter.attachView(this);
    }

    @Override
    public Activity getActivity() {
        return null;
    }

    private void initView() {
        web_iv_btn_fan = (ImageButton) findViewById(R.id.web_iv_btn_fan);
        web_iv_btn_dian = (ImageButton) findViewById(R.id.web_iv_btn_dian);
        web_chaxun = (ImageView) findViewById(R.id.web_chaxun);
        web_title = (TextView) findViewById(R.id.web_title);
        web_biao = (TextView) findViewById(R.id.web_biao);
        web_shi = (TextView) findViewById(R.id.web_shi);
        web_fen = (ImageView) findViewById(R.id.web_fen);
        web_ping = (ImageView) findViewById(R.id.web_ping);
        web_cang = (ImageView) findViewById(R.id.web_cang);
        web_webview = (WebView) findViewById(R.id.web_webview);
        web_recycler = (RecyclerView) findViewById(R.id.web_recycler);
        web_recycler1 = (RecyclerView) findViewById(R.id.web_recycler1);
        web_recycler.setLayoutManager(new LinearLayoutManager(this));
        web_recycler1.setLayoutManager(new LinearLayoutManager(this));
        web_iv_btn_fan.setOnClickListener(this);
        web_iv_btn_dian.setOnClickListener(this);
        web_fen.setOnClickListener(this);
        web_ping.setOnClickListener(this);
        web_cang.setOnClickListener(this);
        web_chaxun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.web_iv_btn_fan:
                finish();
                break;
            case R.id.web_iv_btn_dian:

                break;
            case R.id.web_chaxun:
                list.clear();
               list.addAll(list1);
               hotAdapter.notifyDataSetChanged();
                break;
            case R.id.web_fen:

                break;
            case R.id.web_ping:
                initData();
                break;
            case R.id.web_cang:

                break;
        }
    }

    private void initData() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_ping_layout, null);
        tv_close = view.findViewById(R.id.textView13);
        tv_confirm = view.findViewById(R.id.textView17);
        et_discuss = view.findViewById(R.id.et_discuss);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
            }
        });

        popupWindow = new PopupWindow(view, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
        popupWindow.setFocusable(true);

        //设置点击外边窗口popupwindow消失
        popupWindow.setOutsideTouchable(true);
        //设置弹出窗体软键盘
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_FROM_FOCUSABLE);

        //设置模式，和Activity一样，覆盖，调整大小
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ColorDrawable drawable = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(drawable);
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
//        popupWindow.update();

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_discuss.getText().toString() != null && "".equals(et_discuss.getText().toString())){
                    Toast.makeText(WebActivity.this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(WebActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                }

                //todo        UserId设置死的  登录页面完善后修改
               mPesenter.setupdateTopic(userId,"d56ea66e7ee741f498ca51242c8c6394","0", et_discuss.getText().toString());

                popupWindow.dismiss();


            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("按下了back键   onKeyDown()");
            finish();
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
