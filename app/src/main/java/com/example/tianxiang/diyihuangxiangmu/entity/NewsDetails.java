package com.example.tianxiang.diyihuangxiangmu.entity;

public class NewsDetails {

      public int comments;
    public int isFavoured;
    public int isLiked;
    public String content;
    public String newsId;
    public String origin;
    public String publishTime;
    public String title;

    public NewsDetails(int comments, int isFavoured, int isLiked, String content, String newsId, String origin, String publishTime, String title) {
        this.comments = comments;
        this.isFavoured = isFavoured;
        this.isLiked = isLiked;
        this.content = content;
        this.newsId = newsId;
        this.origin = origin;
        this.publishTime = publishTime;
        this.title = title;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getIsFavoured() {
        return isFavoured;
    }

    public void setIsFavoured(int isFavoured) {
        this.isFavoured = isFavoured;
    }

    public int getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
