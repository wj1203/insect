package com.example.yangyang.insect.entity;

import org.litepal.crud.LitePalSupport;

/**
 * Created by yangyang on 2019/3/11.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.entity
 * 作用：
 */
public class MyComment extends LitePalSupport {
    private int num;
    private String comment_head_uri;
    private String comment_name;
    private String comment;
    private Video video;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getComment_head_uri() {
        return comment_head_uri;
    }

    public void setComment_head_uri(String comment_head_uri) {
        this.comment_head_uri = comment_head_uri;
    }

    public String getComment_name() {
        return comment_name;
    }

    public void setComment_name(String comment_name) {
        this.comment_name = comment_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
