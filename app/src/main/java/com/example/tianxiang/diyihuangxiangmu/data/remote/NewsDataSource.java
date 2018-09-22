package com.example.tianxiang.diyihuangxiangmu.data.remote;

import com.example.tianxiang.diyihuangxiangmu.entity.Comment;
import com.example.tianxiang.diyihuangxiangmu.entity.Data;
import com.example.tianxiang.diyihuangxiangmu.entity.NewsDetails;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

public interface NewsDataSource {
    void loadChannels(LifecycleProvider lifecycleProvider, Observer<List<Channel>> observer);

    void loadNewsByChannel(LifecycleProvider lifecycleProvider, Map<String,String> params, Observer<NewsData> observer);

    Observable<NewsData> getNewsByChannel(Map<String,String> params);

    void saveNews(String channelId, NewsData data);

    void fetchNewsDetail(Map<String,String> params,Observer<NewsDetails> observer);

    void getRelevant(Map<String,String> params,Observer<List<Data>> observer);

    void getListComment(Map<String,String> params,Observer<List<Comment>> observer);

    void updateTopic(Map<String,String> params,Observer<Object> observer);
}
