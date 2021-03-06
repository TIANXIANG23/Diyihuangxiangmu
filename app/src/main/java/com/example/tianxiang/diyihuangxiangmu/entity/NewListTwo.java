package com.example.tianxiang.diyihuangxiangmu.entity;

import java.util.List;

public class NewListTwo {
    /**
     * cursor : 15265440048e26b1c4af024ef4b64e49976788bd02
     * newList : [{"imageListThumb":["http://www.lyunx.com/data/attachment/portal/201805/21/100324trt2d75r0m2l6g61.jpg"],"layoutType":"2","newsId":"b9c1dfdc28de4459947dcc4e74637b2c","origin":"","pageviews":1084,"publishTime":"2018-05-20","title":"江苏一机场因公务机训练时冲出跑道暂时关闭 部分航班取消或备降"},{"imageListThumb":["http://www.lyunx.com/data/attachment/portal/201805/21/105902s4ofi4f6l4hmfloz.jpg"],"layoutType":"2","newsId":"3a785895c7e34447b5f9b995eb1d997d","origin":"","pageviews":346,"publishTime":"2018-05-20","title":"龙华直升机机场试运行 暂不考虑开设观光旅游线路"},{"imageListThumb":["http://www.ga.cn/uploads/allimg/180520/1_180520105723_1-lp.jpg"],"layoutType":"1","newsId":"9c0e04b5884d4238a5d6b1b1409dc5e6","origin":"","pageviews":202,"publishTime":"2018-05-20","title":"广东首家水上机场即将投入运营"},{"imageListThumb":["http://n.sinaimg.cn/sinacn03/202/w600h402/20180403/52d4-fysuuxz8583018.png"],"layoutType":"1","newsId":"6b3ccec8aa0e4f929c2bc86281d25aa0","origin":"","pageviews":111,"publishTime":"2018-05-18","title":"马来西亚国际航空发布全新品牌宣传片"},{"imageListThumb":["null"],"layoutType":"0","newsId":"59d7e50bb0c94ec98baf4fc1eefe8fff","origin":"","pageviews":52,"publishTime":"2018-05-18","title":"江西将组建首家135部短途运输航空公司-江西快线通勤航空即将成立"},{"imageListThumb":["http://www.lyunx.com/data/attachment/portal/201805/17/164654r9c8bgl8e1f9eyfg.jpg.thumb.jpg"],"layoutType":"2","newsId":"77df7c859fab437d98fbe187bba3e52b","origin":"","pageviews":76,"publishTime":"2018-05-17","title":"中国邮政EMS水陆两栖无人机试飞成功"},{"imageListThumb":["http://www.lyunx.com/data/attachment/portal/201805/17/161358lnse5dy5i1eedz13.jpg.thumb.jpg"],"layoutType":"1","newsId":"8e26b1c4af024ef4b64e49976788bd02","origin":"","pageviews":73,"publishTime":"2018-05-17","title":"河南西峡一钢厂发生事故致15伤 官方出动直升机救援"}]
     */

    private String cursor;
    private List<NewListBean> newList;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public List<NewListBean> getNewList() {
        return newList;
    }

    public void setNewList(List<NewListBean> newList) {
        this.newList = newList;
    }

    public static class NewListBean {
        public NewListBean(String layoutType, String newsId, String origin, int pageviews, String publishTime, String title, List<String> imageListThumb) {
            this.layoutType = layoutType;
            this.newsId = newsId;
            this.origin = origin;
            this.pageviews = pageviews;
            this.publishTime = publishTime;
            this.title = title;
            this.imageListThumb = imageListThumb;
        }

        /**
         * imageListThumb : ["http://www.lyunx.com/data/attachment/portal/201805/21/100324trt2d75r0m2l6g61.jpg"]
         * layoutType : 2
         * newsId : b9c1dfdc28de4459947dcc4e74637b2c
         * origin :
         * pageviews : 1084
         * publishTime : 2018-05-20
         * title : 江苏一机场因公务机训练时冲出跑道暂时关闭 部分航班取消或备降
         */



        private String layoutType;
        private String newsId;
        private String origin;
        private int pageviews;
        private String publishTime;
        private String title;
        private List<String> imageListThumb;

        public String getLayoutType() {
            return layoutType;
        }

        public void setLayoutType(String layoutType) {
            this.layoutType = layoutType;
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

        public int getPageviews() {
            return pageviews;
        }

        public void setPageviews(int pageviews) {
            this.pageviews = pageviews;
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

        public List<String> getImageListThumb() {
            return imageListThumb;
        }

        public void setImageListThumb(List<String> imageListThumb) {
            this.imageListThumb = imageListThumb;
        }
    }

}
