package com.example.tianxiang.diyihuangxiangmu.data.remote;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;

public class NewsData {

    private String maxCursor;
    private String minCursor;

    private int tops;

    private List<News> newList;


    public String getMaxCursor() {
        return maxCursor;
    }

    public void setMaxCursor(String maxCursor) {
        this.maxCursor = maxCursor;
    }

    public String getMinCursor() {
        return minCursor;
    }

    public void setMinCursor(String minCursor) {
        this.minCursor = minCursor;
    }

    public int getTops() {
        return tops;
    }

    public void setTops(int tops) {
        this.tops = tops;
    }

    public List<News> getNewList() {
        return newList;
    }

    public void setNewList(List<News> newList) {
        this.newList = newList;
    }

    public static class NewsImageConvert implements PropertyConverter<List<String>,String> {


        @Override
        public List<String> convertToEntityProperty(String databaseValue) {
            return null;
        }

        @Override
        public String convertToDatabaseValue(List<String> entityProperty) {
            return null;
        }
    }


    public void mergeRefreshData(NewsData data){

        List<News> remoteList = data.getNewList();
        if(remoteList != null && remoteList.size() > 0){
            if(this.newList == null){
                this.newList = new ArrayList<>();
            }
            this.newList.addAll(0, remoteList);
        }
    }

    public void mergeLoadMoreData(NewsData data){
        List<News> remoteList = data.getNewList();
        if(remoteList != null && remoteList.size() > 0){
            if(newList == null){
                newList = new ArrayList<>();
            }
            this.newList.addAll(remoteList);
            this.maxCursor = data.maxCursor;
            this.minCursor = data.minCursor;
        }

    }


    public void replace(NewsData data){
        this.maxCursor = data.maxCursor;
        this.minCursor = data.minCursor;
        this.setNewList(data.newList);
    }


    public NewsData getNewsDataForSdcardCache(){

        NewsData newsData = new NewsData();

        newsData.maxCursor = this.maxCursor;
        newsData.minCursor = this.minCursor;

        List<News> newsList = new ArrayList<>();

        for(int i = 0; i < Math.min(7, this.newList.size()); i++){
            newsList.add(this.newList.get(i));
        }
        newsData.setNewList(newsList);

        return newsData;
    }

}
