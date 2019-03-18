package com.example.yangyang.insect.adapter;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yangyang.insect.fragment.FindFragment;

import java.util.ArrayList;

/**
 * Created by yangyang on 2019/3/4.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.adapter
 * 作用：
 */
public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<ImageView> dataList;
    private Handler handler;

    //构造方法
    public ViewPagerAdapter(ArrayList list,Handler handler){
        dataList = list;
        handler = handler;
    }


    @Override
    public int getCount() {
        return   Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        position = position%5;
        ImageView imageView = dataList.get(position);
//         添加图片的手指监听
//        imageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()){
//                    case MotionEvent.ACTION_DOWN:  // 手指放下
//                        handler.removeCallbacksAndMessages(null); // 清空消息队列
//                        break;
//                    case MotionEvent.ACTION_UP:     // 手指离开
//                        handler.removeCallbacksAndMessages(null); // 清空消息队列
//                        handler.sendEmptyMessageDelayed(2,1000);
//                        break;
//
//                    case MotionEvent.ACTION_MOVE:   // 手指移动
//                        handler.removeCallbacksAndMessages(null); // 清空消息队列
//                        break;
//                    case MotionEvent.ACTION_CANCEL:  // 取消
//                        handler.removeCallbacksAndMessages(null); // 清空消息队列
//                        handler.sendEmptyMessageDelayed(3,1000);
//                        break;
//
//                }
//
//                return false; // 不消费点击事件
//            }
//        });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        position = position%5;
        container.removeView(dataList.get(position));

    }
}
