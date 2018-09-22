package com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment.webview;

import android.util.Log;

import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsDataSource;
import com.example.tianxiang.diyihuangxiangmu.entity.Comment;
import com.example.tianxiang.diyihuangxiangmu.entity.CommentList;
import com.example.tianxiang.diyihuangxiangmu.entity.Data;
import com.example.tianxiang.diyihuangxiangmu.entity.NewsDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WebPesenter  implements WebContract.Pesenter {
    public WebContract.View mView;
    private NewsDataSource mNewsDataSource;

    public WebPesenter(NewsDataSource UserDataRepository){
        mNewsDataSource=UserDataRepository;
    }
    @Override
    public void attachView(WebContract.View baseView) {
        mView=baseView;
    }

    @Override
    public void detachView() {
        mView=null;
    }

    @Override
    public void setRequest(String id) {
        final Map<String,String> map=new HashMap<>();
        map.put("userId","");
        map.put("newsId",id);
        mNewsDataSource.fetchNewsDetail(map, new Observer<NewsDetails>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewsDetails newsDetails) {
                mView.webRequest(newsDetails);

                Log.e("onNext", "onNext: "+newsDetails.toString() );
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError", "onError: "+e.getMessage() );
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setcorrelation(String id) {
        Map<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("newsId",id);
        mNewsDataSource.getRelevant(objectObjectHashMap, new Observer<List<Data>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Data> data) {
                mView.setcorrelation(data);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setCommentList(final String commentList) {
        final Map<String,String> map=new HashMap<>();
        map.put("userId",commentList);
        mNewsDataSource.getListComment(map, new Observer<List<Comment>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Comment> commentlists) {
            mView.setCommentList(commentlists);
                Log.e("======kkkkkk", "onNext: "+commentList.toString() );
            }

            @Override
            public void onError(Throwable e) {
                Log.e("====00000", "onError: "+e.getMessage() );
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setupdateTopic(String userId, String object, String objectType, String content) {
        Map<String,String> map=new HashMap<>();
        map.put("userId",userId);
        map.put("objectId",object);
        map.put("objectType",objectType);
        map.put("content",content);
        mNewsDataSource.updateTopic(map, new Observer<Object>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                mView.setupdateTopic();
            }

            @Override
            public void onSubscribe(Disposable d) {

            }
        });
    }


}
