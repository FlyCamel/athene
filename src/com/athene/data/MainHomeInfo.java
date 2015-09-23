package com.athene.data;

import java.util.ArrayList;

import android.R.string;

public class MainHomeInfo {

    private int errno;
    private ArrayList<Banner> banners;
    private ArrayList<RecommendTheme> recomm_themes;
    private ArrayList<RecommendSet> recomm_sets;
    private ArrayList<RecommendShow> recomm_shows;
    
    public int getErrno() {
        return errno;
    }
    
//    public void setErrno(int errno) {
//        this.errno = errno;
//    }
    
    public ArrayList<Banner> getBanners() {
        return banners;
    }
    
//    public void setBanners(ArrayList<Banner> banners) {
//        this.banners = banners;
//    }
    
    public ArrayList<RecommendTheme> getRecommendThemes() {
        return recomm_themes;
    }
    
//    public void setRecommendThemes(ArrayList<RecommendTheme> recomm_themes) {
//        this.recomm_themes = recomm_themes;
//    }
    
    public ArrayList<RecommendSet> getRecommendSets() {
        return recomm_sets;
    }
    
//    public void setRecommendSet(ArrayList<RecommendSet> recomm_sets) {
//        this.recomm_sets = recomm_sets;
//    }
    
    public ArrayList<RecommendShow> getRecommendShows() {
        return recomm_shows;
    }
    
//    public void setRecommendShows(ArrayList<RecommendShow> recomm_shows) {
//        this.recomm_shows = recomm_shows;
//    }
    
    public class Banner {
        private int id;
        private ArrayList<String> images;
        
        public int getId() {
            return id;
        }
        
//        public void setId(int id) {
//            this.id = id;
//        }
        
        public ArrayList<String> getImages() {
            return images;
        }
        
//        public void setImages(ArrayList<String> images) {
//            this.images = images;
//        }
    }
    
    public class RecommendTheme {
        private int id;
        private String theme_name;
        private ArrayList<String> images;
        
        public int getId() {
            return id;
        }
        
//        public void setId(int id) {
//            this.id = id;
//        }
        
        public String getThemeName() {
            return theme_name;
        }
        
//        public void setThemeName(String name) {
//            this.theme_name = name;
//        }
        
        public ArrayList<String> getImages() {
            return images;
        }
        
//        public void setImages(ArrayList<String> images) {
//            this.images = images;
//        }
    }
    
    public class RecommendSet {
        private int id;
//        public String title;
//        public String name;
        private int price;
        private ArrayList<String> images;
        
        public int getId() {
            return id;
        }
        
//        public void setId(int id) {
//            this.id = id;
//        }
        
        public int getPrice() {
            return price;
        }
        
//        public void setPrice(int price) {
//            this.price = price;
//        }
        
        public ArrayList<String> getImages() {
            return images;
        }
        
//        public void setImages(ArrayList<String> images) {
//            this.images = images;
//        }
    }
    
    public class RecommendShow {
        private int id;
        private String title;
        private int comments_count;
        private int pros_count;
        private ArrayList<String> images;
//        public String name;
//        public int againstsCount;
//        public int price;
        
        public int getId() {
            return id;
        }
        
//        public void setId(int id) {
//            this.id = id;
//        }
        
        public String getTitle() {
            return title;
        }
        
//        public void setTitle(String title) {
//            this.title = title;
//        }
        
        public int getCommentsCount() {
            return comments_count;
        }
        
//        public void setCommentsCount(int count) {
//            this.comments_count = count;
//        }
        
        public int getProsCount() {
            return pros_count;
        }
        
//        public void setProsCount(int count) {
//            this.pros_count = count;
//        }
        
        public ArrayList<String> getImages() {
            return images;
        }
        
//        public void setImages(ArrayList<String> images) {
//            this.images = images;
//        }
    }
}
