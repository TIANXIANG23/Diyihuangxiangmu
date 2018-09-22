package com.example.tianxiang.diyihuangxiangmu.data.remote.retrofit;

import com.example.tianxiang.diyihuangxiangmu.data.remote.ChannelData;
import com.example.tianxiang.diyihuangxiangmu.data.remote.HttpResult;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsData;
import com.example.tianxiang.diyihuangxiangmu.data.remote.User;
import com.example.tianxiang.diyihuangxiangmu.entity.Comment;
import com.example.tianxiang.diyihuangxiangmu.entity.CommentList;
import com.example.tianxiang.diyihuangxiangmu.entity.Data;
import com.example.tianxiang.diyihuangxiangmu.entity.NewListTwo;
import com.example.tianxiang.diyihuangxiangmu.entity.NewsDetails;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FirstgaService {
    //登录接口
    @POST("users/phoneLogin")
    Observable<HttpResult<User>> login(@Body Map<String, String> params);
  //短信验证
    @POST("users/sendVerificationCode")
    Observable<HttpResult<Object>> sendVerificationCode(@Body Map<String, String> params);
   //频道
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("news/listNewsChannel")
    Observable<HttpResult<ChannelData>> loadChannels();
    //取得新闻
    @POST("news/upListNews")
    Observable<HttpResult<NewsData>> fetchNewNews(@Body Map<String,String> map);

    @POST("news/downListNews")
    Observable<HttpResult<NewListTwo>> sendRequesttwo(@Body Map<String,String> map);

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("news/info")
    Observable<HttpResult<NewsDetails>> fetchNewsDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("news/relevant")
    Observable<HttpResult<List<Data>>> getRelevant(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("users/listComment")
    Observable<HttpResult<CommentList>> getListComment(@FieldMap Map<String,String> map);

    @Headers("Content-Type:application/json")
    @POST("users/comment")
    Observable<HttpResult<Object>> updateTopic(@Body Map<String,String> map);
}
