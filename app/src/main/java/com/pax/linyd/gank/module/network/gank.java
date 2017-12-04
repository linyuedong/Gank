package com.pax.linyd.gank.module.network;

import java.util.List;

/**
 * Created by linyd on 2017/4/20.
 */
public class gank {


    /**
     * error : false
     * results : [{"_id":"58f7276b421aa9544b77402b","createdAt":"2017-04-19T17:01:31.103Z","desc":"仿微博带自定义身份标识和进度条的圆形头像","images":["http://img.gank.io/e1938fcb-306a-49c3-ab89-0c19ceaa0a44"],"publishedAt":"2017-04-20T14:03:06.490Z","source":"web","type":"Android","url":"https://github.com/385841539/IdentityImageView","used":true,"who":null},{"_id":"58f774f2421aa954511ebed4","createdAt":"2017-04-19T22:32:18.589Z","desc":"基于 Node.js 的声明式可监控爬虫网络","publishedAt":"2017-04-20T14:03:06.490Z","source":"chrome","type":"Android","url":"https://zhuanlan.zhihu.com/p/26463840","used":true,"who":"王下邀月熊"},{"_id":"58f80080421aa9544b77402f","createdAt":"2017-04-20T08:27:44.178Z","desc":"效果超赞的文件选择器","images":["http://img.gank.io/3b59a1b3-3ca6-4761-a413-e3c942b14968"],"publishedAt":"2017-04-20T14:03:06.490Z","source":"web","type":"Android","url":"https://github.com/codekidX/storage-chooser","used":true,"who":"Trumeet"},{"_id":"58f80a04421aa9544b774030","createdAt":"2017-04-20T09:08:20.177Z","desc":"A well-designed local image selector for Android ","images":["http://img.gank.io/d5212e26-53d9-45e6-9ff1-9174ec32d494"],"publishedAt":"2017-04-20T14:03:06.490Z","source":"web","type":"Android","url":"https://github.com/zhihu/Matisse","used":true,"who":"liuzheng"},{"_id":"58f81dea421aa9544ed88996","createdAt":"2017-04-20T10:33:14.23Z","desc":"Android OAuth2 client using OkHttp","publishedAt":"2017-04-20T14:03:06.490Z","source":"web","type":"Android","url":"https://github.com/corcoran/okhttp-oauth2-client","used":true,"who":"瘦纸好过夏"},{"_id":"58f83015421aa954511ebed6","createdAt":"2017-04-20T11:50:45.879Z","desc":"安卓指纹识别API封装！集成了安卓官方API！三星指纹API！魅族指纹API！一键集成！巨方便啊！","images":["http://img.gank.io/17f1d371-baf3-4830-93e2-43dfc20df2b6"],"publishedAt":"2017-04-20T14:03:06.490Z","source":"web","type":"Android","url":"https://github.com/uccmawei/FingerprintIdentify?t=1","used":true,"who":"Awei"},{"_id":"58f5d527421aa9544b77400f","createdAt":"2017-04-18T16:58:15.238Z","desc":"LingoRecord is a better recorder for Android, you can easily process pcm data from it.","publishedAt":"2017-04-19T11:44:51.925Z","source":"web","type":"Android","url":"https://github.com/lingochamp/LingoRecorder","used":true,"who":"Echo"},{"_id":"58f5e7d5421aa954511ebeba","createdAt":"2017-04-18T18:17:57.834Z","desc":"Understanding Context In Android Application","publishedAt":"2017-04-19T11:44:51.925Z","source":"web","type":"Android","url":"https://blog.mindorks.com/understanding-context-in-android-application-330913e32514","used":true,"who":"AMIT SHEKHAR"},{"_id":"58f5ecd7421aa954511ebebb","createdAt":"2017-04-18T18:39:19.747Z","desc":"仿iOS输入法点击输入框以外区域 自动隐藏软键盘","images":["http://img.gank.io/fa038d13-9cf7-4ed4-8a74-17b40c7c3a52"],"publishedAt":"2017-04-19T11:44:51.925Z","source":"web","type":"Android","url":"https://github.com/yingLanNull/HideKeyboard","used":true,"who":"yingLan"},{"_id":"58f60cc8421aa9544b774014","createdAt":"2017-04-18T20:55:36.879Z","desc":"可自定义动画的卡片切换视图","images":["http://img.gank.io/b9db68a2-aea5-412e-9897-8260ef2b592c"],"publishedAt":"2017-04-19T11:44:51.925Z","source":"web","type":"Android","url":"https://github.com/BakerJQ/Android-InfiniteCards","used":true,"who":"BakerJ"}]
     */

    private boolean error;
    public List<GankInfo> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankInfo> getResults() {
        return results;
    }

    public void setResults(List<GankInfo> results) {
        this.results = results;
    }

    public static class GankInfo {
        /**
         * _id : 58f7276b421aa9544b77402b
         * createdAt : 2017-04-19T17:01:31.103Z
         * desc : 仿微博带自定义身份标识和进度条的圆形头像
         * images : ["http://img.gank.io/e1938fcb-306a-49c3-ab89-0c19ceaa0a44"]
         * publishedAt : 2017-04-20T14:03:06.490Z
         * source : web
         * type : Android
         * url : https://github.com/385841539/IdentityImageView
         * used : true
         * who : null
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private Object who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public Object getWho() {
            return who;
        }

        public void setWho(Object who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
