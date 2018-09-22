package com.example.tianxiang.diyihuangxiangmu.data.remote;

import android.content.Context;

import com.example.tianxiang.diyihuangxiangmu.entity.Comment;
import com.example.tianxiang.diyihuangxiangmu.entity.CommentList;
import com.example.tianxiang.diyihuangxiangmu.entity.Data;
import com.example.tianxiang.diyihuangxiangmu.entity.NewsDetails;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.InformationtwoContract;
import com.example.tianxiang.diyihuangxiangmu.utils.FilesUtils;
import com.example.tianxiang.diyihuangxiangmu.utils.SystemFacade;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

/*
 * created by taofu on 2018/9/13
 **/
public class NewsLocalDataSource2 implements NewsDataSource {


    private static NewsLocalDataSource2 INSTANCE;

    private File mDataCacheFileDir;
    private Context context;

    private NewsLocalDataSource2(Context context) {
        File cacheDir = SystemFacade.getExternalCacheDir(context);
        if (cacheDir != null && cacheDir.exists()) {
            mDataCacheFileDir = new File(cacheDir, "news_files");
        }
    }


    public static synchronized NewsLocalDataSource2 getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new NewsLocalDataSource2(context);
        }

        return INSTANCE;
    }
    @Override
    public void loadChannels(LifecycleProvider lifecycleProvider, Observer<List<Channel>> observer) {
    }

    @Override
    public void loadNewsByChannel(LifecycleProvider lifecycleProvider, Map<String, String> params, Observer<NewsData> observer) {

    }

    @Override
    public Observable<NewsData> getNewsByChannel(Map<String, String> params) {
        final String channelId = params.get(InformationtwoContract.PARAMS_CHANNER_ID);
       return Observable.create(new ObservableOnSubscribe<NewsData>() {
            @Override
            public void subscribe(ObservableEmitter<NewsData> emitter) throws Exception {

                NewsData newsData = FilesUtils.getNewsFromFile(createCacheFile(channelId));

                if(newsData != null){
                    emitter.onNext(newsData);
                }
                emitter.onComplete();
            }
        });



    }

    @Override
    public void saveNews(String channelId, NewsData data) {
        FilesUtils.writeNewsDataToFile(data, createCacheFile(channelId));
    }

    @Override
    public void fetchNewsDetail(Map<String, String> params,Observer<NewsDetails> observer) {

    }

    @Override
    public void getRelevant(Map<String, String> params, Observer<List<Data>> observer) {

    }

    @Override
    public void getListComment(Map<String, String> params, Observer<List<Comment>> observer) {

    }

    @Override
    public void updateTopic(Map<String, String> params, Observer<Object> observer) {

    }


    private File createCacheFile(String channelId) {
        if (mDataCacheFileDir != null) {
            if(!mDataCacheFileDir.exists()){
                mDataCacheFileDir.mkdirs();
            }
            return new File(mDataCacheFileDir, channelId);
        }

        return null;
    }
}
