package com.example.yangyang.insect.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yangyang on 2019/3/4.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.util
 * 作用：
 */
public class ToastUtil {
    public static void toast(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }
}
