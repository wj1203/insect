package com.example.yangyang.insect.entity;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;

/**
 * Created by yangyang on 2019/3/7.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.entity
 * 作用：
 */
public class Video extends LitePalSupport {
    private String video_uri;
    private String loading_uri;
    private String introduce;
    private String name;
    private String head_uri;
    private ArrayList<MyComment> comments = new ArrayList<>();

    public ArrayList<MyComment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<MyComment> comments) {
        this.comments = comments;
    }

    public String getHead_uri() {
        return head_uri;
    }

    public void setHead_uri(String head_uri) {
        this.head_uri = head_uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLoading_uri() {
        return loading_uri;
    }

    public void setLoading_uri(String loading_uri) {
        this.loading_uri = loading_uri;
    }

    public String getVideo_uri() {
        return video_uri;
    }

    public void setVideo_uri(String video_uri) {
        this.video_uri = video_uri;
    }
}
