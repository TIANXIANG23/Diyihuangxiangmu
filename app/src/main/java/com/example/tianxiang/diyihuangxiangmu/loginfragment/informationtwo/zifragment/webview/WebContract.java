package com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment.webview;

import com.example.tianxiang.diyihuangxiangmu.base.BasePresenter;
import com.example.tianxiang.diyihuangxiangmu.base.BaseView;
import com.example.tianxiang.diyihuangxiangmu.entity.Comment;
import com.example.tianxiang.diyihuangxiangmu.entity.CommentList;
import com.example.tianxiang.diyihuangxiangmu.entity.Data;
import com.example.tianxiang.diyihuangxiangmu.entity.NewsDetails;

import java.util.List;

public interface WebContract {
    public interface View extends BaseView<WebContract.Pesenter>{
        void webRequest(NewsDetails newsDetails);
        void setcorrelation(List<Data> data);
        void setCommentList(List<Comment> commentList);
        void setupdateTopic();
    }
    public interface Pesenter extends BasePresenter<WebContract.View>{
        void setRequest(String newid);
        void setcorrelation(String id);
        void setCommentList(String commentList);

        void setupdateTopic(String userId,String object,String objectType,String content);
    }
}
