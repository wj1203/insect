package com.example.yangyang.insect.entity;

import org.litepal.crud.LitePalSupport;

/**
 * Created by yangyang on 2019/3/5.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.entity
 * 作用：
 */
public class Insect extends LitePalSupport {
    private String name;
    private String head_uri;

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
}
