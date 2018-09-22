package com.example.tianxiang.diyihuangxiangmu.data.remote;

import com.example.tianxiang.diyihuangxiangmu.entity.Comment;
import com.example.tianxiang.diyihuangxiangmu.entity.CommentList;
import com.example.tianxiang.diyihuangxiangmu.entity.Data;
import com.example.tianxiang.diyihuangxiangmu.entity.NewsDetails;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.InformationtwoContract;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

/*
 * created by taofu on 2018/9/5
 **/
public class NewsDataRepository2 implements NewsDataSource {

    private static NewsDataRepository2 INSTANCE;

    private NewsDataSource mRemote;
    private NewsDataSource mLocal;


    private Map<String, NewsData> mMemoryCache;


    private NewsDataRepository2(NewsDataSource remote, NewsDataSource local) {

        mRemote = remote;
        mLocal = local;


    }


    public static synchronized NewsDataRepository2 getInstance(NewsDataSource remote, NewsDataSource local) {
        if (INSTANCE == null) {
            INSTANCE = new NewsDataRepository2(remote, local);
        }

        return INSTANCE;
    }

    @Override
    public void loadChannels(LifecycleProvider lifecycleProvider, Observer<List<Channel>> observer) {
        mRemote.loadChannels(lifecycleProvider, observer);
    }

    @Override
    public void loadNewsByChannel(LifecycleProvider lifecycleProvider, Map<String, String> params, final Observer<NewsData> observer) {


        if (mMemoryCache != null && mMemoryCache.get(params.get(InformationtwoContract.PARAMS_CHANNER_ID)) != null) {
            Observable.just(mMemoryCache.get(params.get(InformationtwoContract.PARAMS_CHANNER_ID))).subscribe(observer);
            return;
        }


        mRemote.loadNewsByChannel(lifecycleProvider, params, observer);
    }

    @Override
    public Observable<NewsData> getNewsByChannel(Map<String, String> params) {

        final String channelId = params.get(InformationtwoContract.PARAMS_CHANNER_ID);
        if (mMemoryCache != null && mMemoryCache.get(params.get(InformationtwoContract.PARAMS_CHANNER_ID)) != null) {
            return Observable.just(mMemoryCache.get(params.get(InformationtwoContract.PARAMS_CHANNER_ID)));
        }
        Observable<NewsData> remoteObservable = getAndSaveRemoteNewsData(params);

        return Observable.concat(mLocal.getNewsByChannel(params), remoteObservable);
    }

    @Override
    public void saveNews(String channelId, NewsData data) {

    }

    @Override
    public void fetchNewsDetail( Map<String, String> params,Observer<NewsDetails> observer) {
        mRemote.fetchNewsDetail(params,observer);
    }

    @Override
    public void getRelevant(Map<String, String> params, Observer<List<Data>> observer) {
        mRemote.getRelevant(params,observer);
    }

    @Override
    public void getListComment(Map<String, String> params, Observer<List<Comment>> observer) {
        mRemote.getListComment(params,observer);
    }

    @Override
    public void updateTopic(Map<String, String> params, Observer<Object> observer) {
        mRemote.updateTopic(params,observer);
    }


    private Observable<NewsData> getAndSaveRemoteNewsData(Map<String, String> params) {
        final String channelId = params.get(InformationtwoContract.PARAMS_CHANNER_ID);

        Observable<NewsData> observable = mRemote.getNewsByChannel(params);

        return observable.doOnNext(new Consumer<NewsData>() {
            @Override
            public void accept(NewsData data) throws Exception {

                saveRefreshDataToMemory(channelId, data);
                mLocal.saveNews(channelId, mMemoryCache.get(channelId).getNewsDataForSdcardCache());
            }
        });
    }


    /**
     * 保存刷新回来的数据到内存
     *
     * @param channelId
     * @param data
     */
    private void saveRefreshDataToMemory(String channelId, NewsData data) {

        if (mMemoryCache == null) {
            mMemoryCache = new HashMap<>();
        }


        NewsData newsData = mMemoryCache.get(channelId);

        if (newsData == null) {
            newsData = new NewsData();
            mMemoryCache.put(channelId, data);
        }

        if (data.getNewList().size() >= 7) {
            newsData.replace(data);
        } else {
            newsData.mergeRefreshData(data);
        }


    }

    /**
     * 保存加载更多到内存
     *
     * @param channelId
     * @param data
     */
    private void saveLoadMoreDataToMemory(String channelId, NewsData data) {
        if (mMemoryCache == null) {
            mMemoryCache = new HashMap<>();
        }

        NewsData newsData = mMemoryCache.get(channelId);

        if (newsData == null) {
            newsData = new NewsData();
            mMemoryCache.put(channelId, data);
        }

        newsData.mergeLoadMoreData(data);


    }

}
