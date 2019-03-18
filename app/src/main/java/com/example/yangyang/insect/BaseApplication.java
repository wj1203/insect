package com.example.yangyang.insect;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.yangyang.insect.activity.MainActivity;

import org.litepal.LitePal;

/**
 * Created by yangyang on 2018/12/1.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect
 * 作用：
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化数据库
        LitePal.initialize(this);

    }
}
