package com.example.yangyang.insect.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yangyang on 2019/3/7.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.custom
 * 作用：
 */
public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /** onInterceptTouchEvent是在ViewGroup里面定义的。
        Android中的layout布局类一般都是继承此类的。
        onInterceptTouchEvent是用于拦截手势事件的，
        每个手势事件都会先调用onInterceptTouchEvent。
    */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        if (action == MotionEvent.ACTION_DOWN){
            return  false;
        }else {
            return false;
        }
    }
}
