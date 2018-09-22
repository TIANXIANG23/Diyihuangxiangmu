package com.example.tianxiang.diyihuangxiangmu.entity;

import java.util.List;

public class CommentList {

    private List<Comment> userCommentList;

    public CommentList(List<Comment> userCommentList) {
        this.userCommentList = userCommentList;
    }

    public List<Comment> getUserCommentList() {
        return userCommentList;
    }

    public void setUserCommentList(List<Comment> userCommentList) {
        this.userCommentList = userCommentList;
    }

}
