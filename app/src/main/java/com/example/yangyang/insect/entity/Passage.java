package com.example.yangyang.insect.entity;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

/**
 * Created by yangyang on 2019/3/4.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.entity
 * 作用：
 */
public class Passage extends LitePalSupport {
    private String head_uri;
    private String title;
    private String passage_uri;

    public String getPassage_uri() {
        return passage_uri;
    }

    public void setPassage_uri(String passage_uri) {
        this.passage_uri = passage_uri;
    }

    public String getHead_uri() {
        return head_uri;
    }

    public void setHead_uri(String uri) {
        this.head_uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
