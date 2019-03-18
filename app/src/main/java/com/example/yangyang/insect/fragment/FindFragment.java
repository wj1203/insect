package com.example.yangyang.insect.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yangyang.insect.R;
import com.example.yangyang.insect.activity.PassageActivity;
import com.example.yangyang.insect.adapter.RecyclerViewAdapter;
import com.example.yangyang.insect.adapter.ViewPagerAdapter;
import com.example.yangyang.insect.db.MyDataBase;
import com.example.yangyang.insect.entity.Passage;

import org.litepal.LitePal;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class FindFragment extends Fragment {
    private ViewPager vp_find ;
    private RecyclerView rv_find;
    private TextView tv_news;
    private LinearLayout ll_point;

    private int preSelected;
    private ArrayList<String> newsList;
    private ArrayList<Passage> passages = new ArrayList<>();

    ArrayList<ImageView> dataList = new ArrayList<>();

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            vp_find.setCurrentItem(vp_find.getCurrentItem()+1 );
            handler.sendEmptyMessageDelayed(0,2500);
            return true;
        }
    });

    public ViewPagerAdapter adapter = new ViewPagerAdapter(dataList,handler);

    static {
        LitePal.deleteAll(Passage.class);
        MyDataBase.formPassageToDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        initView(view);
        initData();
        vp_find.setAdapter(adapter);
        // vp的滑动监听
        vp_find.addOnPageChangeListener(new MyViewpagerListener());
        handler.sendEmptyMessageDelayed(1,2500);
        // recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_find.setLayoutManager(layoutManager);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),passages);
        rv_find.setAdapter(recyclerViewAdapter);

        return view;
    }

    private void initData() {
        // 图片地址
        ArrayList<String> imageUri = new ArrayList<>();
        imageUri.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551705020611&di=dcbbd29362dffc7bd5af7eb5a8578da5&imgtype=0&src=http%3A%2F%2Fimg.99.com.cn%2Fuploads%2F181024%2F106_154739_1.jpg");
        imageUri.add("https://img3.utuku.china.com/452x0/news/20190212/eac5e5be-c92e-4fc6-bfe4-fa6ce4cfdf4f.jpg");
        imageUri.add("https://cdn2.ettoday.net/images/703/d703305.jpg");
        imageUri.add("https://imgs.tom.com/whyz/201809/CONTENTDDCCB4DD18864394.jpg");
        imageUri.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3540979798,4263624523&fm=26&gp=0.jpg");
        // 文本
        newsList = new ArrayList<>();
        newsList.add("60只蚊子写作文 如何预防蚊子叮咬");
        newsList.add("全球昆虫数目减少 或将于一个世纪内完全绝种");
        newsList.add("昆虫的负重训练？");
        newsList.add("中国北京首届昆虫艺术科普展 9月30日盛大开幕");
        newsList.add("瑞士顽强臭虫“击败”陆军一连队 百余人被隔离");
        // 准备passage到数据源
        passages = (ArrayList<Passage>) LitePal.findAll(Passage.class);

        // 准备vp的数据
        for (int i = 0;i<imageUri.size();i++){
            // 添加imageview
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(imageUri.get(i)).into(imageView);
            dataList.add(imageView);
            // 添加点
            ImageView point = new ImageView(getContext());
            point.setBackgroundResource(R.drawable.point_select);
            // 通过params设置点的大小
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30,30);
            // 设置各个点的左间距
            params.leftMargin = 35;
            // params属性赋给point
            point.setLayoutParams(params);
            // 设置第一个高亮的点
            if(i==0){
                point.setEnabled(true);
            }else {
                point.setEnabled(false);
            }
            ll_point.addView(point);
            preSelected = 0;
            tv_news.setText(newsList.get(0));
        }
    }

    private void initView(View view) {
        vp_find = view.findViewById(R.id.vp_find);
        rv_find = view.findViewById(R.id.rv_find);
        tv_news = view.findViewById(R.id.tv_news);
        ll_point = view.findViewById(R.id.ll_point);
    }

    private class MyViewpagerListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            position = position%5;
            // 改变点的颜色
           ll_point.getChildAt(preSelected).setEnabled(false);
           ll_point.getChildAt(position).setEnabled(true);
           preSelected = position;
           // 改变文字
            tv_news.setText(newsList.get(position));
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
}
