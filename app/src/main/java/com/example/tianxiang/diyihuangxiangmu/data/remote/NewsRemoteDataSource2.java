package com.example.tianxiang.diyihuangxiangmu.data.remote;

import com.example.tianxiang.diyihuangxiangmu.data.remote.retrofit.DataRetrofit;
import com.example.tianxiang.diyihuangxiangmu.data.remote.retrofit.FirstgaService;
import com.example.tianxiang.diyihuangxiangmu.entity.Comment;
import com.example.tianxiang.diyihuangxiangmu.entity.CommentList;
import com.example.tianxiang.diyihuangxiangmu.entity.Data;
import com.example.tianxiang.diyihuangxiangmu.entity.NewsDetails;
import com.example.tianxiang.diyihuangxiangmu.exception.ServerException;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/*
 * created by taofu on 2018/9/13
 **/
public class NewsRemoteDataSource2 implements NewsDataSource {

    private FirstgaService service;

    private static NewsRemoteDataSource2 INSTANCE;

    private NewsRemoteDataSource2() {
        service = DataRetrofit.getRetrofitService();

    }


    public static synchronized NewsRemoteDataSource2 getInstance(){
        if(INSTANCE == null){
            INSTANCE = new NewsRemoteDataSource2();
        }
        return INSTANCE;
    }
    @Override
    public void loadChannels(LifecycleProvider lifecycleProvider, Observer<List<Channel>> observer) {
        Observable<HttpResult<ChannelData>> observable = service.loadChannels();

        buildObserver(observable.flatMap(new Function<HttpResult<ChannelData>, ObservableSource<List<Channel>>>() {
            @Override
            public ObservableSource<List<Channel>> apply(HttpResult<ChannelData> channelDataHttpResult) throws Exception {
                if(channelDataHttpResult.getCode() == 0 ){
                    if(channelDataHttpResult.getData() != null && channelDataHttpResult.getData().getNewsChannelList() != null){
                        return Observable.just(channelDataHttpResult.getData().getNewsChannelList());
                    }else{
                        return Observable.error(new ServerException(1001,"服务器内部异常"));
                    }

                }
                return Observable.error(new ServerException(channelDataHttpResult.getCode(),channelDataHttpResult.getMessage()));
            }
        }), observer,lifecycleProvider );
    }

    @Override
    public void loadNewsByChannel(LifecycleProvider lifecycleProvider, Map<String, String> params, Observer<NewsData> observer) {

    }

    @Override
    public Observable<NewsData> getNewsByChannel(Map<String, String> params) {
        Observable<HttpResult<NewsData>> observable = service.fetchNewNews(params);

      return observable.flatMap(new Function<HttpResult<NewsData>, ObservableSource<NewsData>>() {
            @Override
            public ObservableSource<NewsData> apply(HttpResult<NewsData> newsDataHttpResult) throws Exception {

                if(newsDataHttpResult.getCode() == 0){
                    NewsData newsData = newsDataHttpResult.getData();
                    if(newsData != null && newsData.getNewList()!= null && newsData.getNewList().size() >0){
                        return Observable.just(newsData);
                    }

                    return Observable.error(new ServerException(1001,"服务器内部异常"));
                }

                return Observable.error(new ServerException(newsDataHttpResult.getCode(),newsDataHttpResult.getMessage()));
            }
        });

    }

    @Override
    public void saveNews(String channelId, NewsData data) {

    }

    @Override
    public void fetchNewsDetail(Map<String, String> params,Observer<NewsDetails> observer) {
        final Observable<HttpResult<NewsDetails>> newsDetail = service.fetchNewsDetail(params);
        newsDetail.flatMap(new Function<HttpResult<NewsDetails>, ObservableSource<NewsDetails>>() {
            @Override
            public ObservableSource<NewsDetails> apply(HttpResult<NewsDetails> newsDetailsHttpResult) throws Exception {
                if (newsDetailsHttpResult.getCode()==0){
                    return Observable.just(newsDetailsHttpResult.getData());
                }
                return Observable.error(new ServerException(newsDetailsHttpResult.getCode(),newsDetailsHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getRelevant(Map<String, String> params, Observer<List<Data>> observer) {
        Observable<HttpResult<List<Data>>> relevant = service.getRelevant(params);
        relevant.flatMap(new Function<HttpResult<List<Data>>, ObservableSource<List<Data>>>() {
            @Override
            public ObservableSource<List<Data>> apply(HttpResult<List<Data>> listHttpResult) throws Exception {
                if (listHttpResult.getCode()==0){
                    return Observable.just(listHttpResult.getData());
                }
                return Observable.error(new ServerException(listHttpResult.getCode(),listHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getListComment(Map<String, String> params, Observer<List<Comment>> observer) {
        final Observable<HttpResult<CommentList>> listComment = service.getListComment(params);
        listComment.flatMap(new Function<HttpResult<CommentList>, ObservableSource<List<Comment>>>() {
            @Override
            public ObservableSource<List<Comment>> apply(HttpResult<CommentList> commentListHttpResult) throws Exception {
                if (commentListHttpResult.getCode()==0){
                    return Observable.just(commentListHttpResult.getData().getUserCommentList());
                }
                return Observable.error(new ServerException(commentListHttpResult.getCode(),commentListHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void updateTopic(Map<String, String> params, Observer<Object> observer) {
        Observable<HttpResult<Object>> httpResultObservable = service.updateTopic(params);
        httpResultObservable.flatMap(new Function<HttpResult<Object>, ObservableSource<Object>>() {
            @Override
            public ObservableSource<Object> apply(HttpResult<Object> objectHttpResult) throws Exception {
                if (objectHttpResult.getCode()==0){
                    return Observable.just(objectHttpResult.getData());
                }
                return Observable.error(new ServerException(objectHttpResult.getCode(),objectHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    private <T> void buildObserver(Observable<T> observable,Observer<T> observer,LifecycleProvider lifecycleProvider){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose((lifecycleProvider instanceof RxAppCompatActivity) ?(((RxAppCompatActivity)lifecycleProvider).<T>bindUntilEvent(ActivityEvent.DESTROY)) :  (((RxFragment)lifecycleProvider).<T>bindUntilEvent(FragmentEvent.DETACH)))
                .subscribe(observer);
    }
}