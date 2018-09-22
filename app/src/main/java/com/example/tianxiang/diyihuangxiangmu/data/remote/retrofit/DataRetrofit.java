package com.example.tianxiang.diyihuangxiangmu.data.remote.retrofit;

import com.example.tianxiang.diyihuangxiangmu.AppConstact;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRetrofit {
    private static final long DEFAULT_TIMEOUT = 20000;

    public static FirstgaService getRetrofitService(){
        //登录拦截器
        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                /*           .addInterceptor(new Interceptor() {
                               @Override
                               public Response intercept(Chain chain) throws IOException {
                                   Request request = chain.request();
                                   Request.Builder requestbuilder = request.newBuilder();
                                   request = requestbuilder
                                           .addHeader("Content-Type", "application/json;charset=UTF-8")
                                           .post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),
                                                   bodyToString(request.body())))//关键部分，设置requestBody的编码格式为json
                                           .build();
                                   return chain.proceed(request);
                               }
                           })*/
                .addInterceptor(logging)
                //链接超时
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                //写入超时
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                //读取超时
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(AppConstact.BASE_URL)
                .build();
        return retrofit.create(FirstgaService.class);

    }
    private static String bodyToString(RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }

    }
}
