package com.example.yangyang.insect.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.yangyang.insect.R;
import com.example.yangyang.insect.activity.MainActivity;
import com.example.yangyang.insect.adapter.RecyclerViewAdapter;
import com.example.yangyang.insect.adapter.RecyclerViewVideoAdapter;
import com.example.yangyang.insect.custom.MyRecyclerView;
import com.example.yangyang.insect.db.MyDataBase;
import com.example.yangyang.insect.entity.MyComment;
import com.example.yangyang.insect.entity.Video;

import org.litepal.LitePal;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoFragment extends Fragment {
    private MyRecyclerView rv_video ;
    // 数据源
    private ArrayList<Video> videos = new ArrayList<>();
    // 加载video到数据库
    static {
        LitePal.deleteAll(Video.class);
        LitePal.deleteAll(MyComment.class);
        MyDataBase.formVideoToDB();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        initView(view);
        initData();
        // recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_video.setLayoutManager(layoutManager);
        RecyclerViewVideoAdapter recyclerViewVideoAdapter = new RecyclerViewVideoAdapter(getContext(),videos);
        rv_video.setAdapter(recyclerViewVideoAdapter);

        // attach Snaphelper 到recyclerView上 做到viewPager的效果
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv_video);
        // recyclerView的滑动监听
        rv_video.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                JCVideoPlayerStandard jcVideoPlayerStandard = recyclerView.getRootView().findViewById(R.id.jc_video);
                jcVideoPlayerStandard.startVideo();
            }
        });

        return view;
    }

    private void initData() {
        videos = (ArrayList<Video>) LitePal.findAll(Video.class);
    }

    private void initView(View view) {
            rv_video = view.findViewById(R.id.recycler_view);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            JCVideoPlayer.releaseAllVideos();
        }
    }
}
