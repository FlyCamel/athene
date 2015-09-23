package com.athene.data;

import java.util.ArrayList;

public class WeddingShowInfo {
    private int errno;
    private ArrayList<ShowInfo> shows_info;
    
    //    public void setErrno(int errno) {
    //        this.errno = errno;
    //    }
        
    public int getErrno() {
        return errno;
    }

    public ArrayList<ShowInfo> getShowInfos() {
        return shows_info;
    }
    
    public class ShowInfo {
        private int id;
        private String user_name;
        private String user_icon;
        private String title;
        private String desc;
        private int view_count;
        private int comment_count;
        private ArrayList<String> images;
        
    //    public void setId(int id) {
    //        this.id = id;
    //    }
        
        public int getId() {
            return id;
        }
        
    //    public void setUserName(String name) {
    //        this.user_name = name;
    //    }
        
        public String getUserName() {
            return user_name;
        }
        
    //    public void setUserIconUrl(String url) {
    //        this.user_icon = url;
    //    }
        
        public String getUserIconUrl() {
            return user_icon;
        }
        
    //    public void setTitle(String title) {
    //        this.title = title;
    //    }
        
        public String getTitle() {
            return title;
        }
        
    //    public void setDesc(String desc) {
    //        this.desc = desc;
    //    }
        
        public String getDesc() {
            return desc;
        }
        
    //    public void setViewCount(int count) {
    //        this.view_count = count;
    //    }
        
        public int getViewCount() {
            return view_count;
        }
        
    //    public void setCommentCount(int count) {
    //        this.comment_count = count;
    //    }
        
        public int getCommentCount() {
            return comment_count;
        }
        
    //    public void setImagesUrl(ArrayList<String> urls) {
    //        this.images = urls;
    //    }
        
        public ArrayList<String> getImagesUrl() {
            return images;
        }
    }
}
